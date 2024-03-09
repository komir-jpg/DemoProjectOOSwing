package Controllers;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Boundaries.*;
import DAO.*;

public class CreateGroupController {
	User loggedInUser;
	HomePageController homePageController;
	CreateGroupDialog createGroupDialog;
	
	public CreateGroupController(User loggedInUser,HomePageController homePageController,JFrame previousFrame){
		this.loggedInUser = loggedInUser;
		this.homePageController = homePageController;
		setCreateGroupDialog(previousFrame);
	}
	public void setCreateGroupDialog(JFrame previousFrame) {
		createGroupDialog = new CreateGroupDialog(this);
		SetFramePosition(createGroupDialog, GetFramePosition(previousFrame));
		createGroupDialog.setVisible(true);
	}
	public void setHomePageFrameFromDialog() {
		createGroupDialog.dispose();
	}
	private Point GetFramePosition(JFrame frame) {
		Point point;
		point = frame.getLocationOnScreen();
		return point;
	}
	private void SetFramePosition(JFrame frame,Point point) {
		frame.setLocation(point);
	}
	private void SetFramePosition(JDialog dialog,Point point) {
		dialog.setLocation(point);
	}
	
	private Dimension GetFrameSize(JFrame frame) {
		Dimension dimension;
		dimension = frame.getSize();
		return dimension;
	}
	private void SetFrameSize(JFrame frame,Dimension dimension) {
		frame.setSize(dimension);
	}
	
	
	
	
	public ArrayList<String> setCategory() throws SQLException {
		TagDAO tagDAO = new TagDAO();
		ArrayList<Tag> tagList = tagDAO.getTag();
		ArrayList<String> tagListString = new ArrayList<String>();
		Iterator<Tag> tagIterator = tagList.iterator();
		
		while(tagIterator.hasNext()) {
			tagListString.add(tagIterator.next().getTag());
		}
		return tagListString;
	}
	//TODO modificato il DAO
	public void newTag(String categoria) throws SQLException{
		TagDAO tagDAO = new TagDAO();
		Tag tag = new Tag();
		tag.setTag(categoria);
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
}
