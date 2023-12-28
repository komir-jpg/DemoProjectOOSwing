package DemoUninaSN.OO_Project;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

public class Controller {
	Login LoginFrame;
	HomePage LoginSuccessoframe;
	RegisterUserFrame RegisterFrame;
	
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
