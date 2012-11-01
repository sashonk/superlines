package superlines.server.ws;

import superlines.core.User;

public class UserResponse extends BaseResponse{

	public User getUser(){
		return user;
	}
	
	public void setUser(final User u){
		user = u;
	}
	
	private User user;
}
