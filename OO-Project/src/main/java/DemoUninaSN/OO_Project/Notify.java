package DemoUninaSN.OO_Project;

import java.sql.Date;

public class Notify {
	private User user;
	private Post post;
	private Date notifyDate;
	
	public Notify() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Date getNotifyDate() {
		return notifyDate;
	}

	public void setNotifyDate(Date notifyDate) {
		this.notifyDate = notifyDate;
	}
	

}
