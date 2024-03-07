package DAO;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class getIdDAO {

	private CallableStatement callablestatement;
	private Connection connection;

	public getIdDAO(Connection myConnection)throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		connection = myConnection; 
	}
	public int getUserID(User user) {
		try {
			int userID;
			callablestatement = connection.prepareCall("{? = call progettobd_unina_social_network.getuserid(?)}");
			callablestatement.registerOutParameter(1,Types.INTEGER);
			callablestatement.setString(2, user.getUserName());
			callablestatement.execute();
			userID = callablestatement.getInt(1);
			callablestatement.close();
			return userID;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return -1;
		
	}
	public int getPostID(Post post) {
		try {
			int postID;
			callablestatement = connection.prepareCall("{? = call getidpost(?)}");
			callablestatement.registerOutParameter(1,Types.INTEGER);
			callablestatement.setInt(2, post.getIdPost());
			callablestatement.execute();
			postID = callablestatement.getInt(1);
			callablestatement.close();
			return postID;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return -1;
		
	}
	
	public int getPostID(String userID) throws SQLException {
		
			int postID;
			callablestatement = connection.prepareCall("{? = call getidpost(?)}");
			callablestatement.registerOutParameter(1,Types.INTEGER);
			callablestatement.setString(2, userID);
			callablestatement.execute();
			postID = callablestatement.getInt(1);
			callablestatement.close();
			return postID;
	
	}
	
	public int getGroupID(Group group) {
	try {
		int groupID;
		callablestatement = connection.prepareCall("{? = call progettobd_unina_social_network.getgroupid(?)}");
		callablestatement.registerOutParameter(1,Types.INTEGER);
		callablestatement.setString(2, group.getGroupName());
		callablestatement.execute();
		groupID = callablestatement.getInt(1);
		callablestatement.close();
		return groupID;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return -1;
		
	}
	public int getGroupID(String groupName) {
		try {
			int groupID;
			callablestatement = connection.prepareCall("{? = call progettobd_unina_social_network.getgroupid(?)}");
			callablestatement.registerOutParameter(1,Types.INTEGER);
			callablestatement.setString(2, groupName);
			callablestatement.execute();
			groupID = callablestatement.getInt(1);
			callablestatement.close();
			return groupID;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
			
		}
	public int getCommentID(Comment comment) throws SQLException {
		int commentID;
		
			callablestatement = connection.prepareCall("{? = call getcommentid(?,?)}");
			callablestatement.registerOutParameter(1, Types.INTEGER);
			callablestatement.setInt(2, getPostID(comment.getPost()));
			callablestatement.setInt(3, getUserID(comment.getUser()));
			callablestatement.execute();
			commentID = callablestatement.getInt(1);
			callablestatement.close();
			return commentID;
			
	}
	public int getLikeID(Like like) throws SQLException {
		int likeID;
		
			callablestatement = connection.prepareCall("{? = call getcommentid(?,?)}");
			callablestatement.registerOutParameter(1, Types.INTEGER);
			callablestatement.setInt(2, like.getPost().getIdPost());
			callablestatement.setString(3, like.getUser().getUserName());
			callablestatement.execute();
			likeID = callablestatement.getInt(1);
			callablestatement.close();
			return likeID;
			
	}

	

}
