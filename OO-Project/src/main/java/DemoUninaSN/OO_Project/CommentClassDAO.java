package DemoUninaSN.OO_Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CommentClassDAO extends getIdDAO{
	
	

	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	public CommentClassDAO(Connection myConnection) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		super(myConnection);
	}

	public void addComment(CommentClass comment) {
		int UserID = getUserID(comment.getUser());
		int PostID = getPostID(comment.getPost());
		String insertNewComment = "INSERT INTO progettobd_unina_social_network.RICEVE_COMMENTO VALUES ("
				 + "\'DEFAULT\'"
				 +"\""+PostID+"\""+","
				 +"\""+UserID+"\""+","
				 +"\'"+comment.getText()+"\'"+","
				 +"\'"+comment.getCommentDate()+"\'"+","
				 +"\'"+comment.getCommentAuthor()+"\'"+")";
		try {
			statement = connection.createStatement();
			statement.executeUpdate(insertNewComment);
			statement.close();
			comment.setCommentNumber(getCommentID(comment));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	
	}
	public void deleteComment(CommentClass comment) {
		int commentID = getCommentID(comment);
		String deleteComment = "DELETE  FROM progettobd_unina_social_network.RICEVE_COMMENTO where idcommento = ?";
		try {
			preparedStatement = connection.prepareStatement(deleteComment);
			preparedStatement.setInt(1,commentID);
			preparedStatement.executeUpdate(deleteComment);
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<User> CommentedPostUsers(CommentClass comment){
		int PostID = getPostID(comment.getPost());
		String getUserQuery = "SELECT DISTINCT from progettobd_unina_social_network.UTENTE where idUtente in (SELECT idUtente from "
				+ "progettobd_unina_social_network.RICEVE_COMMENTO where idPost = ?)";
		
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
