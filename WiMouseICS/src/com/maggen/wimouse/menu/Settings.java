package com.maggen.wimouse.menu;


import com.maggen.wimouse.R;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class Settings extends PreferenceFragment{
	
	public void onCreate(Bundle sis)
	{
		super.onCreate(sis);
		this.addPreferencesFromResource(R.xml.settings);
	}
}

//public class Settings extends Activity {
//
//
//	protected void onCreate(Bundle sis)
//	{
//		super.onCreate(sis);
//		Log.d("HERE", "Oncreate");
//		getFragmentManager().beginTransaction().replace(android.R.id.content,
//				new PrefsFragment()).commit();
//	}
//
//	public void onBackPressed() {
//		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
//		String port = pref.getString("port", "9876");
//
//		WiMouseICSActivity.info.setText(WiMouseICSActivity.info.getText().subSequence(0, 37) +" Port: "+port);
//		super.onBackPressed();
//	}
//	
//	public static class PrefsFragment extends PreferenceFragment {
//
//
//		public void onCreate(Bundle sis)
//		{
//			super.onCreate(sis);
//			this.addPreferencesFromResource(R.xml.settings);
//		}
//	}
//}
