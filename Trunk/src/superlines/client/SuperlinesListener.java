package superlines.client;

import java.util.List;

import superlines.core.SuperlinesBall;
import superlines.core.SuperlinesBall.State;

public interface SuperlinesListener {
	

	
	public void scoreChanged(int newScore, int oldScore);

	public void ballChangeColor(int x, int y, int newCol, int oldCol);
	
	public void ballChangeState(int x, int y, superlines.core.SuperlinesBall.State newState, superlines.core.SuperlinesBall.State oldState);
	
	public void clickedBallChanged(int newx, int newy, int oldx, int oldy);
	
	public void clickeBallSet(int x, int y);
	
	public void clickedBallUnset(int x, int y);
        
    public void tableFilled();
    
    public void nextColorsChanged(final List<Integer> data);
}
