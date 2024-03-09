package DAO;

import java.io.IOException;
import java.lang.reflect.Executable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

import ExceptionPackage.DBconnectionError;

public class UserDAO {
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;

	
	
	
	public UserDAO(){
		ConnectionToDB connectionDB = new ConnectionToDB();
		connection = connectionDB.getConnection();
	}
	public void SaveNewUser(User newUser) throws SQLException {
			statement = connection.createStatement();
			String InsertNewUser = "INSERT INTO progettobd_unina_social_network.utente VALUES"+"("
					 				+"\'"+newUser.getUserName()+"\'"+","
					 				+"\'"+newUser.getName()+"\'"+","
								    +"\'"+newUser.getSurname()+"\'"+","
								    +"\'"+newUser.getSex()+"\'" + ","
								    +"\'"+newUser.getEmail()+"\'"+","
								    +"\'"+newUser.getPassword()+"\'"+","
								    +"\'"+newUser.getSubcsriptionDate()+"\'"+","
								    +"\'null\'"
								    +")";
			statement.executeUpdate(InsertNewUser);
			statement.close();
		
	}
	public ArrayList<User> getUserbyName(String name) throws SQLException {
		
			
			preparedStatement = connection.prepareStatement("SELECT *"
															+ "	FROM progettobd_unina_social_network.utente "
															+ "WHERE nome LIKE ?");
			preparedStatement.setString(1, name);
			ResultSet queryRS = preparedStatement.executeQuery();
			
			//Retrieve the data
			ArrayList<User> userData = new ArrayList<User>();
			
			while(queryRS.next()) {
				User userResultQuery = new User();
				userResultQuery.setName(queryRS.getString("nome"));
				userResultQuery.setSurname(queryRS.getString("cognome"));
				userResultQuery.setSubsriptionDate(queryRS.getDate("dataiscrizione"));
				userResultQuery.setEmail(queryRS.getString("email"));
				userResultQuery.setPassword(queryRS.getString("password"));
				userResultQuery.setSex(queryRS.getString("sesso"));
				userResultQuery.setUserName(queryRS.getString("nomeutente"));
				userResultQuery.setUserType(queryRS.getString("tipoutente"));
				userData.add(userResultQuery);				
			}
			preparedStatement.clearBatch();
			queryRS.close();
			return userData;
		}
	public ArrayList<User> getUserbyName(User user) throws SQLException {
		
		
		preparedStatement = connection.prepareStatement("SELECT *"
														+ "	FROM progettobd_unina_social_network.utente "
														+ "WHERE nome LIKE ?");
		preparedStatement.setString(1, user.getUserName());
		ResultSet queryRS = preparedStatement.executeQuery();
		
		//Retrieve the data
		ArrayList<User> userData = new ArrayList<User>();
		
		while(queryRS.next()) {
			User userResultQuery = new User();
			userResultQuery.setName(queryRS.getString("nome"));
			userResultQuery.setSurname(queryRS.getString("cognome"));
			userResultQuery.setSubsriptionDate(queryRS.getDate("dataiscrizione"));
			userResultQuery.setEmail(queryRS.getString("email"));
			userResultQuery.setPassword(queryRS.getString("password"));
			userResultQuery.setSex(queryRS.getString("sesso"));
			userResultQuery.setUserName(queryRS.getString("nomeutente"));
			userResultQuery.setUserType(queryRS.getString("tipoutente"));
			userData.add(userResultQuery);				
		}
		preparedStatement.clearBatch();
		queryRS.close();
		return userData;
	}

	public ArrayList<User> getUserList() throws SQLException {
	
			
			preparedStatement = connection.prepareStatement("SELECT *"
															+ "	FROM progettobd_unina_social_network.utente");
			ResultSet queryRS = preparedStatement.executeQuery();
			
			//Retrieve the data
			ArrayList<User> userData = new ArrayList<User>();
			
			while(queryRS.next()) {
				User userResultQuery = new User();
				userResultQuery.setName(queryRS.getString("nome"));
				userResultQuery.setSurname(queryRS.getString("cognome"));
				userResultQuery.setSubsriptionDate(queryRS.getDate("dataiscrizione"));
				userResultQuery.setEmail(queryRS.getString("email"));
				userResultQuery.setPassword(queryRS.getString("password"));
				userResultQuery.setSex(queryRS.getString("sesso"));
				userResultQuery.setUserName(queryRS.getString("nomeutente"));
				userResultQuery.setUserType(queryRS.getString("tipoutente"));
				userData.add(userResultQuery);				
			}
			preparedStatement.clearBatch();
			queryRS.close();
			return userData;
	}
	public User getUserbyUsername(String username) throws SQLException {
			
			preparedStatement = connection.prepareStatement("SELECT *"
															+ "	FROM progettobd_unina_social_network.utente "
															+ "WHERE nomeutente LIKE ?");
			preparedStatement.setString(1, username);
			ResultSet queryRS = preparedStatement.executeQuery();
			
			//Retrieve the data
			User user = new User();
			
			while(queryRS.next()) {
			
				user.setName(queryRS.getString("nome"));
				user.setSurname(queryRS.getString("cognome"));
				user.setSubsriptionDate(queryRS.getDate("dataiscrizione"));
				user.setEmail(queryRS.getString("email"));
				user.setPassword(queryRS.getString("password"));
				user.setSex(queryRS.getString("sesso"));
				user.setUserName(queryRS.getString("nomeutente"));
				user.setUserType(queryRS.getString("tipoutente"));
								
			}
			preparedStatement.clearBatch();
			queryRS.close();
			return user;
	}
	
	public ArrayList<User>getUserByGroup(Group group) throws SQLException{
		
		preparedStatement = connection.prepareStatement("SELECT *"
														+ "	FROM progettobd_unina_social_network.utente as u join progettobd_unina_social_network.partecipa as p on u.nomeutente = p.idutente"
														+ "WHERE idgruppo LIKE ?");
		preparedStatement.setString(1, group.getGroupName());
		ResultSet queryRS = preparedStatement.executeQuery();
		
		//Retrieve the data
		ArrayList<User> userData = new ArrayList<User>();
		
		while(queryRS.next()) {
			User userResultQuery = new User();
			userResultQuery.setName(queryRS.getString("nome"));
			userResultQuery.setSurname(queryRS.getString("cognome"));
			userResultQuery.setSubsriptionDate(queryRS.getDate("dataiscrizione"));
			userResultQuery.setEmail(queryRS.getString("email"));
			userResultQuery.setPassword(queryRS.getString("password"));
			userResultQuery.setSex(queryRS.getString("sesso"));
			userResultQuery.setUserName(queryRS.getString("nomeutente"));
			userResultQuery.setUserType(queryRS.getString("tipoutente"));
			userData.add(userResultQuery);				
		}
		preparedStatement.clearBatch();
		queryRS.close();
		return userData;

		
		
	}
	public User getUserByPost(int postID) throws SQLException {
		//slelect * from utente as u join post as p on u.idUtente = p.idUtente where p.idPost = ?
		preparedStatement = connection.prepareStatement("slelect * from utente as u join post as p on u.idUtente = p.idUtente where p.idPost = ?");
		preparedStatement.setInt(1, postID);
		ResultSet queryRS = preparedStatement.executeQuery();
		User user = new User();
		while(queryRS.next()) {
			user.setUserName(queryRS.getString("nomeutente"));
			user.setPassword(queryRS.getString("password"));
			user.setEmail(queryRS.getString("email"));
			user.setName(queryRS.getString("nome"));
			user.setSurname(queryRS.getString("cognome"));
			user.setSex(queryRS.getString("sesso"));
			user.setUserType(queryRS.getString("tipoutente"));
		}
		queryRS.close();
		preparedStatement.close();
		return user;
	}
	
	
	
	public ArrayList<User> getUsersByGroup(Group group) throws SQLException{
		preparedStatement = connection.prepareStatement("select * from utente as u join partecipa as p on p.idutente = p.idutente where p.idgruppo = ?");
		preparedStatement.setString(1, group.getGroupName());
		ResultSet queryRS = preparedStatement.executeQuery();
		ArrayList<User> userResult = new ArrayList<User>();
		while(queryRS.next()) {
			User user = new User();
			user.setName(queryRS.getString("nome"));
			user.setSurname(queryRS.getString("cognome"));
			user.setSubsriptionDate(queryRS.getDate("dataiscrizione"));
			user.setPassword(queryRS.getString("password"));
			user.setSex(queryRS.getString("sesso"));
			user.setUserName(queryRS.getString("nomeutente"));
			user.setUserType(queryRS.getString("tipoutente"));
			userResult.add(user);
		}
		preparedStatement.close();
		queryRS.close();
		return userResult;
	}
	public ArrayList<User> getUsersByGroup(String group) throws SQLException{
		preparedStatement = connection.prepareStatement("SELECT * FROM progettobd_unina_social_network.utente as u "
				+ "JOIN progettobd_unina_social_network.partecipa as p ON p.idutente = u.nomeutente where p.idgruppo = ?");
		preparedStatement.setString(1, group);
		ResultSet queryRS = preparedStatement.executeQuery();
		ArrayList<User> userResult = new ArrayList<User>();
		while(queryRS.next()) {
			User user = new User();
			
			user.setName(queryRS.getString("nome"));
			user.setSurname(queryRS.getString("cognome"));
			user.setSubsriptionDate(queryRS.getDate("dataiscrizione"));
			user.setPassword(queryRS.getString("password"));
			user.setSex(queryRS.getString("sesso"));
			user.setUserName(queryRS.getString("nomeutente"));
			user.setUserType(queryRS.getString("tipoutente"));
			userResult.add(user);
		}
		preparedStatement.close();
		queryRS.close();
		return userResult;
	}
	public ArrayList<User> getUserFriends(User user) throws SQLException{
		preparedStatement = connection.prepareStatement("select * from utente as u join amici as a on a.idutente2 = u.nomeutente where idutente2 = ?");
		preparedStatement.setString(1,user.getUserName());
		ResultSet queryRS = preparedStatement.executeQuery();
		ArrayList<User> userResultQuery = new ArrayList<User>();
		while(queryRS.next()) {
			User userResult = new User();
			userResult.setName(queryRS.getString("nome"));
			userResult.setSurname(queryRS.getString("cognome"));
			userResult.setSubsriptionDate(queryRS.getDate("dataiscrizione"));
			userResult.setPassword(queryRS.getString("password"));
			userResult.setSex(queryRS.getString("sesso"));
			userResult.setUserName(queryRS.getString("nomeutente"));
			userResult.setUserType(queryRS.getString("tipoutente"));
			userResultQuery.add(user);
		}
		preparedStatement.close();
		queryRS.close();
		return userResultQuery;
			
		}
	}
	

