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
import superlines.core.User;
import superlines.ws.Response;
import superlines.ws.ScoreData;
import superlines.ws.ScoreParameters;
import superlines.ws.ScoreResponse;
import superlines.ws.SuperlinesContextResponse;
import superlines.ws.SuperlinesWebservice;
import superlines.ws.UserResponse;


public class ServiceAdapter {
	private static Log log = LogFactory.getLog(ServiceAdapter.class);
	private static ServiceAdapter instance;
	private SuperlinesWebservice webservice;
	
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
        
        public SuperlinesRules getRules(final Authentication auth){
            Response<SuperlinesRules> res = webservice.getRules(auth);
            if(res.getMessage()!=null){
                log.error(res.getMessage());                
            }
            
            return res.getValue();
        }
        
        public User getUser(final Authentication auth){
            UserResponse res = webservice.getUser(auth);
            if(res.getMessage()!=null){
                log.error(res.getMessage());                
            }
            
            return res.getUser();
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
