package com.maggen.wimouse.menu;


import com.maggen.wimouse.R;
import com.maggen.wimouse.WiMouseICSActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

public class Settings extends Activity {


	protected void onCreate(Bundle sis)
	{
		super.onCreate(sis);
		Log.d("HERE", "Oncreate");
		getFragmentManager().beginTransaction().replace(android.R.id.content,
				new PrefsFragment()).commit();
	}

	//	public void onBuildHeaders(List<Header> target)
	//	{
	//		this.loadHeadersFromResource(R.xml.preference_headers, target);
	//		Log.d("HERE", "Headers");
	//
	//	}


	public void onBackPressed() {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		String port = pref.getString("port", "9876");

		WiMouseICSActivity.info.setText(WiMouseICSActivity.info.getText().subSequence(0, 37) +" Port: "+port);
		super.onBackPressed();
	}
	
	public static class PrefsFragment extends PreferenceFragment {


		public void onCreate(Bundle sis)
		{
			super.onCreate(sis);
			this.addPreferencesFromResource(R.xml.settings);
		}
	}
}
