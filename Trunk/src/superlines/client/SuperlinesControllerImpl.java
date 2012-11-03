package superlines.client;

import java.util.LinkedList;
import java.util.List;
import superlines.client.ws.ServiceAdapter;
import superlines.core.RulesHelper;
import superlines.core.SuperlinesBall;
import superlines.core.SuperlinesContext;
import superlines.core.SuperlinesTable;

public class SuperlinesControllerImpl implements SuperlinesController {
	
	private  SuperlinesContext m_ctx;

	public SuperlinesControllerImpl() throws Exception{
            Context ctx = Context.get();            
            SuperlinesContext slctx = ServiceAdapter.get().createSuperlinesContext(ctx.getAuth());
            if(slctx==null){
                throw new Exception("failed to create superlines context!");
            }
                        
            m_ctx = slctx;
            ctx.setSuperlinesContext(slctx);
            
            if(m_ctx.getTable()==null){
            	SuperlinesTable t = new SuperlinesTable();
            	t.setWidth(m_ctx.getRules().getTableWidth());
            	m_ctx.setTable(t);

            	
            	t.setContext(m_ctx);
            	
            	
            }
	}
	
        public SuperlinesContext getContext(){
            return m_ctx;
        }

	
	@Override
	public void spotClicked(int x, int y) {
		SuperlinesBall targetBall = m_ctx.getTable().getBalls()[x][y];
		SuperlinesBall clickedBall = m_ctx.getTable().getClickedBall();
		
		if(targetBall.getColor()==0){
			if(clickedBall==null){
				return;
			}
			

        	if(!m_ctx.getRules().isAllowLeap()){
            	List<SuperlinesBall> way = new LinkedList<SuperlinesBall>();
            	boolean checked = RulesHelper.checkWay(m_ctx, clickedBall, targetBall, way);
            	
            	if(!checked){
            		return;
            	}
        	}

			targetBall.setColor(clickedBall.getColor());
			clickedBall.setColor(0);
			m_ctx.getTable().setClickedBall(null);
			
                        List<SuperlinesBall> winBalls = new LinkedList<>();
                        int win = RulesHelper.countWin(m_ctx, targetBall, winBalls);

                        int score = m_ctx.getScore();
                        if(win>0){
                            m_ctx.setScore(score+win);
                            for(SuperlinesBall ball : winBalls){
                                ball.setColor(0);
                            }
                        }
                        else{
                            scatter();
                        }
		}
        else{
        	
        	m_ctx.getTable().setClickedBall(targetBall);
        	

        }
		
	}

	@Override
	public void scatter() {
            RulesHelper.scatter(m_ctx);		
	}

	@Override
	public void restart() {
		//ServiceAdapter.get().getService().createContext(null)
		
	}

}
