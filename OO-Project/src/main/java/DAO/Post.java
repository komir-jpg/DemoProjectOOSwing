package DAO;

import java.util.ArrayList;
import java.util.Date;

public class Post {
	private int idPost;
	private Date datePost;
//	private int numberOfLikes;
//	private int numberOfComments;
//	private int numberOfShare;
	private String content;
	private String fotoFormat;
	private String typeOfPost;
	private boolean eliminatedPost;
	private Group group;
	private User author; 
	private ArrayList<Like>postLikes;
	private ArrayList<Comment>postComments;
	

	public Post() {
		
		
	}
	//costruttore per contenuto testuale
	public Post(String content,Group group,User author ) {
		super();
		this.content = content;
		this.group = group;
		this.author = author;
		typeOfPost = "testo";
		eliminatedPost = false;
		datePost = new Date();
	}



	public int getIdPost() {
		return idPost;
	}



	public void setIdPost(int postNumber) {
		idPost = postNumber;
	}



	public Date getDatePost() {
		return datePost;
	}



	public void setDatePost(Date dataPost) {
		this.datePost = dataPost;
	}



//	public int getNumberOfLikes() {
//		return numberOfLikes;
//	}
//
//
//
//	public void setNumberOfLikes(int numberOfLikes) {
//		this.numberOfLikes = numberOfLikes;
//	}
//
//
//
//	public int getNumberOfComments() {
//		return numberOfComments;
//	}
//
//
//
//	public void setNumberOfComments(int numberOfComments) {
//		this.numberOfComments = numberOfComments;
//	}
//
//
//
//	public int getNumberOfShare() {
//		return numberOfShare;
//	}
//
//
//
//	public void setNumberOfShare(int numberOfShare) {
//		this.numberOfShare = numberOfShare;
//	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public String getFotoFormat() {
		return fotoFormat;
	}



	public void setFotoFormat(String fotoFormat) {
		this.fotoFormat = fotoFormat;
	}



	public String getTypeOfPost() {
		return typeOfPost;
	}



	public void setTypeOfPost(String typeOfPost) {
		this.typeOfPost = typeOfPost;
	}



	public boolean isEliminatedPost() {
		return eliminatedPost;
	}



	public void setEliminatedPost(boolean eliminatedPost) {
		this.eliminatedPost = eliminatedPost;
	}



	public Group getGroupName() {
		return group;
	}



	public void setGroupName(Group group) {
		this.group= group;
	}



	public User getAuthor() {
		return author;
	}



	public void setAuthor(User author) {
		this.author = author;
	}
	
	public void addPostLike(Like like) {
		postLikes.add(like);
	}
	public void addPostComment(Comment comment) {
		postComments.add(comment);
	}
	public void removePostLike(Like like) {
		postLikes.remove(like);
	}
	public void removePostComment(Comment comment) {
		postComments.remove(comment);
	}

}

