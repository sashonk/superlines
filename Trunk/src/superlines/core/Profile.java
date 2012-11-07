package superlines.core;


import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="User")
public class Profile {

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
	
	public int getRank(){
		return m_rankid;
	}
	
	public void setRank(final int rank){
		m_rankid = rank;
	}

	public Date getCreateDate(){
		return m_crts;		
	}
	
	public void setCreateDate(final Date date){
		m_crts = date;
	}
	
	@XmlElement(name="auth", nillable=true)
	private Authentication m_auth;
	
	@XmlElement(name="username")
	private String m_username;
	
	@XmlElement(name="rankid")
	private int m_rankid;
	
	@XmlAttribute(name="crts")
	private Date m_crts;
	
}
