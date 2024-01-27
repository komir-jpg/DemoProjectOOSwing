package DemoUninaSN.OO_Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotifyDAO extends getIdDAO {

	Connection connection;
	private PreparedStatement preparedStatement;
	
	public NotifyDAO(Connection myConnection) throws ClassNotFoundException, SQLException, IOException, RuntimeException{
		super(myConnection);
	}
	
	public ArrayList<User> getUserNotified(Post post){
		int postID = getPostID(post);
		String getNotifyQuery = "SELECT *  FROM progettobd_unina_social_network.INVIA_NOTIFICA as i"
				+ "join progettobd_unina_social_network.UTENTE as u on u.idUtente = i.idUtente"
				+ " where i.idPost = ?";
		try {
			preparedStatement = connection.prepareStatement(getNotifyQuery);
			preparedStatement.setInt(1, postID);
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
