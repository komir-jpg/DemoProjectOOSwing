package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TagDAO{
	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	
	
	public TagDAO() {
		ConnectionToDB connectionToDB = new ConnectionToDB();
		connection = connectionToDB.getConnection();
	}
	
	public void insertNewTag(Tag tag) throws SQLException {
		String insertNewTag = "INSERT INTO progettobd_unina_social_network.TAG VALUES ("
							  +"\'"+tag.getCategory()+"\'"+")";
		
			statement = connection.createStatement();
			statement.executeUpdate(insertNewTag);
			statement.close();
	}
	public ArrayList<Tag> getTag() throws SQLException{
		String getTag = "SELECT * FROM progettobd_unina_social_network.TAG";
		
			preparedStatement = connection.prepareStatement(getTag);
			ResultSet queryRS = preparedStatement.executeQuery();
			ArrayList<Tag> queryResultTag = new ArrayList<Tag>();
			while(queryRS.next()) {
				Tag tag = new Tag();
				tag.setCategory(queryRS.getString("categoria"));
				queryResultTag.add(tag);
				
			}
			queryRS.close();
			preparedStatement.close();
			return queryResultTag;
	}
	
	public Tag getSingleTag(String tag) throws SQLException {
		String getTag = ("SELECT * from progettobd_unina_social_network.TAG where categoria = ?");
		
			preparedStatement = connection.prepareStatement(getTag);
			preparedStatement.setString(1, tag);
			ResultSet queryRS = preparedStatement.executeQuery();
			Tag queryResultTag = new Tag();
			while(queryRS.next()) {
				queryResultTag.setCategory(queryRS.getString("categoria"));
			}
			queryRS.close();
			preparedStatement.close();
			return queryResultTag;
	}
	public ArrayList<Group> getGroupByTag(Tag tag) throws SQLException/*string idTag*/{
		String getGroupByTagQuery = ("SELECT * FROM progettobd_unina_social_network.gruppo where categoria = ?");
		
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
	}
	
	
	
	
}
