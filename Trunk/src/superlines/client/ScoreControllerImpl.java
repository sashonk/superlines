package superlines.client;

import java.util.List;

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

}
