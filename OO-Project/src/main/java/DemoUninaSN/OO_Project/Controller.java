package DemoUninaSN.OO_Project;

import java.sql.Connection;

public class Controller {
	Login loginScreen = new Login(this);
	StartPage startpage = new StartPage(this);
	Connection connection = null;
	
	public static void main(String[] args) {
		new Controller();

	}
	public Controller(){
		startpage.setVisible(true);
		
		
	}
	public void startPage() {
		startpage.setVisible(true);
		loginScreen.setVisible(false);
		
	}
	public void loginScreen() throws Exception{
		getDBconnection();
		startpage.setVisible(false);
		loginScreen.setVisible(true);
		 
	}
	
	
	public Connection getDBconnection() throws Exception {
		final String nomeSchema = "public";
		DBconnection dbConnection = DBconnection.getDBConnection();
		connection = dbConnection.getConnectionBySchema(nomeSchema);
		
		return connection;
	}
}
