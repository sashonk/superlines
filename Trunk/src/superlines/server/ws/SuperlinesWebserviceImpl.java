package superlines.server.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(endpointInterface="superlines.server.ws.SuperlinesWebservice")
public class SuperlinesWebserviceImpl implements SuperlinesWebservice{

	@Override
	@WebMethod
	public boolean authorize(@WebParam InvocationContext ctx) {
		
		return true;
	}

}
