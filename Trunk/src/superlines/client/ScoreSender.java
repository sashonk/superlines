package superlines.client;

import javax.swing.JOptionPane;

import superlines.client.ui.MainFrame;
import superlines.client.ws.ServiceAdapter;
import superlines.core.Authentication;
import superlines.ws.BaseResponse;

public class ScoreSender extends SuperlinesAdapter{
	
	private Authentication m_auth;
	
	public void setAuth(final Authentication auth){
		m_auth = auth;
	}
	
	@Override
	public void tableFilled(final int score) {
		ServiceAdapter sa = ServiceAdapter.get();		
		BaseResponse response = sa.getService().acceptResult(m_auth, score);
		if(response.getMessage()!=null){
			JOptionPane.showMessageDialog(MainFrame.get(), response.getMessage().getText());
		}
	
		
		
	}
}
