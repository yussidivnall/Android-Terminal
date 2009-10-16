package test.term2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ScrollView;
import android.widget.TextView;

public class TestTerm2 extends Activity {
	
	
	TextView termOut;
	EditText termIn;
	
	Button enter_button;
	EditText prompt_box;
	
	
	termThread term;
    public Handler handle = new Handler(){
    	public void handleMessage(Message msg){
    		termOut.append((String)msg.obj);
    		scrollDown();
    	}
    };
	
	
    /** Called when the activity is first created. */	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startThread();
        
        termOut = (TextView)findViewById(R.id.termOutput);
        prompt_box = (EditText)findViewById(R.id.Input);

        
        
        enter_button = (Button)findViewById(R.id.enter);
        enter_button.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				enter_pressed();
			}
        });
        
        
        
        
    }
    
    public void enter_pressed(){
		String cmd = prompt_box.getText().toString();
		//termOut.setTextColor(0x00ff00);
		termOut.append(cmd+"\n");
		term.exec(cmd+"\n");    	
		scrollDown();

		//sv.scrollTo(0, sv.getBaseline());
    }
    
    
    public void startThread(){
        term = new termThread(this);
        term.start();
        term.exec("echo 'a shell of a shell!' \n");
        
    }
    
    public void scrollDown(){
		final ScrollView sv = (ScrollView)findViewById(R.id.ScrollView01);
		sv.post(new Runnable(){ // ugly but works!!!!
			public void run(){
				sv.fullScroll(ScrollView.FOCUS_DOWN);
			}
		});
    }
}