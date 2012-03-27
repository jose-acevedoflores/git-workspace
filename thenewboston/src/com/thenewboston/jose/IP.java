package com.thenewboston.jose;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Context;
//import android.net.ConnectivityManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class IP extends Activity implements OnClickListener{

	private Button bShowIP;
	private Button bShowSSID;
	private Button bExtra;
	private TextView tvIP;
	private Button bResetIP;
	private WifiManager wifiManager;
	private InetAddress addr;
	private WifiConfiguration apconf;
	private TabHost host;
	private TabSpec spec;
	private EditText et;


	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.ip);
		init();
	}



	private void init()
	{
		this.host = (TabHost) findViewById(R.id.tabhostIP);
		this.et = (EditText) findViewById(R.id.etIP);
		
		this.bShowIP = (Button) findViewById(R.id.bShowIP);
		this.bResetIP = (Button) findViewById(R.id.bResetIP);
		this.bShowSSID = (Button) findViewById(R.id.bShowSSSID_IP);
		this.bExtra = (Button) findViewById(R.id.bIP_Extra);

		this.tvIP = (TextView) findViewById(R.id.tvDisplayIP);

		this.bShowIP.setOnClickListener(this);
		this.bResetIP.setOnClickListener(this);
		this.bShowSSID.setOnClickListener(this);
		this.bExtra.setOnClickListener(this);
		
		this.host.setup();
		
		this.spec = this.host.newTabSpec("tab1IP");
		this.spec.setContent(R.id.tab1IP);
		this.spec.setIndicator("Show IP");
		this.host.addTab(spec);
		
		this.spec = this.host.newTabSpec("tab2IP");
		this.spec.setContent(R.id.tab2IP);
		this.spec.setIndicator("UDP");
		this.host.addTab(spec);
		

		//wfm = this.getBaseContext().getSystemService(Context.WIFI_SERVICE);

		Object manager = this.getSystemService(Context.WIFI_SERVICE);

		try {
			addr = InetAddress.getLocalHost();
			
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		
		if(manager instanceof WifiManager)
		{
			wifiManager = (WifiManager) manager;

			//This code uses reflection (Need to check what that is!!)
			//To get to know whether the access point is on or not (tethering)
			Method[] methods = wifiManager.getClass().getDeclaredMethods();
			for(Method m : methods)
			{
			//	Log.d("IP", "Method "+m.getName());
				
				if(m.getName().equals("isWifiApEnabled")) {
					
					try {
						Object obj = m.invoke(wifiManager);
						//if obj is true then the access point is enabled
						this.tvIP.setText("Result : " +obj);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
				else if(m.getName().equals("getWifiApConfiguration"))
				{
					try {
						Object obj = m.invoke(wifiManager);
						apconf = (WifiConfiguration) obj;
						Log.d("IP", "SSID: "+ apconf.SSID);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

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
			String ipS = String.format("%d.%d.%d.%d",
					(ip & 0xff),
					(ip >> 8 & 0xff),
					(ip >> 16 & 0xff),
					(ip >> 24 & 0xff));

			tvIP.setText("IP: "+ipS);
			break;

		case R.id.bShowSSSID_IP:
			tvIP.setText("IP: "+wifiManager.getConnectionInfo().getSSID());
			break;

		case R.id.bIP_Extra:
			byte s[] = (addr.getAddress());
			tvIP.setText("Tes: " +s[0]);
			break;
		case R.id.bResetIP:
			tvIP.setText("INFO: ");
			break;
		}

	}

}
