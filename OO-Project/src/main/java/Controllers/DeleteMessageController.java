package Controllers;

import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Boundaries.DeleteMessageDialog;
import DAO.*;

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
		SetFramePosition(deleteMessageDialog, GetFramePosition(previousFrame));
		deleteMessageDialog.setVisible(true);
		
	}
	private Point GetFramePosition(JFrame frame) {
		Point point;
		point = frame.getLocationOnScreen();
		return point;
	}
	private void SetFramePosition(JDialog dialog,Point point) {
		dialog.setLocation(point);
	}
	
	public ArrayList<String>showUserMessages() throws SQLException {
		PostDAO postDAO = new PostDAO();
		return listToString(postDAO.getUserPostsByGroup(selectedGroup, loggedUser));
		
	}
	private ArrayList<String> listToString(ArrayList<Post> list){
		Iterator<Post> listIterator = list.iterator();
		ArrayList<String> ToString = new ArrayList<String>();
		while(listIterator.hasNext()) {
			ToString.add(listIterator.next().deletePostToString());
		}
		return ToString;
	}
}