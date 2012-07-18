package morsecode.led;

import morsecode.led.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Menu extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menus);

	}

	public void onBackPressed()
	{
		Log.d("DEBUG", "About to finish MENU");
		@SuppressWarnings("rawtypes")
		Class mainAct;
		try {
			mainAct = Class.forName("morsecode.led.MainActivity");
			Intent i = new Intent(Menu.this, mainAct);
			startActivity(i);
		} catch (ClassNotFoundException e) {
			Log.d("DEBUG", "Explosion class not found");
			e.printStackTrace();
		}
		this.finish();
	}

}
