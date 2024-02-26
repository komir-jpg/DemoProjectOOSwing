package DemoUninaSN.OO_Project;

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
import ExceptionPackage.DBconnectionError;

public class Controller {
	Login LoginFrame;
	HomePage homePageFrame;
	RegisterUserFrame RegisterFrame;
	CreateGroupDialog createGroupDialog;
	private User LogInUser;
	private Connection connection;
	private User user;
	private UserDAO userDao;
	private Post post;
	private PublicPostDAO postDAO;
	
	
	
	
	public static void main(String[] args) {
		new Controller();
	}
	
	
	public Controller() {
		LoginFrame = new Login(this);
		LoginFrame.setVisible(true);
		
	}
	public void setCreateGroupDialog() {
		createGroupDialog = new CreateGroupDialog(this);
		SetFramePosition(createGroupDialog, GetFramePosition(homePageFrame));
		createGroupDialog.setVisible(true);
	}
	
	public void setHomePageFrame() {
		homePageFrame = new HomePage(this);
		SetFramePosition(homePageFrame, GetFramePosition(LoginFrame));
		SetFrameSize(homePageFrame,GetFrameSize(LoginFrame));
		LoginFrame.setVisible(false);
		LoginFrame.dispose();
		homePageFrame.setVisible(true);
		
		
	}
	public void setHomePageFrameFromDialog(JDialog dialog) {
		dialog.dispose();
		homePageFrame.setVisible(true);
	}
	public void setRegisterFrame() {
		RegisterFrame = new RegisterUserFrame(this);
		SetFramePosition(RegisterFrame,GetFramePosition(LoginFrame));
		SetFrameSize(RegisterFrame, GetFrameSize(LoginFrame));
		RegisterFrame.setVisible(true);
		LoginFrame.setVisible(false);
		LoginFrame.dispose();
	}
	
	public void setLoginPage() {
		LoginFrame = new Login(this);
		SetFramePosition(LoginFrame, GetFramePosition(RegisterFrame));
		SetFrameSize(LoginFrame,GetFrameSize(RegisterFrame));
		RegisterFrame.setVisible(false);
		LoginFrame.setVisible(true);
		RegisterFrame.dispose();
	}
	
	public void getDBConnection() throws DBconnectionError{
		try {
			ConnectionControllerToDB connection = new ConnectionControllerToDB();
			this.connection = connection.getConnectionController();
			}catch( SQLException | ClassNotFoundException | IOException | RuntimeException exc) {
				exc.printStackTrace();
				throw new DBconnectionError();
			}
		
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
	
	
	public void userLogIn(String username) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		UserDAO userDao = new UserDAO(connection);
		ArrayList<User> userResult = new ArrayList<User>();
		userResult = userDao.getUserbyUsername(username);
		Iterator<User> userIterator = userResult.iterator();
		this.LogInUser = userIterator.next();
	}
	
	
	public void InsertNewUser(String name, String surname, String sex, String userName,String email, String password) throws ClassNotFoundException, SQLException, IOException, RuntimeException 
	{
		user = new User(name,surname,sex,userName,email,password);
		userDao = new UserDAO(connection);
		userDao.SaveNewUser(user);
	}
	
	private ArrayList<User> GetUser() throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		userDao = new UserDAO(connection);
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
	
	public boolean CheckUserLogIn(String Username,String Password) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		ArrayList<User> Result = GetUser();
		Iterator<User> UserIterator = Result.iterator();
		
		while(UserIterator.hasNext()) {
			if(checkCredentials(Username, Password, UserIterator.next()))
				return true;
		}
		return false;
	}
	
	public void NewGroup(String groupName,String Description,String Category) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		CreateGroup newGroup = new CreateGroup(groupName,Description,date(),Category);
		CreateGroupDAO createGroupDao = new CreateGroupDAO(connection);
		createGroupDao.createNewGroup(newGroup, LogInUser);
	}

	private String date() {
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		return dateformat.format(calendar.getTime());
	}
	public ArrayList<String> setUserAdminGroup() throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		CreateGroupDAO createGroupDAO = new CreateGroupDAO(connection);
		ArrayList<CreateGroup> groupResult = new ArrayList<CreateGroup>();
		ArrayList<String> GroupName = new ArrayList<String>();
		
		groupResult = createGroupDAO.getAdminGroups(LogInUser);
		Iterator<CreateGroup> AdminGroupIterator = groupResult.iterator();
		
		while(AdminGroupIterator.hasNext()) {
			GroupName.add(AdminGroupIterator.next().getGroupName());
		}
		return  GroupName;
		
	}
	public void newTag(String categoria) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		TagDAO tagDAO = new TagDAO(connection);
		Tag tag = new Tag();
		tag.setCategory(categoria);
		tagDAO.insertNewTag(tag);
	}
	public ArrayList<String> setCategory() throws ClassNotFoundException, SQLException, IOException, RuntimeException{
		TagDAO tagDAO = new TagDAO(connection);
		ArrayList<Tag> tagList = tagDAO.getTag();
		ArrayList<String> tagListString = new ArrayList<String>();
		Iterator<Tag> tagIterator = tagList.iterator();
		
		while(tagIterator.hasNext()) {
			tagListString.add(tagIterator.next().getCategory());
		}
		return tagListString;
	}
	public void deleteGroup(String groupName) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		GroupDAO groupDAO = new GroupDAO(connection);
		CreateGroupDAO createGroupDAO = new CreateGroupDAO(connection);
		Group SelectedGroup = groupDAO.GetGroupByName(groupName);
		createGroupDAO.deleteGroup(SelectedGroup);
		
	}
}
