package DAO;

import java.io.IOException;
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
	private String CurrentDate;

	
	
	
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
	public ArrayList<User> getUserbyUsername(String username) throws SQLException {
			
			preparedStatement = connection.prepareStatement("SELECT *"
															+ "	FROM progettobd_unina_social_network.utente "
															+ "WHERE nomeutente LIKE ?");
			preparedStatement.setString(1, username);
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
	
	
}
