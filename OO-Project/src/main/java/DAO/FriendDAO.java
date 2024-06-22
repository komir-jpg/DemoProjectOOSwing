package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entities.Friend;
import Entities.User;
import utils.ConnectionToDB;

public class FriendDAO {

	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	
	public FriendDAO(){
		ConnectionToDB connectionToDB = new ConnectionToDB();
		connection = connectionToDB.getConnection();
		
	}
	
	public void addFriend(Friend user,User friend) {
		String userIDFriend1 = user.getUser().getUserName();
		String userIDFriend2 = (friend.getUserName());
		
		String insertNewFriends = "INSERT INTO progettobd_unina_social_network.AMICIZIA values ("
								  +"\""+userIDFriend1+"\""+","
								  +"\""+userIDFriend2+"\""+")";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(insertNewFriends);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteFriend(User user,User friend) throws SQLException {
		String userIDFriend1 = user.getUserName();
		String userIDFriend2 = friend.getUserName();
		String deleteFriends = "DELETE FROM progettobd_unina_social_network.AMICIZIA where idUtente1 = ? and idUtente2 = ?";
		
	
			preparedStatement = connection.prepareStatement(deleteFriends);
			preparedStatement.setString(1, userIDFriend1);
			preparedStatement.setString(2, userIDFriend2);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		
	}
	
	public ArrayList<User> getUserFriends(User user) throws SQLException {
		String userID = user.getUserName();
		String getUserFriends = "SELECT * FROM progettobd_unina_social_network.UTENTE "
								+ "WHERE idUtente IN (SELECT idUtente2 "
													+"FROM progettobd_unina_social_network.AMICIZIA WHERE idUtente1 = ?)";
		
			preparedStatement = connection.prepareStatement(getUserFriends);
			preparedStatement.setString(1, userID);
			ResultSet queryRS = preparedStatement.executeQuery();
			ArrayList<User> queryResultUser = new ArrayList<User>();
			while(queryRS.next()) {
				User userResult = new User();
				userResult.setName(queryRS.getString("nome"));
				userResult.setSurname(queryRS.getString("cognome"));
				userResult.setSubsriptionDate(queryRS.getDate("dataiscrizione"));
				userResult.setEmail(queryRS.getString("email"));
				userResult.setPassword(queryRS.getString("password"));
				userResult.setSex(queryRS.getString("sesso"));
				userResult.setUserName(queryRS.getString("nomeutente"));
				userResult.setUserType(queryRS.getString("tipoutente"));
				queryResultUser.add(userResult);
			}
			queryRS.close();
			preparedStatement.close();
			return queryResultUser;
		
	}
	

}
