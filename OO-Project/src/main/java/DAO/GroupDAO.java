package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GroupDAO {

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedstatement;
	
	public GroupDAO() {
		ConnectionToDB connectionToDB = new ConnectionToDB();
		connection = connectionToDB.getConnection();
	}
	public void createNewGroup(Group createGroup,User userAdmin) throws SQLException {
		String userID = userAdmin.getUserName();
		
			statement = connection.createStatement();
			String insertNewGroup = ("INSERT INTO progettobd_unina_social_network.gruppo VALUES"+"("
									 +"\'"+createGroup.getGroupName()+"\'"+","
									 +"\'"+userID+"\'"+","
									 +"\'"+createGroup.getDateOfCreation()+"\'"+","
									 +"\'"+createGroup.getDescription()+"\'"+","
									 +"\'"+createGroup.getNumberOfPartecipants()+"\'"+
									 ")");
			statement.executeUpdate(insertNewGroup);
			statement.close();
	}
	public void deleteGroup(Group group) throws SQLException/*string group name*/ {
		String groupID = group.getGroupName();
		String deleteGroup = ("DELETE from progettobd_unina_social_network.gruppo WHERE nomegruppo = ?");
			preparedstatement = connection.prepareStatement(deleteGroup);
			preparedstatement.setString(1, groupID);
			preparedstatement.executeUpdate();
			preparedstatement.close();
			
	}
	public ArrayList<Group> getAdminGroups(User userAdmin) throws SQLException/*String nomeUtente*/{
		String userID = userAdmin.getUserName();
		
		String getGroupQuery = ("SELECT * FROM progettobd_unina_social_network.gruppo where idadmin = ?");
		
			preparedstatement = connection.prepareStatement(getGroupQuery);
			preparedstatement.setString(1, userID);
			ResultSet queryRS = preparedstatement.executeQuery();
			ArrayList<Group> queryResultGroup = new ArrayList<Group>();
			while(queryRS.next()) {
				Group groupResult = new Group();
				groupResult.setDateOfCreation(queryRS.getDate("datacreazione"));
				groupResult.setDescription(queryRS.getString("descrizione"));
				groupResult.setGroupName(queryRS.getString("nomegruppo"));
				groupResult.setNumberOfPartecipants(queryRS.getInt("numeropartecipanti"));
				groupResult.setAdmin(userAdmin);
//				groupResult.setGroupPosts(new PostDAO().getPostsByGroup(queryRS.getString("nomegruppo")));
//				groupResult.setGroupUsers(new UserDAO().getUsersByGroup(queryRS.getString("nomegruppo")));
				queryResultGroup.add(groupResult);	
			}
			queryRS.close();
			preparedstatement.close();
			return queryResultGroup;
		
	}
	
	public Group GetGroupByName(String groupName) throws SQLException{
		
			preparedstatement = connection.prepareStatement("SELECT * FROM progettobd_unina_social_network.GRUPPO "
					+ "WHERE nomegruppo LIKE ?");
			preparedstatement.setString(1,groupName);
			ResultSet queryRS = preparedstatement.executeQuery();
			Group groupResult = new Group();
			while(queryRS.next()) {
				groupResult.setGroupName(queryRS.getString("nomegruppo"));
				groupResult.setDateOfCreation(queryRS.getDate("datacreazione"));
				groupResult.setDescription(queryRS.getString("descrizione"));
				groupResult.setNumberOfPartecipants(queryRS.getInt("numeropartecipanti"));
				groupResult.setAdmin(new UserDAO().getUserAdmin(groupName));
//				groupResult.setGroupUsers(new UserDAO().getUsersByGroup(groupName));
//				groupResult.setGroupPosts(new PostDAO().getPostsByGroup(groupName));
			}
			queryRS.close();
			preparedstatement.close();
			return groupResult;
			
			
	}
	/*
	 * select * from post as p join group as g on p.idPost = g.idPost where g.nomeGruppo = ?
	 * 
	 */
	public ArrayList<Group> getUserGroup(User user) throws SQLException{
		preparedstatement = connection.prepareStatement("select * from progettobd_unina_social_network.gruppo as g join progettobd_unina_social_network.partecipa as p on g.nomegruppo = p.idgruppo"
				+ " where p.idutente = ? and g.nomegruppo not in (select gr.nomegruppo from progettobd_unina_social_network.gruppo as gr where gr.idadmin = ?)");
		preparedstatement.setString(1, user.getUserName());
		preparedstatement.setString(2, user.getUserName());
		ResultSet queryRS = preparedstatement.executeQuery();
		ArrayList<Group> groupResult = new ArrayList<Group>();
		while(queryRS.next()) {
			Group group = new Group();
			group.setGroupName(queryRS.getString("nomegruppo"));
			group.setDateOfCreation(queryRS.getDate("datacreazione"));
			group.setDescription(queryRS.getString("descrizione"));
			group.setNumberOfPartecipants(queryRS.getInt("numeropartecipanti"));
			group.setAdmin(new UserDAO().getUserAdmin(queryRS.getString("nomegruppo")));
			groupResult.add(group);
		}
		queryRS.close();
		preparedstatement.close();
		return groupResult;
	}
	public void deletePartecipant(Group group,String username) throws SQLException {
		preparedstatement = connection.prepareStatement("delete from progettobd_unina_social_network.partecipa where idGruppo = ? and idUtente = ?");
		preparedstatement.setString(1, group.getGroupName());
		preparedstatement.setString(2, username);
		preparedstatement.executeUpdate();
		preparedstatement.close();
	}
	
	
	
}
