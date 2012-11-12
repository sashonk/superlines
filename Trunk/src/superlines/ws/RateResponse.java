package superlines.ws;

import javax.xml.bind.annotation.XmlElement;

public class RateResponse extends BaseResponse{

	
	public int getRate(){
		return m_currentRate;
	}
	
	public void setRate(final int rate){
		m_currentRate = rate;
	}
	
	@XmlElement(name="rate")
	private int m_currentRate;
}
