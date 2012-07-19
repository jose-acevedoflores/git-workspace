package jose.matrix;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MatrixDeterminantActivity extends ListActivity{

	private String[] arr;
	private String[] arrP = {"Matrix2x2", "Matrix3x3"};
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		String current = arrP[position];
		try
		{
			
			@SuppressWarnings("rawtypes")
			Class act = Class.forName("jose.matrix." + current);
			Intent intent = new Intent(this, act);
			startActivity(intent);
		}
		catch(ClassNotFoundException e)
		{
			
			e.printStackTrace();
		}
	}
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arr = getResources().getStringArray(R.array.entries);
        
        setListAdapter(new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1, arr));

    
    }
    
    
    
    @Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) 
	{
		//super.onCreateOptionsMenu(menu);
		
		MenuInflater blowUp = getMenuInflater();
		blowUp.inflate(R.menu.menu , menu);
		
		return true;
	}

    public boolean onOptionsItemSelected(MenuItem item) 
    {
    	switch(item.getItemId())
    	{
    	case R.id.about:
    		Intent i = new Intent("jose.matrix.ABOUT");
    		startActivity(i);
    		break;
    		
    	case R.id.exit:
    		finish();
    		break;
    	}
    	
    	return true;
    }   
    
}