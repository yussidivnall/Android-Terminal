package test.term2;

import java.util.ArrayList;


public class StringsManager {

	static ArrayList<pair> tmp_strings = new ArrayList<pair>();
	static ArrayList<pair> perm_strings = load_permenant_strings();
	static String clipboard ="";
	
	public static ArrayList<pair> load_permenant_strings(){
		return new ArrayList<pair>();
	}
	
	public static void copy_to_clipboard(String s){
		clipboard = s;
	}
	public static void append_to_clipboard(String s){
		clipboard+=s;
	}
	public static String paste_from_clipBoard(){
		return clipboard;
	}
	public static void new_permanent_string(String name,String value){
		pair s = new pair("%%"+name+"%%",value); 
		perm_strings.add(s);
	}
}
class pair {
	String name; String value;
	pair(String n,String v){
		name=n;
		value=v;
	}
}