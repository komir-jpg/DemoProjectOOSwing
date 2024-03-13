package Controllers;

import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Boundaries.RequestGroup;
import DAO.*;

public class GroupRequestsController {
	private RequestGroup groupRequestsDialog;
	private HomePageController homePageController;
	private Group selectedGroup;
	
	public GroupRequestsController(JFrame previousFrame,Group selectedGroup,HomePageController homePageController) {
		this.homePageController = homePageController;
		this.selectedGroup = selectedGroup;
		setRequestGroup(previousFrame);
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
	
	private void SetFramePosition(JDialog dialog,Point point) {
		dialog.setLocation(point);
	}
	private Point GetFramePosition(JFrame frame) {
		Point point;
		point = frame.getLocationOnScreen();
		return point;
	}
	private void setRequestGroup(JFrame previousFrame) {
		groupRequestsDialog = new RequestGroup(this);
		SetFramePosition(groupRequestsDialog, GetFramePosition(previousFrame));
		groupRequestsDialog.setVisible(true);
	}
	private <T> ArrayList<String> listToString(ArrayList<T> list){
		Iterator<T> postIterator = list.iterator();
		ArrayList<String>postToString = new ArrayList<String>();
		while(postIterator.hasNext()) {
			postToString.add(postIterator.next().toString());
		}
		return postToString;
	}
}