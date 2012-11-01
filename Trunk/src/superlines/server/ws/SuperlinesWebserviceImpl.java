package superlines.server.ws;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import superlines.core.Authentication;
import superlines.core.SuperlinesContext;
import superlines.core.SuperlinesRules;
import superlines.core.SuperlinesTable;
import superlines.core.User;
import superlines.core.UserDetails;
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
		
		SuperlinesContext c = new SuperlinesContext();
		c.setScore(0);
		
		SuperlinesTable t = new SuperlinesTable(); 
		t.setWidth(10);
		c.setTable(t);
		//user.setContext(c);
		
		UserDetails d = new UserDetails();
		d.setMaxScore(1000);
		d.setRegDate(new Date(0));
		user.setDetails(d);
		
		SuperlinesRules rules = new SuperlinesRules();
		rules.setColorCount(6);
		rules.setCountSkew(true);
		rules.setCountFlat(true);
		rules.setExtraAward(50);
		rules.setMinWinBalls(5);
		rules.setNormatAward(100);
		rules.setScatterBallsCount(3);
		rules.setTableWidth(10);
		
		r.setUser(user);
		}
		catch(Exception ex){
			r.getMessages().add(toString(ex));
		}
		
		return r;
	}
	
	private static String toString(final Exception e){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		 e.printStackTrace(pw);
		 return sw.toString();
	}

}
