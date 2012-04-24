package com.maggen.wimouse;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.maggen.udp.client.UDPClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
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
	private float previousX;
	private float previousY;
	private float currentX;
	private float currentY;

	//rectangle constants
	private float rightClickLeft;
	private float rightClickTop;
	private float rightClickRight;
	private float rightClickBottom;

	private float leftClickLeft;
	private float leftClickTop;
	private float leftClickRight;
	private float leftClickBottom;


	public void onCreate(Bundle savedInstanceSate)
	{
		super.onCreate(savedInstanceSate);

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		//Initialize the pad surface to be viewed.
		view  = new Pad(this);
		view.setOnTouchListener(this);
		this.setContentView(view);

		//initializing the start coordinates of the touch event
		this.previousX = 0; 
		this.previousY= 0;

		//Initialize alert dialog
		AlertDialog.Builder dia = new AlertDialog.Builder(this);

		try{
			SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
			client = new UDPClient(WiMouseICSActivity.ip, pref);
		}
		catch (UnknownHostException e) {
			dia.setMessage("Invalid Host");
			final AlertDialog alert = dia.create();
			alert.setTitle("Alert");
			alert.show();

			//Pause for a moment to show the alert dialog, then finish this Activity
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				public void run() {
					if(alert.isShowing())
						alert.dismiss();
				}
			}, 6000);


		}
		catch (SocketException e) {
			dia.setMessage("Invalid socket");
			final AlertDialog alert = dia.create();
			alert.setTitle("Alert");
			alert.show();

			Handler handler = new Handler(); 
			handler.postDelayed(new Runnable() {
				public void run() {
					if(alert.isShowing())
						alert.dismiss();
				}
			}, 6000);

		}
	}

	public void onPause()
	{
		super.onPause();
		this.finish();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		currentX = event.getX();
		currentY = event.getY();



		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			if( currentY > leftClickTop && currentX < leftClickRight)
			{
				Log.d("Left", "Click");
				new Thread(new Runnable(){
					public void run(){
						try {
							client.leftClick();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}).start();
				
				
			}

			if( currentY > rightClickTop && currentX > rightClickLeft)
			{
				Log.d("Right", "Click");	
				new Thread(new Runnable(){
					public void run(){
						try {
							client.rightClick();
						} catch (IOException e) {

							e.printStackTrace();
						}
					}
				}).start();
			}
			break;
		case MotionEvent.ACTION_UP:
			this.previousX = 0;
			this.previousY = 0;
			return true;
		default:
			break;
		}


		if(this.previousX != 0 && this.previousY != 0)
			new Thread(new Runnable(){
				public void run(){
					try {
						client.updatePointer((int) currentX,(int) currentY, (int) previousX, (int) previousY);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();


		this.previousX = currentX;
		this.previousY = currentY;


		//		catch (IOException e) {
		//
		//			AlertDialog.Builder dia = new AlertDialog.Builder(this);
		//			dia.setMessage("Could not send coordinates");
		//			final AlertDialog alert = dia.create();
		//			alert.setTitle("Alert");
		//			alert.show();
		//
		//			Handler handler = new Handler();
		//			handler.postDelayed(new Runnable() {
		//				public void run() {
		//					if(alert.isShowing())
		//						alert.dismiss();
		//				}
		//			}, 6000);
		//
		//
		//		}

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
			initializeRectangleCoordinates(c);

			Paint p = new Paint();
			p.setColor(Color.RED);

			//Draw left click
			c.drawRect(leftClickLeft, leftClickTop , leftClickRight, leftClickBottom, p);

			//Draw right click
			c.drawRect(rightClickLeft , rightClickTop , rightClickRight, rightClickBottom, p);
		}


		private void initializeRectangleCoordinates(Canvas c)
		{
			float canvasHeight = c.getHeight();
			float canvasWidth = c.getWidth();	
			double dummy;

			/*-----------Left click rectangle coordinates ------------*/
			leftClickLeft = 0;

			dummy = canvasHeight - canvasHeight/4.2;
			leftClickTop = Float.parseFloat(String.valueOf(dummy));

			dummy = canvasWidth/2.4;
			leftClickRight = Float.parseFloat(String.valueOf(dummy));

			leftClickBottom = canvasHeight;

			/*-----------Right click rectangle coordinates ------------*/

			dummy = canvasWidth - canvasWidth/2.4; 
			rightClickLeft = Float.parseFloat(String.valueOf(dummy));

			rightClickTop = leftClickTop;

			rightClickRight = canvasWidth;

			rightClickBottom = leftClickBottom;
		}
	}


	/*-------------------------Private class -------------------------------------*/

}
