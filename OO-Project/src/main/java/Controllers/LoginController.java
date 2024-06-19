package Controllers;

import java.awt.Dimension;
import java.awt.Point;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Boundaries.Login;
import DAO.*;
import Entities.User;

public class LoginController {
	private Login loginFrame;
	private User LogInUser;
	private UserDAO userDao;
	
	
	
	
	public static void main(String[] args) {
		new LoginController();
	}
	
	public LoginController() {
		loginFrame = new Login(this);
		userDao = new UserDAO();
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setVisible(true);
		
	}

	
	public void setHomePageFrame(JFrame frame) {
		new HomePageController(frame,LogInUser);
	}
	public void setLoginFrameVisible(){
		loginFrame.setVisible(true);
	}
	

	public void setRegisterFrame(JFrame frame) {
		new RegisterUserController(frame,this);
	}
	
	public void setLoginFrame(JFrame frame) {
		loginFrame = new Login(this);
		SetFrameSize(loginFrame,GetFrameSize(frame));
		frame.setVisible(false);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setVisible(true);
		frame.dispose();
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
		this.LogInUser = userDao.getUserbyUsername(username);
	}
	
	
	private ArrayList<User> GetUser() throws SQLException{
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
	
	
	
	
}
