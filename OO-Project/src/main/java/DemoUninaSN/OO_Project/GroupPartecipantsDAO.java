package DemoUninaSN.OO_Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GroupPartecipantsDAO extends getIdDAO{

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	
	public GroupPartecipantsDAO(Connection myConnection) throws ClassNotFoundException, SQLException, IOException, RuntimeException{
		super(myConnection);
	}
	
	public void insertPartecipant(User user,Group group) {
		int UserID = getUserID(user);
		int GroupID = getGroupID(group);
		String insertPartecipantQuery = "INSERT INTO progettobd_unina_social_network.PARTECIPA VALUES"+
										"\""+UserID+"\""+","
										+"\""+GroupID+"\""+")";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(insertPartecipantQuery);
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void deleteGroupPartecipant(Group group,User user) {
		int groupID = getGroupID(group);
		int UserID = getUserID(user);
		String deleteGroup = ("DELETE  from progettobd_unina_social_network.PARTECIPA WHERE idgruppo = ? and idutente = ?");
		try {
			preparedStatement = connection.prepareStatement(deleteGroup);
			preparedStatement.setInt(1, groupID);
			preparedStatement.setInt(2, UserID);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<User> getGroupPartecipants(Group group){
		int groupID = getGroupID(group);
		String getPartecipantsQuery = "SELECT * "
				+ "from progettobd_unina_social_network.UTENTE as U "
				+ "where u.idUtente in (select idUtente "
				+                      "from progettobd_unina_social_network.PARTECIPA as p "
				+                      "where p.idGruppo = ?)";
		try {
			preparedStatement = connection.prepareStatement(getPartecipantsQuery);
			preparedStatement.setInt(1, groupID);
			ResultSet queryRS = preparedStatement.executeQuery();
			ArrayList<User> queryResultUser = new ArrayList<User>();
			while(queryRS.next()) {
				User user = new User();
				user.setName(queryRS.getString("nome"));
				user.setSurname(queryRS.getString("cognome"));
				user.setSubsriptionDate(queryRS.getDate("dataiscrizione"));
				user.setDateofBirth(queryRS.getDate("dataiscrizione"));
				user.setEmail(queryRS.getString("email"));
				user.setPassword(queryRS.getString("password"));
				user.setSex(queryRS.getString("sesso"));
				user.setUserName(queryRS.getString("nomeutente"));
				user.setUserType(queryRS.getString("tipoutente"));
				queryResultUser.add(user);				
			}
			queryRS.close();
			preparedStatement.close();
			return queryResultUser;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Group> getGroupByUser(User user){
		int userID = getUserID(user);
		String getGroupsQuery = "SELECT * from progettobd_unina_social_network.GRUPPO as g where "
				+ "idGruppo in (select idGruppo from progettobd_unina_social_network.PARTECIPA as p "
				+ "where p.idUtente = ?)";
		try {
			preparedStatement = connection.prepareStatement(getGroupsQuery);
			preparedStatement.setInt(1, userID);
			ResultSet queryRS = preparedStatement.executeQuery();
			ArrayList<Group> queryResultGroup = new ArrayList<Group>();
			while(queryRS.next()) {
				Group group = new Group();
				group.setGroupName(queryRS.getString("nomegruppo"));
				group.setCreationDate(queryRS.getDate("datacreazione"));
				group.setDescription(queryRS.getString("descrizione"));
				group.setNumberOfPatecipants(queryRS.getInt("numeropartecipanti"));
				queryResultGroup.add(group);
			}
			queryRS.close();
			preparedStatement.close();
			return queryResultGroup;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
