package DemoUninaSN.OO_Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TagDAO extends getIdDAO{
	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	
	
	public TagDAO(Connection myConnection) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		super(myConnection);
		connection = myConnection;
	}
	
	public void insertNewTag(Tag tag) {
		String insertNewTag = "INSERT INTO progettobd_unina_social_network.TAG VALUES ("
							  +"\'"+tag.getCategory()+"\'"+")";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(insertNewTag);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Tag> getTag(){
		String getTag = "SELECT * FROM progettobd_unina_social_network.TAG";
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
}
