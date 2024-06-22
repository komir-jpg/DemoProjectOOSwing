package Controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

import Boundaries.*;
import DAO.*;
import Entities.Group;
import Entities.Post;
import Entities.User;

public class DeleteMessageAdminController {
	DeleteMessageAdminDialog deleteMessageAdminDialog;
	User loggedUser;
	Group selectedGroup;
	
	
	public void setHomePageFrame() {
		deleteMessageAdminDialog.dispose();
	}
	private void setDeleteMessageAdminDialog(JFrame previousFrame) {
		deleteMessageAdminDialog = new DeleteMessageAdminDialog(this);
		deleteMessageAdminDialog.setLocationRelativeTo(previousFrame);
		deleteMessageAdminDialog.setVisible(true);
	}
	
	/**
	 * deletes the messages from all the participants of the group
	 * @param
	 * @param
	 * */
	public DeleteMessageAdminController(JFrame previousFrame,Group selectedGroup,User loggedUser) {
		this.selectedGroup = selectedGroup;
		this.loggedUser = loggedUser;
		setDeleteMessageAdminDialog(previousFrame);
		
	}
	private ArrayList<String> listToStringUserMessages(ArrayList<Post> list){
		Iterator<Post> listIterator = list.iterator();
		ArrayList<String> ToString = new ArrayList<String>();
		while(listIterator.hasNext()) {
			ToString.add(listIterator.next().deletePostAdminToString());
		}
		return ToString;
	}
	/**
	 * show all the messages of the group
	 * @param
	 * @param
	 * */
	public ArrayList<String> showMessages() throws SQLException {
		PostDAO postDAO = new PostDAO();
		return listToStringUserMessages(postDAO.getPostsByGroup(selectedGroup));
	}
	/**
	 * delete a specific message of the group
	 * @param
	 * @param
	 * */
	public void deleteMessage(String user,String message,String date) throws SQLException {
		PostDAO postDAO = new PostDAO();
		postDAO.deletePost(message, date, user, selectedGroup);
	}
	
}
