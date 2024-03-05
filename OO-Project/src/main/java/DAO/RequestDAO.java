package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestDAO {

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	
	
	public  RequestDAO() throws ClassNotFoundException, SQLException, IOException, RuntimeException{
		ConnectionToDB connectionToDB = new ConnectionToDB();
		connection = connectionToDB.getConnection();
	}
	
	public void insertNewRequest(Request request) throws SQLException {
		String userID = request.getUser().getUserName();
		String groupID = request.getGroup().getGroupName();
		
		String insertNewRequest = "INSERT INTO progettobd_unina_social_network.PARTECIPAZIONE values ("+
								  "DEFAULT"+","
								  +"\'"+request.getRequestState()+"\'"+","
								  +"\'"+userID+"\'"+","
								  +"\'"+groupID+"\'"+")";
		
			statement = connection.createStatement();
			statement.executeUpdate(insertNewRequest);
			statement.close();
						
	}
	public void requestAccepted(Request request) throws SQLException {
		String userID = request.getUser().getUserName();
		String groupID = request.getGroup().getGroupName();
		String requestAccepted = "UDATE progettobd_unina_social_network.PARTECIPAZIONE set statoRichiesta = accettata where idGruppo = ? and idUtente = ?";
		
		
			preparedStatement = connection.prepareStatement(requestAccepted);
			preparedStatement.setString(1, groupID);
			preparedStatement.setString(2, userID);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		
	}
	
	public void requestDenied(Request request) throws SQLException {
		String userID = request.getUser().getUserName();
		String groupID = request.getGroup().getGroupName();
		String requestAccepted = "UDATE progettobd_unina_social_network.PARTECIPAZIONE set statoRichiesta = rifiutata where idGruppo = ? and idUtente = ?";
		
		
			preparedStatement = connection.prepareStatement(requestAccepted);
			preparedStatement.setString(1, groupID);
			preparedStatement.setString(2, userID);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		
	}


}
