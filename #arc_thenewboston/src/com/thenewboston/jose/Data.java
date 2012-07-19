package com.thenewboston.jose;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data extends Activity implements OnClickListener {
	private EditText sendET;
	private Button safr;
	private Button sa;
	private TextView gotAnswer;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		initialize();
	}

	private void initialize()
	{
		gotAnswer = (TextView) findViewById(R.id.tvGot);
		safr = (Button) findViewById(R.id.bSAFR);
		sa = (Button) findViewById(R.id.bSA);
		sendET = (EditText) findViewById(R.id.etSend);
		safr.setOnClickListener(this);
		sa.setOnClickListener(this);
	}
	
	public void onClick(View v)
	{
		switch(v.getId())
		{
		case R.id.bSA:
			String bread = sendET.getText().toString();
			Bundle basket = new Bundle();
			basket.putString("key", bread);
			Intent a = new Intent(Data.this, OpenedClass.class);
			a.putExtras(basket);
			
			startActivity(a);
			
			break;
		
		case R.id.bSAFR:
			Intent i = new Intent(Data.this, OpenedClass.class);
			startActivityForResult(i, 0);
			break;
			
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK)
		{
			//Bundle basket =data.getExtras();
			//String s = basket.getString("answer");
			gotAnswer.setText(data.getExtras().getString("answer"));
		}
	}
	
	
	
}
