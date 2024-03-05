package DAO;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DBConnection{
		    // istanza statica e privata di questa classe
		    private static DBConnection dbcon = null;
		    // istanza privata della connessione ad SQL
		    private Connection connection = null;

		    // costruttore private
		    private DBConnection(){}

		    // metodo pubblico per ottenere una istanza della classe privata
		    public static DBConnection getDBConnection()
		    {   // se la classe connessione è nulla, la crea
		        if (dbcon == null) {
		            dbcon = new DBConnection();
		        }
		        // e la restituisce
		        return dbcon;
		    }
		    public Connection getConnectionBySchema(String schema_name) throws SQLException,ClassNotFoundException,IOException,RuntimeException
		    {
		        String password = null;
		        BufferedReader bufferReader = null;
		        if (Objects.equals(schema_name, ""))
		            throw new RuntimeException("Schema name is empty");
		        
		           // se la connessione non esiste oppure è stata chiusa
		            if(connection==null || connection.isClosed())
		            {   //legge la pwd dal file
		                bufferReader = new BufferedReader(new FileReader(new File("src/main/java/password.txt")));
		                password = bufferReader.readLine();
		                // registra il driver
		                Class.forName("org.postgresql.Driver");
		                // chiama il DriverManager e chiedi la connessione
		                String s_url="jdbc:postgresql://localhost:5432/postgres?currentSchema="+schema_name;
		                System.out.println("surl" + s_url);
		                connection  = DriverManager.getConnection(s_url, "postgres", password);

		            } 

		        return connection;
		    }

	}


