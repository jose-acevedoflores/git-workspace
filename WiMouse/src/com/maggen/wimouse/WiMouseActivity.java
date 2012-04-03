package com.maggen.wimouse;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class WiMouseActivity extends Activity {

	private Button startButton;
	private EditText[] ipFields;
	
	private ListView previousIPs;
	private String ipHistory;
	
	private AlertDialog.Builder dia;
	private AssetManager history;

	public static String ip ="";
	public static TextView info;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Set what is going to be displayed to the user.
		setContentView(R.layout.main);

		this.checkAssets();

		//Initialize the variables.
		this.initializeVars();
	}

	private void checkAssets()
	{
		InputStream in=null;
		//Initialize a string writer to save the data from the input stream
		StringWriter sw= new StringWriter(100);
		//Get the assets of this application 
		history = this.getAssets();
		try{
			//Check the file history.txt (IOexception)
			in = this.history.open("history.txt");
			int n;
			
			//While in.read() is not -1 (end of input stream)
			while((n = in.read()) != -1)
				//Write to the string writer the character found.
				sw.write(n);
			
			//Close the stream.
			in.close();
			this.ipHistory = sw.toString();
			Log.d("History", "Content: "+this.ipHistory);
		}
		catch (IOException e) {
			Log.d("CheckAsset", "File not found");
		}

	}

	private void initializeVars()
	{
		//Initialize the button
		this.startButton = (Button) findViewById(R.id.bStart);
		this.startButton.setOnClickListener(new ButtonListener());

		//Initialize the list view
		this.previousIPs = (ListView) findViewById(R.id.lvPreviousIP);

		//Initialize the four ip fields.
		this.ipFields = new EditText[4];
		this.ipFields[0] = (EditText) findViewById(R.id.etIP1);
		this.ipFields[1] = (EditText) findViewById(R.id.etIP2);
		this.ipFields[2] = (EditText) findViewById(R.id.etIP3);
		this.ipFields[3] = (EditText) findViewById(R.id.etIP4);

		// initialize the alert dialog just in case.
		dia = new AlertDialog.Builder(this);

		//Get the preference to access the port 
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
		String port = pref.getString("port", "9876");

		//Initialize the field to show the port
		info = (TextView) findViewById(R.id.tvInfo);
		info.setText(info.getText() +" Port: "+port);
	}

	public void onPause()
	{
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = this.getMenuInflater();
		inflater.inflate(R.menu.menu, menu);

		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) {
		case R.id.about:
			Intent i = new Intent("com.maggen.wimouse.menu.ABOUT");
			startActivity(i);
			break;

		case R.id.settings:
			Intent settings = new Intent("com.maggen.wimouse.menu.SETTINGS");
			startActivity(settings);
			break;

		case R.id.exit:
			this.finish();
			break;

		default:
			break;
		}
		return true;
	}

	/*-------------------------Private class -------------------------------------*/


	private class ButtonListener implements OnClickListener{

		@Override
		public void onClick(View v) {

			ip ="";
			for(int i = 0 ; i < ipFields.length; i++)
				ip += ipFields[i].getText().toString().trim()+".";

			ip = ip.substring(0,ip.length()-1);

			if(this.checkValidIP())
			{
				Intent i = new Intent("android.intent.action.MOUSEAREA");
				startActivity(i);
			}
			else
			{
				dia.setMessage("Invalid IP "+ ip);
				AlertDialog alert = dia.create();
				alert.setTitle("Alert");
				alert.show();
			}
		}

		private boolean checkValidIP()
		{
			String ipA[] = ip.split("[.]");
			//This if statement takes care if the user forgets to fill one field.
			if(ipA.length != 4)
				return false;

			int n;
			for(int i = 0 ; i < 4; i++)
			{
				try{
					n = Integer.parseInt(ipA[i]);

					if(n > 255)
						return false;
				}
				catch (NumberFormatException e) {
					return false;
				}
			}

			return true;
		}

	}
	/*-------------------------Private class -------------------------------------*/

}