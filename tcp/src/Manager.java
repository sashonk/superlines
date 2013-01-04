import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.ibm.db2.jcc.DB2Driver;

public class Manager implements Runnable  {
	
	private static void initConnection()
	{
		try {
			Log.I().writeln("initilize db2driver");
			//DB2Driver.class.newInstance();
			DB2Driver.class.newInstance();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			Log.I().writeln(e1.toString());
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			Log.I().writeln(e1.toString());
		}
		
	    //Connection conn = null;

		try {
			Log.I().writeln("create db connection");
			conn = DriverManager.getConnection(
			        "jdbc:db2://localhost:50000/TEST",
			        "admin", "8926sas");
			if(conn==null)
				Log.I().writeln("connection is null");
			Statement s= conn.createStatement();
			//System.out.println(s.toString());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			Log.I().writeln(e1.toString());
		}
	}
	
	static {
		model = new MyTableModel();
		initConnection();
	}
	
	public Manager(Socket client) throws IOException
	{
		this.client = client;
	    in  = new BufferedReader(new 
	    InputStreamReader(client.getInputStream()));
	     out = new PrintWriter(client.getOutputStream(),true);

		
		Thread t = new Thread(this);
		t.start();
	}

	public void run() {
		try{
		String input = null;
	    while ((input = in.readLine()) != null) {
	        if (input.equalsIgnoreCase("exit")) break;
	        if(input.equalsIgnoreCase("getxml")){
	        	out.println(getXml());
	        }
	        if(input.equalsIgnoreCase("result"))
	        {
	        	out.println(""); 
	        	String username = in.readLine();
	        	out.println("");
	        	String score = in.readLine();
	        	out.println("");     
	        	savetResult(username, Integer.parseInt(score));
	        }        
	        if(input.equalsIgnoreCase("appenduser"))
	        {
	        	out.println(""); 
	        	String username = in.readLine();
	        	out.println("");
	        	String pswd = in.readLine();
	        	out.println(Boolean.toString(AppendUser(username, pswd)));
	        }
	        if(input.equalsIgnoreCase("checkuser"))
	        {
	        	out.println(""); 
	        	String username = in.readLine();
	        	out.println("");
	        	String pswd = in.readLine();
	        	out.println(Boolean.toString(CheckUser(username, pswd)));
	        }
	        if(input.equalsIgnoreCase("shutdown"))
	        {
	        	 Log.I().writeln("TCP shutdown");
	        	//JOptionPane.showMessageDialog(null, "Shutting down");
	        	System.exit(0);
	        }
	        else{
	        	out.println("S:::"+input);
	        }
	       }
	    
		out.close();
		in.close();
		client.close();
		}
		catch(Exception e){e.printStackTrace();}
		 Log.I().writeln( "dispose manager");
	}
	
	private boolean AppendUser(String username, String pswdhash)
	{
		synchronized(conn)
		{
			try {
				conn.setAutoCommit(false);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM aqfd.players WHERE nickname = '"+username+"'");
				if(rs.next()==true)
					return false;//return rs.next()==true ? true : false;
				
			
				st.executeUpdate("INSERT INTO aqfd.players (nickname, pswdhash, regdate) VALUES ('"+username+"','"+ pswdhash +"', CURRENT_DATE)");
				conn.commit();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			      Log.I().writeln(e.toString());
			      try {
			    	  Log.I().writeln("Transaction failed.");
			         conn.rollback();
			      }
			      catch (SQLException se) {
			    	  Log.I().writeln(se.toString());
			      }
			}
			return true;
		}
	}
	
	private boolean CheckUser(String username, String pswdhash)
	{
		synchronized(conn)
		{
			try {
				conn.setAutoCommit(false);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("SELECT * FROM aqfd.players WHERE nickname = '"+username+"' AND pswdhash = '"+pswdhash+"'");
				if(rs.next()==true)
					return true;//return rs.next()==true ? true : false;
				
				conn.commit();
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			      Log.I().writeln(e.toString());
			      try {
			    	  Log.I().writeln("Transaction failed.");
			         conn.rollback();
			      }
			      catch (SQLException se) {
			    	  Log.I().writeln(se.toString());
			      }
			}
			return false;
		}
	}
	
	private String getXml()
	{
		try {
			FileInputStream ifstr = new FileInputStream("settings.xml");
			byte[] b = new byte[ifstr.available()];
			ifstr.read(b);
			//b = DES.encrypt(b);
			String s =new String(b, "UTF-8");
			//System.out.println(s);
			
			ifstr.close();
			s = s.replace("\r\n", "");
			s  = s.replace("\t", "");
			s = s.replace("\n", "");

			return s;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.I().writeln(e.toString());
		}
		return "";
	}
	
	private void savetResult(String username, int score)
	{
		synchronized (model){
			Log.I().writeln("accept result: "+ username+ " " + score);
			Vector<Object> v = new Vector<Object>();
			v.add(0);
			v.add(username);
			v.add(score);
			v.add(1);		
			model.addRecord(v);
			model.Save();
			synchronized(conn){
			try {
				conn.setAutoCommit(false);

				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("SELECT id FROM aqfd.players WHERE RTRIM(nickname) = '"+username+"'");
				if(rs.next()==false)			
					throw new Exception("No such user");
					//st.executeUpdate("INSERT INTO aqfd.players (nickname) VALUES ('"+username+"')");
				
				
				st.executeUpdate("INSERT INTO aqfd.results(userid, score, tcreated) VALUES (" +
						"(SELECT id FROM aqfd.players WHERE RTRIM(nickname) = '"+username+"' ), "+score+", CURRENT_TIMESTAMP )");
				
				conn.commit();
				st.close();
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
			      Log.I().writeln(e.toString());
			      try {
			    	  Log.I().writeln("Transaction failed.");
			         conn.rollback();
			      }
			      catch (SQLException se) {
			    	  Log.I().writeln(se.toString());
			      }
			}
			
			}
		}
	}

	private Socket client = null;
	private BufferedReader in = null;
	private PrintWriter out = null;
	private static MyTableModel model;// = new MyTableModel();
	//private static Statement st = null;
	private static Connection conn ;
	//private static DB2Driver driver ;
	private final String version = "1.0";

}
