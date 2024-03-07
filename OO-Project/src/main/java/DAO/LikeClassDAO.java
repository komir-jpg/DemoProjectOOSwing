package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LikeClassDAO{
	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	public LikeClassDAO() throws ClassNotFoundException, SQLException, IOException, RuntimeException {
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
				 +"\'"+like.getLikeDate()+"\'"+","
				 +"\'"+like.getLikeAuthor()+"\'"+")";
			statement = connection.createStatement();
			statement.executeUpdate(insertNewLike);
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
		
}
