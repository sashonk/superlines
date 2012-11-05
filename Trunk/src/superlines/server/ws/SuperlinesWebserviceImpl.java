package superlines.server.ws;

import superlines.Util;
import superlines.ws.UserResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;


import javax.annotation.Resource;
import javax.annotation.Resource.AuthenticationType;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.ws.WebServiceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import superlines.core.Authentication;
import superlines.core.SuperlinesContext;
import superlines.core.SuperlinesRules;

import superlines.core.User;

import superlines.ws.Message;
import superlines.ws.Messages;
import superlines.ws.Response;
import superlines.ws.ScoreData;
import superlines.ws.ScoreParameters;
import superlines.ws.ScoreResponse;
import superlines.ws.SuperlinesContextResponse;
import superlines.ws.SuperlinesWebservice;

@WebService(endpointInterface="superlines.ws.SuperlinesWebservice")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuperlinesWebserviceImpl implements SuperlinesWebservice{
	
	private final static Log log = LogFactory.getLog(SuperlinesWebserviceImpl.class);

	//@Resource(name="jdbc/mysql")
 	private DataSource m_dataSource;
	
	public SuperlinesWebserviceImpl(){
		  try {
				Context ctx = new InitialContext();
				m_dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
				
			  } catch (NamingException e) {
				  log.error(e);
			 }
	}
	
	@Override
	@WebMethod
	public UserResponse getUser(@WebParam Authentication ctx) {
		UserResponse r = new UserResponse();		
		try{
			

			
		Connection c = m_dataSource.getConnection();
			
		User user = new User();
		user.setAuth(ctx);
		user.setUsername("SUPER USER");
		r.setUser(user);
		
		}
		catch(Exception ex){
                    Message m = new Message();
                    m.setText(Messages.GENERIC_ERROR);
                    m.setDetails(Util.toString(ex));
                    r.setMessage(m);
                    
		}
		
		return r;
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
           rules.setAllowLeap(false);
           
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
           m.setDetails(Util.toString(ex));
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

	@Override
	@WebMethod
	public ScoreResponse getScore(@WebParam Authentication auth,
			@WebParam ScoreParameters params) {
		ScoreResponse response = new ScoreResponse();
		try{
			
			for(int i = 0; i<5; i++){
				ScoreData data = new ScoreData();
				data.setName("some user");
				data.setScore(1555+i*10);
				response.getData().add(data);
			}
			
			
		}
		catch(Exception ex){
			Message m = new Message();
			m.setText("error getting score");
			m.setDetails(Util.toString(ex));
			response.setMessage(m);
			return response;
		}
		
		
		return response;
	}


}
