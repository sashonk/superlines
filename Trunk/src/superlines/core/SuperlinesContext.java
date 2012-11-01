package superlines.core;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SuperlinesContext {
	
	
	public void registerListener(final SuperlinesListener l){
		m_listeners.add(l);
	}
	
	public List<SuperlinesListener> getListeners(){
		return m_listeners;
	}
		
	public void setRules(SuperlinesRules value){
		m_rules = value;
	}
	
	public SuperlinesRules getRules(){
		return m_rules;				
	}
	
	public SuperlinesTable getTable(){
		return m_table;
	}
	
	public int getScore(){
		return m_score;
	}
	
	public void setScore(int value){
		int oldval = m_score;
		m_score = value;		
		for(SuperlinesListener l : m_listeners){
			l.scoreChanged(m_score, oldval);
		}

	}
	
	public void setTable(final SuperlinesTable value){
		m_table= value;
	}
	
	@XmlElement(name="table",nillable=true)
	private SuperlinesTable m_table;
	
	@XmlElement(name="rules", nillable=true)
	private SuperlinesRules m_rules;
	
	@XmlElement(name="score")
	private int m_score;
	
	@XmlTransient
	private List<SuperlinesListener> m_listeners = new LinkedList<SuperlinesListener>();

}
