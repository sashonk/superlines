package superlines.core;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Authentication {
	
	public void setLogin(final String value){
		m_login = value;
	}
	
	public String getLogin(){
		return m_login;				
	}
	
	public void setPassword(final String value){
		m_password = value;
	}
	
	public String getPassword(){
		return m_password;
	}
	
	@XmlElement(name="password")
	private String m_password;
	
	@XmlElement(name="login")
	private String m_login;
}
