package superlines.client.ws;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import superlines.core.Authentication;
import superlines.core.Configuration;
import superlines.core.SuperlinesContext;
import superlines.core.SuperlinesRules;
import superlines.core.Profile;
import superlines.ws.Response;
import superlines.ws.ScoreData;
import superlines.ws.ScoreParameters;
import superlines.ws.ScoreResponse;
import superlines.ws.SuperlinesContextResponse;
import superlines.ws.SuperlinesWebservice;
import superlines.ws.ProfileResponse;


/**
 * adapter for superlines web service. 
 * 
 * @author Sashonk
 *
 */

public class ServiceAdapter {
	private static Log log = LogFactory.getLog(ServiceAdapter.class);
	private static ServiceAdapter instance;
	private SuperlinesWebservice webservice;
	
	/**
	 * 
	 * @return web service adapter or <b>null</b> if web service is unavailable
	 */
	public static ServiceAdapter get(){
		if(instance ==null){
			try{
				instance = new ServiceAdapter();
			}
			catch(Exception e){
				log.error("failed to create Invoker", e);
			}
		}
		
		return instance;
	}
	
	private ServiceAdapter() throws Exception{
		Configuration cfg = Configuration.get();
		String url = cfg.getProperty("service.url");
		
		SuperlinesWebserviceImplService service = new SuperlinesWebserviceImplService(new URL(url));
		webservice = service.getPort(SuperlinesWebservice.class);		
	}
	
	public SuperlinesWebservice getService(){
		return webservice;
	}

	
	public Profile getProfile(final Authentication auth){
		ProfileResponse res = webservice.getProfile(auth);
		if(res.getMessage()!=null){
			log.error(res.getMessage());
		}
		
		return res.getProfile();
	}
        
        public SuperlinesRules getRules(final Authentication auth){
            Response<SuperlinesRules> res = webservice.getRules(auth);
            if(res.getMessage()!=null){
                log.error(res.getMessage());                
            }
            
            return res.getValue();
        }
        
        public Profile getUser(final Authentication auth){
            ProfileResponse res = webservice.getProfile(auth);
            if(res.getMessage()!=null){
                log.error(res.getMessage());                
            }
            
            return res.getProfile();
        }
        
        public SuperlinesContext createSuperlinesContext(final Authentication auth){
        	SuperlinesContextResponse res = webservice.createSuperlinesContext(auth);
            if(res.getMessage()!=null){
                log.error(res.getMessage());                
            }
            
            return res.getContext();          
        }
        
        public List<ScoreData> getScore(final Authentication auth, final ScoreParameters params){
        	ScoreResponse res = webservice.getScore(auth, params);
            if(res.getMessage()!=null){
                log.error(res.getMessage());                
            }
            
            return res.getData();     	
        }
	
	public static void main(String[] argc) throws Exception{
			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/superlines", "root", "8926sas");
			
			Statement st = c.createStatement();
			ResultSet dataSet = st.executeQuery("select * from roles");
			while(dataSet.next()){
				System.out.println(dataSet.getString("role_name"));
			}
	}
}
