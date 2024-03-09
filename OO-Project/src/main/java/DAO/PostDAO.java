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
	private PreparedStatement preparedstatement;

	
	public PostDAO(){
		ConnectionToDB connectionToDB = new ConnectionToDB();
		connection = connectionToDB.getConnection();
	}
	
	
	public void insertNewPostText(Post newPost) throws SQLException {
						
			String idUser = newPost.getAuthor().getUserName();
			String idGroup = newPost.getGroup().getGroupName();
			statement = connection.createStatement();
			String insertNewPost = "INSERT INTO progettobd_unina_social_network.POST VALUES"+"("+
									 "DEFAULT"+","+
									 "\""+idUser+"\""+","
									 +"\'"+"1/1/12"+"\'"+","
//									 +"\'"+newPost.getNumberOfLikes()+"\'"+","
//									 +"\'"+newPost.getNumberOfComments()+"\'"+","
//									 +"\'"+newPost.getNumberOfShare()+"\'"+","
									 +"\'"+newPost.getContent()+"\'"+","
									 +newPost.getFotoFormat()+"," //valore null
									 +"\'"+newPost.getTypeOfPost()+"\'"+","
									 +"\'"+newPost.isEliminatedPost()+"\'"
									 +"\""+idGroup+"\""+")";
		statement.executeUpdate(insertNewPost);
		statement.close();
		newPost.setIdPost(getPostID(idUser));
	}
	public void insertNewPostFoto(Post newPost,User user,Group group ) throws SQLException {
		
			String idUser = user.getUserName();
			String idGroup = group.getGroupName();
			statement = connection.createStatement();
			String insertNewPost = "INSERT INTO progettobd_unina_social_network.POST VALUES"+"("+
									 "DEFAULT"+","
									 +"\""+idUser+"\""+","
									 +"\'"+"1/1/12"+"\'"+","
//									 +"\'"+newPost.getNumberOfLikes()+"\'"+","
//									 +"\'"+newPost.getNumberOfComments()+"\'"+","
//									 +"\'"+newPost.getNumberOfShare()+"\'"+","
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
				postQueryResult.setDatePost(queryRS.getDate("datapost"));
//				postQueryResult.setNumberOfLikes(queryRS.getInt("numerolike"));
//				postQueryResult.setNumberOfComments(queryRS.getInt("numerocommenti"));
//				postQueryResult.setNumberOfShare(queryRS.getInt("numerocondivisioni"));
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
				postQueryResult.setDatePost(queryRS.getDate("datapost"));
//				postQueryResult.setNumberOfLikes(queryRS.getInt("numerolike"));
//				postQueryResult.setNumberOfComments(queryRS.getInt("numerocommenti"));
//				postQueryResult.setNumberOfShare(queryRS.getInt("numerocondivisioni"));
				postQueryResult.setFotoFormat(queryRS.getString("formatofoto"));
				postQueryResult.setEliminatedPost(queryRS.getBoolean("posteliminato"));
				postData.add(postQueryResult);
			}
			queryRS.close();
			preparedStatement.close();
			return postData;
		
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
	
	public ArrayList<Post> getPostsByGroup(Group group) throws SQLException{
		preparedstatement = connection.prepareStatement("SELECT * FROM progettobd_unina_social_network.post as p where p.gruppo = ?");
		preparedstatement.setString(1, group.getGroupName());
		ResultSet queryRS = preparedstatement.executeQuery();
		ArrayList<Post> postResult = new ArrayList<Post>();
		while(queryRS.next()) {
			Post post = new Post();
			post.setContent(queryRS.getString("contenuto"));
			post.setIdPost(queryRS.getInt("idPost"));
			post.setDatePost(queryRS.getDate("datapost"));
			post.setTypeOfPost(queryRS.getString("tipoPost"));
			post.setEliminatedPost(queryRS.getBoolean("posteliminato"));
			post.setGroup(group);
			post.setAuthor(new UserDAO().getUserbyUsername(queryRS.getString("idutente")));
			post.setPostLikes(new LikeDAO().getLikesByPost(post));
			post.setPostShare(new ShareDAO().getSharesByPost(post));
			postResult.add(post);
		}
		return postResult;
	}
	
		public ArrayList<Post> getPostsByGroup(String group) throws SQLException{
			preparedstatement = connection.prepareStatement("SELECT * FROM progettobd_unina_social_network.post as p where p.gruppo = ?");
			preparedstatement.setString(1, group);
			ResultSet queryRS = preparedstatement.executeQuery();
			ArrayList<Post> postResult = new ArrayList<Post>();
			while(queryRS.next()) {
				Post post = new Post();
				post.setContent(queryRS.getString("contenuto"));
				post.setIdPost(queryRS.getInt("idPost"));
				post.setDatePost(queryRS.getDate("datapost"));
				post.setTypeOfPost(queryRS.getString("tipoPost"));
				post.setEliminatedPost(queryRS.getBoolean("posteliminato"));
				post.setGroup(new GroupDAO().GetGroupByName(group));
				post.setAuthor(new UserDAO().getUserbyUsername(queryRS.getString("idutente")));
				post.setPostLikes(new LikeDAO().getLikesByPost(post));
				post.setPostComment(new CommentDAO().getCommentByPost(post));;
				postResult.add(post);
			}
			return postResult;
		}	
}
