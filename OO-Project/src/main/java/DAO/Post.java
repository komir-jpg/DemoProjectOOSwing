package DAO;

import java.sql.Date;

public class Post {
	private int idPost;
	private Date dataPost;
	private int numberOfLikes;
	private int numberOfComments;
	private int numberOfShare;
	private String content;
	private String fotoFormat;
	private String typeOfPost;
	private boolean eliminatedPost;
	private Group group;
	private User author; 
	
	

	public Post() {
		
		
	}



	public int getIdPost() {
		return idPost;
	}



	public void setIdPost(int postNumber) {
		idPost = postNumber;
	}



	public Date getDataPost() {
		return dataPost;
	}



	public void setDataPost(Date dataPost) {
		this.dataPost = dataPost;
	}



	public int getNumberOfLikes() {
		return numberOfLikes;
	}



	public void setNumberOfLikes(int numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}



	public int getNumberOfComments() {
		return numberOfComments;
	}



	public void setNumberOfComments(int numberOfComments) {
		this.numberOfComments = numberOfComments;
	}



	public int getNumberOfShare() {
		return numberOfShare;
	}



	public void setNumberOfShare(int numberOfShare) {
		this.numberOfShare = numberOfShare;
	}



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

	
}
