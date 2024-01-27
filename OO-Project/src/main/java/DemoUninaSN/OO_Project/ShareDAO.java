package DemoUninaSN.OO_Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ShareDAO extends getIdDAO {

	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	
	
	public ShareDAO(Connection myConnection) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		super(myConnection);
	}
	
	public void newSharedPost(Share sharedPost) {
		int postID = getPostID(sharedPost.getPostShared());
		int groupID = getGroupID(sharedPost.getGroupSharedPost());
		int userID = getUserID(sharedPost.getUserSharing());
		
		String insertNewSharedPost= "INSERT INTO progettobd_unina_social_network.CONDIVISIONE_POST VALUES ("
				 + "\'DEFAULT\'"
				 +"\""+postID+"\""+","
				 +"\""+groupID+"\""+","
				 +"\'"+sharedPost.getShareDate()+"\'"
				 +"\'"+userID+"\'"+")";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(insertNewSharedPost);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

}
