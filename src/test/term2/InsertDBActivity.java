package test.term2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class InsertDBActivity extends Activity {
	final String DEFAULT_TABLE="buff";
	
	String value;
	String table_name;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		this.setContentView(R.layout.insert_db);
		Intent mIntent = this.getIntent();
		EditText tableName = (EditText)findViewById(R.id.insert_db_table_name);
		Button select = (Button)findViewById(R.id.insert_db_sel_button);
		ListView tableContents = (ListView)findViewById(R.id.insert_db_lv);
		EditText insertStatementET = (EditText)findViewById(R.id.insert_db_et);
		Button ok_button = (Button)findViewById(R.id.insert_db_ok);
		Button cancel_button=(Button) findViewById(R.id.cancel_button);
		EditText err = (EditText)findViewById(R.id.insert_db_err_et);
	
		table_name=(String) mIntent.getCharSequenceExtra("table");
		if(table_name.equals(""))table_name=DEFAULT_TABLE;
		tableName.setText(table_name);
		
	}
}
