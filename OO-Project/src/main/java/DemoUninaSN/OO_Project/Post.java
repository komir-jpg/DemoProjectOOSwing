package DemoUninaSN.OO_Project;

import java.sql.Date;

public class Post {
	int PostNumber;
	Date dataPost;
	int numberOfLikes;
	int numberOfComments;
	int numberOfShare;
	String content;
	String fotoFormat;
	String typeOfPost;
	boolean preferredPost;
	boolean eliminatedPost;
	
	
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
	public boolean isPreferredPost() {
		return preferredPost;
	}
	public void setPreferredPost(boolean preferredPost) {
		this.preferredPost = preferredPost;
	}
	public boolean isEliminatedPost() {
		return eliminatedPost;
	}
	public void setEliminatedPost(boolean eliminatedPost) {
		this.eliminatedPost = eliminatedPost;
	}
	
	

}
