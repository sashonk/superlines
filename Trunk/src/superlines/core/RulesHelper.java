package superlines.core;

import java.util.LinkedList;
import java.util.List;

public class RulesHelper {
	public static int countWin(final SuperlinesContext ctx, final SuperlinesBall b, final List<SuperlinesBall> winBalls){
		int totalScore = 0;
		
		SuperlinesTable t = ctx.getTable();
		SuperlinesRules r = ctx.getRules();
		
		winBalls.addAll(collect(t,b, 0, 1));
		winBalls.addAll(collect(t,b , -1,1));
		winBalls.addAll(collect(t,b, -1, 0));
		winBalls.addAll(collect(t,b, -1, -1));
		
		if(winBalls.size()>=r.getMinWinBalls()){
			totalScore +=r.getNormalAward();
			totalScore += r.getExtraAward()* (winBalls.size()-r.getMinWinBalls());
		}
								
		return totalScore;
	}
	
	
	private static List<SuperlinesBall> collect(final SuperlinesTable table, final SuperlinesBall ball, int incx, int incy){
		
		List<SuperlinesBall> balls = new LinkedList<SuperlinesBall>();
		int targetColor=ball.getColor();
		
		for(int i = 0; i<2; i++){
			int x = ball.getX();
			int y = ball.getY();
			while(true){
				x += incx;
				y += incy;
				
				if(x<0 || x>=table.getSize() || y<0 || y>=table.getSize()){
					break;
				}
				
				int c = table.getBall(x, y).getColor();
				if(c==targetColor){
					balls.add(table.getBall(x, y));
				}
			}
		
			incx*=-1;
			incy*=-1;
		}
		
		
		return balls;
	}
	

}
