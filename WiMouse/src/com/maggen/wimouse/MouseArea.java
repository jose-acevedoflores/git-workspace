package com.maggen.wimouse;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;

public class MouseArea extends Activity{


	public void onCreate(Bundle savedInstanceSate)
	{
		super.onCreate(savedInstanceSate);

		this.setContentView(new Pad(this));
	}

	public void onPause()
	{
		super.onPause();
		this.finish();
	}


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
			Log.d("D", "W "+width);
			
			double dummy = height - height/2.6;
			float top=Float.parseFloat(String.valueOf(dummy));
			
			dummy = width/2.4;
			float right = Float.parseFloat(String.valueOf(dummy));
			
			c.drawRect(0, top , right, height, p);
			
			dummy = width - width/2.4; 
			float left = Float.parseFloat(String.valueOf(dummy));
			
			c.drawRect(left , top , width, height, p);
			
		}
		


	}

}
