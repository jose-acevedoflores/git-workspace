package morsecode.led.LEDControl;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.util.Log;
import android.widget.EditText;

/**
 * This class encapsulated the functionality of flashing the camera LED
 * to transmit a morse code message.
 * @author jose
 *
 */
public class Morse {

	private final EditText et;
	private Camera cam;
	private Parameters params;
	private String s;
	private int dotDuration;

	/**
	 * The constructors needs the reference to the EDitText field that will contain
	 * the word to be transmitted in morse code. 
	 * @param et the reference to the EditText that contains the word to be translated
	 * to morse code.
	 */
	public Morse(EditText et)
	{
		this.et = et;
	}

	/**
	 * This method takes a reference to the camera and the duration of the dot
	 * and using the reference provided in the constructor (EditText) flashes the 
	 * camera LED according to the morse code pattern that represents that word.
	 * @param cam the camera reference
	 * @param params the parameters of the camera.
	 * @param dotDuration the duration of the dot (e.g. 200ms)
	 * @return returns the string in morse form.
	 */
	public String flashLed(Camera cam, Parameters params, int dotDuration)
	{
		this.cam = cam;
		this.params = params;
		this.dotDuration = dotDuration;
		s = et.getText().toString();
		if(!s.equals(""))
		{
			s = s.toLowerCase();
			s = this.stringToMorse(s);
			new Thread(new MyRunnable()).start();	
		}
		
		return s;
	}
	
	/**
	 * This method takes a string and computes the morse code equivalent.
	 * @param str the string to get the equivalent.
	 * @return the morse code representation of the parameter.
	 */
	private String stringToMorse(String str)
	{
		//						a		b		c		  d		   e	f
		String[] equivalent = {".- ",  "-... ", "-.-. ", "-.. ", ". ", "..-. ",
				
		"--. ", ".... ", ".. ",".--- ", "-.- ", ".-.. ", "-- ", "-. ", "--- ",".--. ",	
		// g	  h		  i		j		   k	  l		  m		  n		  o		 p
		
		// q	   r 	  s		   t 	 u		 v		  w		  x		 y		 z
		"--.- ", ".-. ", "... ", "- ", "..- ", "...- ", ".-- ", "-..- ","-.-- ","--.. " };
		String result = "";
		int index;
		for(int i = 0; i < str.length(); i++)
		{
			index = str.charAt(i) % 97;
			result += equivalent[index];		
		}
		
		return result.substring(0,result.length()-1);
	}


	//Morse code translator. 
	// Dot (.) equals:  200 ms (Light ON)
	// Dash equals:		600 ms (Light ON)
	//Space between letters:	  600 ms (No light)
	//Space between dash and dot: 200 ms (No light)

	private class MyRunnable implements Runnable{

		public void run() {

			String[] str = s.split(" ");

			Log.d("DEBUG", "str length = "+str.length);

			for(int i = 0; i < str.length; i++)
			{
				for(int c = 0; c < str[i].length(); c++)
				{
					if(str[i].charAt(c) == '-')
						this.toggle(dotDuration*3, dotDuration);
				
					else if(str[i].charAt(c) == '.')
						this.toggle(dotDuration, dotDuration);
					
				}
				
				// Sleep between letters.
				try {
					Thread.sleep(dotDuration*3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					Log.d("DEBUG", "Thread interupted");
				}


			}// for loop i

		}
		
		/**
		 * This method toggles the LED according to the parameters that it receives
		 * The LED are flashed by sleeping the thread.
		 * @param timeOn the time the LED should be on
		 * @param timeOff the time the LED should be off.
		 */
		private void toggle(int timeOn, int timeOff)
		{
			params.setFlashMode(Parameters.FLASH_MODE_TORCH);					 
			cam.setParameters(params);
			cam.startPreview();

			try {
				Thread.sleep(timeOn);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Log.d("DEBUG", "Thread interupted");
			}


			params.setFlashMode(Parameters.FLASH_MODE_OFF);
			cam.setParameters(params);
			cam.stopPreview();

			try {
				Thread.sleep(timeOff);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Log.d("DEBUG", "Thread interupted");
			}

		}

	}


}
