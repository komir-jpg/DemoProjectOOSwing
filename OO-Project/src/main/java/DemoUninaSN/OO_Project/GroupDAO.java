package DemoUninaSN.OO_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
									
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
