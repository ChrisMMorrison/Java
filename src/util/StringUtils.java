package util;

import java.util.ArrayList;

public class StringUtils {
	
	//returns true if the given string is 
	public static boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c) && c != '.') return false;
	    }
	    return true;
	}
	
	//given a master string and a token string, finds the index of all occurences of the token in the master
	public static ArrayList<Integer> findOccurences(String str, String token){
		int lastPos = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(-1);
		while(str.indexOf(token, lastPos) > 0) {
			int pos = str.indexOf(" ", lastPos);
			list.add(pos);
			lastPos = pos+1;
		}
		list.add(str.length());
		return list;
	}
	
	public static ArrayList<Integer> findOccurences(String str, char[] token){
		char[] chars = str.toCharArray();
		int lastPos = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(-1);
		for(int i = 0; i < chars.length; i++) {
			for(int o = 0; o < token.length; o++) {
				if(token[o] == chars[i]) {
					list.add(i);
				}
			}
		}
		
		list.add(str.length());
		return list;
	}

}
