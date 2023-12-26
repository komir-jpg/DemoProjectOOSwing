package DemoUninaSN.OO_Project;

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
		Point point;
		LoginSuccessoframe = new HomePage(this);
		point = GetFramePosition(LoginFrame);
		LoginFrame.setVisible(false);
		LoginSuccessoframe.setVisible(true);
		SetFramePosition(LoginSuccessoframe, point);
		
	}
	public void setRegisterFrame() {
		RegisterFrame = new RegisterUserFrame();
		RegisterFrame.setVisible(true);
		LoginFrame.setVisible(false);
	}
	private Point GetFramePosition(JFrame frame) {
		Point point;
		point = frame.getLocationOnScreen();
		return point;
	}
	private void SetFramePosition(JFrame frame,Point point) {
		frame.setLocation(point);
	}
	

}
