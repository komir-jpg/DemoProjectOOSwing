package Controllers;

import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Boundaries.SearchTagDialog;
import DAO.*;
import Entities.Request;
import Entities.User;
import java.awt.Toolkit;

public class SearchTagController {
	SearchTagDialog searchTagDialog;
	User loggedUser;

	public SearchTagController(JFrame previousFrame,User loggedUser) {
		this.loggedUser = loggedUser;	
		setSearchTagDialog(previousFrame);
			
	}

	private void setSearchTagDialog(JFrame previousFrame) {
		searchTagDialog = new SearchTagDialog(this);
		searchTagDialog.setIconImage(Toolkit.getDefaultToolkit().getImage(SearchTagController.class.getResource("/resources/_3707e1ea-9c9b-4142-82e2-be32952fd594_res_icon.png")));
		searchTagDialog.setTitle("cerca tag");
		searchTagDialog.setLocationRelativeTo(previousFrame);
		searchTagDialog.setVisible(true);
	}
	public void setHomePageFrameFromDialog() {
		searchTagDialog.dispose();
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
