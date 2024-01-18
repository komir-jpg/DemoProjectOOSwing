package DemoUninaSN.OO_Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionControllerToDB {

	public Connection getConnectionController() throws SQLException,ClassNotFoundException,IOException,RuntimeException{
		final String nomeSchema = "progettodb_unina_social_network";
		Connection connection = null;
		DBConnection dbConnection = DBConnection.getDBConnection();
		connection = dbConnection.getConnectionBySchema(nomeSchema);
		
		if(connection == null) {
			System.out.println("ERRORE");
			return null;
		}
		else {
			System.out.println("Connessione riuscita");
			return connection;
		}
		
		
		
		
	}

}
