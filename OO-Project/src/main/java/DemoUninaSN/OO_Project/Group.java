package DemoUninaSN.OO_Project;

import javax.xml.crypto.Data;

public class Group {

	private String groupName;
	private Data creationDate;
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

	public Data getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Data creationDate) {
		this.creationDate = creationDate;
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
