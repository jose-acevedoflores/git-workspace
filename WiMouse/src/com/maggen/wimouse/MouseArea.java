package com.maggen.wimouse;

import com.maggen.udp.client.UDPClient;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.view.Window;

public class MouseArea extends Activity implements OnTouchListener{

	private Pad view;
	private UDPClient client;
	
	public void onCreate(Bundle savedInstanceSate)
	{
		super.onCreate(savedInstanceSate);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		view  = new Pad(this);
		view.setOnTouchListener(this);
		this.setContentView(view);

    	client = new UDPClient(WiMouseActivity.ip);
	}

	public void onPause()
	{
		super.onPause();
		this.finish();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		float x = event.getX();
		float y = event.getY();
		
		Log.d("update", "x "+x+" y "+y);
		
		client.updatePointer(x, y);
		
		return true;
	}
	
	
	/*-------------------------Private class -------------------------------------*/
	
	private class Pad extends SurfaceView {

		public Pad(Context context)
		{
			super(context);
			this.setWillNotDraw(false);
		}

		public void onDraw(Canvas c)
		{	
			Paint p = new Paint();
			p.setColor(Color.RED);
			float width = c.getWidth();
			float height = c.getHeight();
			
			//Change the value for the top of the rectangle.
			double dummy = height - height/4.2;
			float top=Float.parseFloat(String.valueOf(dummy));
			
			//Change the value for the right side of the rectangle
			dummy = width/2.4;
			float right = Float.parseFloat(String.valueOf(dummy));
			
			c.drawRect(0, top , right, height, p);
			
			//Change the value for the left side of the rectangle.
			dummy = width - width/2.4; 
			float left = Float.parseFloat(String.valueOf(dummy));
			
			c.drawRect(left , top , width, height, p);
			
		}
	}
	/*-------------------------Private class -------------------------------------*/

}
