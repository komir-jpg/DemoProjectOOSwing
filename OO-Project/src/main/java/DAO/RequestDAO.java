package DAO;

import java.sql.*;

public class RequestDAO {

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private CallableStatement callablestatement;
	
	
	public  RequestDAO() throws SQLException{
		ConnectionToDB connectionToDB = new ConnectionToDB();
		connection = connectionToDB.getConnection();
	}
	
	public void insertNewRequest(Request request) throws SQLException {
		String userID = request.getUser().getUserName();
		String groupID = request.getGroupRequesting().getGroupName();
		
		String insertNewRequest = "INSERT INTO progettobd_unina_social_network.richiesta_partecipazione values ("+
								  "DEFAULT"+","
								  +"\'"+request.getRequestState()+"\'"+","
								  +"\'"+userID+"\'"+","
								  +"\'"+groupID+"\'"+")";
		
			statement = connection.createStatement();
			statement.executeUpdate(insertNewRequest);
			request.setIdRequest(getRequestID(request));
			statement.close();
						
	}
	public void requestAccepted(Request request) throws SQLException {
		String userID = request.getUser().getUserName();
		String groupID = request.getGroupRequesting().getGroupName();
		String requestAccepted = "UPDATE progettobd_unina_social_network.richiesta_partecipazione set statoRichiesta = accettata where idGruppo = ? and idUtente = ?";
		
		
			preparedStatement = connection.prepareStatement(requestAccepted);
			preparedStatement.setString(1, groupID);
			preparedStatement.setString(2, userID);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		
	}
	
	public void requestDenied(Request request) throws SQLException {
		String userID = request.getUser().getUserName();
		String groupID = request.getGroupRequesting().getGroupName();
		String requestAccepted = "UPDATE progettobd_unina_social_network.richiesta_partecipazione set statoRichiesta = rifiutata where idGruppo = ? and idUtente = ?";
		
		
			preparedStatement = connection.prepareStatement(requestAccepted);
			preparedStatement.setString(1, groupID);
			preparedStatement.setString(2, userID);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		
	}
	public int getRequestID(Request request) throws SQLException {
			int requestID;
			callablestatement = connection.prepareCall("{? = call progettobd_unina_social_network.getrequestid(?,?)}");
			callablestatement.registerOutParameter(1, Types.INTEGER);
			callablestatement.setString(2, request.getUser().getUserName());
			callablestatement.setString(3, request.getGroupRequesting().getGroupName());
			callablestatement.execute();
			requestID = callablestatement.getInt(1);
			callablestatement.close();
			return requestID;
			
	}
}
