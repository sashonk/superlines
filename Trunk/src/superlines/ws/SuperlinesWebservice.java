package superlines.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import superlines.core.Authentication;
import superlines.core.Rank;
import superlines.core.SuperlinesContext;
import superlines.core.SuperlinesRules;



@WebService
public interface SuperlinesWebservice {
	
	@WebMethod
	public ProfileResponse getProfile(@WebParam(name="auth") final Authentication auth);
	
	@WebMethod
        public SuperlinesContextResponse getSuperlinesContext(@WebParam(name="auth") final Authentication auth, @WebParam(name="createOnly") final boolean createOnly);
        
        @WebMethod
        public Response<SuperlinesRules> getRules(@WebParam(name="auth") final Authentication auth);
        
        @WebMethod
        public RateResponse getRateData(@WebParam(name="auth") final Authentication auth, @WebParam(name="params") final RateParameters params);
	
        @WebMethod
        public BaseResponse acceptResult(@WebParam(name="auth") final Authentication auth, @WebParam(name="score") final int score);
        
        @WebMethod
        public PromotionResponse getPromotionMessage(@WebParam(name="auth") final Authentication auth, @WebParam(name="rank") final  Rank rank, @WebParam(name="locale") final String locale);
        

        @WebMethod
        public BaseResponse persist(@WebParam(name="auth") final Authentication auth ,@WebParam(name="context") final byte[] ctxBytes);
        
    	@WebMethod
        public SuperlinesContextResponse loadSuperlinesContext(@WebParam(name="auth") final Authentication auth);
}
