package DAO;

import java.sql.Date;
import java.util.ArrayList;

public class Notify {
	private ArrayList<User> userNotified;
	private Post post;
	private Date notifyDate;
	
	public Notify() {
	}

	public ArrayList<User> getUserNotified() {
		return userNotified;
	}

	public void setUserNotified(ArrayList<User> user) {
		this.userNotified = user;
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
