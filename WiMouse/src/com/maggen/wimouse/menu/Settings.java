package com.maggen.wimouse.menu;

import com.maggen.wimouse.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Settings extends PreferenceActivity {
	
	public void onCreate(Bundle sis)
	{
		super.onCreate(sis);
		this.addPreferencesFromResource(R.xml.settings);
	}

}
