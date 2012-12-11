package superlines.core;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Pattern;



public class Util {
	
	private static final Pattern EMAIL_ADDRESS_PATTERN =
			Pattern
			.compile("([0-9A-Za-z-_]+\\.)*[0-9A-Za-z-_]+@((([0-9A-Za-z-_]+\\.)+[A-Za-z]+)|(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}))"); 
	
	public static String toString(final Exception e){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		 e.printStackTrace(pw);
		 return sw.toString();
	}
	
	
	public static boolean isBlank(final String str){
		return str == null || str.equals("");
	}
	
	public static boolean isValidEmailAddress(final String str) {
	    if (isBlank(str)) {
	        return false;
	    }
	    return EMAIL_ADDRESS_PATTERN.matcher(str).matches();
	}



}

