package superlines.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ScoreData {

	public String getName(){
		return m_username;
	}
	
	public void setName(final String name){
		m_username = name;
	}
	
	public int getScore(){
		return m_score;
	}
	
	public void setScore(final int score){
		m_score= score;
	}
	
	
	
	@XmlElement(name="score")
	private int m_score;
	
	@XmlElement(name="username")
	private String m_username;
}
