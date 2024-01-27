package DemoUninaSN.OO_Project;

import java.sql.Date;

public class Share {
	
	private Post postShared;
	private Group groupSharedPost;
	private User userSharing;
	private Date shareDate;
	
	public Share() {
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
	
	

}
