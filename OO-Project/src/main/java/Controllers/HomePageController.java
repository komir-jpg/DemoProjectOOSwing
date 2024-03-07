package Controllers;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Boundaries.*;
import DAO.*;

public class HomePageController {
	HomePage homePageFrame;
	User loginUser;
	CreateGroupDialog createGroupDialog;
	SearchTag searchTagDialog;
	
	
	public HomePageController(JFrame previousFrame,User loginUser) {
		homePageFrame = new HomePage(this);
		this.loginUser = loginUser; 
		setHomePageFrame(previousFrame);
	}
	public void setHomePageFrame(JFrame previousFrame) {
		homePageFrame = new HomePage(this);
		SetFramePosition(homePageFrame, GetFramePosition(previousFrame));
		SetFrameSize(homePageFrame,GetFrameSize(previousFrame));
		previousFrame.setVisible(false);
		previousFrame.dispose();
		homePageFrame.setVisible(true);
	}
	public void setHomePageFrameFromDialog(JDialog dialog) {
		dialog.dispose();
		homePageFrame.setVisible(true);
	}
	public void setCreateGroupDialog() {
		createGroupDialog = new CreateGroupDialog(this);
		SetFramePosition(createGroupDialog, GetFramePosition(homePageFrame));
		createGroupDialog.setVisible(true);
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
	public void setSearchTagDialog() {
		searchTagDialog = new SearchTag(this);
		SetFramePosition(searchTagDialog, GetFramePosition(homePageFrame));
		searchTagDialog.setVisible(true);
	}
	
	private ArrayList<User> GetUser() throws SQLException{
		UserDAO userDao = new UserDAO();
		ArrayList<User> userResult= new ArrayList<User>();
		userResult = userDao.getUserList();
		return userResult;
	}
	
	public void newGroup(String groupName,String Description,String Category) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		Group newGroup = new Group(groupName,Description,date());
		GroupDAO createGroupDao = new GroupDAO();
		createGroupDao.createNewGroup(newGroup, loginUser);
		loginUser.addGroupAdmin(newGroup);
	}
	/**
	 * creates a new Post in a group from the logged in User
	 * @param 
	 * @param 
	 * @throws SQLException 
	 * */
	public void newPost(Group group,String content) throws SQLException {
		Post post = new Post(content,group,loginUser);
		PostDAO postDAO = new PostDAO();
		postDAO.insertNewPostText(post);
		group.addGroupPosts(post);
		loginUser.addPost(post);
		
	}
	/**
	 * user inserted a new comment
	 * @param
	 * @param
	 * @throws SQLException 
	 * */
	public void newComment(Post post,String text) throws SQLException {
		Comment comment = new Comment(loginUser,post,text);
		CommentDAO commentDAO = new CommentDAO();
		commentDAO.addComment(comment);
		post.addPostComment(comment);
		loginUser.addComment(comment);
	}
	/**
	 * method that inserts a new like of a post in the DB 
	 * @param
	 * @throws SQLException 
	 * */
	public void newLike(Post post) throws SQLException {
		Like like = new Like(loginUser,post);
		LikeDAO likeDAO = new LikeDAO();
		likeDAO.addLike(like);
		post.addPostLike(like);
		loginUser.addLike(like);
	}

	private String date() {
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		return dateformat.format(calendar.getTime());
	}
	public ArrayList<String> setUserAdminGroup() throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		GroupDAO createGroupDAO = new GroupDAO();
		ArrayList<Group> groupResult = new ArrayList<Group>();
		ArrayList<String> GroupName = new ArrayList<String>();
		
		groupResult = createGroupDAO.getAdminGroups(loginUser);
		Iterator<Group> AdminGroupIterator = groupResult.iterator();
		
		while(AdminGroupIterator.hasNext()) {
			GroupName.add(AdminGroupIterator.next().getGroupName());
		}
		return  GroupName;
		
	}
	public void newTag(String categoria) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		TagDAO tagDAO = new TagDAO();
		Tag tag = new Tag();
		tag.setTag(categoria);
		tagDAO.insertNewTag(tag);
	}
	public ArrayList<String> setCategory() throws ClassNotFoundException, SQLException, IOException, RuntimeException{
		TagDAO tagDAO = new TagDAO();
		ArrayList<Tag> tagList = tagDAO.getTag();
		ArrayList<String> tagListString = new ArrayList<String>();
		Iterator<Tag> tagIterator = tagList.iterator();
		
		while(tagIterator.hasNext()) {
			tagListString.add(tagIterator.next().getTag());
		}
		return tagListString;
	}
	public void deleteGroup(String groupName) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		GroupDAO groupDAO = new GroupDAO();
		GroupDAO createGroupDAO = new GroupDAO();
		Group SelectedGroup = groupDAO.GetGroupByName(groupName);
		createGroupDAO.deleteGroup(SelectedGroup);
		
	}
	
	public boolean checkGroupName(String groupName) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		Group group = getGroup(groupName);
		if(group.getGroupName() != null)
			return true;
		return false;
	}
	
	private Group getGroup(String groupName) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		GroupDAO groupDAO = new GroupDAO();
		Group group = new Group();
		group = groupDAO.GetGroupByName(groupName);
		group.setGroupPosts(new PostDAO().getPostbyGroup(group));
		//group.setGroupUsers(new);
		return group;
	}
	
	public void newRequest(String groupName) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		Group group = getGroup(groupName);
		Request request = new Request(loginUser,group);
		RequestDAO requestDAO = new RequestDAO();
		requestDAO.insertNewRequest(request);
	}
	private ArrayList<Group> getGroupFromTag(String selectedTag) throws ClassNotFoundException, SQLException, IOException, RuntimeException{ 
		TagDAO tagDAO = new TagDAO();
		Tag tag = new Tag();
		return tagDAO.getGroupByTag(tag);
		
	}
	public ArrayList<String> showGroup(String selectedTag) throws ClassNotFoundException, SQLException, IOException, RuntimeException{
		ArrayList<String> tagList = new ArrayList<>();
		ArrayList<Group> group = new ArrayList<Group>();
		group = getGroupFromTag(selectedTag);
		Iterator<Group> groupIterator = group.iterator();
		while(groupIterator.hasNext()) {
			tagList.add(groupIterator.next().getGroupName());
		}
		return tagList;
	}
	public boolean checkUser(String username) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		ArrayList<User> userList = new ArrayList<User>();
		userList = GetUser();
		Iterator <User>UserIterator = userList.iterator();
		while(UserIterator.hasNext()) {
			if(UserIterator.next().getUserName().compareTo(username) == 0)
				return true;
		}
		return false;
	
	}
	
}
