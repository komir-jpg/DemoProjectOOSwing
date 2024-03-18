package Controllers;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Boundaries.CreateGroupDialog;
import Boundaries.HomePage;
import Boundaries.Login;
import Boundaries.RegisterUserFrame;
import Boundaries.SearchTagDialog;
import DAO.*;
import ExceptionPackage.DBconnectionError;

public class LoginController {
	private Login loginFrame;
	private HomePage homePageFrame;
	private RegisterUserFrame RegisterFrame;
	private CreateGroupDialog createGroupDialog;
	private SearchTagDialog searchTagDialog;
	private User LogInUser;
	private Connection connection;
	private User user;
	private UserDAO userDao;
	private Post post;
	private PostDAO postDAO;
	private RegisterUserController registerController;
	private HomePageController homePageController;
	
	
	
	
	public static void main(String[] args) {
		new LoginController();
	}
	
	public LoginController() {
		loginFrame = new Login(this);
		loginFrame.setVisible(true);
		
	}
//	public void setCreateGroupDialog() {
//		createGroupDialog = new CreateGroupDialog(this);
//		SetFramePosition(createGroupDialog, GetFramePosition(homePageFrame));
//		createGroupDialog.setVisible(true);
//	}
	
	public void setHomePageFrame(JFrame frame) {
		homePageController = new HomePageController(frame,LogInUser);
	}
	public void setLoginFrameVisible(){
		loginFrame.setVisible(true);
	}
	
//	public void setHomePageFrameFromDialog(JDialog dialog) {
//		dialog.dispose();
//		homePageFrame.setVisible(true);
//	}
	public void setRegisterFrame(JFrame frame) {
		registerController = new RegisterUserController(frame,this);
	}
	
	public void setLoginFrame(JFrame frame) {
		loginFrame = new Login(this);
		SetFramePosition(loginFrame, GetFramePosition(frame));
		SetFrameSize(loginFrame,GetFrameSize(frame));
		frame.setVisible(false);
		loginFrame.setVisible(true);
		frame.dispose();
	}
//	public void setSearchTagDialog() {
//		searchTagDialog = new SearchTag(this);
//		SetFramePosition(searchTagDialog, GetFramePosition(homePageFrame));
//		searchTagDialog.setVisible(true);
//	}
	
//	public void getDBConnection() throws DBconnectionError{
//		try {
//			ConnectionToDB connection = new ConnectionToDB();
//			this.connection = connection.getConnection();
//			}catch( RuntimeException exc) {
//				exc.printStackTrace();
//				throw new DBconnectionError();
//			}
//		
//	}
	
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
	
	
	public void userLogIn(String username) throws  SQLException{
		UserDAO userDao = new UserDAO();
		this.LogInUser = userDao.getUserbyUsername(username);
	}
	
	
//	public void InsertNewUser(String name, String surname, String sex, String userName,String email, String password) throws SQLException 
//	{
//		user = new User(name,surname,sex,userName,email,password);
//		userDao = new UserDAO();
//		userDao.SaveNewUser(user);
//	}
	
	private ArrayList<User> GetUser() throws SQLException{
		userDao = new UserDAO();
		ArrayList<User> userResult= new ArrayList<User>();
		userResult = userDao.getUserList();
		return userResult;
	}
	private boolean checkCredentials(String Username,String Password,User userIterator) {
		if(Username.compareTo(userIterator.getUserName()) == 0 && Password.compareTo(userIterator.getPassword()) == 0)
			return true;
		else
			return false;
	}
	
	public boolean CheckUserLogIn(String Username,String Password) throws SQLException  {
		ArrayList<User> Result = GetUser();
		Iterator<User> UserIterator = Result.iterator();
		
		while(UserIterator.hasNext()) {
			if(checkCredentials(Username, Password, UserIterator.next()))
				return true;
		}
		return false;
	}
	
//	public void NewGroup(String groupName,String Description,String Category) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
//		Group newGroup = new Group(groupName,Description,date(),Category);
//		GroupDAO createGroupDao = new GroupDAO();
//		createGroupDao.createNewGroup(newGroup, LogInUser);
//	}
//
//	private String date() {
//		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
//		Calendar calendar = Calendar.getInstance();
//		return dateformat.format(calendar.getTime());
//	}
//	public ArrayList<String> setUserAdminGroup() throws ClassNotFoundException, SQLException, IOException, RuntimeException {
//		GroupDAO createGroupDAO = new GroupDAO();
//		ArrayList<Group> groupResult = new ArrayList<Group>();
//		ArrayList<String> GroupName = new ArrayList<String>();
//		
//		groupResult = createGroupDAO.getAdminGroups(LogInUser);
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
//		tag.setCategory(categoria);
//		tagDAO.insertNewTag(tag);
//	}
//	public ArrayList<String> setCategory() throws ClassNotFoundException, SQLException, IOException, RuntimeException{
//		TagDAO tagDAO = new TagDAO();
//		ArrayList<Tag> tagList = tagDAO.getTag();
//		ArrayList<String> tagListString = new ArrayList<String>();
//		Iterator<Tag> tagIterator = tagList.iterator();
//		
//		while(tagIterator.hasNext()) {
//			tagListString.add(tagIterator.next().getCategory());
//		}
//		return tagListString;
//	}
//	public void deleteGroup(String groupName) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
//		GroupDAO groupDAO = new GroupDAO();
//		GroupDAO createGroupDAO = new GroupDAO();
//		Group SelectedGroup = groupDAO.GetGroupByName(groupName);
//		createGroupDAO.deleteGroup(SelectedGroup);
//		
//	}
//	
//	public boolean checkGroupName(String groupName) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
//		Group group = getGroup(groupName);
//		if(group.getGroupName() != null)
//			return true;
//		return false;
//	}
//	
//	private Group getGroup(String groupName) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
//		GroupDAO groupDAO = new GroupDAO();
//		Group group = new Group();
//		group = groupDAO.GetGroupByName(groupName);
//		return group;
//	}
//	
//	public void newRequest(String groupName) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
//		Group group = getGroup(groupName);
//		Request request = new Request(LogInUser,group);
//		RequestDAO requestDAO = new RequestDAO();
//		requestDAO.insertNewRequest(request);
//	}
//	private ArrayList<Group> getGroupFromTag(String selectedTag) throws  SQLException{ 
//		TagDAO tagDAO = new TagDAO();
//		Tag tag = new Tag();
//		tag = tagDAO.getSingleTag(selectedTag);
//		return tagDAO.getGroupByTag(tag);
//		
//	}
//	public ArrayList<String> showGroup(String selectedTag) throws SQLException{
//		ArrayList<String> tagList = new ArrayList<>();
//		ArrayList<Group> group = new ArrayList<Group>();
//		group = getGroupFromTag(selectedTag);
//		Iterator<Group> groupIterator = group.iterator();
//		while(groupIterator.hasNext()) {
//			tagList.add(groupIterator.next().getGroupName());
//		}
//		return tagList;
//	}
	public boolean checkUser(String username) throws SQLException{
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
