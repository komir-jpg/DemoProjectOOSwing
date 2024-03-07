package DAO;

import java.util.Date;

public class Comment {
	private User user;
	private Post post;
	private int commentID;
	private Date commentDate;
	private String text;
	
	
	public Comment() {
		
	}
	public Comment(User author,Post postCommented,String text) {
		super();
		user = author;
		post = postCommented;
		this.text = text;
		commentDate = new Date();
		
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

	public int getCommentID() {
		return commentID;
	}

	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
