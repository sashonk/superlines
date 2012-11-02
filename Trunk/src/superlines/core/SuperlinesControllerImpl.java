package superlines.core;

public class SuperlinesControllerImpl implements SuperlinesController {
	
	private final SuperlinesContext m_ctx;

	public SuperlinesControllerImpl(final SuperlinesContext ctx){
		m_ctx = ctx;
	}
	

	
	@Override
	public void spotClicked(int x, int y) {
		SuperlinesBall targetBall = m_ctx.getTable().getBalls()[x][y];
		SuperlinesBall clickedBall = m_ctx.getTable().getClickedBall();
		
		if(targetBall.getColor()==0){
			if(clickedBall==null){
				return;
			}
			
			targetBall.setColor(clickedBall.getColor());
			clickedBall.setColor(0);
			m_ctx.getTable().setClickedBall(null);
			
			//if(hasWin(m_ctx, targetBall)){
				
			//}
		}
		
	}

	@Override
	public void scatter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

}
