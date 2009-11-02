package test.term2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ExportActivity extends Activity {
	EditText out_et;
	public void onCreate(Bundle b){
		super.onCreate(b);
		setContentView(R.layout.export_var);
		
		out_et = (EditText)findViewById(R.id.export_output);
		Button ok_button = (Button)findViewById(R.id.export_ok);
		Button cancel_button = (Button)findViewById(R.id.export_cancel);
		
		Intent mIntent = this.getIntent();
		String Value = (String) mIntent.getCharSequenceExtra("value");
		out_et.setText("export SEL="+Value);
		
		cancel_button.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				done(Activity.RESULT_CANCELED);
			}
		});
		
		ok_button.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				dispatch();
			}
		});
	}
	public void dispatch(){
		//Message msg = Message.obtain();
		//msg.obj=out_et.getText().toString();
		//msg.what=5;
		
		
	}
	
	public void done(int return_code){
		this.setResult(return_code);
		this.finish();
	}
}
