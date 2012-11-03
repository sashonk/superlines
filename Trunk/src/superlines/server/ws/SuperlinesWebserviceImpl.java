package superlines.server.ws;

import superlines.ws.UserResponse;
import java.io.PrintWriter;
import java.io.StringWriter;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import superlines.core.Authentication;
import superlines.core.SuperlinesContext;
import superlines.core.SuperlinesRules;

import superlines.core.User;

import superlines.ws.Message;
import superlines.ws.Messages;
import superlines.ws.Response;
import superlines.ws.SuperlinesContextResponse;
import superlines.ws.SuperlinesWebservice;

@WebService(endpointInterface="superlines.ws.SuperlinesWebservice")
public class SuperlinesWebserviceImpl implements SuperlinesWebservice{

	@Override
	@WebMethod
	public UserResponse getUser(@WebParam Authentication ctx) {
		UserResponse r = new UserResponse();		
		try{
		User user = new User();
		user.setAuth(ctx);
		user.setUsername("SUPER USER");
		r.setUser(user);
		
		}
		catch(Exception ex){
                    Message m = new Message();
                    m.setText(Messages.GENERIC_ERROR);
                    m.setDetails(toString(ex));
                    r.setMessage(m);
                    
		}
		
		return r;
	}
	
	private static String toString(final Exception e){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		 e.printStackTrace(pw);
		 return sw.toString();
	}

    @Override
    public SuperlinesContextResponse createSuperlinesContext(Authentication auth) {
    	SuperlinesContextResponse response = new SuperlinesContextResponse();
       try{
       boolean authorized = authorize(auth);

       if(authorized){
           SuperlinesContext ctx = new SuperlinesContext();
           
           SuperlinesRules rules = new SuperlinesRules();
           rules.setColorCount(6);
           rules.setCountFlat(true);
           rules.setCountSkew(true);
           rules.setExtraAward(50);
           rules.setMinWinBalls(5);
           rules.setNormalAward(100);
           rules.setScatterBallsCount(5);
           rules.setTableWidth(9);
           rules.setShowTip(true);
           ctx.setRules(rules);
           ctx.setScore(0);
           
           response.setContext(ctx);           
       }
       else{
           Message m = new Message();
           m.setText(Messages.AUTH_FAILED);
           response.setMessage(m);
           return response;
       }
       
       }
       catch(Exception ex){
           Message m = new Message();
           m.setText("failed to create context");
           m.setDetails(toString(ex));
           response.setMessage(m);
       }
       
       return response;
    }
    
    @Override
    public Response<SuperlinesRules> getRules(Authentication auth){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private boolean authorize(final Authentication auth){
        return true;
    }
}
