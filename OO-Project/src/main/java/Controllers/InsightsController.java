package Controllers;

import java.awt.Dimension;
import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Boundaries.InsightsFrame;
import DAO.*;

public class InsightsController {
	
	InsightsFrame insightsFrame;
	Group groupSelected;
	User loggedUser;
	
	public InsightsController(JFrame previousFrame,Group groupSelected,User loggedUser) {
		this.groupSelected = groupSelected;
		this.loggedUser = loggedUser;
		setInsightsFrame(previousFrame);
	}
	
	public void setInsightsFrame(JFrame previousFrame) {
		insightsFrame = new InsightsFrame(this);
		SetFramePositionOffset(insightsFrame, GetFramePosition(previousFrame));
		SetFrameSize(insightsFrame, GetFrameSize(previousFrame));
		insightsFrame.setVisible(true);
	}
	
	private Point GetFramePosition(JFrame frame) {
		Point point;
		point = frame.getLocationOnScreen();
		return point;
	}
	private void SetFramePositionOffset(JFrame frame,Point point) {
		point.move(point.x+100, point.y+100);
		frame.setLocation(point);
	}
	
	private Dimension GetFrameSize(JFrame frame) {
		Dimension dimension;
		dimension = frame.getSize();
		return dimension;
	}
	private void SetFrameSize(JFrame frame,Dimension dimension) {
		frame.setSize(dimension);
	}
	private  ArrayList<String> listToStringLikes(ArrayList<Post> list){
		Iterator<Post> postIterator = list.iterator();
		ArrayList<String>postToString = new ArrayList<String>();
		while(postIterator.hasNext()) {
			postToString.add(postIterator.next().toStringLikes());
		}
		return postToString;
	}
	private  ArrayList<String> listToStringComments(ArrayList<Post> list){
		Iterator<Post> postIterator = list.iterator();
		ArrayList<String>postToString = new ArrayList<String>();
		while(postIterator.hasNext()) {
			postToString.add(postIterator.next().toStringComments());
		}
		return postToString;
	}
	private <T> ArrayList<String> listToString(ArrayList<T> list){
		Iterator<T> postIterator = list.iterator();
		ArrayList<String>postToString = new ArrayList<String>();
		while(postIterator.hasNext()) {
			postToString.add(postIterator.next().toString());
		}
		return postToString;
	}

	public ArrayList<String> mostNumberOfLikes(int month) throws SQLException {
		PostDAO postDAO = new PostDAO();
		ArrayList<Post> postList = postDAO.getPostsMostNuberOfLikes(month, groupSelected);
		return listToStringLikes(postList);
		
	}
	public ArrayList<String> mostNumberOfComments(int month) throws SQLException{
		PostDAO postDAO = new PostDAO();
		ArrayList<Post> postList = postDAO.getPostsMostNuberOfComments(month, groupSelected);
		return listToStringComments(postList);
	}
	public ArrayList<String> leastNumberOfLikes(int month) throws SQLException {
		PostDAO postDAO = new PostDAO();
		ArrayList<Post> postList = postDAO.getPostsLeastNuberOfLikes(month, groupSelected);
		return listToStringLikes(postList);
	}
	public ArrayList<String> leastNumberOfComments(int month) throws SQLException {
		PostDAO postDAO = new PostDAO();
		ArrayList<Post> postList = postDAO.getPostsLeastNuberOfComments(month, groupSelected);
		return listToStringComments(postList);
	}
	public ArrayList<String> getUserAdminGroups() throws SQLException{
		GroupDAO groupDAO = new GroupDAO();
		ArrayList<Group> groups = new ArrayList<Group>();
		ArrayList<String> groupsToString = new ArrayList<String>();
		groups = groupDAO.getAdminGroups(loggedUser);
		groupsToString = listToString(groups);
		return groupsToString;
	}

	public void setSelectedGroup(String selectedItem) throws SQLException {
		GroupDAO groupDAO = new GroupDAO();
		groupSelected = groupDAO.GetGroupByName(selectedItem);
	}
}
