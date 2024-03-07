package DAO;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

public class CommentDAO {
	
	

	Connection connection;
	Statement statement;
	PreparedStatement preparedStatement;
	private CallableStatement callablestatement;
	public CommentDAO() {
		ConnectionToDB connectionToDB = new ConnectionToDB();
		connection = connectionToDB.getConnection();
	}

	public void addComment(Comment comment) throws SQLException {
		String UserID = (comment.getUser().getUserName());
		int PostID = (comment.getPost().getIdPost());
		String insertNewComment = "INSERT INTO progettobd_unina_social_network.COMMENTO VALUES ("
				 + "\'DEFAULT\'"
				 +"\""+PostID+"\""+","
				 +"\""+UserID+"\""+","
				 +"\'"+comment.getText()+"\'"+","
				 +"\'"+comment.getCommentDate()+"\'"+","+")";
				 
		
			statement = connection.createStatement();
			statement.executeUpdate(insertNewComment);
			statement.close();
			comment.setCommentID(getCommentID(comment));
	
	}
	
	public void deleteComment(Comment comment) {
		int commentID = getCommentID(comment);
		String deleteComment = "DELETE  FROM progettobd_unina_social_network.COMMENTO where idcommento = ?";
		try {
			preparedStatement = connection.prepareStatement(deleteComment);
			preparedStatement.setInt(1,commentID);
			preparedStatement.executeUpdate(deleteComment);
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<User> CommentedPostUsers(Comment comment){
		int PostID = (comment.getPost().getIdPost());
		String getUserQuery = "SELECT DISTINCT from progettobd_unina_social_network.UTENTE where idUtente in (SELECT idUtente from "
				+ "progettobd_unina_social_network.COMMENTO where idPost = ?)";
		
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
	private int getCommentID(Comment comment) {
		int commentID;
		try {
			callablestatement = connection.prepareCall("{? = call getcommentid(?,?)}");
			callablestatement.registerOutParameter(1, Types.INTEGER);
			callablestatement.setInt(2, comment.getPost().getIdPost());
			callablestatement.setString(3, comment.getUser().getUserName());
			callablestatement.execute();
			commentID = callablestatement.getInt(1);
			callablestatement.close();
			return commentID;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
			
	}

	
	

}
