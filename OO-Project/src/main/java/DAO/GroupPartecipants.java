package DAO;

import java.util.ArrayList;

public class GroupPartecipants {

	private ArrayList<User> partecipants;
	private Group group;
	
	public GroupPartecipants(Group group) {
		this.group = group;
	}

	public ArrayList<User> getPartecipants() {
		return partecipants;
	}

	public void setUser(User user) {
		this.partecipants.add(user);
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
}
