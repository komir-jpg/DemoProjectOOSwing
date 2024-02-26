package DemoUninaSN.OO_Project;

import java.sql.Date;

public class CreateGroup {
	private String GroupName;
	private String usernameAdmin;
	private String description;
	private int numberOfPartecipants;
	private String dateOfCreation;
	private String category;
	public CreateGroup() {
		
	}
	public CreateGroup(String groupName, String description,String dateOfCreation,String category) {
		super();
		GroupName = groupName;
		this.description = description;
		this.dateOfCreation = dateOfCreation;
		this.category = category;
	}
	public String getGroupName() {
		return GroupName;
	}
	public void setGroupName(String groupName) {
		GroupName = groupName;
	}
	public String getUsernameAdmin() {
		return usernameAdmin;
	}
	public void setUsernameAdmin(String userName) {
		this.usernameAdmin = userName;
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
	

}
