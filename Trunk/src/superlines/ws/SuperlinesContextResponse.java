package superlines.ws;

import javax.xml.bind.annotation.XmlElement;

import superlines.core.SuperlinesContext;

public class SuperlinesContextResponse extends BaseResponse{

	public void setContext(final SuperlinesContext value){
		m_ctx = value;
	}
	
	public SuperlinesContext getContext(){
		return m_ctx;
	}
	
	@XmlElement(name="context")
	private SuperlinesContext m_ctx;
}
