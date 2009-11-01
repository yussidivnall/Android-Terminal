package test.term2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import android.os.Message;

public class termThread extends Thread implements Runnable {
	private static final String SHELL="/system/bin/sh";
	private boolean done = false;
	
	TestTerm2 parent;
	
	DataOutputStream stdIn;
	DataInputStream stdOut;
	DataInputStream stdErr;
	
	Process process;
	
	termThread(TestTerm2 p){
		try {
			parent = p;
			
			process = Runtime.getRuntime().exec(SHELL);
			stdIn = new DataOutputStream(process.getOutputStream());
			stdOut = new DataInputStream(process.getInputStream());
			stdErr = new DataInputStream(process.getErrorStream());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void run(){
		try {
			Thread.sleep(100);
			while (!done){
				while( (stdOut.available()==0) && (stdErr.available()==0) ){ Thread.sleep(10);}
				dump();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public void dump() {
    	try{
	    	String out ="";
			if(stdOut.available()>0){
				while(stdOut.available() > 0){
					out+=""+(char)stdOut.read();
				}						
			}
			
			if(stdErr.available() >0 ){
				while(stdErr.available() > 0){
					out+=""+(char)stdErr.read();
				}						
			}
			parent.handle.sendMessage(Message.obtain(parent.handle,0,out));
    	}catch(IOException e){}
    }
    
    public void exec(String command){
    	try {
			stdIn.write(command.getBytes());
			stdIn.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
	
}
