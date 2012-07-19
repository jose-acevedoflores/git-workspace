package com.thenewboston.jose;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity
{
	private MediaPlayer song;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);

		song = MediaPlayer.create(Splash.this, R.raw.time_bomb);

		SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean music = getPrefs.getBoolean("checkbox", true);
		boolean splashOn = getPrefs.getBoolean("noSplash", true);

		if(!splashOn)
		{
			Intent openStartingPoint = new Intent("com.thenewboston.jose.MENU");
			startActivity(openStartingPoint);
		}
		else
		{
			setContentView(R.layout.splash);
			if(music==true)
				song.start();

			Thread timer = new Thread()
			{
				public void run()
				{
					try
					{
						sleep(4500);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					finally
					{
						Intent openStartingPoint = new Intent("com.thenewboston.jose.MENU");
						startActivity(openStartingPoint);
					}
				}
			};

			timer.start();


		}


	}

	@Override
	protected void onPause() 
	{
		super.onPause();
		song.release();
		finish();
	}



}
