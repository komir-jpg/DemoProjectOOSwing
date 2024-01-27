package DemoUninaSN.OO_Project;

import java.sql.Date;

import javax.xml.crypto.Data;

public class Group {

	private String groupName;
	private Date creationDate;
	private String description;
	private int numberOfPatecipants;
	
	public Group() {
		
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date date) {
		this.creationDate = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getNumberOfPatecipants() {
		return numberOfPatecipants;
	}

	public void setNumberOfPatecipants(int numberOfPatecipants) {
		this.numberOfPatecipants = numberOfPatecipants;
	}

}
