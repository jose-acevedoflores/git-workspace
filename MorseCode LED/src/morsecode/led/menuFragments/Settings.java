package morsecode.led.menuFragments;


import morsecode.led.R;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;

public class Settings extends PreferenceFragment {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.settings);
		Log.d("DEBUG", "On create fragment -_-");
		
	}



}
