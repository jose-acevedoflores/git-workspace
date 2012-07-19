package project.binary;

import baseXNumber.BaseXNumber;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class BinaryAndroidActivity extends Activity {
	/** Called when the activity is first created. */

	private EditText input;
	private Spinner inputSpinner;
	private TextView convertedField;
	private Spinner outputSpinner;
	
	private TextView inBaseTV;
	private TextView outBaseTV;
	
	private AlertDialog.Builder dia;
	private EditText field;
	private AlertDialog alert;
	private ButtonListener buttonListener;
	
	private int baseInput=0;
	private int baseOut=0;

	/**
	 * OnCreate.....
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initVars();
	}
	

	@Override
	protected void onPause() {
		super.onPause();
		this.finish();
	}


	/**
	 * Initializes all the fields of this Activity
	 */
	private void initVars()
	{
		input = (EditText) findViewById(R.id.etInput);
		inputSpinner = (Spinner) findViewById(R.id.spinnerInput);
		convertedField = (TextView) findViewById(R.id.tvConverted);
		outputSpinner = (Spinner) findViewById(R.id.spinnerOutput);

		inBaseTV = (TextView) findViewById(R.id.tvCutomBaseInp);
		outBaseTV = (TextView) findViewById(R.id.tvCutomBaseOut);
		
		inputSpinner.setSelection(2); // This makes the Decimal option be the one that appears by default.

		outputSpinner.setOnItemSelectedListener(new OutputSpinnerListener());
		inputSpinner.setOnItemSelectedListener(new InputSpinnerListener());
		input.addTextChangedListener(new TextChangedListener());
		
		field = new EditText(BinaryAndroidActivity.this);
		field.setInputType(InputType.TYPE_CLASS_NUMBER);

		buttonListener = new ButtonListener();
		
		dia = new AlertDialog.Builder(BinaryAndroidActivity.this);
		dia.setMessage("Enter custom base from 3 to 9");
		alert = dia.create();
		alert.setView(field, 20, 0, 20, 20);
		alert.setTitle("Enter Base");
		alert.setButton(Dialog.BUTTON_POSITIVE, "Done", buttonListener );	
	}

	
	
	
	/**
	 * Class that implements the OnItem.... so we can update the Converted field every time a 
	 * new item from the spinner is selected. 
	 * @author Jose
	 *
	 */
	private class OutputSpinnerListener implements OnItemSelectedListener
	{

		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,long id) 
		{
	
			if(pos==4)
			{
				buttonListener.inOrOut = 0;
				alert.show();
			}
			else
				outBaseTV.setText("");

			BaseXNumber num;
			//Check whether the input spinner is set to the custom position, so we can call the special constructor that takes an (int , string)
			if(inputSpinner.getSelectedItemPosition() == 4)
				num = new BaseXNumber(baseInput, input.getText().toString().trim());
			
			else
				num = new BaseXNumber(inputSpinner.getItemAtPosition(inputSpinner.getSelectedItemPosition()).toString().toLowerCase(), input.getText().toString().trim());
	
			if(pos == 0) //Binary output
			{
				convertedField.setText(num.toBinary());
			}
			else if(pos == 1)//Octal Number
			{
				convertedField.setText(num.toOctal());
			}
			else if(pos == 2)//Decimal output
			{
				convertedField.setText(num.toDecimal());
			}
			else if(pos == 3) //Hexadecimal output
			{
				convertedField.setText(num.toHexadecimal());
			}
			else if(pos == 4)
			{
				convertedField.setText(num.toCustom(baseOut));
			}

		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {/*Do nothing*/}
	}

	/**
	 * Class that implements the OnItem.... so we can update the Converted field every time a 
	 * new item from the spinner is selected. 
	 * @author Jose 
	 *
	 */
	private class InputSpinnerListener implements OnItemSelectedListener
	{

		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,long id) 
		{
			if(pos==4)
			{	
				buttonListener.inOrOut = 1;
				alert.show();
			}
			else
				inBaseTV.setText("");
			
			BaseXNumber num;
			
			//Check whether the input spinner is set to the custom position, so we can call the special constructor that takes an (int , string)
			if(pos == 4)
				num = new BaseXNumber(baseInput, input.getText().toString().trim());
			
			else	
				num = new BaseXNumber(parent.getItemAtPosition(pos).toString(), input.getText().toString().trim());

			if(outputSpinner.getSelectedItemPosition() == 0) //Binary output
			{
				convertedField.setText(num.toBinary());
			}
			else if(outputSpinner.getSelectedItemPosition()==1)//Octal Number
			{
				convertedField.setText(num.toOctal());
			}
			else if(outputSpinner.getSelectedItemPosition() ==2)//Decimal output
			{
				convertedField.setText(num.toDecimal());
			}
			else if(outputSpinner.getSelectedItemPosition() == 3) //Hexadecimal output
			{
				convertedField.setText(num.toHexadecimal());
			}
			else if(outputSpinner.getSelectedItemPosition() == 4)
			{
				convertedField.setText(num.toCustom(baseOut));
			}

		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {/* Do nothing*/}
	}

	/**
	 * Class in charge of implementing the TextWatcher so we can
	 * update the TextView of the converted number in real time.
	 * @author Jose
	 *
	 */
	private class TextChangedListener implements TextWatcher {

		public void afterTextChanged(Editable s) 	{/*Do nothing*/}
		public void beforeTextChanged(CharSequence s, int start, int count,	int after) {	/*Do nothing*/		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,int count) 
		{
			BaseXNumber num;
			
			//Check whether the input spinner is set to the custom position, so we can call the special constructor that takes an (int , string)
			if(inputSpinner.getSelectedItemPosition() == 4)
				num = new BaseXNumber(baseInput, input.getText().toString().trim());
			
			else
				num = new BaseXNumber(inputSpinner.getItemAtPosition(inputSpinner.getSelectedItemPosition()).toString(), input.getText().toString().trim());

			
			if(outputSpinner.getSelectedItemPosition() == 0) //Binary output
			{
				convertedField.setText(num.toBinary());
			}
			else if(outputSpinner.getSelectedItemPosition() == 1)//Octal Number
			{
				convertedField.setText(num.toOctal());
			}
			else if(outputSpinner.getSelectedItemPosition() == 2)//Decimal output
			{
				convertedField.setText(num.toDecimal());
			}
			else if(outputSpinner.getSelectedItemPosition() == 3) //Hexadecimal output
			{
				convertedField.setText(num.toHexadecimal());
			}
			else if(outputSpinner.getSelectedItemPosition() == 4)
			{
				convertedField.setText(num.toCustom(baseOut));
			}

		}

	}

	private	class ButtonListener implements DialogInterface.OnClickListener{

			private int inOrOut;
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) 
			{
				
					if(inOrOut == 1)
					{
						baseInput = Integer.parseInt(field.getText().toString());
						inBaseTV.setText(" Base = " + baseInput);
					}
					else
					{
						baseOut = Integer.parseInt(field.getText().toString());
						outBaseTV.setText(" Base = " + baseOut);
					}
					
					BaseXNumber num;
					
					//Check whether the input spinner is set to the custom position, so we can call the special constructor that takes an (int , string)
					if(inputSpinner.getSelectedItemPosition() == 4)
						num = new BaseXNumber(baseInput, input.getText().toString().trim() );
					
					else
						num = new BaseXNumber(inputSpinner.getItemAtPosition(inputSpinner.getSelectedItemPosition()).toString(), input.getText().toString().trim());
					
					if(outputSpinner.getSelectedItemPosition() == 0) //Binary output
					{
						convertedField.setText(num.toBinary());
					}
					else if(outputSpinner.getSelectedItemPosition() == 1)//Octal Number
					{
						convertedField.setText(num.toOctal());
					}
					else if(outputSpinner.getSelectedItemPosition() == 2)//Decimal output
					{
						convertedField.setText(num.toDecimal());
					}
					else if(outputSpinner.getSelectedItemPosition() == 3) //Hexadecimal output
					{
						convertedField.setText(num.toHexadecimal());
					}
					else if(outputSpinner.getSelectedItemPosition() == 4)
					{
						convertedField.setText(num.toCustom(baseOut));
					}
				
			}
		
		}
}