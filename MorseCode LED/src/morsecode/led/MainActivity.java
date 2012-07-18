package morsecode.led;

import morsecode.led.LEDControl.Morse;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	/*- These objects are used to set the tabs on the app */
	private TabHost tabHost;
	private TabSpec specs;

	//Buttons
	private Button bMorse;

	//EditTexts 
	private EditText etTab1;

	//Morse 
	private Morse morse;
	private Camera camera;
	private Parameters params;

	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
        
        this.init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	
    	if(item.getItemId() == R.id.menu_settings)
    	{			
			Intent i = new Intent("morsecode.led.MENU");
			startActivity(i);
    	}
    	return true;
    }
    
    
    
    public void onBackPressed()
	{
    	Log.d("DEBUG", "About to finish");
		this.finish();
	}
    

	protected void onStop() {
		super.onStop();


		if (this.camera != null) {
			this.camera.release();
		}

		this.finish();
	}
    
    public void onClick(View v) {
		switch(v.getId())
		{

		case R.id.bMorse:
			int dotDuration=200;
			try{

				SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

				String temp = prefs.getString("dot_duration_preference", "200");
				Log.d("DEBUG", " i =  "+temp);
				dotDuration = Integer.parseInt(temp);

			}
			catch (NumberFormatException e)
			{
				Toast t = Toast.makeText(this, "Number format is incorrect ", Toast.LENGTH_LONG);
				t.show();
				break;
			}

			String inMorse = this.morse.flashLed(this.camera, this.params, dotDuration );
			Toast t = Toast.makeText(this, "Morse Translation = "+inMorse, Toast.LENGTH_LONG);
			t.show();
			break;

		}//End of switch

	}

    private void init()
	{
		//TabHost initialization 
		this.tabHost = (TabHost) findViewById(R.id.tabhost);
		this.tabHost.setup();

		// Tab1 setup
		this.specs = this.tabHost.newTabSpec("tab1");
		this.specs.setContent(R.id.tab1);
		this.specs.setIndicator("Text to Morse");
		this.tabHost.addTab(specs);

		// Tab2 setup
		this.specs = this.tabHost.newTabSpec("tab2");
		this.specs.setContent(R.id.tab2);
		this.specs.setIndicator("Morse Code");
		this.tabHost.addTab(specs);

		// Tab3 setup
		this.specs = this.tabHost.newTabSpec("tab3");
		this.specs.setContent(R.id.tab3);
		this.specs.setIndicator("Settings");
		this.tabHost.addTab(specs);



		//Button setup
		this.bMorse = (Button) findViewById(R.id.bMorse);
		this.bMorse.setOnClickListener(this);
		

		//Edit Text setup
		this.etTab1 = (EditText) findViewById(R.id.etTab1);
		this.morse = new Morse(etTab1);

		this.camera = Camera.open();
		this.params = this.camera.getParameters();

	}
    
}
