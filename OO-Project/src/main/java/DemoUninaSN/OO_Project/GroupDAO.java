package DemoUninaSN.OO_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GroupDAO {

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	
	public GroupDAO(Connection myConnection) {
		connection = myConnection;
	}
	
	public void SaveNewGroup(Group newGroup) {
		try {
			statement = connection.createStatement();
			String insertNewGroup = "INSERT INTO progettobd_unina_social_network.GRUPPO VALUES"+"("+
									"\'DEFAULT\'"+","
									 +"\'"+newGroup.getGroupName()+"\'"+","
									 +"\'"+newGroup.getCreationDate()+"\'"+","
									 +"\'"+newGroup.getDescription()+"\'"+","
									 +"\'"+newGroup.getNumberOfPatecipants()+"\'"+")";
									
			statement.executeUpdate(insertNewGroup);
			statement.clearBatch();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Group GetGroupByName(String groupName){
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM GRUPPO WHERE nomegruppo LIKE ?");
			preparedStatement.setString(1,groupName);
			ResultSet queryRS = preparedStatement.executeQuery();
			Group groupResult = new Group();
			while(queryRS.next()) {
				groupResult.setGroupName(queryRS.getString("nomegruppo"));
				groupResult.setCreationDate(queryRS.getDate("datacreazione"));
				groupResult.setDescription(queryRS.getString("descrizione"));
				groupResult.setNumberOfPatecipants(queryRS.getInt("numeropartecipanti"));
			}
			return groupResult;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

}
