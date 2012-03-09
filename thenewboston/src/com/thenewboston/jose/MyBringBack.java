package com.thenewboston.jose;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

public class MyBringBack extends View{

	private Bitmap gBall;
	private float changingY;
	private Typeface font;
	
	public MyBringBack(Context context) 
	{
		super(context);
		gBall = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
		changingY = 0;
		font = Typeface.createFromAsset(context.getAssets(), "neogrey-regular.otf");
	}
	
	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		
		Paint textPaint = new Paint();
		textPaint.setARGB(50, 254, 10, 50);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(30);
		textPaint.setTypeface(font);
		
		canvas.drawText("my-bring-back", canvas.getWidth()/2, canvas.getHeight()/2, textPaint);
		
		canvas.drawBitmap(gBall, canvas.getWidth()/2, changingY, null  );
		
		if(changingY < canvas.getHeight())
			changingY += 10;
		else
			changingY=0;
		
		Rect middleRect = new Rect();
		middleRect.set(0, canvas.getHeight()-200, canvas.getWidth(), canvas.getHeight()-100);
		Paint ourBlue = new Paint();
		ourBlue.setColor(Color.BLUE);
		canvas.drawRect(middleRect, ourBlue);
		
		invalidate();
	}

}
