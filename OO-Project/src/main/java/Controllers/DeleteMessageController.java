package Controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;

import Boundaries.DeleteMessageDialog;
import DAO.*;
import Entities.Group;
import Entities.Post;
import Entities.User;

public class DeleteMessageController {
	private Group selectedGroup;
	private DeleteMessageDialog deleteMessageDialog;
	private User loggedUser;
	
	public DeleteMessageController(JFrame previousFrame,Group selectedGroup,User loggedUser) {
		this.selectedGroup = selectedGroup;
		this.loggedUser = loggedUser;
		setDeleteMessageDialog(previousFrame);
	}
	
	private void setDeleteMessageDialog(JFrame previousFrame) {
		deleteMessageDialog = new DeleteMessageDialog(this);
		deleteMessageDialog.setLocationRelativeTo(previousFrame);
		deleteMessageDialog.setVisible(true);
		
	}
	
	/**
	 * show all the user messages of the group
	 * 
	 * 
	 * */
	public ArrayList<String>showUserMessages() throws SQLException {
		PostDAO postDAO = new PostDAO();
		return listToStringUserMessages(postDAO.getUserPostsByGroup(selectedGroup, loggedUser));
		
	}
	private ArrayList<String> listToStringUserMessages(ArrayList<Post> list){
		Iterator<Post> listIterator = list.iterator();
		ArrayList<String> ToString = new ArrayList<String>();
		while(listIterator.hasNext()) {
			ToString.add(listIterator.next().deletePostToString());
		}
		return ToString;
	}
	/**
	 * delete a specific user message 
	 * 
	 * 
	 * */
	public void deleteMessage(String message, String date) throws SQLException {
		PostDAO postDAO = new PostDAO();
		postDAO.deletePost(message, date, loggedUser, selectedGroup);
	}
	
	public void setHomePageFrame() {
		deleteMessageDialog.dispose();
	}
}
