package superlines.core;

public class SuperlinesBall {
	
	public enum State{
		NORMAL,
		CLICKED;	
	}

	public int getColor(){
		return m_color;
	}
	
	public void setColor(int color){
		m_color = color;
	}
	
	public State getState(){
		return m_state;
	}
	
	public void setState(State s){
		m_state = s;
	}
	
	private State m_state;
	private int m_color;
	
}
