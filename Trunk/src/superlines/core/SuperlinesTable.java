package superlines.core;

public class SuperlinesTable {

	
	public SuperlinesTable(int width){
		m_width = width;
		balls = new SuperlinesBall[width][];
		for(int i = 0; i<width; i++){
			balls[i] = new SuperlinesBall[width];
		}
	}
	
	public SuperlinesBall[][] getBalls(){
		return balls;
	}
	
	public SuperlinesBall getBall(int x, int y){
		return balls[x][y];
	}
	
	public int getWidth(){
		return m_width;
	}
	
	public SuperlinesBall getClickedBall(){
		return m_clickedBall;
	}
	
	public void setClickedBall(final SuperlinesBall ball){
		m_clickedBall = ball;
	}
	
	private SuperlinesBall[][] balls;
	private int m_width;
	private SuperlinesBall m_clickedBall;
}
