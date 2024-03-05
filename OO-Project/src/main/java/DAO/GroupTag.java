package DAO;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GroupTag {
	private Group groupName;
	private ArrayList<Tag> groupTag;
	
	public GroupTag() {
		
	}

	public Group getGroupName() {
		return groupName;
	}

	public void setGroupName(Group groupName) {
		this.groupName = groupName;
	}

	public ArrayList<Tag> getCategory() {
		return groupTag;
	}

	public void setCategory(ArrayList<Tag> tag) {
		this.groupTag = tag;
	}
	
}
