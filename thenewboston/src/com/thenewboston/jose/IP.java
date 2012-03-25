package com.thenewboston.jose;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class IP extends Activity{

	private Button bShowIP;
	private TextView tvIP;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		init();
	}
	
	private void init()
	{
		this.bShowIP = (Button) findViewById(R.id.bShowIP);
		this.tvIP = (TextView) findViewById(R.id.tvDisplayIP); 	
	}
	
	public void onPause()
	{
		super.onPause();
		finish();
	}
	
}
