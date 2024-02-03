package DemoUninaSN.OO_Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ExceptionPackage.DBconnectionError;

public class UserDAO {
	
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	
	
	
	public UserDAO(Connection myConnection) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		connection = myConnection;
	}
	public void SaveNewUser(User newUser,int idIncrement) {
		try {
			//INSERT INTO UTENTE values ('1234','nome','cognome'...)
			statement = connection.createStatement();
			String InsertNewUser = "INSERT INTO progettobd_unina_social_network.utente VALUES"+"("+
									"DEFAULT"+","
								    +"\'"+newUser.getName()+"\'"+","
								    +"\'"+newUser.getSurname()+"\'"+","
								    +"\'"+newUser.getSex()+"\'" + ","
								    +"\'"+newUser.getEmail()+"\'"+","
								    +"\'"+newUser.getUserName()+"\'"+","
								    +"\'"+newUser.getPassword()+"\'"+","
								    +"\'12/12/12\'"+","
								    +"\'null\'"+","
								    +"null"+","
								    +"\'null\'"
								    +")";
			statement.executeUpdate(InsertNewUser);
			statement.clearBatch();
			
		}catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println( throwables.getClass().getName()+": "+ throwables.getMessage() );
        }
		
	}
	public ArrayList<User> getUserbyName(String name) {
		try {
			
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
				userResultQuery.setDateofBirth(queryRS.getDate("dataiscrizione"));
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
		}catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println( throwables.getClass().getName()+": "+ throwables.getMessage() );
        }
		return null;
	}
	
}
