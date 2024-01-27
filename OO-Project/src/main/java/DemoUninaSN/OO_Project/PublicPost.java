package DemoUninaSN.OO_Project;

import java.sql.Date;

public class PublicPost {
	private int PostNumber;
	private String groupName;
	private String author; 
	
	

	public PublicPost() {
		
		
	}


	public int getPostNumber() {
		return PostNumber;
	}


	public void setPostNumber(int postNumber) {
		PostNumber = postNumber;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}
	

}
