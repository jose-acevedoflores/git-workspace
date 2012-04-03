package com.maggen.wimouse;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class WiMouseActivity extends Activity {

	private Button startButton;
	private EditText[] ipFields;

	private ListView previousIPs;
	private static final int historyMax = 5;

	private AlertDialog.Builder dia;
	private SharedPreferences pref;


	public static String ip ="";
	public static TextView info;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Set what is going to be displayed to the user.
		setContentView(R.layout.main);
		
		pref = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
		//Initialize the list view
		this.previousIPs = (ListView) findViewById(R.id.lvPreviousIP);

		this.checkIPHistory();

		//Initialize the variables.
		this.initializeVars();
	}

	private void checkIPHistory()
	{
		String sArr = "";
		String temp="";
		for(int i = 0 ; i < this.pref.getInt("ipHistory", 0); i++)
		{
			temp = this.pref.getString("ip"+i, "-1");
			//if the ip# is not equal to -1 then it should be a valid ip.
			if(!temp.equals("-1"))
				sArr += temp+":";
			Log.d("temp", temp);
		}

		if(!sArr.equals(""))
		{
			//Get rid of the : at the end
			sArr = sArr.substring(0, sArr.length()-1);
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , sArr.split(":"));

			this.previousIPs.setAdapter(adapter);

		}

	}

	private void initializeVars()
	{
		//Initialize the button
		this.startButton = (Button) findViewById(R.id.bStart);
		this.startButton.setOnClickListener(new ButtonListener());

		//Initialize the four ip fields.
		this.ipFields = new EditText[4];
		this.ipFields[0] = (EditText) findViewById(R.id.etIP1);
		this.ipFields[1] = (EditText) findViewById(R.id.etIP2);
		this.ipFields[2] = (EditText) findViewById(R.id.etIP3);
		this.ipFields[3] = (EditText) findViewById(R.id.etIP4);

		// initialize the alert dialog just in case.
		dia = new AlertDialog.Builder(this);

		//Get the preference to access the port 
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
	
	private void updateHistory(String ip)
	{
		int ipHistory = this.pref.getInt("ipHistory", 0);
		SharedPreferences.Editor editor = pref.edit();
		if(ipHistory == 0)
		{
			editor.putInt("ipHistory", 1);
			editor.putString("ip0", ip);
			editor.apply();
			return;
		}
		else
		{
			Log.d("Update", ""+ipHistory);
			if(ipHistory < historyMax )
			{
				editor.putString("ip"+ipHistory, ip);
				editor.putInt("ipHistory", ++ipHistory);
				editor.apply();
				return;
			}
			else
			{
				for(int i = 0 ; i < historyMax-1; i++)
				{
					editor.putString("ip"+(i+1), this.pref.getString("ip"+i, "-1"));
				}
				editor.putString("ip0", ip);
				editor.apply();
				return;	
			}
		}
		
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
				updateHistory(ip);
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