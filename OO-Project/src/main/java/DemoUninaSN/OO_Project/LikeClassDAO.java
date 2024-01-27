package DemoUninaSN.OO_Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LikeClassDAO extends getIdDAO{
	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	public LikeClassDAO(Connection myConnection) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		super(myConnection);
	}

	public void addLike(LikeClass like) {
		int UserID = getUserID(like.getUser());
		int PostID = getPostID(like.getPost());
		String insertNewLike = "INSERT INTO progettobd_unina_social_network.RICEVE_LIKE VALUES ("
				 + "\'DEFAULT\'"
				 +"\""+UserID+"\""+","
				 +"\""+PostID+"\""+","
				 +"\'"+like.getLikeDate()+"\'"+","
				 +"\'"+like.getLikeAuthor()+"\'"+")";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(insertNewLike);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
	}
	public void deleteLike(LikeClass like) {
		int UserID = getUserID(like.getUser());
		int PostID = getPostID(like.getPost());
		String deleteLike = "DELETE  FROM progettobd_unina_social_network.RICEVE_LIKE where idPost = ? and idUtente = ?";
		try {
			preparedStatement = connection.prepareStatement(deleteLike);
			preparedStatement.setInt(1,PostID);
			preparedStatement.setInt(2, UserID);
			preparedStatement.executeUpdate(deleteLike);
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<User> likedPostUsers(LikeClass like){
		int PostID = getPostID(like.getPost());
		String getUserQuery = "SELECT * from progettobd_unina_social_network.UTENTE where idUtente in (SELECT idUtente from "
				+ "progettobd_unina_social_network.RICEVE_LIKE where idPost = ?)";
		
		try {
			preparedStatement = connection.prepareStatement(getUserQuery);
			preparedStatement.setInt(1, PostID);
			ResultSet queryRS = preparedStatement.executeQuery();
			ArrayList<User> queryResultUser = new ArrayList<User>();
			while(queryRS.next()) {
				User user = new User();
				user.setName(queryRS.getString("nome"));
				user.setSurname(queryRS.getString("cognome"));
				user.setSubsriptionDate(queryRS.getDate("dataiscrizione"));
				user.setDateofBirth(queryRS.getDate("dataiscrizione"));
				user.setEmail(queryRS.getString("email"));
				user.setPassword(queryRS.getString("password"));
				user.setSex(queryRS.getString("sesso"));
				user.setUserName(queryRS.getString("nomeutente"));
				user.setUserType(queryRS.getString("tipoutente"));
				user.setInviteState(queryRS.getString("statoinvito"));
				user.setRequestState(queryRS.getString("statorichiesta"));
				queryResultUser.add(user);
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
