package Entities;

import java.util.Date;

public class Group {
	private String groupName;
	private User admin;
	private String description;
	private int numberOfPartecipants;
	private Date dateOfCreation;	
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
	public User getAdmin() {
		return admin;
	}
	public void setAdmin(User admin) {
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

	@Override
	public String toString() {
		return groupName;
	}
	

}
