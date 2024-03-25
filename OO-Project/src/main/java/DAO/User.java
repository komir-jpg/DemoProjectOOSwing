package DAO;

import java.util.ArrayList;
import java.util.Date;

public class User {
	
	private String name;
	private String surname;
	private String sex;
	private Date subscriptionDate;
	private String userName;
	private String email;
	private String password;
	private String userType;
	//private ArrayList<Group>groupAdmin;
	//private ArrayList<Group>groupParticipate;
	private ArrayList<User>friends;
	//private ArrayList<Comment>userComments;
	//private ArrayList<Like>userLike;
	//private ArrayList<Share>userShare;
	//private ArrayList<Notify>notifications;
	//private ArrayList<Post>postPublished;
	
	
	
	public User() {
		
	}

	public User(String name, String surname, String sex, String userName,
			String email, String password) {
		super();
		this.name = name;
		this.surname = surname;
		this.sex = sex;
		this.userName = userName;
		this.email = email;
		this.password = password;
		subscriptionDate = new Date();
//		groupAdmin = new ArrayList<Group>();
//		groupParticipate = new ArrayList<Group>();
		friends = new ArrayList<User>();
//		userComments = new ArrayList<Comment>();
//		userLike = new ArrayList<Like>();
//		userShare = new ArrayList<Share>();
//		notifications = new ArrayList<Notify>();
//		postPublished = new ArrayList<Post>();
	}
	


	public void setName(String name) {
		this.name = name;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}



	public void setSex(String sex) {
		this.sex = sex;
	}


	public void setSubsriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	
	public String getName() {
		return name;
	}


	public String getSurname() {
		return surname;
	}


	public String getSex() {
		return sex;
	}


	public Date getSubcsriptionDate() {
		return subscriptionDate;
	}


	public String getUserName() {
		return userName;
	}


	public String getEmail() {
		return email;
	}


	public String getPassword() {
		return password;
	}


	public String getUserType() {
		return userType;
	}

//	public ArrayList<Group> getGroupAdmin() {
//		return groupAdmin;
//	}
//
//	public void setGroupAdmin(ArrayList<Group> groupAdmin) {
//		this.groupAdmin = groupAdmin;
//	}
//
//	public ArrayList<Group> getGroupParticipate() {
//		return groupParticipate;
//	}
//
//	public void setGroupParticipate(ArrayList<Group> groupParticipate) {
//		this.groupParticipate = groupParticipate;
//	}
//
	public ArrayList<User> getFriends() {
		return friends;
	}
//
	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}
//
//	public ArrayList<Comment> getUserComments() {
//		return userComments;
//	}
//
//	public void setUserComments(ArrayList<Comment> userComments) {
//		this.userComments = userComments;
//	}
//
//	public ArrayList<Post> getPostPublished() {
//		return postPublished;
//	}
//
//	public void setPostPublished(ArrayList<Post> postPublished) {
//		this.postPublished = postPublished;
//	}
//
//	public ArrayList<Notify> getNotifications() {
//		return notifications;
//	}
//
//	public void setNotifications(ArrayList<Notify> notifications) {
//		this.notifications = notifications;
//	}
//
//	public ArrayList<Share> getUserShare() {
//		return userShare;
//	}
//
//	public void setUserShare(ArrayList<Share> userShare) {
//		this.userShare = userShare;
//	}
//
//	public ArrayList<Like> getUserLike() {
//		return userLike;
//	}
//
//	public void setUserLike(ArrayList<Like> userLike) {
//		this.userLike = userLike;
//	}
//	
//	public void addGroupAdmin(Group group) {
//		groupAdmin.add(group);
//	}
//	public void addGroupPartecipate(Group group) {
//		groupParticipate.add(group);
//	}
//	public void addLike(Like like) {
//		userLike.add(like);
//	}
//	public void addComment(Comment comment) {
//		userComments.add(comment);
//	}
//	public void addShare(Share share) {
//		userShare.add(share);
//	}
//	public void addPost(Post post) {
//		postPublished.add(post);
//	}
//	
//	public void removeGroupAdmin(Group group) {
//		groupAdmin.remove(group);
//	}
//	public void removeGroupPartecipate(Group group) {
//		groupParticipate.remove(group);
//	}
//	public void removeLike(Like like) {
//		userLike.remove(like);
//	}
//	public void removeComment(Comment comment) {
//		userComments.remove(comment);
//	}
//	public void removeShare(Share share) {
//		userShare.remove(share);
//	}
//	public void removePost(Post post) {
//		postPublished.remove(post);
//	}

	@Override
	public String toString() {
		return userName;
	}



	@Override
	public boolean equals(Object obj) {
		String ComparedUser;
		User user = (User)obj;
		
		ComparedUser = user.getUserName();
		
		return ComparedUser.compareTo(this.userName) == 0;
	}
	
	

}
