package superlines.client;

import java.util.LinkedList;
import java.util.List;

import superlines.ws.ScoreData;

public class ScorePanelModel {
	
	public void registerListener(final ScoreListener listener){
		m_listeners.add(listener);
	}

	public List<ScoreData> getData(){
		return m_data;				
	}
	
	public void setData(final List<ScoreData> data){
		m_data = data;
		for(ScoreListener listener : m_listeners){
			listener.updateData(m_data);
		}
	}
	
	private List<ScoreData> m_data;
	private List<ScoreListener> m_listeners = new LinkedList<ScoreListener>();
}
