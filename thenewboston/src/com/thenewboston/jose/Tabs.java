package com.thenewboston.jose;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener{

	private TabHost tabHost;
	private TabSpec specs;
	private TextView showResult;
	private long start =0, stop = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		tabHost = (TabHost) findViewById(R.id.tabhost);

		Button newTab = (Button) findViewById(R.id.bAddTab);
		Button bStart = (Button) findViewById(R.id.bStartW);
		Button bStop = (Button) findViewById(R.id.bStopW);
		showResult = (TextView) findViewById(R.id.tvShowR);
		
		newTab.setOnClickListener(this);
		bStart.setOnClickListener(this);
		bStop.setOnClickListener(this);

		tabHost.setup();

		specs = tabHost.newTabSpec("tag1");		
		specs.setContent(R.id.tab1);
		specs.setIndicator("Stopwatch");
		tabHost.addTab(specs);

		specs = tabHost.newTabSpec("tag2");		
		specs.setContent(R.id.tab2);
		specs.setIndicator("Tab2");
		tabHost.addTab(specs);

		specs = tabHost.newTabSpec("tag3");		
		specs.setContent(R.id.tab3);
		specs.setIndicator("Add a Tab");
		tabHost.addTab(specs);

	}

	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.bAddTab:
			TabSpec ourSpec = tabHost.newTabSpec("tag1");
			ourSpec.setContent(new TabHost.TabContentFactory() 
			{

				public View createTabContent(String tag) 
				{
					TextView text = new TextView(Tabs.this);
					text.setText("You have created a new Tab!!!");
					return text;
				}
			});

			ourSpec.setIndicator("New");
			tabHost.addTab(ourSpec);
			break;

		case R.id.bStartW:
			start = System.currentTimeMillis();
			break;

		case R.id.bStopW:
			stop = System.currentTimeMillis();
			if(start != 0)
			{
				long result = stop - start;
				int millis = (int) result;
				int seconds = (int) result/1000;
				int minutes = seconds/60;
				millis %=100;
				seconds %= 60;
				
				showResult.setText(String.format("%d:%02d:%02d ", minutes, seconds,millis  ));
			}
			break;
		}

	}




}
