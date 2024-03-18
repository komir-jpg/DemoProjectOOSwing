package Controllers;

import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Boundaries.SearchTagDialog;
import DAO.TagDAO;

public class SearchTagController {
	SearchTagDialog searchTagDialog;

	public SearchTagController(JFrame previousFrame) {
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
	public ArrayList<String> getGroups(ArrayList<String> groupTags){
		return null;
	}

	public ArrayList<String> showGroup(ArrayList<String> selectedTags) throws SQLException {
		String stringTags = stringTags(selectedTags);
		TagDAO tagDAO = new TagDAO();
		tagDAO.getGroupByTag(stringTags);
	}
	private String stringTags(ArrayList<String> selectedTags) {
		String tagString = "";
		Iterator<String> selectedTagIterator = selectedTags.iterator();
		while(selectedTagIterator.hasNext()) {
			tagString = tagString.concat(","+selectedTagIterator.next());
		}
		return tagString;
	}
}
