package com.maggen.wimouse;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class WiMouseActivity extends Activity {
	
	private Button startButton;
	private EditText[] ipFields;
	private ListView previousIPs;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set what is going to be displayed to the user.
        setContentView(R.layout.main);
        
        //Initialize the variables.
        this.initializeVars();
    }
    
    private void initializeVars()
    {
    	//Initialize the button
    	this.startButton = (Button) findViewById(R.id.bStart);
    	
    	//Initialize the list view
    	this.previousIPs = (ListView) findViewById(R.id.lvPreviousIP);
    	
    	//Initialize the four ip fields.
    	this.ipFields = new EditText[4];
    	this.ipFields[0] = (EditText) findViewById(R.id.etIP1);
    	this.ipFields[1] = (EditText) findViewById(R.id.etIP2);
    	this.ipFields[2] = (EditText) findViewById(R.id.etIP3);
    	this.ipFields[3] = (EditText) findViewById(R.id.etIP4);


    }
    
    public void onPause()
    {
    	super.onPause();
    	this.finish();
    }
    
}