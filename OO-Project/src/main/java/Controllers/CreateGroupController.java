package Controllers;

import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JDialog;
import javax.swing.JFrame;



import Boundaries.*;
import DAO.*;
import Entities.Group;
import Entities.Tag;
import Entities.User;

public class CreateGroupController {
	User loggedInUser;
	CreateGroupDialog createGroupDialog;
	
	public CreateGroupController(User loggedInUser,JFrame previousFrame){
		this.loggedInUser = loggedInUser;
		setCreateGroupDialog(previousFrame);
	}
	public void setCreateGroupDialog(JFrame previousFrame) {
		createGroupDialog = new CreateGroupDialog(this);
		createGroupDialog.setLocationRelativeTo(previousFrame);
		createGroupDialog.setVisible(true);
	}
	public void setHomePageFrameFromDialog() {
		createGroupDialog.dispose();
	}
	
	
	public ArrayList<String> setTag() throws SQLException {
		TagDAO tagDAO = new TagDAO();
		ArrayList<Tag> tagList = tagDAO.getTag();
		ArrayList<String> tagListString = new ArrayList<String>();
		Iterator<Tag> tagIterator = tagList.iterator();
		
		while(tagIterator.hasNext()) {
			tagListString.add(tagIterator.next().getTag());
		}
		return tagListString;
	}

	public void newTag(String userTag) throws SQLException{
		TagDAO tagDAO = new TagDAO();
		Tag tag = new Tag();
		tag.setTag(userTag);
		tagDAO.insertNewTag(tag);
	}
	
	/**
	 * creates a new group 
	 * @param
	 * @param
	 * */
	public void newGroup(String groupName,String Description) throws SQLException{
		Group newGroup = new Group(groupName,Description,loggedInUser);
		GroupDAO createGroupDao = new GroupDAO();
		createGroupDao.createNewGroup(newGroup, loggedInUser);
	}
	public void setGroupTags(String groupName, String selectedTag) throws SQLException {
		TagDAO tagDAO = new TagDAO();
		tagDAO.setGroupTag(groupName,selectedTag);
	}
	public void checkTag(String tag)throws InputMismatchException {
		Pattern pattern = Pattern.compile("^\\w+$");
		Matcher matcher = pattern.matcher(tag);
		if(!matcher.find())
			throw new InputMismatchException();
		
	}
}
