package DemoUninaSN.OO_Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateGoupDAO extends getIdDAO{

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedstatement;
	public CreateGoupDAO(Connection myConnection) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		super(myConnection);
	}
	public void createNewGroup(CreateGroup createGroup,User userAdmin) {
		int userID = getUserID(userAdmin);
		try {
			statement = connection.createStatement();
			String insertNewGroup = ("INSERT INTO progettobd_unina_social_network.CREA VALUES"+
									"DEFAULT"+","+
									"\""+userID+"\""+","
									 +"\'"+createGroup.getGroupName()+"\'"+","
									 +"\'"+createGroup.getDateOfCreation()+"\'"+","
									 +"\'"+createGroup.getDescription()+"\'"+","
									 +"\'"+createGroup.getNumberOfPartecipants()+"\'"+")");
			statement.executeUpdate(insertNewGroup);
			statement.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
	}
	public void deleteGroup(Group group) {
		int groupID = getGroupID(group);
		String deleteGroup = ("DELETE from progettobd_unina_social_network.CREA WHERE idgruppo = ?");
		try {
			preparedstatement = connection.prepareStatement(deleteGroup);
			preparedstatement.setInt(1, groupID);
			preparedstatement.executeUpdate();
			preparedstatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
