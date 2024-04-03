package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import Entities.Like;
import Entities.Post;
import Entities.User;
import utils.ConnectionToDB;

public class LikeDAO{
	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	private CallableStatement callablestatement;
	public LikeDAO() {
		ConnectionToDB connectionToDB = new ConnectionToDB();
		connection = connectionToDB.getConnection();
	}

	public void addLike(Like like) throws SQLException {
		String UserID = like.getUser().getUserName();
		int PostID = like.getPost().getIdPost();
		String insertNewLike = "INSERT INTO progettobd_unina_social_network.LIKE VALUES ("
				 + "\'DEFAULT\'"
				 +"\""+UserID+"\""+","
				 +"\""+PostID+"\""+","
				 +"\'"+like.getLikeDate()+"\'"+","+")";
			statement = connection.createStatement();
			statement.executeUpdate(insertNewLike);
			like.setLikeID(getLikeID(like));
			statement.close();
	
	}
	public void deleteLike(Like like) throws SQLException {
		String UserID = like.getUser().getUserName();
		int PostID = like.getPost().getIdPost();
		String deleteLike = "DELETE  FROM progettobd_unina_social_network.LIKE where idPost = ? and idUtente = ?";
			preparedStatement = connection.prepareStatement(deleteLike);
			preparedStatement.setInt(1,PostID);
			preparedStatement.setString(2, UserID);
			preparedStatement.executeUpdate(deleteLike);
			preparedStatement.close();
	}
	public ArrayList<User> likedPostUsers(Like like) throws SQLException{
		int PostID = like.getPost().getIdPost();
		String getUserQuery = "SELECT * from progettobd_unina_social_network.UTENTE where idUtente in (SELECT idUtente from "
				+ "progettobd_unina_social_network.RICEVE_LIKE where idPost = ?)";
		
			preparedStatement = connection.prepareStatement(getUserQuery);
			preparedStatement.setInt(1, PostID);
			ResultSet queryRS = preparedStatement.executeQuery();
			ArrayList<User> queryResultUser = new ArrayList<User>();
			while(queryRS.next()) {
				User user = new User();
				user.setName(queryRS.getString("nome"));
				user.setSurname(queryRS.getString("cognome"));
				user.setSubsriptionDate(queryRS.getDate("dataiscrizione"));
				user.setEmail(queryRS.getString("email"));
				user.setPassword(queryRS.getString("password"));
				user.setSex(queryRS.getString("sesso"));
				user.setUserName(queryRS.getString("nomeutente"));
				user.setUserType(queryRS.getString("tipoutente"));
				queryResultUser.add(user);
				}
				queryRS.close();
				preparedStatement.close();
				return queryResultUser;
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

	public Post getPostByLike(Like like) {
		
		return null;
	}

	public ArrayList<Like> getLikesByPost(Post post) throws SQLException {
		preparedStatement = connection.prepareStatement("select * from  progettobd_unina_social_network.like where idpost = ?");
		preparedStatement.setInt(1, post.getIdPost());
		ResultSet queryRS = preparedStatement.executeQuery();
		ArrayList<Like> likeResult = new ArrayList<Like>();
		while(queryRS.next()) {
			Like like = new Like();
			like.setLikeDate(queryRS.getDate("datalike"));
			like.setLikeID(queryRS.getInt("likeid"));
			like.setPost(post);
			like.setUser(new UserDAO().getUserbyUsername(queryRS.getString("userid")));
			likeResult.add(like);
		}
		preparedStatement.close();
		queryRS.close();
		return likeResult;
	}
}
