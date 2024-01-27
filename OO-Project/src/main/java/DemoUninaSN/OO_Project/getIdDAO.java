package DemoUninaSN.OO_Project;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class getIdDAO {

	private CallableStatement callablestatement;
	private Connection connection;

	public getIdDAO(Connection myConnection)throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		connection =myConnection; 
	}
	public int getUserID(User user) {
		try {
			int userID;
			callablestatement = connection.prepareCall("{? = call getuserid(?)}");
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
			callablestatement.setInt(2, post.getPostNumber());
			callablestatement.execute();
			postID = callablestatement.getInt(1);
			callablestatement.close();
			return postID;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return -1;
		
	}
	
	public int getPostID(int userID) {
		try {
			int postID;
			callablestatement = connection.prepareCall("{? = call getidpost(?)}");
			callablestatement.registerOutParameter(1,Types.INTEGER);
			callablestatement.setInt(2, userID);
			callablestatement.execute();
			postID = callablestatement.getInt(1);
			callablestatement.close();
			return postID;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return -1;
		
	}
	
	public int getGroupID(Group group) {
	try {
		int groupID;
		callablestatement = connection.prepareCall("{? = call getgroupid(?)}");
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
			callablestatement = connection.prepareCall("{? = call getgroupid(?)}");
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
	public int getCommentID(CommentClass comment) {
		int commentID;
		try {
			callablestatement = connection.prepareCall("{? = call getcommentid(?,?)}");
			callablestatement.registerOutParameter(1, Types.INTEGER);
			callablestatement.setInt(2, getPostID(comment.getPost()));
			callablestatement.setInt(3, getUserID(comment.getUser()));
			callablestatement.execute();
			commentID = callablestatement.getInt(1);
			callablestatement.close();
			return commentID;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
		
		
	}

}
