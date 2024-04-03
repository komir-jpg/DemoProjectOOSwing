package Entities;

import java.util.ArrayList;

public class Friend {
	private User user;
	private ArrayList<User> friends;
	
	public Friend(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}
	

}
