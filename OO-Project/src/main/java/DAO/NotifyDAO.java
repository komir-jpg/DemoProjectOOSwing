package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entities.Post;
import Entities.User;
import utils.ConnectionToDB;

public class NotifyDAO {

	Connection connection;
	private PreparedStatement preparedStatement;
	
	public NotifyDAO() {
		ConnectionToDB connectionToDB = new ConnectionToDB();
		connection = connectionToDB.getConnection();
	}
	
	public ArrayList<User> getUserNotified(Post post) throws SQLException{
		int postID = post.getIdPost();
		String getNotifyQuery = "SELECT *  FROM progettobd_unina_social_network.INVIA_NOTIFICA as i"
				+ "join progettobd_unina_social_network.UTENTE as u on u.idUtente = i.idUtente"
				+ " where i.idPost = ?";
		
			preparedStatement = connection.prepareStatement(getNotifyQuery);
			preparedStatement.setInt(1, postID);
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
