package Controllers;

import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JDialog;
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
	
	
	public DeleteMessageAdminController(JFrame previousFrame,Group selectedGroup,User loggedUser) {
		this.selectedGroup = selectedGroup;
		this.loggedUser = loggedUser;
		setDeleteMessageAdminDialog(previousFrame);
		
	}
	public void setHomePageFrame() {
		deleteMessageAdminDialog.dispose();
	}
	private void setDeleteMessageAdminDialog(JFrame previousFrame) {
		deleteMessageAdminDialog = new DeleteMessageAdminDialog(this);
		SetFramePosition(deleteMessageAdminDialog, GetFramePosition(previousFrame));
		deleteMessageAdminDialog.setVisible(true);
	}
	private Point GetFramePosition(JFrame frame) {
		Point point = new Point(0,0);
		//point = frame.getLocationOnScreen();
		point.x += (frame.getWidth()/2);
		point.y += (frame.getHeight()/2);
		
		return point;
	}
	private void SetFramePosition(JDialog dialog,Point point) {
		dialog.setLocation(point);
	}
	private ArrayList<String> listToStringUserMessages(ArrayList<Post> list){
		Iterator<Post> listIterator = list.iterator();
		ArrayList<String> ToString = new ArrayList<String>();
		while(listIterator.hasNext()) {
			ToString.add(listIterator.next().deletePostAdminToString());
		}
		return ToString;
	}

	public ArrayList<String> showMessages() throws SQLException {
		PostDAO postDAO = new PostDAO();
		return listToStringUserMessages(postDAO.getPostsByGroup(selectedGroup));
	}
	public void deleteMessage(String user,String message,String date) throws SQLException {
		PostDAO postDAO = new PostDAO();
		postDAO.deletePost(message, date, user, selectedGroup);
	}
	
}
