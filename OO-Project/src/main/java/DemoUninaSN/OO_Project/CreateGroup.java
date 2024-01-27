package DemoUninaSN.OO_Project;

import java.sql.Date;

public class CreateGroup {
	private String GroupName;
	private String usernameAdmin;
	private String description;
	private int numberOfPartecipants;
	private Date dateOfCreation;
	public CreateGroup() {
		
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
	

}
