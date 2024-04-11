package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import Entities.Group;
import Entities.Post;
import Entities.User;
import utils.ConnectionToDB;


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
									 "\'"+idUser+"\'"+","
									 +"\'"+newPost.getDatePost()+"\'"+","
									 +"\'"+newPost.getNumberOfLikes()+"\'"+","
									 +"\'"+newPost.getNumberOfComments()+"\'"+","
									 +"\'"+newPost.getNumberOfShare()+"\'"+","
									 +"\'"+newPost.getContent()+"\'"+","
									 +newPost.getFotoFormat()+"," //valore null
									 +"\'"+newPost.getTypeOfPost()+"\'"+","
									 +"\'"+newPost.isEliminatedPost()+"\'"+","
									 +"\'"+idGroup+"\'"+")";
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
	

	public int getPostID(String userID) throws SQLException {
		
		int postID;
		callablestatement = connection.prepareCall("{? = call progettobd_unina_social_network.getidpost(?)}");
		callablestatement.registerOutParameter(1,Types.INTEGER);
		callablestatement.setString(2, userID);
		callablestatement.execute();
		postID = callablestatement.getInt(1);
		callablestatement.close();
		return postID;

}
	
	public ArrayList<Post> getPostsByGroupNoUser(Group group,User user) throws SQLException{
		preparedstatement = connection.prepareStatement("SELECT * FROM progettobd_unina_social_network.post where gruppo = ? and autorepost <> ?");
		preparedstatement.setString(1, group.getGroupName());
		preparedstatement.setString(2, user.getUserName());
		ResultSet queryRS = preparedstatement.executeQuery();
		ArrayList<Post> postResult = new ArrayList<Post>();
		while(queryRS.next()) {
			Post post = new Post();
			post.setContent(queryRS.getString("contenuto"));
			post.setIdPost(queryRS.getInt("idPost"));
			post.setDatePost(queryRS.getTimestamp("datapost"));
			post.setTypeOfPost(queryRS.getString("tipoPost"));
			post.setEliminatedPost(queryRS.getBoolean("posteliminato"));
			post.setGroup(group);
			post.setAuthor(new UserDAO().getUserbyUsername(queryRS.getString("autorepost")));
			post.setPostLikes(new LikeDAO().getLikesByPost(post));
			post.setPostShare(new ShareDAO().getSharesByPost(post));
			postResult.add(post);
		}
		queryRS.close();
		preparedstatement.close();
		return postResult;
	}
	
		public ArrayList<Post> getPostsByGroupNoUser(String group,String username) throws SQLException{
			preparedstatement = connection.prepareStatement("SELECT * FROM progettobd_unina_social_network.post where gruppo = ? and autorepost <> ?");
			preparedstatement.setString(1, group);
			preparedstatement.setString(2, username);
			ResultSet queryRS = preparedstatement.executeQuery();
			ArrayList<Post> postResult = new ArrayList<Post>();
			while(queryRS.next()) {
				Post post = new Post();
				post.setContent(queryRS.getString("contenuto"));
				post.setIdPost(queryRS.getInt("idPost"));
				post.setDatePost(queryRS.getTimestamp("datapost"));
				post.setTypeOfPost(queryRS.getString("tipoPost"));
				post.setEliminatedPost(queryRS.getBoolean("posteliminato"));
				post.setGroup(new GroupDAO().GetGroupByName(group));
				post.setAuthor(new UserDAO().getUserbyUsername(queryRS.getString("autorepost")));
				post.setPostLikes(new LikeDAO().getLikesByPost(post));
				post.setPostComment(new CommentDAO().getCommentByPost(post));;
				postResult.add(post);
			}
			return postResult;
		}
		public ArrayList<Post> getUserPostsByGroup(String group,String username) throws SQLException{
			preparedstatement = connection.prepareStatement("SELECT * FROM progettobd_unina_social_network.post where gruppo = ? and autorepost = ?");
			preparedstatement.setString(1, group);
			preparedstatement.setString(2, username);
			ResultSet queryRS = preparedstatement.executeQuery();
			ArrayList<Post> postResult = new ArrayList<Post>();
			while(queryRS.next()) {
				Post post = new Post();
				post.setContent(queryRS.getString("contenuto"));
				post.setIdPost(queryRS.getInt("idPost"));
				post.setDatePost(queryRS.getTimestamp("datapost"));
				post.setTypeOfPost(queryRS.getString("tipoPost"));
				post.setEliminatedPost(queryRS.getBoolean("posteliminato"));
				post.setGroup(new GroupDAO().GetGroupByName(group));
				post.setAuthor(new UserDAO().getUserbyUsername(queryRS.getString("autorepost")));
				post.setPostLikes(new LikeDAO().getLikesByPost(post));
				post.setPostComment(new CommentDAO().getCommentByPost(post));;
				postResult.add(post);
			}
			return postResult;
		}
		public ArrayList<Post> getUserPostsByGroup(Group group,User user) throws SQLException{
			preparedstatement = connection.prepareStatement("SELECT * FROM progettobd_unina_social_network.post where gruppo = ? and autorepost = ?");
			preparedstatement.setString(1, group.getGroupName());
			preparedstatement.setString(2, user.getUserName());
			ResultSet queryRS = preparedstatement.executeQuery();
			ArrayList<Post> postResult = new ArrayList<Post>();
			while(queryRS.next()) {
				Post post = new Post();
				post.setContent(queryRS.getString("contenuto"));
				post.setIdPost(queryRS.getInt("idPost"));
				post.setDatePost(queryRS.getTimestamp("datapost"));
				post.setTypeOfPost(queryRS.getString("tipoPost"));
				post.setEliminatedPost(queryRS.getBoolean("posteliminato"));
				post.setGroup(group);
				post.setAuthor(new UserDAO().getUserbyUsername(queryRS.getString("autorepost")));
				post.setPostLikes(new LikeDAO().getLikesByPost(post));
				post.setPostShare(new ShareDAO().getSharesByPost(post));
				postResult.add(post);
			}
			queryRS.close();
			preparedstatement.close();
			return postResult;
		}
		public ArrayList<Post> getPostsByGroup(Group group) throws SQLException{
			preparedstatement = connection.prepareStatement("SELECT * FROM progettobd_unina_social_network.post where gruppo = ? order by datapost");
			preparedstatement.setString(1, group.getGroupName());
			ResultSet queryRS = preparedstatement.executeQuery();
			ArrayList<Post> postResult = new ArrayList<Post>();
			while(queryRS.next()) {
				Post post = new Post();
				post.setContent(queryRS.getString("contenuto"));
				post.setIdPost(queryRS.getInt("idPost"));
				post.setDatePost(queryRS.getTimestamp("datapost"));
				post.setTypeOfPost(queryRS.getString("tipoPost"));
				post.setEliminatedPost(queryRS.getBoolean("posteliminato"));
				post.setGroup(group);
				post.setAuthor(new UserDAO().getUserbyUsername(queryRS.getString("autorepost")));
				post.setPostLikes(new LikeDAO().getLikesByPost(post));
				post.setPostComment(new CommentDAO().getCommentByPost(post));;
				postResult.add(post);
			}
			preparedstatement.close();
			return postResult;
		}
			
		public void deletePost(String message,String date,User user,Group group) throws SQLException {
			preparedstatement = connection.prepareStatement("Delete from progettobd_unina_social_network.post where autorepost = ? and gruppo = ? and datapost = ? and contenuto = ?");
			preparedstatement.setString(1, user.getUserName());
			preparedstatement.setString(2, group.getGroupName());
			preparedstatement.setTimestamp(3,Timestamp.valueOf(date));
			preparedstatement.setString(4, message);
			preparedstatement.executeUpdate();
			preparedstatement.close();
		}
		public void deleteLastMessage(User user,Group group) throws SQLException {
			callablestatement = connection.prepareCall("{? = call progettobd_unina_social_network.deletelastmessage(?,?)}");
			callablestatement.registerOutParameter(1, Types.INTEGER);
			callablestatement.setString(2,user.getUserName());
			callablestatement.setString(3, group.getGroupName());
			callablestatement.execute();
			callablestatement.close();
		}


		public void deletePost(String message, String date, String user, Group group) throws SQLException {
			preparedstatement = connection.prepareStatement("Delete from progettobd_unina_social_network.post where autorepost = ? and gruppo = ? and datapost = ? and contenuto = ?");
			preparedstatement.setString(1, user);
			preparedstatement.setString(2, group.getGroupName());
			preparedstatement.setTimestamp(3,Timestamp.valueOf(date));
			preparedstatement.setString(4, message);
			preparedstatement.executeUpdate();
			preparedstatement.close();
		}
		public ArrayList<Post> getPostsMostNuberOfLikes(int month,Group group) throws SQLException{
			getPostByMonthFunction(month, group);
			preparedstatement = connection.prepareStatement("select * from progettobd_unina_social_network.postbuffertable as p where numerolike = (select max(numerolike)"
																																				+ " from progettobd_unina_social_network.postbuffertable);");
			ResultSet queryRS = preparedstatement.executeQuery();
			ArrayList<Post> postResult = new ArrayList<Post>();
			while(queryRS.next()) {
				Post post = new Post();
				post.setContent(queryRS.getString("contenuto"));
				post.setIdPost(queryRS.getInt("idPost"));
				post.setDatePost(queryRS.getTimestamp("datapost"));
				post.setTypeOfPost(queryRS.getString("tipoPost"));
				post.setNumberOfLikes(queryRS.getInt("numerolike"));
				post.setNumberOfShare(queryRS.getInt("numerocondivisioni"));
				post.setNumberOfComments(queryRS.getInt("numerocommenti"));
				post.setEliminatedPost(queryRS.getBoolean("posteliminato"));
				post.setGroup(group);
				post.setAuthor(new UserDAO().getUserbyUsername(queryRS.getString("autorepost")));
				post.setPostLikes(new LikeDAO().getLikesByPost(post));
				post.setPostComment(new CommentDAO().getCommentByPost(post));;
				postResult.add(post);
			}
			preparedstatement.close();
			return postResult;
		}
			
		public void getPostByMonthFunction(int month,Group group) throws SQLException {
			callablestatement = connection.prepareCall("{call progettobd_unina_social_network.getpostbymonth(?,?)}");
			callablestatement.setInt(1, month);
			callablestatement.setString(2, group.getGroupName());
			callablestatement.execute();
			callablestatement.close();
		}


		public ArrayList<Post> getPostsMostNuberOfComments(int month, Group group) throws SQLException {
			getPostByMonthFunction(month, group);
			preparedstatement = connection.prepareStatement("select * from progettobd_unina_social_network.postbuffertable as p where numerocommenti = (select max(numerocommenti)"
																																				+ " from progettobd_unina_social_network.postbuffertable);");
			ResultSet queryRS = preparedstatement.executeQuery();
			ArrayList<Post> postResult = new ArrayList<Post>();
			while(queryRS.next()) {
				Post post = new Post();
				post.setContent(queryRS.getString("contenuto"));
				post.setIdPost(queryRS.getInt("idPost"));
				post.setDatePost(queryRS.getTimestamp("datapost"));
				post.setTypeOfPost(queryRS.getString("tipoPost"));
				post.setNumberOfLikes(queryRS.getInt("numerolike"));
				post.setNumberOfShare(queryRS.getInt("numerocondivisioni"));
				post.setNumberOfComments(queryRS.getInt("numerocommenti"));
				post.setEliminatedPost(queryRS.getBoolean("posteliminato"));
				post.setGroup(group);
				post.setAuthor(new UserDAO().getUserbyUsername(queryRS.getString("autorepost")));
				post.setPostLikes(new LikeDAO().getLikesByPost(post));
				post.setPostComment(new CommentDAO().getCommentByPost(post));;
				postResult.add(post);
			}
			preparedstatement.close();
			return postResult;
		}


		public ArrayList<Post> getPostsLeastNuberOfLikes(int month, Group group) throws SQLException {
			getPostByMonthFunction(month, group);
			preparedstatement = connection.prepareStatement("select * from progettobd_unina_social_network.postbuffertable as p where numerolike = (select min(numerolike)"
																																				+ " from progettobd_unina_social_network.postbuffertable);");
			ResultSet queryRS = preparedstatement.executeQuery();
			ArrayList<Post> postResult = new ArrayList<Post>();
			while(queryRS.next()) {
				Post post = new Post();
				post.setContent(queryRS.getString("contenuto"));
				post.setIdPost(queryRS.getInt("idPost"));
				post.setDatePost(queryRS.getTimestamp("datapost"));
				post.setTypeOfPost(queryRS.getString("tipoPost"));
				post.setNumberOfLikes(queryRS.getInt("numerolike"));
				post.setNumberOfShare(queryRS.getInt("numerocondivisioni"));
				post.setNumberOfComments(queryRS.getInt("numerocommenti"));
				post.setEliminatedPost(queryRS.getBoolean("posteliminato"));
				post.setGroup(group);
				post.setAuthor(new UserDAO().getUserbyUsername(queryRS.getString("autorepost")));
				post.setPostLikes(new LikeDAO().getLikesByPost(post));
				post.setPostComment(new CommentDAO().getCommentByPost(post));;
				postResult.add(post);
			}
			preparedstatement.close();
			return postResult;
		}


		public ArrayList<Post> getPostsLeastNuberOfComments(int month, Group group) throws SQLException {
			getPostByMonthFunction(month, group);
			preparedstatement = connection.prepareStatement("select * from progettobd_unina_social_network.postbuffertable as p where numerocommenti = (select min(numerocommenti)"
																																				+ " from progettobd_unina_social_network.postbuffertable);");
			ResultSet queryRS = preparedstatement.executeQuery();
			ArrayList<Post> postResult = new ArrayList<Post>();
			while(queryRS.next()) {
				Post post = new Post();
				post.setContent(queryRS.getString("contenuto"));
				post.setIdPost(queryRS.getInt("idPost"));
				post.setDatePost(queryRS.getTimestamp("datapost"));
				post.setTypeOfPost(queryRS.getString("tipoPost"));
				post.setNumberOfLikes(queryRS.getInt("numerolike"));
				post.setNumberOfShare(queryRS.getInt("numerocondivisioni"));
				post.setNumberOfComments(queryRS.getInt("numerocommenti"));
				post.setEliminatedPost(queryRS.getBoolean("posteliminato"));
				post.setGroup(group);
				post.setAuthor(new UserDAO().getUserbyUsername(queryRS.getString("autorepost")));
				post.setPostLikes(new LikeDAO().getLikesByPost(post));
				post.setPostComment(new CommentDAO().getCommentByPost(post));;
				postResult.add(post);
			}
			preparedstatement.close();
			return postResult;
		}
}
