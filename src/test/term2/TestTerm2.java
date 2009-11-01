package test.term2;

import java.util.ArrayList;
import java.util.Iterator;

import android.app.Activity;
import android.content.Intent;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
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
	
	ArrayList<String> localHistory = new ArrayList<String>();
	int localHistoryIndex=0;
	
	termThread term;
	CommandEditor cmdEditor;
	
    public Handler handle = new Handler(){
    	public void handleMessage(Message msg){
    		termOut.append((String)msg.obj);
    		scrollDown();
    		prompt_box.requestFocusFromTouch();
    	}
    };
	
    /** Called when the activity is first created. */	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        startThread();
        
        
        termOut = (EditText)findViewById(R.id.termOutput);
        termOut.setFocusable(false);
        termOut.setOnTouchListener(new OnTouchListener(){
			public boolean onTouch(View v, MotionEvent event) {
				termOut.setFocusable(true);
				prompt_box.setFocusable(false);
				termOut.requestFocus();
				return true;
			}
        });
        
        //termOut.setOnClickListener(termOut_onClicklsn);
        
        prompt_box = (EditText)findViewById(R.id.Input);
        
        prompt_box.setOnTouchListener(new OnTouchListener(){
			public boolean onTouch(View v, MotionEvent event) {
				termOut.setFocusable(false);
				prompt_box.setFocusable(true);
				prompt_box.requestFocus();
				return true;
			}
        });
        //cmdEditor = new CommandEditor(termOut,prompt_box);
        prompt_box.setOnLongClickListener(new OnLongClickListener(){
			public boolean onLongClick(View v) {
				//prompt_box.setText("Long click");
				Intent i = new Intent(TestTerm2.this,CommandEditor.class);
				i.putExtra("termOutTXT", termOut.getText().toString());
				i.putExtra("prompt_boxTXT", prompt_box.getText().toString());
				startActivity(i);
				return true;
			}
        	
        });

        
        
        enter_button = (Button)findViewById(R.id.enter);
        enter_button.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				enter_pressed();
			}
        });
        term.exec("export PATH=$PATH:/data/ybin \n");
    }
    public boolean onCreateOptionsMenu(Menu menu){
		
    	return false;
    }
    public boolean onKeyDown(int keyCode,KeyEvent event){
    	//super.onKeyDown(keyCode, event);
    	//prompt_box.setText(""+event.);
    	switch(keyCode){
    		case KeyEvent.KEYCODE_DPAD_DOWN:
    			retriveHistory(false);
    			return true;
    		case KeyEvent.KEYCODE_DPAD_UP:
    			retriveHistory(true);
    			prompt_box.setSelected(true);
    			termOut.setSelected(false);
    			
    			return true;
    		case KeyEvent.KEYCODE_DPAD_CENTER:
    			prompt_box.setText("DPAD_CENTER");
    			return true;
    		case 66:
    			prompt_box.setText("66");
    			return true;
    	}
    	//return true;
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
		prompt_box.selectAll();
		prompt_box.requestFocusFromTouch();
    }
    public void addHistory(String cmd){
    	localHistory.add(cmd);
    	localHistoryIndex=localHistory.size();
    }
    public void retriveHistory(boolean up){
    	if(up){
    		if(localHistoryIndex > 0){
    			localHistoryIndex--;
    			prompt_box.setText(""+localHistory.get(localHistoryIndex));
    		}
    	}else{
    		if(localHistoryIndex < localHistory.size()){    			
    			prompt_box.setText(""+localHistory.get(localHistoryIndex));
    			localHistoryIndex++;
    		}    		
    	}
    	//prompt_box.setText("Got me here!!!! motherfucker");
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
		sv.setFocusable(false);
    }
}