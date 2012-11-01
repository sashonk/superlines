package superlines.core;


public class SuperlinesRules {
	
	public int getExtraAward(){
		return m_extraAward;
	}
	
	public void setExtraAward(int value){
		m_extraAward = value;
	}
	
	public int getNormalAward(){
		return m_normalAward;
	}
	
	public void setNormatAward(int value){
		m_normalAward= value;
	}
	
	public int getColorCount(){
		return m_colorCount;
	}
	
	public void setColorCount(int value){
		m_colorCount = value;
	}
	
	public int getTableWidth(){
		return m_width;
	}
	
	public void setTableWidth(int value){
		m_width = value;
	}
	
	public int getMinWinBalls(){
		return m_minWinBalls;
	}
	
	public void setMinWinBalls(int value){
		m_minWinBalls = value;
	}
	
	public int getScatterBallsCount(){
		return m_scatterBallsCount;
	}
	
	public void setScatterBallsCount(int value){
		m_scatterBallsCount = value;
	}
	
	private int m_scatterBallsCount;
	private int m_minWinBalls;
	private int m_extraAward;
	private int m_normalAward;
	private int m_colorCount;
	private int m_width;
}
