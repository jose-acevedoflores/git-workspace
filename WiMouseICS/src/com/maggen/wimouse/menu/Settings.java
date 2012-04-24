package com.maggen.wimouse.menu;

import java.util.List;

import com.maggen.wimouse.R;
import com.maggen.wimouse.WiMouseICSActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class Settings extends PreferenceActivity {

	
	public void onCreate(Bundle sis)
	{
		super.onCreate(sis);	
	}

	public void onBuildHeaders(List<Header> target)
	{
		this.loadHeadersFromResource(R.xml.preference_headers, target);
		this.switchToHeader("com.maggen.wimouse.menu.Settings$FragmentIMP",null);
	}
	
//	public Intent onBuildStartFragmentIntent(String fragmentName, Bundle args, int titleRes, int shortTitleRes)
//	{
//		
//		return null;
//	}
	

	
	public void onBackPressed() {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		String port = pref.getString("port", "9876");
		
		WiMouseICSActivity.info.setText(WiMouseICSActivity.info.getText().subSequence(0, 37) +" Port: "+port);
		super.onBackPressed();
	}
	
	public static class FragmentIMP extends PreferenceFragment {
		
		public void onCreate(Bundle sis)
		{
			super.onCreate(sis);
			this.addPreferencesFromResource(R.xml.settings);
		}
	}


}
