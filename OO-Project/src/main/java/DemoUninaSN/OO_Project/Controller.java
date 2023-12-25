package DemoUninaSN.OO_Project;

public class Controller {
	Login LoginFrame;
	HomePage LoginSuccessoframe;
	RegisterUserFrame RegisterFrame;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Controller();
	}
	public Controller() {
		LoginFrame = new Login(this);
		LoginFrame.setVisible(true);
	}
	
	public void setHomePageFrame() {
		LoginSuccessoframe = new HomePage(this);
		LoginFrame.setVisible(false);
		LoginSuccessoframe.setVisible(true);
	}
	public void setRegisterFrame() {
		RegisterFrame = new RegisterUserFrame();
		RegisterFrame.setVisible(true);
		LoginFrame.setVisible(false);
	}

}
