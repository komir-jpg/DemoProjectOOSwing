package DemoUninaSN.OO_Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestDAO extends getIdDAO{

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	
	
	public  RequestDAO(Connection myConnection) throws ClassNotFoundException, SQLException, IOException, RuntimeException{
		super(myConnection);
		connection = myConnection;
	}
	
	public void insertNewRequest(Request request) throws SQLException {
		int userID = getUserID(request.getUser());
		int groupID = getGroupID(request.getGroup());
		
		String insertNewRequest = "INSERT INTO progettobd_unina_social_network.RICHIESTA_PARTECIPAZIONE values ("+
								  "DEFAULT"+","
								  +"\'"+request.getRequestState()+"\'"+","
								  +"\'"+userID+"\'"+","
								  +"\'"+groupID+"\'"+")";
		
			statement = connection.createStatement();
			statement.executeUpdate(insertNewRequest);
			statement.close();
						
	}
	public void requestAccepted(Request request) throws SQLException {
		int userID = getUserID(request.getUser());
		int groupID = getGroupID(request.getGroup());
		String requestAccepted = "UDATE progettobd_unina_social_network.RICHIESTA_PARTECIPAZIONE set statoRichiesta = accettata where idGruppo = ? and idUtente = ?";
		
		
			preparedStatement = connection.prepareStatement(requestAccepted);
			preparedStatement.setInt(1, groupID);
			preparedStatement.setInt(2, userID);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		
	}
	
	public void requestDenied(Request request) throws SQLException {
		int userID = getUserID(request.getUser());
		int groupID = getGroupID(request.getGroup());
		String requestAccepted = "UDATE progettobd_unina_social_network.RICHIESTA_PARTECIPAZIONE set statoRichiesta = rifiutata where idGruppo = ? and idUtente = ?";
		
		
			preparedStatement = connection.prepareStatement(requestAccepted);
			preparedStatement.setInt(1, groupID);
			preparedStatement.setInt(2, userID);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		
	}


}
