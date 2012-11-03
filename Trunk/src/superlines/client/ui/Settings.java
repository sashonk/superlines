package superlines.client.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Settings {
	
	private static final Log log= LogFactory.getLog(Settings.class);
	
	private static Settings instance;
	
	private Properties m_props;
	
	public static Settings get(){
		if(instance==null){
			
			try{
				instance = new Settings();
			}
			catch(Exception ex){
				log.error("failed get settings", ex);
			}
			
		}
		
		return instance;
	}
	
	
	private Settings() throws Exception{

			m_props = new Properties();
			FileInputStream ifstr = new FileInputStream(new File("settings/"));
			
			m_props.load(ifstr);
			
			ifstr.close();
			
		
	}

}
