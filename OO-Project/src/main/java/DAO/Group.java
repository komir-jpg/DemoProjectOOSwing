package DAO;

import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class Group {
	private String groupName;
	private User admin;
	private String description;
	private int numberOfPartecipants;
	private Date dateOfCreation;
//	private ArrayList<Post>groupPosts;
//	private ArrayList<User>groupUsers;
	
	public Group() {
		
	}
	public Group(String groupName, String description,User admin) {
		super();
		this.groupName = groupName;
		this.description = description;
		this.admin = admin;
		dateOfCreation = new Date();
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public User getUsernameAdmin() {
		return admin;
	}
	public void setUsernameAdmin(User admin) {
		this.admin = admin;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNumberOfPartecipants() {
		return numberOfPartecipants;
	}
	public void setNumberOfPartecipants(int numberOfPartecipants) {
		this.numberOfPartecipants = numberOfPartecipants;
	}
	public Date getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
//	public ArrayList<Post> getGroupPosts() {
//		return groupPosts;
//	}
//	public void setGroupPosts(ArrayList<Post> groupPosts) {
//		this.groupPosts = groupPosts;
//	}
//	public ArrayList<User> getGroupUsers() {
//		return groupUsers;
//	}
//	public void setGroupUsers(ArrayList<User> groupUsers) {
//		this.groupUsers = groupUsers;
//	}
//	public void setPost(ArrayList<Post> groupPosts) throws SQLException{
//		this.groupPosts = groupPosts;
//	}
//	public ArrayList<Tag> getGroupTags() {
//		return groupTags;
//	}
//	public void setGroupTags(ArrayList<Tag> groupTags) {
//		this.groupTags = groupTags;
//	}
//	public void addGroupUser(User user) {
//		groupUsers.add(user);
//	}
//	public void addGroupPosts(Post post) {
//		groupPosts.add(post);
//	}
//	public void removeGroupUser(User user) {
//		groupUsers.remove(user);
//	}
//	public void removeGroupPosts(Post post) {
//		groupPosts.remove(post);
//	}
	@Override
	public String toString() {
		return groupName;
	}
	

}
