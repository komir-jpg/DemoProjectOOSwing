package DemoUninaSN.OO_Project;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class PublicPostDAO extends getIdDAO{

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private CallableStatement callablestatement;

	
	public PublicPostDAO(Connection myConnection) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		super(myConnection);
	}
	
	
	public void insertNewPostText(Post newPost,User user,Group group) {
		try {
			
			
			int idUser = getUserID(user);
			int idGroup = getGroupID(group);
			statement = connection.createStatement();
			String insertNewPost = "INSERT INTO progettobd_unina_social_network.PUBBLICA VALUES"+"("+
									
									 "DEFAULT"+","+
									 "\""+idUser+"\""+","
									 +"\'"+"1/1/12"+"\'"+","
									 +"\'"+newPost.getNumberOfLikes()+"\'"+","
									 +"\'"+newPost.getNumberOfComments()+"\'"+","
									 +"\'"+newPost.getNumberOfShare()+"\'"+","
									 +"\'"+newPost.getContent()+"\'"+","
									 +newPost.getFotoFormat()+"," //valore null
									 +"\'"+newPost.getTypeOfPost()+"\'"+","
									 +"\'"+newPost.isEliminatedPost()+"\'"+
									 "\""+idGroup+"\""+")";
		statement.executeUpdate(insertNewPost);
		statement.close();
		newPost.setPostNumber(getPostID(idUser));
		
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void insertNewPostFoto(Post newPost,User user,Group group ) {
		try {
			int idUser = getUserID(user);
			int idGroup = getGroupID(group);
			statement = connection.createStatement();
			String insertNewPost = "INSERT INTO progettobd_unina_social_network.PUBBLICA VALUES"+"("+
									
									
									 "DEFAULT"+","
									 +"\""+idUser+"\""+","
									 +"\'"+"1/1/12"+"\'"+","
									 +"\'"+newPost.getNumberOfLikes()+"\'"+","
									 +"\'"+newPost.getNumberOfComments()+"\'"+","
									 +"\'"+newPost.getNumberOfShare()+"\'"+","
									 +newPost.getContent()+"," //valore null
									 +"\'"+newPost.getFotoFormat()+"\'"+","
									 +"\'"+newPost.getTypeOfPost()+"\'"+","
									 +"\'"+newPost.isEliminatedPost()+"\'"+
									 "\""+idGroup+"\""+")";
		statement.executeUpdate(insertNewPost);
		statement.close();
		newPost.setPostNumber(idUser);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
	 }
	public void setDeletedPost(Post post,Group group) {
		 //update sulla tabella dove per quell'id post setto post eliminato a true
		 //questo fa scattare il trigger
		 try {
			preparedStatement = connection.prepareStatement("UPDATE pubblica set posteliminato = true where idPost = ? and idGruppo = ?");
			preparedStatement.setInt(1, getPostID(post));
			preparedStatement.setInt(2, getGroupID(group));
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public ArrayList<Post> getPostbyUser(User user){
		try {
			int UserID = getUserID(user);
			preparedStatement = connection.prepareStatement("SELECT *"+
															"FROM progettobd_unina_social_network.post as po join "
															+ "progettobd_unina_social_network.pubblica as pu"+
															"WHERE pu.autorepost = ?");
			preparedStatement.setInt(1, UserID);
			ResultSet queryRS = preparedStatement.executeQuery();
			
			ArrayList<Post> postData = new ArrayList<Post>();
			while(queryRS.next()) {
				Post postQueryResult = new Post();
				postQueryResult.setPostNumber(queryRS.getInt("idpost"));
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Post> getPostbyGroup(Group group){
		try {
			int groupID = getGroupID(group);
			preparedStatement = connection.prepareStatement("SELECT *"+
															"FROM progettobd_unina_social_network.post as po join "
															+ "progettobd_unina_social_network.pubblica as pu"+
															"WHERE pu.gruppo = ?");
			preparedStatement.setInt(1, groupID);
			ResultSet queryRS = preparedStatement.executeQuery();
			
			ArrayList<Post> postData = new ArrayList<Post>();
			while(queryRS.next()) {
				Post postQueryResult = new Post();
				postQueryResult.setPostNumber(queryRS.getInt("idpost"));
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	
	
}
