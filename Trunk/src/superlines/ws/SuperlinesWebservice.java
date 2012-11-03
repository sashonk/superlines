package superlines.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import superlines.core.Authentication;
import superlines.core.SuperlinesContext;
import superlines.core.SuperlinesRules;
import superlines.core.User;


@WebService
public interface SuperlinesWebservice {
	
	@WebMethod
	public UserResponse getUser(@WebParam final Authentication auth);
	
	@WebMethod
        public SuperlinesContextResponse createSuperlinesContext(@WebParam final Authentication auth);
        
        @WebMethod
        public Response<SuperlinesRules> getRules(@WebParam final Authentication auth);
	
}
