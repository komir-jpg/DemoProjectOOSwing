package Controllers;

import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Boundaries.RequestGroupDialog;
import DAO.*;
import Entities.Group;
import Entities.Request;

public class GroupRequestsController {
	private RequestGroupDialog groupRequestsDialog;
	private Group selectedGroup;
	
	public GroupRequestsController(JFrame previousFrame,Group selectedGroup) {
		this.selectedGroup = selectedGroup;
		setRequestGroup(previousFrame);
	}
	private void setRequestGroup(JFrame previousFrame) {
		groupRequestsDialog = new RequestGroupDialog(this);
		groupRequestsDialog.setLocationRelativeTo(previousFrame);
		groupRequestsDialog.setVisible(true);
	}
	public void setHomePageFrameFromDialog() {
		groupRequestsDialog.dispose();
	}
	
	
	public ArrayList<String> getRequestList() throws SQLException{
		RequestDAO requestDAO = new RequestDAO();
		ArrayList<Request> request = requestDAO.getGroupRequests(selectedGroup);
		return listToString(request);
	}
	
	public void requestAccepted(String userRequestAccepted) throws SQLException {
		RequestDAO requestDAO = new RequestDAO();
		requestDAO.requestAccepted(userRequestAccepted,selectedGroup);
	}
	
	
	private <T> ArrayList<String> listToString(ArrayList<T> list){
		Iterator<T> postIterator = list.iterator();
		ArrayList<String>postToString = new ArrayList<String>();
		while(postIterator.hasNext()) {
			postToString.add(postIterator.next().toString());
		}
		return postToString;
	}
	public void requestDenied(String username) throws SQLException {
		RequestDAO requestDAO = new RequestDAO();
		requestDAO.requestDenied(username, selectedGroup);
	}
}