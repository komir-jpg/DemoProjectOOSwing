package DemoUninaSN.OO_Project;

public class Controller {
	Login loginScreen = new Login(this);
	StartPage startpage = new StartPage(this);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Controller();

	}
	public Controller() {
		startpage.setVisible(true);
	}
	public void startPage() {
		startpage.setVisible(true);
		loginScreen.setVisible(false);
		
	}
	public void loginScreen() {
		startpage.setVisible(false);
		loginScreen.setVisible(true);
		
	}
	
	
	

}
