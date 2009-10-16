package test.term2;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.style.StyleSpan;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ScrollView;
import android.widget.TextView;


public class TestTerm2 extends Activity {
	
	
	EditText termOut;
	EditText termIn;
	
	Button enter_button;
	EditText prompt_box;
	
	ArrayList<String> localHistory;
	int localHistoryIndex=0;
	Iterator localHistoryIterator;
	
	termThread term;
    public Handler handle = new Handler(){
    	public void handleMessage(Message msg){
    		termOut.append((String)msg.obj);
    		scrollDown();
    	}
    };
	
    public OnClickListener termOut_onClicklsn = new OnClickListener(){
    	int spacing_mode = 0; // only spaces
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			// TODO Add a copy paste menu
			
		}
		    	
    };
	
    /** Called when the activity is first created. */	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        startThread();
        
        termOut = (EditText)findViewById(R.id.termOutput);
        termOut.setOnClickListener(termOut_onClicklsn);
        
        prompt_box = (EditText)findViewById(R.id.Input);
        
        
        
        enter_button = (Button)findViewById(R.id.enter);
        enter_button.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				enter_pressed();
			}
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
		
    	return false;
    }
    public boolean onKeyDown(int keyCode,KeyEvent event){
    	super.onKeyDown(keyCode, event);
    	//prompt_box.setText(""+event.);
    	switch(keyCode){
    		case KeyEvent.KEYCODE_DPAD_UP:
    			if(!localHistory.isEmpty() && localHistoryIndex > 0){
    				String c = localHistory.get(localHistoryIndex);
    				prompt_box.setText(c);
    				localHistoryIndex--;
    			}
    		return true;
//    		case KeyEvent.KEYCODE_ENTER:
//    			prompt_box.setText("Ah ahaha");
//    			return true;
    		case KeyEvent.KEYCODE_DPAD_CENTER:
    			prompt_box.setText("DPAD_CENTER");
    			return true;
    		case 66:
    			prompt_box.setText("66");
    			return true;
    	}
    	
    	if(event.getKeyCode()==KeyEvent.KEYCODE_DPAD_CENTER){
    		prompt_box.setText("Ah ahaha");
    		enter_pressed();
    		return true;
    	}
    	//return false;
    	return super.onKeyDown(keyCode, event);
    }
    
    public void enter_pressed(){
		String cmd = prompt_box.getText().toString();
		//Spannable s = prompt_box.getText();
		//s.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		termOut.append(cmd+"\n");
		addHistory(cmd);
		term.exec(cmd+"\n");    	
		scrollDown();
    }
    public void addHistory(String cmd){
    	//localHistory.add(cmd);
    	//localHistoryIndex++;
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