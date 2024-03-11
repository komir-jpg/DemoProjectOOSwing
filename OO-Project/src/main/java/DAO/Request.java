package DAO;

public class Request {

	int idRequest;
	User user;
	String requestState;
	Group groupRequesting;
	
	public Request() {	
	}
	public Request(User user,Group group) {
		this.user = user;
		groupRequesting = group;
		requestState = "attesa";
	}
	public int getIdRequest() {
		return idRequest;
	}
	public void setIdRequest(int idRequest) {
		this.idRequest = idRequest;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getRequestState() {
		return requestState;
	}
	public void setRequestState(String requestState) {
		this.requestState = requestState;
	}
	public Group getGroupRequesting() {
		return groupRequesting;
	}
	public void setGroupRequesting(Group groupRequesting) {
		this.groupRequesting = groupRequesting;
	}

}
