package Controllers;

import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Boundaries.SearchTagDialog;
import DAO.*;

public class SearchTagController {
	SearchTagDialog searchTagDialog;
	User loggedUser;

	public SearchTagController(JFrame previousFrame,User loggedUser) {
		this.loggedUser = loggedUser;	
		setSearchTagDialog(previousFrame);
			
	}

	private void setSearchTagDialog(JFrame previousFrame) {
		searchTagDialog = new SearchTagDialog(this);
		SetFramePosition(searchTagDialog, GetFramePosition(previousFrame));
		searchTagDialog.setVisible(true);
	}
	public void setHomePageFrameFromDialog() {
		searchTagDialog.dispose();
	}
	private Point GetFramePosition(JFrame frame) {
		Point point;
		point = frame.getLocationOnScreen();
		return point;
	}
	private void SetFramePosition(JDialog dialog,Point point) {
		dialog.setLocation(point);
	}
	
	private <T> ArrayList<String> listToString(ArrayList<T> list){
		Iterator<T> postIterator = list.iterator();
		ArrayList<String>postToString = new ArrayList<String>();
		while(postIterator.hasNext()) {
			postToString.add(postIterator.next().toString());
		}
		return postToString;
	}
	public void newGroupRequest(String groupname) throws SQLException {
		RequestDAO requestDAO = new RequestDAO();
		GroupDAO groupDAO = new GroupDAO();
		Request request = new Request(loggedUser, groupDAO.GetGroupByName(groupname));
		requestDAO.insertNewRequest(request);
	}
	public ArrayList<String>getTags() throws SQLException{
		TagDAO tagDAO = new TagDAO();
		return listToString(tagDAO.getTag());
	}
	public ArrayList<String> showGroup(ArrayList<String> selectedTags) throws SQLException {
		String stringTags = stringTags(selectedTags);
		TagDAO tagDAO = new TagDAO();
		return tagDAO.setGroupByTag(stringTags);
	}
	private String stringTags(ArrayList<String> selectedTags) {
		String tagString = "";
		Iterator<String> selectedTagIterator = selectedTags.iterator();
		while(selectedTagIterator.hasNext()) {
			tagString = tagString.concat(selectedTagIterator.next()+",");
		}
		return tagString.substring(0, tagString.length()-1);
	}
}
