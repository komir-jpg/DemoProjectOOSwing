package Controllers;

import java.sql.SQLException;
import javax.swing.JFrame;

import Boundaries.RegisterUserFrame;
import DAO.UserDAO;
import Entities.User;

public class RegisterUserController {

	
	private RegisterUserFrame registerFrame;
	private LoginController loginController;
	
	
	
	public RegisterUserController(JFrame previousFrame,LoginController loginController) {
		setRegisterFrame(previousFrame);
		this.loginController = loginController;
	}
	
	public void setRegisterFrame(JFrame previousFrame) {
		registerFrame = new RegisterUserFrame(this);
		registerFrame.setLocationRelativeTo(previousFrame);
		registerFrame.setVisible(true);
		previousFrame.setVisible(false);
		previousFrame.dispose();
	}
	

	public void setLoginFrame(JFrame previousFrame) {
		loginController.setLoginFrame(previousFrame);
	}
	
		
	public void InsertNewUser(String name, String surname, String sex, String userName,String email, String password) throws SQLException 
	{
		User user = new User(name,surname,sex,userName,email,password);
		UserDAO userDao = new UserDAO();
		userDao.SaveNewUser(user);
	}
	
	
	
}
