package superlines.core;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDetails {

	public int getMaxScore(){
		return m_maxScore;
	}
	
	public void setMaxScore(int val){
		m_maxScore = val;
	}
	
	public Date getRegDate(){
		return m_regDate;
	}
	
	public void setRegDate(final Date value){
		m_regDate = value;
	}
	
	@XmlElement(name="maxScore")
	private int m_maxScore;
	
	@XmlElement(name="regDate")
	private Date m_regDate;

}
