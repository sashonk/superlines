package superlines.client;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.SwingUtilities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import superlines.client.ws.ServiceAdapter;
import superlines.ws.ScoreData;


public class ScoreControllerImpl implements ScoreController{
	private final static Log log = LogFactory.getLog(ScoreControllerImpl.class);
	private ScorePanelModel m_model;
	
	@Override
	public void setModel(final ScorePanelModel model){
		m_model = model;
	}
	
	@Override
	public void update() {
		Context ctx = Context.get();
		List<ScoreData> data = ServiceAdapter.get().getScore(ctx.getAuth(), null);
		if(data==null){
			log.error("failed get score data");
			return;
		}
			
		m_model.setData(data);		
	}
	
	public ScoreControllerImpl(){
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(true){
					try{
						Thread.sleep(5000);
						SwingUtilities.invokeAndWait(new Runnable() {
							
							@Override
							public void run() {
								ScoreControllerImpl.this.update();
								
							}
						});
					}
					catch(Exception ex){
						
					}

				}
				
			}
		});
		
		t.start();
	}

}
