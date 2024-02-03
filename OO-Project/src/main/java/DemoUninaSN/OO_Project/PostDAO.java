package DemoUninaSN.OO_Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PostDAO {
	Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	
	public PostDAO(Connection myConnection) {
		connection = myConnection;
	}
	public void insertNewPostTesto(Post newPost) {
		try {
			statement = connection.createStatement();
			String insertNewPost = "INSERT INTO progettobd_unina_social_network.POST VALUES"+"("+
									
									 "\'"+newPost.getNumberOfComments()+"\'"+","
									 +"DEFAULT"+","
									 +"\'"+"1/1/12"+"\'"+","
									 +"\'"+newPost.getNumberOfLikes()+"\'"+","
									 +"\'"+newPost.getNumberOfShare()+"\'"+","
									 +"\'"+newPost.getContent()+"\'"+","
									 +newPost.getFotoFormat()+"," //valore null
									 +"\'"+newPost.getTypeOfPost()+"\'"+","
									 +"\'"+newPost.isEliminatedPost()+"\'"+")";
		statement.executeUpdate(insertNewPost);
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void insertNewPostFoto(Post newPost ) {
		try {
			statement = connection.createStatement();
			String insertNewPost = "INSERT INTO progettobd_unina_social_network.POST VALUES"+"("+
									
									 "\'"+newPost.getNumberOfComments()+"\'"+","
									 +"DEFAULT"+","
									 +"\'"+"1/1/12"+"\'"+","
									 +"\'"+newPost.getNumberOfLikes()+"\'"+","
									 +"\'"+newPost.getNumberOfShare()+"\'"+","
									 +newPost.getContent()+"," //valore null
									 +"\'"+newPost.getFotoFormat()+"\'"+","
									 +"\'"+newPost.getTypeOfPost()+"\'"+","
									 +"\'"+newPost.isEliminatedPost()+"\'"+")";
		statement.executeUpdate(insertNewPost);
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	 
		
	}
}
