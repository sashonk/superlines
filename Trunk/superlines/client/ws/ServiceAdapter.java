package superlines.client.ws;

import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import superlines.Configuration;
import superlines.server.ws.SuperlinesWebservice;
import superlines.ws.artefacts.SuperlinesWebserviceImplService;

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
}
