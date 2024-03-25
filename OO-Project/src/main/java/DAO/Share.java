package DAO;

import java.util.Date;

public class Share {
	
	private int shareID;
	private Post postShared;
	private Group groupSharedPost;
	private User userSharing;
	private Date shareDate;
	
	public Share() {
	}
	public Share(Post postShared,Group groupSharedPost,User userSharing) {
		this.postShared = postShared;
		this.groupSharedPost = groupSharedPost;
		this.userSharing = userSharing;
		shareDate = new Date();
		
	}

	public Post getPostShared() {
		return postShared;
	}

	public void setPostShared(Post postShared) {
		this.postShared = postShared;
	}

	public Group getGroupSharedPost() {
		return groupSharedPost;
	}

	public void setGroupSharedPost(Group groupSharedPost) {
		this.groupSharedPost = groupSharedPost;
	}
	
	public User getUserSharing() {
		return userSharing;
	}

	public void setUserSharing(User userSharing) {
		this.userSharing = userSharing;
	}

	public Date getShareDate() {
		return shareDate;
	}

	public void setShareDate(Date shareDate) {
		this.shareDate = shareDate;
	}

	public int getShareID() {
		return shareID;
	}

	public void setShareID(int shareID) {
		this.shareID = shareID;
	}
	
	

}
