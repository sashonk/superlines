package superlines.server.ws;

import superlines.Util;

import superlines.server.PromotionHelper;
import superlines.server.RateDAO;
import superlines.server.RulesHelper;
import superlines.server.mail.MailHelper;
import superlines.ws.ProfileResponse;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import superlines.core.Authentication;
import superlines.core.Localizer;
import superlines.core.Rank;
import superlines.core.SuperlinesContext;
import superlines.core.SuperlinesRules;

import superlines.core.Profile;

import superlines.ws.BaseResponse;
import superlines.ws.Message;
import superlines.ws.PromotionResponse;
import superlines.ws.Response;

import superlines.ws.RateParameters;
import superlines.ws.RateResponse;
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
		 
		st = c.prepareStatement("select p.id, p.name as name, p.rankid as rankid, p.crts as crts, (select sum(s.score) from scoredata as s where s.userid = p.id and s.rankid = p.rankid) as rate from profiles as p where accountid = ?");
		st.setString(1, ctx.getLogin());
		rs = st.executeQuery();
		
		if(rs.next()){
			Profile profile = new Profile();
			profile.setAuth(ctx);
			profile.setUsername(rs.getString("name"));
			profile.setRank(Rank.getRank(rs.getInt("rankid")));
			profile.setCreateDate(rs.getTimestamp("crts"));		
			profile.setRate(rs.getInt("rate"));
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
		
		List<String> recipients = new LinkedList<>();
		recipients.add("sashonk@yandex.ru");
		MailHelper.mail("Потверждение", "You were successfully authenticated да", recipients);
		
		return r;
	}
	
	
	
	@Override
	@WebMethod
	public PromotionResponse getPromotionMessage(
			final Authentication auth,
			final Rank rank, final String locale) {
		
		PromotionResponse response = new PromotionResponse();
		try{
			String message = PromotionHelper.getPromotionMessage(rank, locale);
			response.setPromotionMessage(message);
			
		}
		catch(Exception x){
			Message m = new Message();
			m.setText("get promotion msg failed");
			m.setDetails(Util.toString(x));
			response.setMessage(m);
		}
		
		return response;
		
	}
	


    @Override
    public SuperlinesContextResponse createSuperlinesContext(Authentication auth) {
    	SuperlinesContextResponse response = new SuperlinesContextResponse();
       try{
    	   ProfileResponse p = getProfile(auth);
    	   Profile profile = p.getProfile();
    	   
    	   SuperlinesRules rules =  RulesHelper.createRules(profile.getRank());
    	   
           SuperlinesContext ctx = new SuperlinesContext();
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
	public BaseResponse acceptResult(final Authentication auth,
			final int score) {
		BaseResponse response = new BaseResponse();
		
		Connection c = null;
		PreparedStatement st= null;
		

		
		try{
			auth(auth);
				
				c = m_dataSource.getConnection();
				
				
				ResultSet rs;
				c.setAutoCommit(false);				
				st =c.prepareStatement("select id, rankid from profiles where accountid = ?");
				st.setString(1, auth.getLogin());
				rs = st.executeQuery();
				
				int id, rank;
				if(rs.next()){
					id = rs.getInt("id");
					rank = rs.getInt("rankid");
				}
				else{
					throw new Exception(String.format("profile not found for %s", auth.getLogin()));
				}
				st.close();
				
				int acceptableScore = RulesHelper.getAcceptableResult(Rank.getRank(rank));
				if(acceptableScore>score){
					Message m = new Message();
					m.setText(String.format("%s %d", Localizer.getLocalizedString(superlines.server.Messages.NOT_ENOUGH_SCORE, auth.getLocale()), acceptableScore));
					response.setMessage(m);
					return response;
				}
				
				
				st = c.prepareStatement("insert into scoredata (userid ,score, crts, rankid) values (?, ?, CURRENT_TIMESTAMP, ? )");
				st.setInt(1, id);
				st.setInt(2, score);
				st.setInt(3, rank);
				st.executeUpdate();
				st.close();
				
				
				st = c.prepareStatement("select sum(score) as total from scoredata where userid = ? and rankid = ?");
				st.setInt(1, id);
				st.setInt(2, rank);
				rs = st.executeQuery();
				rs.next();			
				int total = rs.getInt("total");
				st.close();
				
				Rank nextRank = Rank.getRank(rank).getNext();
				int targetRate = PromotionHelper.getQualifiedRate(nextRank);
				if(targetRate <= total ){
					st = c.prepareStatement("update profiles set rankid = ? where id = ?");
					st.setInt(1, nextRank.getRank());
					st.setInt(2, id);
					st.executeUpdate();				
				}

				st.close();
				c.commit();
		}
		catch(Exception ex){
			try{
				c.rollback();
			}
			catch(Exception e){
				log.error(e);
			}
			
			Message m = new Message();
			m.setText("accept result failed");
			m.setDetails(Util.toString(ex));
			response.setMessage(m);
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


	@Override
	@WebMethod
	public RateResponse getRateData(@WebParam Authentication auth,
			@WebParam RateParameters params) {
		RateResponse response = new RateResponse();
		try{
			auth(auth);
			response.getData().addAll(RateDAO.get().getRateData(params)) ;
					
		}
		catch(Exception ex){
			Message m = new Message();
			m.setText("error getting rate data");
			m.setDetails(Util.toString(ex));
			response.setMessage(m);
			return response;
		}

		return response;
	}
	
	private void auth(final Authentication auth) throws Exception{
		
		log.error("try auth!");
		
		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try{
			
			c = m_dataSource.getConnection();
			st = c.prepareStatement("select count(0) count from users u, user_roles r where u.user_name = r.user_name and u.user_name = ? and u.user_password = ?  and r.role_name = 'user'");
			st.setString(1, auth.getLogin());
			st.setString(2, auth.getPassword());
			 rs = st.executeQuery();
			
			int count = 0;
			if(rs.next()){
				count = rs.getInt("count");
			}
			 
			if(count != 1){
				throw new Exception("authentication failed");
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
