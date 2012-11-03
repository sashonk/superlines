package superlines.ws;

import javax.xml.bind.annotation.XmlElement;

import superlines.core.User;

public class UserResponse extends BaseResponse{

	public User getUser(){
		return user;
	}
	
	public void setUser(final User u){
		user = u;
	}
	
	@XmlElement(name="user")
	private User user;
}
