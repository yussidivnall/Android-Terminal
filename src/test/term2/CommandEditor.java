package test.term2;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Bundle;
import android.widget.*;

public class CommandEditor extends Activity {

	
	public void onCreate(Bundle b){
		super.onCreate(b);
		setContentView(R.layout.cmdedit);
		
		ListView profiles_listview = (ListView)findViewById(R.id.CommandProfiles);
		
		String profiles[] = new String[]{"Op1","Op2"};
		ArrayAdapter<String> myArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, profiles);
		profiles_listview.setAdapter(myArrayAdapter);
		
		
		
	}
	
	
}
