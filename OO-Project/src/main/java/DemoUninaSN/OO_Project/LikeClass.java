package DemoUninaSN.OO_Project;

import java.sql.Date;

public class LikeClass {
	private User user;
	private Post post;
	private Date likeDate;
	private String LikeAuthor;
	
	public LikeClass() {
		// TODO Auto-generated constructor stub
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

	public String getLikeAuthor() {
		return LikeAuthor;
	}

	public void setLikeAuthor(String likeAuthor) {
		LikeAuthor = likeAuthor;
	}
	


}
