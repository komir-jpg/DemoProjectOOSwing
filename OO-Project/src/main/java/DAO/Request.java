package DAO;

public class Request {
	private int idRequest;
	private String requestState;
	private User user;
	private Group group;

	public Request(User user,Group group) {
		this.user = user;
		this.group = group;
		requestState = "attesa";
	}

	public String getRequestState() {
		return requestState;
	}

	public void setRequestState(String requestState) {
		this.requestState = requestState;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public int getIdRequest() {
		return idRequest;
	}

	public void setIdRequest(int idRequest) {
		this.idRequest = idRequest;
	}
	
	

}
