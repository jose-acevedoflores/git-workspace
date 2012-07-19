package jose.matrix;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Matrix2x2 extends Activity implements OnClickListener{

	private TextView[] tv = new TextView[4];
	private Button bm2;
	private double result;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.matrix2x2);
		initVar();
	}

	private void initVar()
	{
		bm2 = (Button) findViewById(R.id.bM2);
		bm2.setOnClickListener(this);

		for(int i = 1; i < 5; i++)
		{
			String id = "et"+i+"M2";
			int resId = getResources().getIdentifier(id, "id", "jose.matrix");
			tv[i-1] = (TextView) findViewById(resId);
			
		}

	}

	public void onClick(View v) 
	{
		result=0;
		double[] numbersFromFields = new double[4];
		int i=0;
		
		for(TextView t : tv)
		{
			if(t.getText().toString().equals(""))
			{
				break;
			}
			numbersFromFields[i] = Double.parseDouble(t.getText().toString());
			i++;
		}
		
		//Then there was an empty field
		if(i != 4 )
		{
			result = 0;
		}
		else
			result = numbersFromFields[0]*numbersFromFields[3] - numbersFromFields[1]*numbersFromFields[2];
		
		AlertDialog.Builder dia = new AlertDialog.Builder(this);
		dia.setMessage("Determinant is: "+result);
		
		AlertDialog alert = dia.create();
		alert.setTitle("Result");
		alert.show();
	}



}
