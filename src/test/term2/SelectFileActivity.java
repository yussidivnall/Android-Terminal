package test.term2;

import java.io.InputStream;
import java.util.Vector;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;

public class SelectFileActivity extends Activity {
	final String INPUT_DELIMINATOR=",";
	String output_deliminator=":";
	String output_string="";
	
	Intent mIntent;
	ListView mListView;
	
	String mIntentExtra;
	//Vector <String> mFileList;
	Spinner action_spinner;
	EditText output_et;
	EditText deliminator_et;
	public void onCreate(Bundle b){
		super.onCreate(b);
		setContentView(R.layout.file_select);
		output_et = (EditText)findViewById(R.id.output_et);
		deliminator_et = (EditText)findViewById(R.id.deliminator);
		mListView =(ListView)findViewById(R.id.files_list);
		
		
		
		mIntent=this.getIntent();
		mIntentExtra = (String)mIntent.getCharSequenceExtra("filenames");
		String args[] = mIntentExtra.split(INPUT_DELIMINATOR);
		ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, args);
		mListView.setAdapter(mArrayAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {
				String selected=(String)mListView.getItemAtPosition(position);
				output_string = (String)output_et.getText().toString();
				output_deliminator = (String)deliminator_et.getText().toString();
				if(output_string.equals("")){
					output_string+=selected;
				}else output_string+=output_deliminator+selected;
				output_et.setText(output_string);
			}
		});
		
		
		
		
		action_spinner = (Spinner)findViewById(R.id.action_spinner);
		ArrayAdapter action_s_adapter = ArrayAdapter.createFromResource(this, R.array.select_actions, android.R.layout.simple_spinner_item);
		action_s_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		action_spinner.setAdapter(action_s_adapter);
		
		Button ok_button = (Button)findViewById(R.id.ok_button);
		ok_button.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				preformAction();
			}
		});
		
		Button cancel_button = (Button)findViewById(R.id.cancel_button);
		cancel_button.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				returnToCaller();
			}
		});
	}
	
	
	
	public void preformAction(){
		String act = (String)action_spinner.getSelectedItem();
		if(act.startsWith("export")){
			Intent intent = new Intent(this,ExportActivity.class);
			intent.putExtra("value", output_et.getText().toString());
			startActivity(intent);
		}
		if(act.startsWith("buffer")){
			Intent intent = new Intent(this,BufferActivity.class);
			intent.putExtra("name", "str0");
			intent.putExtra("value", output_et.getText().toString());
			startActivity(intent);			
		}
		if(act.startsWith("insert")){
			Intent intent = new Intent(this,InsertDBActivity.class);
			intent.putExtra("table", "buff");
			intent.putExtra("value", output_et.getText().toString());
			startActivity(intent);			
		}
	}
	
	public void returnToCaller(){
		this.setResult(Activity.RESULT_OK);
		this.finish();
		
	}
}
