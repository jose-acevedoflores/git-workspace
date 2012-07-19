package com.thenewboston.jose;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class GFX extends Activity{

	private MyBringBack ourView;
	private WakeLock wL;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		//wake-lock
		PowerManager pM  = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "whatever");
		
		super.onCreate(savedInstanceState);
		wL.acquire();
		ourView = new MyBringBack(this);
		setContentView(ourView);
	}
	
	
	protected void onPause()
	{
		super.onPause();
		wL.release();
	}
	
	protected void onResume()
	{
		super.onResume();
		wL.acquire();
	}
	
}
