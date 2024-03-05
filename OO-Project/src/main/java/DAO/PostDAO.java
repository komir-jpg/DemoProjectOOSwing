package DAO;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class PostDAO{

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private CallableStatement callablestatement;

	
	public PostDAO(){
		ConnectionToDB connectionToDB = new ConnectionToDB();
		connection = connectionToDB.getConnection();
	}
	
	
	public void insertNewPostText(Post newPost,User user,Group group) throws SQLException {
						
			String idUser = user.getUserName();
			String idGroup = group.getGroupName();
			statement = connection.createStatement();
			String insertNewPost = "INSERT INTO progettobd_unina_social_network.POST VALUES"+"("+
									 "DEFAULT"+","+
									 "\""+idUser+"\""+","
									 +"\'"+"1/1/12"+"\'"+","
									 +"\'"+newPost.getNumberOfLikes()+"\'"+","
									 +"\'"+newPost.getNumberOfComments()+"\'"+","
									 +"\'"+newPost.getNumberOfShare()+"\'"+","
									 +"\'"+newPost.getContent()+"\'"+","
									 +newPost.getFotoFormat()+"," //valore null
									 +"\'"+newPost.getTypeOfPost()+"\'"+","
									 +"\'"+newPost.isEliminatedPost()+"\'"
									 +"\""+idGroup+"\""+")";
		statement.executeUpdate(insertNewPost);
		statement.close();
	}
	public void insertNewPostFoto(Post newPost,User user,Group group ) throws SQLException {
		
			String idUser = user.getUserName();
			String idGroup = group.getGroupName();
			statement = connection.createStatement();
			String insertNewPost = "INSERT INTO progettobd_unina_social_network.POST VALUES"+"("+
									 "DEFAULT"+","
									 +"\""+idUser+"\""+","
									 +"\'"+"1/1/12"+"\'"+","
									 +"\'"+newPost.getNumberOfLikes()+"\'"+","
									 +"\'"+newPost.getNumberOfComments()+"\'"+","
									 +"\'"+newPost.getNumberOfShare()+"\'"+","
									 +newPost.getContent()+"," //valore null
									 +"\'"+newPost.getFotoFormat()+"\'"+","
									 +"\'"+newPost.getTypeOfPost()+"\'"+","
									 +"\'"+newPost.isEliminatedPost()+"\'"+","
									 +"\""+idGroup+"\""+")";
		statement.executeUpdate(insertNewPost);
		statement.close();
	 }
	public void setDeletedPost(Post post,Group group) throws SQLException {
		 //update sulla tabella dove per quell'id post setto post eliminato a true
		 //questo fa scattare il trigger
		 
			preparedStatement = connection.prepareStatement("UPDATE pubblica set posteliminato = true where idPost = ? and idGruppo = ?");
		//	preparedStatement.setInt(1, getPostID(post));
		//	preparedStatement.setInt(2, getGroupID(group));
			preparedStatement.executeUpdate();
			preparedStatement.close();
		
		
	}
	public ArrayList<Post> getPostbyUser(User user) throws SQLException{
			String UserID = user.getUserName();
			preparedStatement = connection.prepareStatement("SELECT *"+
															"FROM progettobd_unina_social_network.post as po join "
															+ "progettobd_unina_social_network.pubblica as pu"+
															"WHERE pu.autorepost = ?");
			preparedStatement.setString(1, UserID);
			ResultSet queryRS = preparedStatement.executeQuery();
			
			ArrayList<Post> postData = new ArrayList<Post>();
			while(queryRS.next()) {
				Post postQueryResult = new Post();
				postQueryResult.setIdPost(queryRS.getInt("idpost"));
				postQueryResult.setContent(queryRS.getString("contenuto"));
				postQueryResult.setDataPost(queryRS.getDate("datapost"));
				postQueryResult.setNumberOfLikes(queryRS.getInt("numerolike"));
				postQueryResult.setNumberOfComments(queryRS.getInt("numerocommenti"));
				postQueryResult.setNumberOfShare(queryRS.getInt("numerocondivisioni"));
				postQueryResult.setFotoFormat(queryRS.getString("formatofoto"));
				postQueryResult.setEliminatedPost(queryRS.getBoolean("posteliminato"));
				postQueryResult.setAuthor(user);//creare un metodo che prende dall'id l'autore del post
				//postQueryResult.setGroup(null);
				postData.add(postQueryResult);
			}
			queryRS.close();
			preparedStatement.close();
			return postData;
	}
	
	public ArrayList<Post> getPostbyGroup(Group group) throws SQLException{
			
			preparedStatement = connection.prepareStatement("SELECT *"+
															"FROM progettobd_unina_social_network.post as po join "
															+ "progettobd_unina_social_network.pubblica as pu"+
															"WHERE pu.gruppo = ?");
			preparedStatement.setString(1, group.getGroupName());
			ResultSet queryRS = preparedStatement.executeQuery();
			
			ArrayList<Post> postData = new ArrayList<Post>();
			while(queryRS.next()) {
				Post postQueryResult = new Post();
				postQueryResult.setIdPost(queryRS.getInt("idpost"));
				postQueryResult.setContent(queryRS.getString("contenuto"));
				postQueryResult.setDataPost(queryRS.getDate("datapost"));
				postQueryResult.setNumberOfLikes(queryRS.getInt("numerolike"));
				postQueryResult.setNumberOfComments(queryRS.getInt("numerocommenti"));
				postQueryResult.setNumberOfShare(queryRS.getInt("numerocondivisioni"));
				postQueryResult.setFotoFormat(queryRS.getString("formatofoto"));
				postQueryResult.setEliminatedPost(queryRS.getBoolean("posteliminato"));
				postData.add(postQueryResult);
			}
			queryRS.close();
			preparedStatement.close();
			return postData;
		
	}
	

	
	
}