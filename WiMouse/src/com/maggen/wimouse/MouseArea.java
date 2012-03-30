package com.maggen.wimouse;

import android.app.Activity;
import android.os.Bundle;

public class MouseArea extends Activity{
	
	public void onCreate(Bundle savedInstanceSate)
	{
		super.onCreate(savedInstanceSate);
		this.setContentView(R.layout.mousearea);
	}
	
	public void onPause()
	{
		super.onPause();
		this.finish();
	}
	
}
