package DemoUninaSN.OO_Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestDAO extends getIdDAO{

	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	
	
	public  RequestDAO(Connection myConnection) throws ClassNotFoundException, SQLException, IOException, RuntimeException{
		super(myConnection);
	}
	
	public void insertNewRequest(Request request) {
		int userID = getUserID(request.getUser());
		int groupID = getGroupID(request.getGroup());
		
		String insertNewRequest = "INSERT INTO progettobd_unina_social_network.RICHIESTA_PARTECIPAZIONE values ("+
								  "\'DEFAULT\'"+","
								  +"\'"+request.getRequestState()+"\'"+","
								  +"\""+userID+"\""+","
								  +"\""+groupID+"\""+")";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(insertNewRequest);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
						
	}
	public void requestAccepted(Request request) {
		int userID = getUserID(request.getUser());
		int groupID = getGroupID(request.getGroup());
		String requestAccepted = "UDATE progettobd_unina_social_network.RICHIESTA_PARTECIPAZIONE set statoRichiesta = accettata where idGruppo = ? and idUtente = ?";
		
		try {
			preparedStatement = connection.prepareStatement(requestAccepted);
			preparedStatement.setInt(1, groupID);
			preparedStatement.setInt(2, userID);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void requestDnied(Request request) {
		int userID = getUserID(request.getUser());
		int groupID = getGroupID(request.getGroup());
		String requestAccepted = "UDATE progettobd_unina_social_network.RICHIESTA_PARTECIPAZIONE set statoRichiesta = rifiutata where idGruppo = ? and idUtente = ?";
		
		try {
			preparedStatement = connection.prepareStatement(requestAccepted);
			preparedStatement.setInt(1, groupID);
			preparedStatement.setInt(2, userID);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
