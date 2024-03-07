package DAO;

import java.util.ArrayList;

public class Tag {
	private String tag;
	private ArrayList<Group> groupsSameTag;
	public Tag() {
		
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String category) {
		this.tag = category;
	}
	

}
