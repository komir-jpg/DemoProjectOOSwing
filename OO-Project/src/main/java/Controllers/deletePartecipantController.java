package Controllers;

import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Boundaries.DeletePartecipantDialog;
import DAO.*;
import Entities.Group;

public class deletePartecipantController {
	private Group selectedGroup;
	private DeletePartecipantDialog deletePartecipantDialog;
	public deletePartecipantController(JFrame previousFrame,Group selectedGroup) {
		this.selectedGroup = selectedGroup;
		setDeletePartecipantDialog(previousFrame);
		
	}
	
	private void setDeletePartecipantDialog(JFrame previousFrame) {
		deletePartecipantDialog = new DeletePartecipantDialog(this);
		deletePartecipantDialog.setLocationRelativeTo(previousFrame);
		deletePartecipantDialog.setVisible(true);
	}
	
	
	
	private <T> ArrayList<String> listToString(ArrayList<T> list){
		Iterator<T> listIterator = list.iterator();
		ArrayList<String> ToString = new ArrayList<String>();
		while(listIterator.hasNext()) {
			ToString.add(listIterator.next().toString());
		}
		return ToString;
	}
	public ArrayList<String> groupPartecipants() throws SQLException{
		UserDAO userDAO = new UserDAO();
		return listToString(userDAO.getUsersByGroup(selectedGroup));
		
	}
	public void deletePartecipant(String username) throws SQLException {
		GroupDAO groupDAO = new GroupDAO();
		groupDAO.deletePartecipant(selectedGroup, username);
	}
	public void setHomePageFrame() {
		deletePartecipantDialog.dispose();
	}
	
}
