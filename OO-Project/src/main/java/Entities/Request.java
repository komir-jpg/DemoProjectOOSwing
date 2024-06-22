package Entities;

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
	/**
	 * returns the string in this form:
	 * utente: [username]   data di iscrizione: [subscription date]   stato richiesta: [request state]
	 * 
	 */
	@Override
	public String toString() {
		return "utente: " + user.getUserName()+"   "+"data di iscrizione: "+ user.getSubcsriptionDate()+"   "+"stato richiesta: " + requestState;
	}

	
}
