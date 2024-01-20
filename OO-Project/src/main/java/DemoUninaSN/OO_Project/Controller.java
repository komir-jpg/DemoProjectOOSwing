package DemoUninaSN.OO_Project;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JFrame;

import ExceptionPackage.DBconnectionError;

public class Controller {
	Login LoginFrame;
	HomePage LoginSuccessoframe;
	RegisterUserFrame RegisterFrame;
	private Connection connection;
	private User user;
	private UserDAO userDao;
	private ArrayList<User> userQueryResult;
	private Iterator<User> userIterator;
	
	public static void main(String[] args) {
		new Controller();
	}
	
	
	public Controller() {
		LoginFrame = new Login(this);
		LoginFrame.setVisible(true);
		
	}
	
	public void setHomePageFrame() {
		LoginSuccessoframe = new HomePage(this);
		SetFramePosition(LoginSuccessoframe, GetFramePosition(LoginFrame));
		SetFrameSize(LoginSuccessoframe,GetFrameSize(LoginFrame));
		LoginFrame.setVisible(false);
		LoginFrame.dispose();
		LoginSuccessoframe.setVisible(true);
		
		
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
	public void newUser() {
		user = new User(); 
	}
	public void getNameField(String name){
		user.setName(name);
	}
	public void getSurnameField(String surname) {
		user.setSurname(surname);
	}
	public void getDateOfBirthField(Date dateOfBirth) {
		user.setDateofBirth(dateOfBirth);
	}
	public void getSexField(String sex) {
		user.setSex(sex);
	}
	public void getUsername(String username) {
		user.setUserName(username);
	}
	public void getEmailField(String email) {
		user.setEmail(email);
	}
	public void getPasswordField(String password) {
		user.setPassword(password);
	}
	
	public void InsertNewUser(int idIncrement) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		System.out.println(connection);
		userDao = new UserDAO(connection);
		userDao.SaveNewUser(user,idIncrement);
	}
	public void getUserByName() throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		UserDAO userDaoConnection = new UserDAO(connection);
		userQueryResult = userDaoConnection.getUserbyName("mirko");
		userIterator = userQueryResult.iterator();
		while(userIterator.hasNext()) {
			System.out.println(userIterator.next()+"\n");
		}
		
	};
	
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
	
	
}
