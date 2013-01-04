import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Log {
	private static Log Instance = null;
	private Log() 
	{

	}
	
	public static Log I()
	{
		if(Instance ==null)
		{
			Instance = new Log();
		}
		return Instance;
	}
	
/*	public void write(String s)
	{
		System.out.print(s);

		try {
			LogFile.write(s.getBytes());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/
	
	public void writeln(String s)
	{
		synchronized(Instance){
		 Date d = new Date();
		 DateFormat df = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
		 String dateStr = df.format(d);
		
		s = d + "| " + s + "\n";
		System.out.print(s);

		try {
			FileOutputStream LogFile = new FileOutputStream("Log.txt", true);
			LogFile.write(s.getBytes());
			LogFile.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
}
