package superlines.core;

public interface SuperlinesListener {
	
	public void scoreChanged(int newScore, int oldScore);

	public void ballChangeColor(int x, int y, int newCol, int oldCol);
	
	public void ballChangeState(int x, int y, superlines.core.SuperlinesBall.State newState, superlines.core.SuperlinesBall.State oldState);
	
	public void clickedBallChanged(int newx, int newy, int oldx, int oldy);
}
