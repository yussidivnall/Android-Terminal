package test.term2;

import java.util.Vector;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class SelectFileActivity extends Activity {
	final String INPUT_DELIMINATOR=",";
	String output_deliminator=" ";
	
	Intent mIntent;
	ListView mListView;
	
	String mIntentExtra;
	//Vector <String> mFileList;
	Spinner action_spinner;
	
	public void onCreate(Bundle b){
		super.onCreate(b);
		setContentView(R.layout.file_select);
		mListView =(ListView)findViewById(R.id.files_list);
		
		mIntent=this.getIntent();
		mIntentExtra = (String)mIntent.getCharSequenceExtra("filenames");
		String args[] = mIntentExtra.split(INPUT_DELIMINATOR);
		ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, args);
		mListView.setAdapter(mArrayAdapter);
		
		
		
		action_spinner = (Spinner)findViewById(R.id.action_spinner);
		ArrayAdapter action_s_adapter = ArrayAdapter.createFromResource(this, R.array.select_actions, android.R.layout.simple_spinner_item);
		action_s_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		action_spinner.setAdapter(action_s_adapter);
		
		Button ok_button = (Button)findViewById(R.id.ok_button);
		
		Button cancel_button = (Button)findViewById(R.id.cancel_button);
		cancel_button.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				returnToCaller();
			}
		});
	}
	
	public void preformAction(){
		String act = (String)action_spinner.getSelectedItem();
	}
	
	public void returnToCaller(){
		this.setResult(Activity.RESULT_OK);
		this.finish();
	}
}
