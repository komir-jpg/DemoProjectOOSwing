package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class TagDAO{
	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	private CallableStatement callableStatement;
	
	
	public TagDAO() {
		ConnectionToDB connectionToDB = new ConnectionToDB();
		connection = connectionToDB.getConnection();
	}
	
	public void insertNewTag(Tag tag) throws SQLException {
		String insertNewTag = "INSERT INTO progettobd_unina_social_network.TAG VALUES ("
							  +"\'"+tag.getTag()+"\'"+")";
		
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
				tag.setTag(queryRS.getString("categoria"));
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
				queryResultTag.setTag(queryRS.getString("categoria"));
			}
			queryRS.close();
			preparedStatement.close();
			return queryResultTag;
	}
	public ArrayList<Group> getGroupByTag(Tag tag) throws SQLException/*string idTag*/{
		String getGroupByTagQuery = ("SELECT * FROM progettobd_unina_social_network.gruppo where categoria = ?");
		
			preparedStatement = connection.prepareStatement(getGroupByTagQuery);
			preparedStatement.setString(1,tag.getTag());
			ResultSet queryRS = preparedStatement.executeQuery();
			ArrayList<Group> queryResultGroup = new ArrayList<Group>();
			while(queryRS.next()) {
				Group groupResult = new Group();
				groupResult.setDateOfCreation(queryRS.getDate("datacreazione"));
				groupResult.setDescription(queryRS.getString("descrizione"));
				groupResult.setGroupName(queryRS.getString("nomegruppo"));
				groupResult.setNumberOfPartecipants(queryRS.getInt("numeropartecipanti"));
//				groupResult.setGroupPosts(new PostDAO().getPostsByGroup(queryRS.getString("idgruppo")));
				queryResultGroup.add(groupResult);
			}
			queryRS.close();
			preparedStatement.close();
			return queryResultGroup;
	}
	public ArrayList<Group> getGroupByTag(String tag) throws SQLException/*string idTag*/{
		String getGroupByTagQuery = ("SELECT * FROM progettobd_unina_social_network.tag:gruppo where categoria = ?");
		
			preparedStatement = connection.prepareStatement(getGroupByTagQuery);
			preparedStatement.setString(1,tag);
			ResultSet queryRS = preparedStatement.executeQuery();
			ArrayList<Group> queryResultGroup = new ArrayList<Group>();
			while(queryRS.next()) {
				Group groupResult = new Group();
				groupResult.setDateOfCreation(queryRS.getDate("datacreazione"));
				groupResult.setDescription(queryRS.getString("descrizione"));
				groupResult.setGroupName(queryRS.getString("nomegruppo"));
				groupResult.setNumberOfPartecipants(queryRS.getInt("numeropartecipanti"));
//				groupResult.setGroupPosts(new PostDAO().getPostsByGroup(queryRS.getString("idgruppo")));
				queryResultGroup.add(groupResult);
			}
			queryRS.close();
			preparedStatement.close();
			return queryResultGroup;
	}
	public ArrayList<Tag> getGroupTags(Group group) throws SQLException{
		preparedStatement = connection.prepareStatement("select * from tag as t join tagGruppo as tg on t.tag = tg.tag where tg.idGruppo = ?");
		preparedStatement.setString(1, group.getGroupName());
		ResultSet queryRS = preparedStatement.executeQuery();
		ArrayList<Tag> tagResult = new ArrayList<Tag>();
		while(queryRS.next()) {
			Tag tag = new Tag();
			tag.setTag(queryRS.getString("tag"));
			tag.setGroupsSameTag(new TagDAO().getGroupByTag(queryRS.getString("tag")));
			tagResult.add(tag);
		}
		preparedStatement.close();
		queryRS.close();
		return tagResult;
	}
	public ArrayList<Tag> getGroupTags(String group) throws SQLException{
		preparedStatement = connection.prepareStatement("select * from tag as t join tagGruppo as tg on t.tag = tg.tag where tg.idGruppo = ?");
		preparedStatement.setString(1, group);
		ResultSet queryRS = preparedStatement.executeQuery();
		ArrayList<Tag> tagResult = new ArrayList<Tag>();
		while(queryRS.next()) {
			Tag tag = new Tag();
			tag.setTag(queryRS.getString("tag"));
			tag.setGroupsSameTag(getGroupByTag(queryRS.getString("tag")));
			tagResult.add(tag);
		}
		preparedStatement.close();
		queryRS.close();
		return tagResult;
	}

	public void setGroupTag(String groupName, String selectedTag) throws SQLException {
		String insertNewGroupTag = ("insert into progettobd_unina_social_network.tag_gruppo values("+
									"\'"+selectedTag+"\'"+","+"\'"+groupName+"\'"+")");
		statement = connection.createStatement();
		statement.executeUpdate(insertNewGroupTag);
		statement.close();
	}
	public ArrayList<String>setGroupByTag(String tag) throws SQLException{
		resetBufferTable();
		getGroupFunction(tag);
		preparedStatement = connection.prepareStatement("select * from progettobd_unina_social_network.bufferResultTable");
		ResultSet queryRS = preparedStatement.executeQuery();
		ArrayList<String> resultString = new ArrayList<String>();
		while(queryRS.next()) {
			resultString.add(queryRS.getString("idgruppo"));
		}
		queryRS.close();
		return resultString;
	}

	private void getGroupFunction(String tags) throws SQLException {
		callableStatement = connection.prepareCall("{? = CALL progettobd_unina_social_network.getgroupbytag(?)}");
		callableStatement.registerOutParameter(1, Types.VARCHAR);
		callableStatement.setString(2, tags);
		callableStatement.executeUpdate();
		callableStatement.close();
	}
	private void resetBufferTable() throws SQLException {
		String resetBufferTable = "delete from progettobd_unina_social_network.bufferResultTable";
		statement = connection.createStatement();
		statement.executeUpdate(resetBufferTable);
		statement.close();
	}
}
