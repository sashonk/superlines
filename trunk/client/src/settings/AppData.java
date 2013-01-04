package settings;


import java.io.File;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JOptionPane;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import javax.xml.parsers.*;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


//import org.w3c.dom.*;


public class AppData {
	private static AppData Instance = null;
	
	private AppData()
	{			
		try {
			int rez = JOptionPane.showOptionDialog(null, "Играть OnLine?", "Сообщение", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			//JOptionPane.showMessageDialog(null, rez);
			if(rez==-1)
				System.exit(0);
			
			if(rez==JOptionPane.YES_OPTION){
					client c = null;
					 try {
						c = new client();
						try {
							File f = new File("data/defer/");
							File[] files = f.listFiles();
							for(int i = 0; i<files.length; i++)
							{
								FileInputStream ifstr = null;

									ifstr = new FileInputStream(files[i]);
									byte[] b = new byte[ifstr.available()];
									ifstr.read(b);
									b = DES.decrypt(b);
									String[] s = new String(b, "UTF-8").split(" ");
									
									//client c = new client();
									c.sendResults(s[0], Integer.parseInt(s[1]));
									//c.close();
									
									ifstr.close();
									files[i].delete();
							
							}
							
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					 }
					 catch(Exception exp)
					 {
						 JOptionPane.showMessageDialog(null, "Невозможно установить соединение с сервером");
						 //JOptionPane.showMessageDialog(null, exp.toString());

						 return;
						 
					 }
					String txt;

					try {								
						txt = c.getXml();
				        InputStream is = new ByteArrayInputStream(txt.getBytes("UTF-8"));
						DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
						doc = db.parse(is);
				        
					    c.close();	
					        
					    online = true;
					      
					} catch (Exception exc) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, exc.toString(), "Ошибка", JOptionPane.ERROR_MESSAGE);
						//Runtime.getRuntime().exit(0);
						System.exit(0);
					} 
	     
					//new MyFrame(true);
			}
			
			if(rez==JOptionPane.NO_OPTION){
						doc = null;
						online = false;
					//new MyFrame(false);
			}
			
			
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			locdoc = db.parse(new File("settings.xml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
		
	public static AppData getInstance()
	{
		if(Instance==null)
		{
			Instance = new AppData();
		}
		return Instance;
	}
	
	public String[] getUserNames()
	{
		NodeList nl = locdoc.getElementsByTagName("user");
		String[] usernames = new String[nl.getLength()];
		for(int i =0; i<nl.getLength(); i++)
		{
			usernames[i] = nl.item(i).getFirstChild().getTextContent();
		}
		return usernames;
	}
	
	public boolean checkUser(String username, String pswdhash){
		if(online){
			try {
				client c = new client();
				 boolean check = c.checkUser(username, pswdhash);
				 c.close();
				 return check;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
		else{
			
		}
		
		return true;
	}
	
	public boolean checkLocalUser(String username, String pswdhash){

		NodeList users = locdoc.getElementsByTagName("user");
		
		for(int i=0; i<users.getLength(); i++){
			Element nameNode = (Element) ((Element)users.item(i)).getElementsByTagName("name").item(0);
			Element hashNode = (Element) ((Element)users.item(i)).getElementsByTagName("pswdhash").item(0);

			if(nameNode.getTextContent().equalsIgnoreCase(username) && hashNode.getTextContent().equalsIgnoreCase(pswdhash)){
					return true;
			}
		}
		
		return false;
		
	}
	
	//return  username
	public String addLocalUser(String username, String pswdhash){
/*		String[] usrnames = getUserNames();
		
		String suff = "";
		String norm = "";
		int count = 1;
		while(true){
			norm = username + suff;
			for(int i=0 ; i<usrnames.length; i++)
			{
				if(norm.equals(usrnames[i])){
					suff = " ("+count+")";					
					count +=1;
					continue;
				}
			}
			
			break;
		}*/
			
		Node nd = locdoc.getElementsByTagName("localData").item(0);
		Node child = nd.appendChild(locdoc.createElement("user"));
		child.appendChild(locdoc.createElement("name")).setTextContent(username);
		child.appendChild(locdoc.createElement("pswdhash")).setTextContent(pswdhash);
		child.appendChild(locdoc.createElement("online")).setTextContent(Boolean.toString(online));

		Node stgs = child.appendChild(locdoc.createElement("settings"));
		stgs.appendChild(locdoc.createElement("sound")).setTextContent("0");
		stgs.appendChild(locdoc.createElement("tip")).setTextContent("1");

		Save();
		return username;
	}
	
	public boolean appendUser(String username, String pswdhash)
	{

			try {
				client c = new client();
				 boolean append  = c.appendUser(username, pswdhash);
				 c.close();
				 return append;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
				return false;
			}
		

	}
	
	
	public String getUserPswdHash(String username)
	{
		//ApplicationData data = locdoc.getApplicationData();
		NodeList children = locdoc.getElementsByTagName("user");
		for(int i = 0; i<children.getLength(); i++)
		{
			Node nd = children.item(i).getFirstChild();
			if(nd.getTextContent().equals(username))
			{
				
				return  nd.getNextSibling().getTextContent().trim();
			}		
		}
		
		JOptionPane.showMessageDialog(null, "Error: getUserPswdHash");
		return null;
	}
	
	public boolean Updated(){
		
		Node updated  = doc.getElementsByTagName("updated").item(0);
		if(updated.getTextContent().equals(version))
			return true;
		return false;
	}
	
	public UserSettings getUserSettings(String username)
	{
		NodeList children = locdoc.getElementsByTagName("user");
		for(int i = 0; i<children.getLength(); i++)
		{
			Node nd = children.item(i).getFirstChild();
			if(nd.getTextContent().equals(username))
			{
				Node node = nd.getParentNode().getLastChild();
				
				UserSettings ud = new UserSettings();
				Node next = node.getFirstChild();
				while(next!=null)
				{
					if(next.getNodeName().equals("sound"))
					{
						ud.setSound(next.getTextContent().equals("0") ? false : true);
					}
					if(next.getNodeName().equals("tip"))
					{
						ud.setTip(next.getTextContent().equals("0") ? false : true);
					}
					next = next.getNextSibling();
				}
				return ud;
			}		
			
		}
		return null;
	}
	
	public boolean isOnlineUser(String username)
	{

		
		
/*		NodeList children = doc.getElementsByTagName("user");
		for(int i = 0; i<children.getLength(); i++)
		{
			Node nd = children.item(i).getFirstChild();
			if(nd.getTextContent().equals(username))
			{
				Node online = ((Element)nd).getElementsByTagName("online").item(0);
				return Boolean.parseBoolean(online.getTextContent());
			}		
		}*/
		
		//JOptionPane.showMessageDialog(null, "Error: isOnlineUser()");
		return false;	
		
	}
	
	public int getColorCount()
	{
		return online ? Integer.parseInt(doc.getElementsByTagName("colorcount").item(0).getTextContent()): Integer.parseInt(locdoc.getElementsByTagName("colorcount").item(0).getTextContent());
	}
		
	public int getNewBallsCount()
	{
		return online ? Integer.parseInt(doc.getElementsByTagName("colornew").item(0).getTextContent()): Integer.parseInt(locdoc.getElementsByTagName("colornew").item(0).getTextContent());
	}
	
	public int getAreaSize()
	{
		return online ? Integer.parseInt(doc.getElementsByTagName("size").item(0).getTextContent()): Integer.parseInt(locdoc.getElementsByTagName("size").item(0).getTextContent());
	}
	
	public int getBaseWin()
	{
		return online ? Integer.parseInt(doc.getElementsByTagName("baseWin").item(0).getTextContent()): Integer.parseInt(locdoc.getElementsByTagName("baseWin").item(0).getTextContent());
	}
	public int getExtraWin()
	{
		return online ? Integer.parseInt(doc.getElementsByTagName("extraWin").item(0).getTextContent()): Integer.parseInt(locdoc.getElementsByTagName("extraWin").item(0).getTextContent());
	}
	public int getCountWin()
	{
		return online ? Integer.parseInt(doc.getElementsByTagName("countWin").item(0).getTextContent()): Integer.parseInt(locdoc.getElementsByTagName("countWin").item(0).getTextContent());
	}
	
	
	public boolean getFullScreen()
	{
		return locdoc.getElementsByTagName("fullscreen").item(0).getTextContent().equals("0") ? false : true; 
	}

	
	/// PRIVATE ///
	private void Save()
	{
		try {
			  //write the content into xml file
			  TransformerFactory transformerFactory = TransformerFactory.newInstance();
			  Transformer transformer = transformerFactory.newTransformer();
			  DOMSource source = new DOMSource(locdoc);
			  StreamResult result =  new StreamResult(new File("settings.xml"));
			  transformer.transform(source, result);

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void SetUserName(String username)
	{
		this.username = username;
	}
	
	public String GetUserName()
	{
		return username;
	}
	
	
	public Object[][] GetStats()
	{
		NodeList nl = online ? doc.getElementsByTagName("record") : locdoc.getElementsByTagName("record");
		if(nl.getLength()==0)
			return null;
		Object[][] ret = new Object[nl.getLength()][2];
		for(int i = 0; i<nl.getLength(); i++)
		{
			Node nd = nl.item(i);
			ret[i][0] = nd.getFirstChild().getTextContent();
			ret[i][1] = Integer.parseInt(nd.getFirstChild().getNextSibling().getTextContent());
		}

		return ret;
	}
	
	public void SaveStats(Object[][] stats)
	{
		if(online)
			return;
		Node nd = locdoc.getElementsByTagName("stats").item(0);
		//ApplicationData data = doc.getApplicationData();
		//Node st = data.getStats().getDomNode();
		nd.getParentNode().removeChild(nd);
		Node root = locdoc.getElementsByTagName("applicationData").item(0);
		Node st = root.appendChild(locdoc.createElement("stats"));
		
		//Stats sts = data.addNewStats();
		for(int i=0; i<stats.length; i++)
		{
			//Record rec = sts.addNewRecord();
			Node rec = st.appendChild(locdoc.createElement("record"));
			rec.appendChild(locdoc.createElement("name")).setTextContent((String)stats[i][1]);
			rec.appendChild(locdoc.createElement("score")).setTextContent(Integer.toString((Integer)stats[i][2]));
		}
		Save();
	}
	
	public int GetMaxRows()
	{
		return online ? Integer.parseInt(doc.getElementsByTagName("statmaxRows").item(0).getTextContent()) : Integer.parseInt(locdoc.getElementsByTagName("statmaxRows").item(0).getTextContent());
	}

	
	public boolean IsOnline()
	{
		return online;
	}
	
	private static Document locdoc = null;
	private static Document doc = null;
	private final String datafilename = "settings.xml";
	private String username = null;
	private static boolean online;
	private String version = "1.1";
}
