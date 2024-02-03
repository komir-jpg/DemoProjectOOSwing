package DemoUninaSN.OO_Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class FriendDAO extends getIdDAO{

	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	
	public FriendDAO(Connection myConnection) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		super(myConnection);
	}
	
	public void addFriend(Friend friend) {
		int userIDFriend1 = getUserID(friend.getFriend1());
		int userIDFriend2 = getUserID(friend.getFriend2());
		
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
	public void deleteFriend(Friend friend) {
		int userIDFriend1 = getUserID(friend.getFriend1());
		int userIDFriend2 = getUserID(friend.getFriend2());
		String deleteFriends = "DELETE FROM progettobd_unina_social_network.AMICIZIA where idUtente1 = ? and idUtente2 = ?";
		
		try {
			preparedStatement = connection.prepareStatement(deleteFriends);
			preparedStatement.setInt(1, userIDFriend1);
			preparedStatement.setInt(2, userIDFriend2);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public ArrayList<User> getUserFriends(User user) {
		int userID = getUserID(user);
		String getUserFriends = "SELECT * FROM progettobd_unina_social_network.UTENTE "
								+ "WHERE idUtente IN (SELECT idUtente2 "
													+"FROM progettobd_unina_social_network.AMICIZIA WHERE idUtente1 = ?)";
		try {
			preparedStatement = connection.prepareStatement(getUserFriends);
			preparedStatement.setInt(1, userID);
			ResultSet queryRS = preparedStatement.executeQuery();
			ArrayList<User> queryResultUser = new ArrayList<User>();
			while(queryRS.next()) {
				User userResult = new User();
				userResult.setName(queryRS.getString("nome"));
				userResult.setSurname(queryRS.getString("cognome"));
				userResult.setSubsriptionDate(queryRS.getDate("dataiscrizione"));
				userResult.setDateofBirth(queryRS.getDate("dataiscrizione"));
				userResult.setEmail(queryRS.getString("email"));
				userResult.setPassword(queryRS.getString("password"));
				userResult.setSex(queryRS.getString("sesso"));
				userResult.setUserName(queryRS.getString("nomeutente"));
				userResult.setUserType(queryRS.getString("tipoutente"));
				queryResultUser.add(userResult);
				queryRS.close();
				preparedStatement.close();
				return queryResultUser;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
