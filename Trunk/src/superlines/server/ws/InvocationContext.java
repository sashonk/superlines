package superlines.server.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class InvocationContext {

	public String getLogin(){
		return m_login;
	}
	
	public void setLogin(String value){
		m_login = value;
	}
	
	public String getPassword(){
		return m_password;
	}
	
	public void setPassword(String value){
		m_password =value;
	}
	
	
	@XmlElement(name="login")
	private String m_login;
	
	@XmlElement(name="password")
	private String m_password;
}
