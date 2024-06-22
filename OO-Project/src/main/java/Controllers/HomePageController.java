package Controllers;

import java.awt.Dimension;
import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Boundaries.*;
import DAO.*;
import Entities.Comment;
import Entities.Group;
import Entities.Like;
import Entities.Post;
import Entities.Request;
import Entities.Share;
import Entities.Tag;
import Entities.User;

public class HomePageController {
	HomePageFrame homePageFrame;
	private User loginUser;
	private Group groupSelected;
	SearchTagDialog searchTagDialog;
	
	
	public HomePageController(JFrame previousFrame,User loginUser) {
		homePageFrame = new HomePageFrame(this);
		this.loginUser = loginUser; 
		setHomePageFrame(previousFrame);
	}
	public void setHomePageFrame(JFrame previousFrame) {
		homePageFrame = new HomePageFrame(this);
		homePageFrame.setLocationRelativeTo(previousFrame);
		SetFrameSize(homePageFrame);
		previousFrame.setVisible(false);
		previousFrame.dispose();
		homePageFrame.setVisible(true);
	}
	
	public void setCreateGroupDialog() {
		new CreateGroupController(loginUser,homePageFrame);
	}
	
	public void setGroupRequestDialog() {
		new GroupRequestsController(homePageFrame,groupSelected);
	}
	public void setInsightsFrame() {
		new InsightsController(homePageFrame, groupSelected,loginUser);
	}

	
	private void SetFrameSize(JFrame frame) {
		frame.setSize(1080, 700);
	}
	public void setSearchTagDialog() {
		new SearchTagController(homePageFrame,loginUser);
	}
	
	private ArrayList<User> GetUser() throws SQLException{
		UserDAO userDao = new UserDAO();
		ArrayList<User> userResult= new ArrayList<User>();
		userResult = userDao.getUserList();
		return userResult;
	}

	/**
	 * creates a new Post in a group from the logged in User
	 * @param 
	 * @param 
	 * @throws SQLException 
	 * */
	public String newPost(String content) throws SQLException {
		Post post = new Post(content,groupSelected,loginUser);
		PostDAO postDAO = new PostDAO();
		postDAO.insertNewPostText(post);
		return post.toString();
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
	public void newRequest(String groupName) throws SQLException {
		RequestDAO requestDAO = new RequestDAO();
		Group group = getGroup(groupName);
		Request request = new Request(loginUser,group);
		requestDAO.insertNewRequest(request);
	}
	/**
	 * this method returns the posts in a group
	 * @return arrayList Post
	 * @throws SQLException 
	 * */
	public ArrayList<Post>GroupPosts(String groupName) throws SQLException{
		PostDAO postDAO = new PostDAO();
		ArrayList<Post> postList = new ArrayList<Post>();
		postList = postDAO.getPostsByGroupNoUser(groupName,loginUser.getUserName());
		return postList;
	}
	
	public ArrayList<String> getUserAdminGroups() throws SQLException{
		GroupDAO groupDAO = new GroupDAO();
		ArrayList<Group> groups = new ArrayList<Group>();
		ArrayList<String> groupsToString = new ArrayList<String>();
		groups = groupDAO.getAdminGroups(loginUser);
		groupsToString = listToString(groups);
		return groupsToString;
	}
	public ArrayList<String>getUserGroups() throws SQLException{
		GroupDAO groupDAO = new GroupDAO();
		ArrayList<Group> groups = groupDAO.getUserGroup(loginUser);
		ArrayList<String>groupsToString = listToString(groups);
		return groupsToString;
	}
	
	public ArrayList<String> getGroupPosts() throws SQLException{
		PostDAO postDAO = new PostDAO();
		ArrayList<Post> post = new ArrayList<Post>(); 
		post = postDAO.getPostsByGroup(groupSelected);
		ArrayList<String> postToString = listToString(post);
		return postToString;
		
	}
	
	public void selectedGroup(String groupName) throws SQLException {
		this.groupSelected = getGroup(groupName);
	}
	public String getCurrentUser() {
		return loginUser.getUserName();
	}
	
	private <T> ArrayList<String> listToString(ArrayList<T> list){
		Iterator<T> listIterator = list.iterator();
		ArrayList<String> ToString = new ArrayList<String>();
		while(listIterator.hasNext()) {
			ToString.add(listIterator.next().toString());
		}
		return ToString;
	}
	
	public void deleteGroup() throws SQLException{
		GroupDAO groupDAO = new GroupDAO();
		groupDAO.deleteGroup(groupSelected);
		groupSelected = null;
		
	}
	
	public boolean checkGroupName(String groupName) throws SQLException {
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
	
	private ArrayList<Group> getGroupFromTag(String selectedTag) throws SQLException { 
		TagDAO tagDAO = new TagDAO();
		Tag tag = new Tag();
		return tagDAO.getGroupByTag(tag);
		
	}
	public ArrayList<String> showGroup(String selectedTag) throws SQLException {
		ArrayList<String> tagList = new ArrayList<String>();
		ArrayList<Group> group = new ArrayList<Group>();
		group = getGroupFromTag(selectedTag);
		Iterator<Group> groupIterator = group.iterator();
		while(groupIterator.hasNext()) {
			tagList.add(groupIterator.next().getGroupName());
		}
		return tagList;
	}
	public boolean checkUser(String username) throws SQLException {
		ArrayList<User> userList = new ArrayList<User>();
		userList = GetUser();
		Iterator <User>UserIterator = userList.iterator();
		while(UserIterator.hasNext()) {
			if(UserIterator.next().getUserName().compareTo(username) == 0)
				return true;
		}
		return false;
	
	}
	public void deletePartecipantDialog() {
		new deletePartecipantController(homePageFrame, groupSelected);
	}
	public void deleteMessageDialog() {
		new DeleteMessageController(homePageFrame, groupSelected, loginUser);
	}
	public void deleteMessageAdminDialog() {
		new DeleteMessageAdminController(homePageFrame, groupSelected, loginUser);
	}
	public void leaveGroup() throws SQLException {
		GroupDAO groupDAO = new GroupDAO();
		groupDAO.deletePartecipant(groupSelected, loginUser.getUserName());
	}
	public void deleteLastMessage() throws SQLException {
		PostDAO postDAO = new PostDAO();
		postDAO.deleteLastMessage(loginUser, groupSelected);
	}
	public void setLoginScreen() {
		new LoginController();
		homePageFrame.dispose();
	}
	
}
