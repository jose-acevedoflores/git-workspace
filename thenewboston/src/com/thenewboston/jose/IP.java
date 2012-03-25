package com.thenewboston.jose;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class IP extends Activity implements OnClickListener{

	private Button bShowIP;
	private Button bShowSSID;
	private TextView tvIP;
	private Button bResetIP;
	private WifiManager wifiManager;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.ip);
		init();
	}



	private void init()
	{
		this.bShowIP = (Button) findViewById(R.id.bShowIP);
		this.bResetIP = (Button) findViewById(R.id.bResetIP);
		this.bShowSSID = (Button) findViewById(R.id.bShowSSSID_IP);

		this.tvIP = (TextView) findViewById(R.id.tvDisplayIP);

		this.bShowIP.setOnClickListener(this);
		this.bResetIP.setOnClickListener(this);
		this.bShowSSID.setOnClickListener(this);
		
		//wfm = this.getBaseContext().getSystemService(Context.WIFI_SERVICE);
		
		Object manager = this.getSystemService(Context.WIFI_SERVICE);
		//Just to make sure :)
		if(manager instanceof WifiManager)
			wifiManager = (WifiManager) manager;
	}

	public void onPause()
	{
		super.onPause();
		finish();
	}



	public void onClick(View v) {

		switch (v.getId())
		{
		case R.id.bShowIP:
			int ip = wifiManager.getConnectionInfo().getIpAddress();
			tvIP.setText("IP: "+ip);
			break;
			
		case R.id.bShowSSSID_IP:
			tvIP.setText("IP: "+wifiManager.getConnectionInfo().getSSID());
			break;
			
		case R.id.bResetIP:
			tvIP.setText("INFO: ");
			break;
		}

	}

}
