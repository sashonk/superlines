package superlines.core;

public class SuperlinesContext {

	
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
		m_score = value;
	}
	
	private SuperlinesTable m_table;
	private SuperlinesRules m_rules;
	private int m_score;
	

}
