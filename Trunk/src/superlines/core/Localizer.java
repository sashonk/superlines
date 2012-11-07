package superlines.core;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Localizer {
	
	private static final Log log = LogFactory.getLog(Localizer.class);
	private static Properties m_strings;
	
	static{
		Configuration cfg =  Configuration.get();
		File folder = cfg.getDataFolder();
		File locFile = new File(folder, String.format("localization-%s.properties",cfg.getProperty("locale")));
		
		FileInputStream ifstr = null;
		try{
			m_strings = new Properties();
			 ifstr = new FileInputStream(locFile);		
			m_strings.load(ifstr);
		}
		catch(Exception ex){
			log.error(ex);
		}
		finally{
			if(ifstr!=null){
				try{
				ifstr.close();
				}
				catch(Exception t){
					log.error(t);
				}
			}
		}
	}
	
	
	public static String getLocalizedString(final Object key){
		return m_strings.getProperty(key.toString());
	}
}
