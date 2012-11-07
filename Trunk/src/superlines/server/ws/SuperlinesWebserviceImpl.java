package superlines.server.ws;

import superlines.Util;
import superlines.ws.ProfileResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


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
import superlines.core.Messages;
import superlines.core.SuperlinesContext;
import superlines.core.SuperlinesRules;

import superlines.core.Profile;

import superlines.ws.Message;
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
	public ProfileResponse getProfile(@WebParam Authentication ctx) {
		ProfileResponse r = new ProfileResponse();	
		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			
		auth(ctx);

		 c = m_dataSource.getConnection();
		 
		st = c.prepareStatement("select name, crts, rankid from profiles where accountid = ?");
		st.setString(1, ctx.getLogin());
		rs = st.executeQuery();
		
		if(rs.next()){
			Profile profile = new Profile();
			profile.setAuth(ctx);
			profile.setUsername(rs.getString("name"));
			profile.setRank(rs.getInt("rankid"));
			profile.setCreateDate(rs.getTimestamp("crts"));			
			r.setProfile(profile);
		}
		else{
			throw new Exception("profile not found");
		}
			
		
		
		
		}
		catch(Exception ex){
                    Message m = new Message();
                    m.setText("generic error");
                    m.setDetails(Util.toString(ex));
                    r.setMessage(m);
                    
		}
		finally{

			try{
				if(st!=null){
					st.close();
				}
				if(c!=null){
					c.close();
				}
			}catch(Exception ex){
				log.error(ex);
			}
				
		}
		
		return r;
	}
	


    @Override
    public SuperlinesContextResponse createSuperlinesContext(Authentication auth) {
    	SuperlinesContextResponse response = new SuperlinesContextResponse();
       try{
    	   auth(auth);


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



	@Override
	@WebMethod
	public ScoreResponse getScore(@WebParam Authentication auth,
			@WebParam ScoreParameters params) {
		ScoreResponse response = new ScoreResponse();
			
		Connection c = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			auth(auth);

			c = m_dataSource.getConnection();
			st = c.createStatement();
			rs = st.executeQuery("select s.score as score, (select p.name from profiles as p) as name from scoredata as s order by s.score desc limit 20;");
			while(rs.next()){
				ScoreData  data = new ScoreData();
				data.setName(rs.getString("name"));
				data.setScore(rs.getInt("score"));
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
		finally{
				try{
					if(st!=null){
						st.close();
					}
					if(c!=null){
						c.close();
					}
				}catch(Exception ex){
					log.error(ex);
				
				}
		}
	
		
		
		return response;
	}
	
	private void auth(final Authentication auth) throws Exception{
		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			
			c = m_dataSource.getConnection();
			st = c.prepareStatement("select count(0) as count from users where user_name = ? and user_password = ?");
			st.setString(1, auth.getLogin());
			st.setString(2, auth.getPassword());
			 rs = st.executeQuery();
			
			int count = 0;
			if(rs.next()){
				count = rs.getInt("count");
			}
			 
			if(count != 1){
				throw new Exception("not authorized");
			}
		}

		finally{
				try{
					if(st!=null){
						st.close();
					}
					if(c!=null){
						c.close();
					}
				}catch(Exception ex){
					log.error(ex);
				
				}
		}		
	}


}
