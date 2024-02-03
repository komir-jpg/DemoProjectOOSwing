package DemoUninaSN.OO_Project;

public class Request {
	private String RequestState;
	private User user;
	private Group group;

	public Request() {
	}

	public String getRequestState() {
		return RequestState;
	}

	public void setRequestState(String requestState) {
		RequestState = requestState;
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
	
	

}
