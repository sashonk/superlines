package superlines.core;

import java.io.File;
import java.io.FileInputStream;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Configuration {
	
	private final static Log log = LogFactory.getLog(Configuration.class); 
	private static Configuration instance;
	private Properties m_props;
	
	public static Configuration get(){
		if(instance == null){
			try{
				instance = new Configuration();
			}
			catch(Exception  ex){
				log.error("failed create configuration:", ex);
			}
		}
		
		return instance;
	}
	
	private Configuration() throws Exception{
		m_props = new Properties();
		FileInputStream ifstr = new FileInputStream(new File(System.getProperty("config.file.path")));
		
		m_props.load(ifstr);
		
		ifstr.close();
		
	}
	
	public String getProperty(String key){
		return m_props.getProperty(key);
	}

}
