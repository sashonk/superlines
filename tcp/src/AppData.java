

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URL;


import javax.swing.JOptionPane;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.*;
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
					
			DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			 doc = db.parse(new File(datafilename));
			 			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);		}		
		
	}
	
	public static AppData getInstance()
	{
		if(Instance==null)
		{
			Instance = new AppData();
		}
		return Instance;
	}
	
	
	/// PRIVATE ///
	private void Save()
	{
		try {
			  TransformerFactory transformerFactory = TransformerFactory.newInstance();
			  Transformer transformer = transformerFactory.newTransformer();
			  DOMSource source = new DOMSource(doc);
			  StreamResult result =  new StreamResult(new File("settings.xml"));
			  transformer.transform(source, result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	
	public Object[][] GetStats()
	{
		NodeList nl = doc.getElementsByTagName("record");
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
		Node nd = doc.getElementsByTagName("stats").item(0);
		//ApplicationData data = doc.getApplicationData();
		//Node st = data.getStats().getDomNode();
		nd.getParentNode().removeChild(nd);
		Node root = doc.getElementsByTagName("applicationData").item(0);
		Node st = root.appendChild(doc.createElement("stats"));
		
		//Stats sts = data.addNewStats();
		for(int i=0; i<stats.length; i++)
		{
			//Record rec = sts.addNewRecord();
			Node rec = st.appendChild(doc.createElement("record"));
			rec.appendChild(doc.createElement("name")).setTextContent((String)stats[i][1]);
			rec.appendChild(doc.createElement("score")).setTextContent(Integer.toString((Integer)stats[i][2]));
		}
		Save();
	}
	
	public int GetMaxRows()
	{
		return Integer.parseInt(doc.getElementsByTagName("statmaxRows").item(0).getTextContent());
	}
	
	private Document doc = null;
	private final String datafilename = "settings.xml";
	private String username = null;
	private static URL uri = null;
}
