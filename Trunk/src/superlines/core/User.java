package superlines.core;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

	public Authentication getAuth(){
		return m_auth;
	}
	
	public void setAuth(final Authentication value){
		m_auth = value;
	}

	
	public String getUsername(){
		return m_username;
	}
	
	public void setUsername(final String value){
		m_username = value;
	}
	

	
	public UserDetails getDetails(){
		return m_details;
	}
	
	public void setDetails(final UserDetails val){
		m_details = val;
	}
	
	public SuperlinesContext getContext(){
		return m_ctx;
	}
	
	public void  setContext(final SuperlinesContext value){
		m_ctx = value;
	}
	
	@XmlElement(name="auth", nillable=true)
	private Authentication m_auth;
	
	@XmlElement(name="username")
	private String m_username;
	
	@XmlElement(name="details")
	private UserDetails m_details;
	
	@XmlElement(name="context")
	private SuperlinesContext m_ctx;
	
}
