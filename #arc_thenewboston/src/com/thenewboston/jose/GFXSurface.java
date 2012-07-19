package com.thenewboston.jose;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener{
	
	private MyBringBrackSurface ourSurfaceView;
	private float x,y, sX, sY, fX, fY, dX, dY, animateX, animateY, scaledX, scaledY;
	private Bitmap test, plus;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		ourSurfaceView = new MyBringBrackSurface(this);
		ourSurfaceView.setOnTouchListener(this);
		x=0;
		y=0;
		sX=0;
		sY=0;
		fX=0;
		fY=0;
		dX=0;
		dY=0;
		animateX=0;
		animateY=0;
		scaledX=0;
		scaledY=0;
		test = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
		plus = BitmapFactory.decodeResource(getResources(), R.drawable.plus_mio);
		setContentView(ourSurfaceView);
	}

	protected void onPause()
	{
		super.onPause();
		ourSurfaceView.pause();
	}
	
	protected void onResume()
	{
		super.onResume();
		ourSurfaceView.resume();
	}

	
	public boolean onTouch(View v, MotionEvent event) {
		
		
		try
		{
			Thread.sleep(50);
		}
		catch (InterruptedException e) 
		{			
			e.printStackTrace();
		}
		
		x = event.getX();
		y = event.getY();
		
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			sX = event.getX();
			sY = event.getY();
			animateX=0;
			animateY=0;
			fX=0;
			fY=0;
			scaledX=0;
			scaledY=0;
			break;
			
		case MotionEvent.ACTION_UP:
			fX = event.getX();
			fY = event.getY();
			dX = fX - sX;
			dY = fY - sY;
			scaledX = dX/30;
			scaledY = dY/30;
			x=0;
			y=0;
			break;
		
		}
		
		return true;
	}
	
/*---------------------------------------------------------------------------------*/
	
	public class MyBringBrackSurface extends SurfaceView implements Runnable{

		private SurfaceHolder ourHolder;
		private Thread ourThread = null;
		private boolean isRunning = false;
		
		public MyBringBrackSurface(Context context) 
		{
			super(context);
			ourHolder = getHolder();
		}

		public void pause()
		{
			isRunning = false;
			while(true)
			{
				try {
					ourThread.join();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				break;
			}
			ourThread = null;
		}
		
		public void resume()
		{
			isRunning = true;
			ourThread = new Thread(this);
			ourThread.start();
		}
		
		public void run()
		{
			while(isRunning)
			{
				if(!ourHolder.getSurface().isValid())
					continue;
				
				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB( 02, 02, 150);
				
				if(x != 0 && y != 0)
				{
					canvas.drawBitmap(test, x-(test.getWidth()/2), y-(test.getHeight()/2), null);
				}
				
				if(sX != 0 && sY != 0)
				{
					canvas.drawBitmap(plus, sX-(plus.getWidth()/2), sY-(plus.getHeight()/2), null);
				}
				
				if(fX != 0 && fY != 0)
				{
					canvas.drawBitmap(test, fX-(test.getWidth()/2)-animateX, fY-(test.getHeight()/2)-animateY, null);
					canvas.drawBitmap(plus, fX-(plus.getWidth()/2), fY-(plus.getHeight()/2), null);
				}
				
				animateX += scaledX;
				animateY += scaledY;
				
				ourHolder.unlockCanvasAndPost(canvas);
			}
		}

	}

	
}
