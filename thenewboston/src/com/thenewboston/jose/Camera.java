package com.thenewboston.jose;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Camera extends Activity implements View.OnClickListener{

	private ImageButton ib;
	private Button b;
	private ImageView iv;
	private Intent i;
	private final static int cameraData=0;
	private Bitmap bmp;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		initialize();
		
		//Done to prevent the null pointer 
		//Eliminate this and the dialog warning will appear
		InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
		bmp = BitmapFactory.decodeStream(is);

	}

	private void initialize()
	{
		ib = (ImageButton) findViewById(R.id.ibTakePic);
		b = (Button) findViewById(R.id.bSetWall);
		iv = (ImageView) findViewById(R.id.ivReturnedPic);
		b.setOnClickListener(this);
		ib.setOnClickListener(this);
	}

	public void onClick(View v) 
	{
		switch(v.getId())
		{
		case R.id.ibTakePic:

			i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(i, cameraData);

			break;

		case R.id.bSetWall:
			if(bmp != null)
			{
				try {
					getApplicationContext().setWallpaper(bmp);
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
			else
			{
				AlertDialog.Builder dia = new AlertDialog.Builder(this);
				dia.setMessage("No picture taken yet");
				
				AlertDialog alert = dia.create();
				alert.setTitle("Alert");
				alert.show();
			}
			break;
		}


	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK)
		{
			Bundle extras = data.getExtras();
			bmp = (Bitmap) extras.get("data");
			iv.setImageBitmap(bmp);
		}
	}



}
