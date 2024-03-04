package Controllers;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

import Boundaries.HomePage;
import Boundaries.Login;
import Boundaries.RegisterUserFrame;
import DemoUninaSN.OO_Project.User;
import DemoUninaSN.OO_Project.UserDAO;

public class RegisterUserController {

	
	private RegisterUserFrame registerFrame;
	private LoginController loginController;
	
	
	
	public RegisterUserController(JFrame previousFrame) {
		setRegisterFrame(previousFrame);
	}
	
	public void setRegisterFrame(JFrame previousFrame) {
		registerFrame = new RegisterUserFrame(this);
		SetFramePosition(registerFrame,GetFramePosition(previousFrame));
		SetFrameSize(registerFrame, GetFrameSize(previousFrame));
		registerFrame.setVisible(true);
		previousFrame.setVisible(false);
		previousFrame.dispose();
	}
	
	
	
	public void setLoginFrame(JFrame previousFrame) {
		loginController = new LoginController();
		loginController.setLoginFrame(previousFrame);
	}
	
	
	private Point GetFramePosition(JFrame frame) {
		Point point;
		point = frame.getLocationOnScreen();
		return point;
	}
	private void SetFramePosition(JFrame frame,Point point) {
		frame.setLocation(point);
	}
	
	private Dimension GetFrameSize(JFrame frame) {
		Dimension dimension;
		dimension = frame.getSize();
		return dimension;
	}
	private void SetFrameSize(JFrame frame,Dimension dimension) {
		frame.setSize(dimension);
	}
	

	
	/*
	 * 
	 * LOGIN FUNCTIONS
	 * 
	 */
	public void InsertNewUser(String name, String surname, String sex, String userName,String email, String password) throws SQLException 
	{
		User user = new User(name,surname,sex,userName,email,password);
		UserDAO userDao = new UserDAO();
		userDao.SaveNewUser(user);
	}
	
	
	
}
