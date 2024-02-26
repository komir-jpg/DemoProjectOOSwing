package DemoUninaSN.OO_Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CreateGroupDAO extends getIdDAO{

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedstatement;
	private PreparedStatement secondPreparedStatement;
	
	public CreateGroupDAO(Connection myConnection) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		super(myConnection);
		connection = myConnection;
	}
	public void createNewGroup(CreateGroup createGroup,User userAdmin) {
		int userID = getUserID(userAdmin);
		try {
			statement = connection.createStatement();
			String insertNewGroup = ("INSERT INTO progettobd_unina_social_network.crea VALUES"+"("+
									"DEFAULT"+","+
									"\'"+userID+"\'"+","
									 +"\'"+createGroup.getGroupName()+"\'"+","
									 +"\'"+createGroup.getDateOfCreation()+"\'"+","
									 +"\'"+createGroup.getDescription()+"\'"+","
									 +"\'"+createGroup.getNumberOfPartecipants()+"\'"+","
									 +"\'"+createGroup.getCategory()+"\'"+
									 ")");
			statement.executeUpdate(insertNewGroup);
			statement.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	
	}
	public void deleteGroup(Group group) {
		int groupID = getGroupID(group);
		String deleteGroup = ("DELETE from progettobd_unina_social_network.CREA WHERE idgruppo = ?");
		String deleteGroupOG = ("DELETE from progettobd_unina_social_network.gruppo WHERE idgruppo = ?");
		try {
			secondPreparedStatement = connection.prepareStatement(deleteGroupOG);
			preparedstatement = connection.prepareStatement(deleteGroup);
			secondPreparedStatement.setInt(1, groupID);
			preparedstatement.setInt(1, groupID);
			secondPreparedStatement.executeUpdate();
			preparedstatement.executeUpdate();
			secondPreparedStatement.close();
			preparedstatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<CreateGroup> getAdminGroups(User userAdmin){
		int userID = getUserID(userAdmin);
		
		String getGroupQuery = ("SELECT * FROM progettobd_unina_social_network.crea where idadmin = ?");
		try {
			preparedstatement = connection.prepareStatement(getGroupQuery);
			preparedstatement.setInt(1, userID);
			ResultSet queryRS = preparedstatement.executeQuery();
			ArrayList<CreateGroup> queryResultGroup = new ArrayList<CreateGroup>();
			while(queryRS.next()) {
				CreateGroup groupResult = new CreateGroup();
				groupResult.setDateOfCreation(queryRS.getString("datacreazione"));
				groupResult.setDescription(queryRS.getString("descrizione"));
				groupResult.setGroupName(queryRS.getString("nomegruppo"));
				groupResult.setNumberOfPartecipants(queryRS.getInt("numeropartecipanti"));
				queryResultGroup.add(groupResult);
				
				
			}
			queryRS.close();
			preparedstatement.close();
			return queryResultGroup;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
		
	}
	
}
