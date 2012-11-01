package superlines.server.ws;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseResponse {

	public List<String> getMessages(){
		return messages;
	}
	
	@XmlElement
	private List<String > messages = new LinkedList<String>();
}
