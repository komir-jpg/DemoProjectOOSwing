package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionToDB {

	public Connection getConnection() {
		final String nomeSchema = "progettodb_unina_social_network";
		Connection connection = null;
		try {
			DBConnection dbConnection = DBConnection.getDBConnection();
			connection = dbConnection.getConnectionBySchema(nomeSchema);
		} catch (ClassNotFoundException | SQLException | IOException | RuntimeException e) {
			e.printStackTrace();
		}
		
		if(connection == null) {
			System.out.println("ERRORE");
			return null;
		}
		else {
			return connection;
		}
		
		
		
		
	}

}
