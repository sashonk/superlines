package superlines.ws;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ScoreResponse extends BaseResponse{

	public List<ScoreData> getData(){
		return m_data;
	}
	
	@XmlElementWrapper(name="data", nillable=true)
	@XmlElement(name="scoreItem", nillable=true)
	private List<ScoreData> m_data = new LinkedList<ScoreData>();
}
