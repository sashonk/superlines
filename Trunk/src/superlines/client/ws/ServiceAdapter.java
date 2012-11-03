package superlines.client.ws;

import java.net.URL;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import superlines.core.Authentication;
import superlines.core.Configuration;
import superlines.core.SuperlinesContext;
import superlines.core.SuperlinesRules;
import superlines.core.User;
import superlines.ws.Response;
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
	
	public static void main(String[] argc){
		System.setProperty("config.file.path", "config/client/boot.properties");
		
		ServiceAdapter sa = get();
		
		Authentication a = new Authentication();
		a.setLogin("asd");
		//a.setPassword("pppp");
		//UserResponse r = sa.getService().getUser(a);
		//System.out.println(r.getUser().getContext());
	}
}
