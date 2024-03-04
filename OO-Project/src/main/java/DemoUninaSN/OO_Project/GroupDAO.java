package DemoUninaSN.OO_Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GroupDAO extends getIdDAO{

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedstatement;
	
	public GroupDAO(Connection myConnection) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		super(myConnection);
		connection = myConnection;
	}
	public void createNewGroup(Group createGroup,User userAdmin) {
		int userID = getUserID(userAdmin);
		try {
			statement = connection.createStatement();
			String insertNewGroup = ("INSERT INTO progettobd_unina_social_network.gruppo VALUES"+"("+
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
	public void deleteGroup(Group group)/*string group name*/ {
		int groupID = getGroupID(group);
		String deleteGroup = ("DELETE from progettobd_unina_social_network.gruppo WHERE idgruppo = ?");
		try {
			preparedstatement = connection.prepareStatement(deleteGroup);
			preparedstatement.setInt(1, groupID);
			preparedstatement.executeUpdate();
			preparedstatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Group> getAdminGroups(User userAdmin)/*String nomeUtente*/{
		int userID = getUserID(userAdmin);
		
		String getGroupQuery = ("SELECT * FROM progettobd_unina_social_network.gruppo where idadmin = ?");
		try {
			preparedstatement = connection.prepareStatement(getGroupQuery);
			preparedstatement.setInt(1, userID);
			ResultSet queryRS = preparedstatement.executeQuery();
			ArrayList<Group> queryResultGroup = new ArrayList<Group>();
			while(queryRS.next()) {
				Group groupResult = new Group();
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
	public ArrayList<Group> getGroupByTag(Tag tag)/*string idTag*/{
		String getGroupByTagQuery = ("SELECT * FROM progettobd_unina_social_network.gruppo where categoria = ?");
		try {
			preparedstatement = connection.prepareStatement(getGroupByTagQuery);
			preparedstatement.setString(1,tag.getCategory());
			ResultSet queryRS = preparedstatement.executeQuery();
			ArrayList<Group> queryResultGroup = new ArrayList<Group>();
			while(queryRS.next()) {
				Group groupResult = new Group();
				groupResult.setCategory(queryRS.getString("categoria"));
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
	public Group GetGroupByName(String groupName){
		try {
			preparedstatement = connection.prepareStatement("SELECT * FROM progettobd_unina_social_network.GRUPPO WHERE nomegruppo LIKE ?");
			preparedstatement.setString(1,groupName);
			ResultSet queryRS = preparedstatement.executeQuery();
			Group groupResult = new Group();
			while(queryRS.next()) {
				groupResult.setGroupName(queryRS.getString("nomegruppo"));
				groupResult.setDateOfCreation(queryRS.getString("datacreazione"));
				groupResult.setDescription(queryRS.getString("descrizione"));
				groupResult.setNumberOfPartecipants(queryRS.getInt("numeropartecipanti"));
			}
			queryRS.close();
			preparedstatement.close();
			return groupResult;
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
}
