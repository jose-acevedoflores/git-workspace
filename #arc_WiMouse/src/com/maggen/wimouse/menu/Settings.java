package com.maggen.wimouse.menu;

import com.maggen.wimouse.R;
import com.maggen.wimouse.WiMouseActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Settings extends PreferenceActivity {

	
	public void onCreate(Bundle sis)
	{
		super.onCreate(sis);
		this.addPreferencesFromResource(R.xml.settings);
	}

	@Override
	public void onBackPressed() {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
		String port = pref.getString("port", "9876");
		
		WiMouseActivity.info.setText(WiMouseActivity.info.getText().subSequence(0, 37) +" Port: "+port);
		super.onBackPressed();
	}


}
