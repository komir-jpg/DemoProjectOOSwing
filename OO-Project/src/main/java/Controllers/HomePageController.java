package Controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;

import Boundaries.*;
import DAO.*;
import Entities.Comment;
import Entities.Group;
import Entities.Like;
import Entities.Post;
import Entities.Request;
import Entities.Share;
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
	
	

	/**
	 * creates a new Post in a group from the logged in User
	 *  
	 *  
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
	 * 
	 * 
	 * @throws SQLException 
	 * */
	public void newComment(Post post,String text) throws SQLException {
		Comment comment = new Comment(loginUser,post,text);
		CommentDAO commentDAO = new CommentDAO();
		commentDAO.addComment(comment);
		post.addPostComment(comment);
	}
	/**
	 * method that inserts a new like of a post
	 * 
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
	 * 
	 * 
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
	 * this method returns all the current user owned groups
	 * 
	 * @throws SQLException 
	 * */
	
	public ArrayList<String> getUserAdminGroups() throws SQLException{
		GroupDAO groupDAO = new GroupDAO();
		ArrayList<Group> groups = new ArrayList<Group>();
		ArrayList<String> groupsToString = new ArrayList<String>();
		groups = groupDAO.getAdminGroups(loginUser);
		groupsToString = listToString(groups);
		return groupsToString;
	}
	/**
	 * this method returns all the current user groups which he is part of
	 * 
	 * @throws SQLException 
	 * */
	public ArrayList<String>getUserGroups() throws SQLException{
		GroupDAO groupDAO = new GroupDAO();
		ArrayList<Group> groups = groupDAO.getUserGroup(loginUser);
		ArrayList<String>groupsToString = listToString(groups);
		return groupsToString;
	}
	/**
	 * this method returns all the selectedGroup post
	 * 
	 * @throws SQLException 
	 * */
	public ArrayList<String> getGroupPosts() throws SQLException{
		PostDAO postDAO = new PostDAO();
		ArrayList<Post> post = new ArrayList<Post>(); 
		post = postDAO.getPostsByGroup(groupSelected);
		ArrayList<String> postToString = listToString(post);
		return postToString;
		
	}
	/**
	 * get the current selected group
	 * 
	 * @throws SQLException 
	 * */
	public void selectedGroup(String groupName) throws SQLException {
		this.groupSelected = getGroup(groupName);
	}
	/**
	 * get the current user of this session
	 * 
	 * @throws SQLException 
	 * */
	public String getCurrentUser() {
		return loginUser.getUserName();
	}
	
	
	/**
	 * this method deletes the selected group
	 * 
	 * @throws SQLException 
	 * */
	public void deleteGroup() throws SQLException{
		GroupDAO groupDAO = new GroupDAO();
		groupDAO.deleteGroup(groupSelected);
		groupSelected = null;
		
	}
	/**
	 * this method checks if the inserted group name corresponds to an existing group
	 * 
	 * @throws SQLException 
	 * */
	public boolean checkGroupName(String groupName) throws SQLException {
		Group group = getGroup(groupName);
		if(group.getGroupName() != null)
			return true;
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
	
	private Group getGroup(String groupName) throws SQLException{
		GroupDAO groupDAO = new GroupDAO();
		Group group = new Group();
		group = groupDAO.GetGroupByName(groupName);
		return group;
	}
	
	
	private <T> ArrayList<String> listToString(ArrayList<T> list){
		Iterator<T> listIterator = list.iterator();
		ArrayList<String> ToString = new ArrayList<String>();
		while(listIterator.hasNext()) {
			ToString.add(listIterator.next().toString());
		}
		return ToString;
	}
}
