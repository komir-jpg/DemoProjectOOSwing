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
	Group groupSelected;
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
	
//	public void setHomePageFrameFromDialog(JDialog dialog) {
//		dialog.dispose();
//		homePageFrame.setVisible(true);
//	}
	public void setCreateGroupDialog() {
		new CreateGroupController(loginUser,this,homePageFrame);
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
//	/**
//	 * creates a new group 
//	 * @param
//	 * @param
//	 * */
//	public void newGroup(String groupName,String Description) throws SQLException{
//		Group newGroup = new Group(groupName,Description,loginUser);
//		GroupDAO createGroupDao = new GroupDAO();
//		createGroupDao.createNewGroup(newGroup, loginUser);
//	}
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
	}
	/**		
	 * insert a new shared post on the DB
	 * @param
	 * @param
	 * @throws SQLException 
	 * */
	public void newShare(Post post,Group group) throws SQLException {
		Share share = new Share(post,group,loginUser);
		ShareDAO shareDAO = new ShareDAO();
		shareDAO.newSharedPost(share, group, loginUser);
		post.addShare(share);
	}
	/**
	 * this method returns the posts in a group
	 * @return arrayList Post
	 * @throws SQLException 
	 * */
	public ArrayList<Post>getGroupPosts(String groupName) throws SQLException{
		PostDAO postDAO = new PostDAO();
		ArrayList<Post> postList = new ArrayList<Post>();
		postList = postDAO.getPostsByGroup(groupName);
		return postList;
	}
	/**
	 * this method returns the group partecipants
	 * @return arrayList User
	 * */
	public ArrayList<User>getGroupPartecipants(){
		return null;
	}
	public ArrayList<String> getUserAdminGroups() throws SQLException{
		GroupDAO groupDAO = new GroupDAO();
		ArrayList<Group> groups = new ArrayList<Group>();
		ArrayList<String> groupsToString = new ArrayList<String>();
		groups = groupDAO.getAdminGroups(loginUser);
		groupsToString = groupToString(groups);
		return groupsToString;
	}
	public ArrayList<String> getGroupPosts(){
		ArrayList<Post> post = new ArrayList<Post>();
		ArrayList<String> postToString = new ArrayList<String>();
		post = groupSelected.getGroupPosts();
		postToString = postToString(post);
		return postToString;
		
	}

	private String date() {
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		return dateformat.format(calendar.getTime());
	}
//	public ArrayList<String> setUserAdminGroup() throws ClassNotFoundException, SQLException, IOException, RuntimeException {
//		GroupDAO createGroupDAO = new GroupDAO();
//		ArrayList<Group> groupResult = new ArrayList<Group>();
//		ArrayList<String> GroupName = new ArrayList<String>();
//		
//		groupResult = createGroupDAO.getAdminGroups(loginUser);
//		Iterator<Group> AdminGroupIterator = groupResult.iterator();
//		
//		while(AdminGroupIterator.hasNext()) {
//			GroupName.add(AdminGroupIterator.next().getGroupName());
//		}
//		return  GroupName;
//		
//	}
//	public void newTag(String categoria) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
//		TagDAO tagDAO = new TagDAO();
//		Tag tag = new Tag();
//		tag.setTag(categoria);
//		tagDAO.insertNewTag(tag);
//	}
//	public ArrayList<String> setCategory() throws SQLException {
//		TagDAO tagDAO = new TagDAO();
//		ArrayList<Tag> tagList = tagDAO.getTag();
//		ArrayList<String> tagListString = new ArrayList<String>();
//		Iterator<Tag> tagIterator = tagList.iterator();
//		
//		while(tagIterator.hasNext()) {
//			tagListString.add(tagIterator.next().getTag());
//		}
//		return tagListString;
//	}
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
	
	private Group getGroup(String groupName) throws SQLException{
		GroupDAO groupDAO = new GroupDAO();
		Group group = new Group();
		group = groupDAO.GetGroupByName(groupName);
		return group;
	}
	
	public void newRequest(String groupName) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		Group group = getGroup(groupName);
//		Request request = new Request(loginUser,group);
//		RequestDAO requestDAO = new RequestDAO();
//		requestDAO.insertNewRequest(request);
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
	private ArrayList<String> groupToString(ArrayList<Group> groups){
		Iterator<Group> groupIterator = groups.iterator();
		ArrayList<String>groupToString = new ArrayList<String>();
		while(groupIterator.hasNext()) {
			groupToString.add(groupIterator.next().toString());
		}
		return groupToString;
	}
	private ArrayList<String> postToString(ArrayList<Post> post){
		Iterator<Post> postIterator = post.iterator();
		ArrayList<String>postToString = new ArrayList<String>();
		while(postIterator.hasNext()) {
			postToString.add(postIterator.next().toString());
		}
		return postToString;
	}
	public void selectedGroup(String groupName) throws SQLException {
		this.groupSelected = getGroup(groupName);
	}
	
}
