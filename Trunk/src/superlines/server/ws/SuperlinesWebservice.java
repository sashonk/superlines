package superlines.server.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface SuperlinesWebservice {
	
	@WebMethod
	public boolean authorize(@WebParam final InvocationContext ctx);
	
	
	
}
