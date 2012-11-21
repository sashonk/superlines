package superlines;


import java.io.PrintWriter;
import java.io.StringWriter;



public class Util {
	public static String toString(final Exception e){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		 e.printStackTrace(pw);
		 return sw.toString();
	}
	
	
	public static boolean isBlank(final String str){
		return str == null || str.equals("");
	}
	
	

}

