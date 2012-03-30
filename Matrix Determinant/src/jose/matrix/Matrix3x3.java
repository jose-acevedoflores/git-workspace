package jose.matrix;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Matrix3x3 extends Activity implements OnClickListener{
	
	private TextView[] tv = new TextView[9];
	private Button bm3;
	private double result;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.matrix3x3);
		initVar();
	}

	
	private void initVar()
	{
		bm3 = (Button) findViewById(R.id.bM3);
		bm3.setOnClickListener(this);
		
		for(int i = 1; i < 10; i++)
		{
			String id = "et"+i+"M3";
			int resId = getResources().getIdentifier(id, "id", "jose.matrix");
			tv[i-1] = (TextView) findViewById(resId);
		}
		
	}

	public void onClick(View v) 
	{
			result =0;
			double[] numbersFromFields = new double[9]; 
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
			if(i != 9 )
			{
				result = 0;
			}
			else
			{
				//Formula for adding the determinant of a 3x3 matrix
				result += numbersFromFields[0]*numbersFromFields[4]*numbersFromFields[8];
				result += numbersFromFields[1]*numbersFromFields[5]*numbersFromFields[6];
				result += numbersFromFields[2]*numbersFromFields[3]*numbersFromFields[7];
			
				result -= numbersFromFields[6]*numbersFromFields[4]*numbersFromFields[2];
				result -= numbersFromFields[7]*numbersFromFields[5]*numbersFromFields[0];
				result -= numbersFromFields[8]*numbersFromFields[3]*numbersFromFields[1];
			}
			

			AlertDialog.Builder dia = new AlertDialog.Builder(this);
			dia.setMessage("Determinant is: "+result);
			
			AlertDialog alert = dia.create();
			alert.setTitle("Result");
			alert.show();
		
	}

	
}
