package superlines.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import superlines.client.ui.MainFrame;
import superlines.client.ws.ServiceAdapter;
import superlines.core.Authentication;
import superlines.core.Configuration;
import superlines.core.RulesHelper;
import superlines.core.SuperlinesBall;
import superlines.core.SuperlinesContext;
import superlines.core.SuperlinesListener;
import superlines.core.SuperlinesTable;

public class SuperlinesControllerImpl implements SuperlinesController {
	
	private  SuperlinesContext m_ctx;


	
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
            	List<SuperlinesBall> way = new LinkedList<>();
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
            List<SuperlinesBall> newBalls = RulesHelper.scatter(m_ctx);		
            List<SuperlinesBall> winBalls = new LinkedList<>();
            
            for(SuperlinesBall b : newBalls){
            	if(b.getColor()==0){
            		continue;
            	}
            	
            	int win = RulesHelper.countWin(m_ctx, b, winBalls);
                int score = m_ctx.getScore();
                if(win>0){
                    m_ctx.setScore(score+win);
                    for(SuperlinesBall ball : winBalls){
                        ball.setColor(0);
                    }
                }

            }
            
            
            
            m_ctx.getTable().checkFilled();      
	}

	

	public void start(){
		
		try{
        byte[] slctxBytes = ServiceAdapter.get().getSuperlinesContext(m_auth, false);
        if(slctxBytes==null){
        	log.error("failed to get superlines context!");
        	return;
        }	
        
        ByteArrayInputStream bais = new ByteArrayInputStream(slctxBytes);
        ObjectInputStream ois = new ObjectInputStream(bais);       
        m_ctx =  (SuperlinesContext) ois.readObject();
        
        if(m_ctx.getTable()==null){
        	SuperlinesTable t = new SuperlinesTable();
        	t.setWidth(m_ctx.getRules().getTableWidth());
        	t.setContext(m_ctx);
        	m_ctx.setTable(t);
        	
        	scatter();
           // RulesHelper.populateNextolors(m_ctx);   
        }
        
		}
		catch(Exception ex){
			log.error(ex);
		}

	}

	@Override
	public void restart(){
		
		try{
		int result = m_ctx.getTable().isFilled() ? JOptionPane.YES_OPTION : JOptionPane.showConfirmDialog(MainFrame.get(), Messages.SURE, "", JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION){
	        byte[] slctxBytes = ServiceAdapter.get().getSuperlinesContext(m_auth, true);
	        if(slctxBytes==null){
	        	log.error("failed to create superlines context!");
	        }
	        
	        ByteArrayInputStream bais = new ByteArrayInputStream(slctxBytes);
	        ObjectInputStream ois = new ObjectInputStream(bais);       
	        SuperlinesContext slctx =  (SuperlinesContext) ois.readObject();
	        
	        for(SuperlinesListener l : m_ctx.getListeners()){
	        	slctx.registerListener(l);
	        }
	                    
	        m_ctx = slctx;
	        
	        if(m_ctx.getTable()==null){
	        	SuperlinesTable t = new SuperlinesTable();
	        	t.setWidth(m_ctx.getRules().getTableWidth());
	        	t.setContext(m_ctx);
	        	m_ctx.setTable(t);

	        }

	        RulesHelper.populateNextolors(m_ctx);
	        m_ctx.touch();
	        
	        scatter();
	      //  RulesHelper.populateNextolors(m_ctx);		
		}

       
		}
		catch(Exception ex){
			log.error(ex);
		}

	}
	
	public void setAuth(final Authentication auth){
		m_auth = auth;
	}
	

	
	private Authentication m_auth;
	private static final Log log = LogFactory.getLog(SuperlinesControllerImpl.class);

}
