package superlines.ws;

import javax.xml.bind.annotation.XmlElement;

import superlines.core.SuperlinesContext;

public class SuperlinesContextResponse extends BaseResponse{

	public void setContextBytes(final byte[] value){
		m_ctxBytes = value;
	}
	
	public byte[] getContextBytes(){
		return m_ctxBytes;
	}
	
	@XmlElement(name="context")
	private byte[] m_ctxBytes;
}
