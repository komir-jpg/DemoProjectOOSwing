package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ShareDAO {

	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	
	
	public ShareDAO() throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		ConnectionToDB connectionToDB = new ConnectionToDB();
		connection = connectionToDB.getConnection();
	}
	
	public void newSharedPost(Share sharedPost,Group group,User user) throws SQLException {
		int postID = sharedPost.getPostShared().getIdPost();
		String groupID = group.getGroupName();
		String userID = user.getUserName();
		
		String insertNewSharedPost= "INSERT INTO progettobd_unina_social_network.CONDIVISIONE VALUES ("
				 + "\'DEFAULT\'"
				 +"\""+postID+"\""+","
				 +"\""+groupID+"\""+","
				 +"\'"+sharedPost.getShareDate()+"\'"
				 +"\'"+userID+"\'"+")";
		
			statement = connection.createStatement();
			statement.executeUpdate(insertNewSharedPost);
			statement.close();
		
	}
	public void deleteSharedPost(Share sharedPost,Group group) throws SQLException {
		int postID = sharedPost.getPostShared().getIdPost();
		String groupID = group.getGroupName();
		String userID = sharedPost.getUserSharing().getUserName();
		
		String deleteSharedPost = "DELETE FROM progettobd_unina_social_network.CONDIVISIONE WHERE postcondiviso = ? "
				+ "and gruppocondivisione = ? and idUtenteCondivide = ?;";
		
			preparedStatement = connection.prepareStatement(deleteSharedPost);
			preparedStatement.setInt(1, postID);
			preparedStatement.setString(2, groupID);
			preparedStatement.setString(3, userID);
			preparedStatement.executeUpdate(deleteSharedPost);
			preparedStatement.close();
		
	}
	

}
