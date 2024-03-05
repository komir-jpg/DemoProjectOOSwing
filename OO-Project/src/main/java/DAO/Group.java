package DAO;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class Group {
	private String groupName;
	private User admin;
	private String description;
	private int numberOfPartecipants;
	private String dateOfCreation;
	private String category;
	private ArrayList<Post>groupPosts;
	private ArrayList<User>groupUsers;
	public Group() {
		
	}
	public Group(String groupName, String description,String dateOfCreation,String category) {
		super();
		this.groupName = groupName;
		this.description = description;
		this.dateOfCreation = dateOfCreation;
		this.category = category;
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
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCategory() {
		return category;
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
	public String getDateOfCreation() {
		return dateOfCreation;
	}
	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	public ArrayList<Post> getGroupPosts() {
		return groupPosts;
	}
	public void setGroupPosts(ArrayList<Post> groupPosts) {
		this.groupPosts = groupPosts;
	}
	public ArrayList<User> getGroupUsers() {
		return groupUsers;
	}
	public void setGroupUsers(ArrayList<User> groupUsers) {
		this.groupUsers = groupUsers;
	}
	public void setPost() throws SQLException{
		PostDAO postDAO = new PostDAO();
		groupPosts.addAll(postDAO.getPostbyGroup(this));
	}

}
