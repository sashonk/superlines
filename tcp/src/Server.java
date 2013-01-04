import java.io.*;
import java.net.*;
import java.sql.*;

import com.ibm.db2.jcc.DB2Driver;

public class Server {

  public static void main(String[] args) throws IOException {
  
	  int port = 4444;
    Log.I().writeln("starting server side on port "+ port);

    ServerSocket servers = null;
    Socket       fromclient = null;

    // create server socket
    try {

      servers = new ServerSocket(port);
      
    } catch (IOException e) {
    	 Log.I().writeln("can not listen to port " +port);
  	//JOptionPane.showMessageDialog(null, "Couldn't listen to port 4444");

      System.exit(-1);
    }

    while(true)
    try {
    	 //Log.I().writeln("waiting for a client...");
      Log.I().writeln("waiting for a client...");
      fromclient= servers.accept();
      //System.out.println(fromclient.getInetAddress().getHostAddress()+ " connected");
      Log.I().writeln(fromclient.getInetAddress().getHostAddress()+ " connected");

      new Manager(fromclient);
    } catch (IOException e) {
    	 Log.I().writeln("can not accept");
      System.exit(-1);
    }


    //servers.close();
  }
}
