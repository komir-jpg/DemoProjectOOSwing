package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GroupTagDAO extends getIdDAO{
	private Connection connection;
	private PreparedStatement preparedStatement;
	private Statement statement;

	public GroupTagDAO(Connection myConnection) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		super(myConnection);
	}

	public void insertTagGroup(GroupTag tag) {
		int groupID = getGroupID(tag.getGroupName());
		try {
			statement = connection.createStatement();
			String insertNewGroupTag = "INSERT INTO progettobd_unina_social_network.Tag_gruppo VALUES"+
									    "("+ 
									    "\""+groupID+"\""+","
									    +"\'"+tag.getCategory()+"\'"+","
									    +"\'"+tag.getGroupName()+"\'"+")";
			statement.executeUpdate(insertNewGroupTag);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Group> getGroupByTag(Tag tag)/*string idTag*/{
		String getGroupByTagQuery = ("SELECT * FROM progettobd_unina_social_network.gruppo where categoria = ?");
		try {
			preparedStatement = connection.prepareStatement(getGroupByTagQuery);
			preparedStatement.setString(1,tag.getCategory());
			ResultSet queryRS = preparedStatement.executeQuery();
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
			preparedStatement.close();
			return queryResultGroup;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
}

