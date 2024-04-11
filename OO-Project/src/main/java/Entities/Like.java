package Entities;

import java.util.Date;

public class Like {
	private User user;
	private Post post;
	private Date likeDate;
	private int likeID;
	
	public Like() {
		
	}
	public Like(User user,Post post) {
		this.user = user;
		this.post = post;
		likeDate = new Date();
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

	public Date getLikeDate() {
		return likeDate;
	}

	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}

	public int getLikeID() {
		return likeID;
	}

	public void setLikeID(int likeID) {
		this.likeID = likeID;
	}
	


}
