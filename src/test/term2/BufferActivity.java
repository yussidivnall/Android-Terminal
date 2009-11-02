package test.term2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.util.Log;
public class BufferActivity extends Activity {
	EditText name;
	EditText val;
	ListView table;
	
	SQLiteDatabase mDatabase;
	public void onCreate(Bundle b){
		super.onCreate(b);
		this.setContentView(R.layout.clipboard_buffer);
		Intent mIntent = this.getIntent();
		name = (EditText)findViewById(R.id._buffer_name);
		val  = (EditText)findViewById(R.id._buffer_val);
		table = (ListView)findViewById(R.id._buffer_table);
		Button Set_Button = (Button)findViewById(R.id._buffer_set);
		Button Append_Button = (Button)findViewById(R.id._buffer_append);
		Button Cancel_Button = (Button)findViewById(R.id._buffer_cancel);
		
		name.setText(mIntent.getCharSequenceExtra("name"));
		val.setText(mIntent.getCharSequenceExtra("value"));
	
		mDatabase = this.openOrCreateDatabase("CLIPBOARD", this.MODE_PRIVATE, null);
		mDatabase.execSQL("CREATE TABLE IF NOT EXISTS BUFF (name VARCHAR, val VARCHAR);");
		Cursor ents = mDatabase.rawQuery("select * from BUFF", null);
		int num = ents.getColumnCount();
		//name.setText("num:"+num);
		
		String col_names[] = ents.getColumnNames();
		ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, col_names);
		table.setAdapter(mArrayAdapter);

		
		
		Set_Button.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				ins();
			}
		});
		
	}
	public void ins(){
		String n = name.getText().toString();
		String v = val.getText().toString();
		String s = "INSERT INTO BUFF (name, val) VALUES(\'"+n+"\',\'"+v+"\');";
		//name.setText(s);
		mDatabase.execSQL(s);
	}
}
