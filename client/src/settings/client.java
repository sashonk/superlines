package settings;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class client {
  public client() throws Exception{
	  String ip = "77.37.156.28";//"localhost";//
	  int port = 4444;
    System.out.println("Welcome to Client side");



    //fromserver = new Socket(ip,port);
    
    //задаем прокси - тор
    //System.out.println("cr proxy");
   // Proxy p = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress("localhost", 9050));
    
    System.out.println("open socket");
    fromserver = new Socket();
    fromserver.connect(new InetSocketAddress(ip, port));
    
    in  = new
    BufferedReader(new 
     InputStreamReader(fromserver.getInputStream()));
       out = new 
    PrintWriter(fromserver.getOutputStream(),true);
    
    System.out.println("Connecting to... "+ip+":"+port);

    //fromserver.connect(new InetSocketAddress(ip, port));


  }
  
  public String getXml() throws IOException
  {		
	  out.println("getxml");
	  return in.readLine();
  }
  
  public boolean checkUser(String username, String pswdhash)
  {
	  try {
			out.println("checkuser");
			System.out.println(in.readLine());
			out.println(username);
			System.out.println(in.readLine());
			out.println(pswdhash);
			return Boolean.parseBoolean(in.readLine());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.toString(), "Ошибка", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return true;
  }
  
  public boolean appendUser(String username, String pswdhash)
  {
	  try {
			out.println("appenduser");
			System.out.println(in.readLine());
			out.println(username);
			System.out.println(in.readLine());
			out.println(pswdhash);
			return Boolean.parseBoolean(in.readLine());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.toString(), "Ошибка", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		return true;
  }
  
  public void sendResults(String username, int score)
  {
	  try {
		out.println("result");
		System.out.println(in.readLine());
		out.println(username);
		System.out.println(in.readLine());
		out.println(Integer.toString(score));
		System.out.println(in.readLine());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, e.toString(), "Ошибка", JOptionPane.ERROR_MESSAGE);
	}
  }
  
  public void close()
  {
	  try{
	  	out.println("exit");
	  	System.out.println(in.readLine());
	    out.close();
	    in.close();
	    //inu.close();
	    fromserver.close();
	  }
	catch (IOException e) {
		// TODO Auto-generated catch block
		JOptionPane.showMessageDialog(null, e.toString(), "Ошибка", JOptionPane.ERROR_MESSAGE);
	}
  }
  
  private BufferedReader in;
  private PrintWriter out;
  private Socket fromserver;
  private final String version = "1.0";
}

