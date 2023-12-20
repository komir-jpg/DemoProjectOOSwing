package DemoUninaSN.OO_Project;

import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DBconnection {
	private static DBconnection dbconnection = null;
	private Connection connection = null;
	
	private DBconnection(){}
	
	public static DBconnection getDBConnection() {
		if(dbconnection == null)
			dbconnection = new DBconnection();
		return dbconnection;
	}
	
    public Connection getConnection()
    {
        String password = null;
        BufferedReader bufferReader = null;
        try
        {   
            if(connection==null || connection.isClosed()){   
                bufferReader = new BufferedReader(new FileReader(new File("src/main/java/pwd.txt")));
                password = bufferReader.readLine();
                
                Class.forName("org.postgresql.Driver");
                
                connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", password);
            }
        } catch (SQLException | ClassNotFoundException | IOException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }


	public Connection getConnectionBySchema(String schema_name) throws Exception//propagate error on screen
    {
        String password = null;
        BufferedReader bufferReader = null;
        if (Objects.equals(schema_name, ""))
            throw new RuntimeException("Schema name is empty");
           
            if(connection==null || connection.isClosed()){   
                bufferReader = new BufferedReader(new FileReader(new File("src/main/java/pwd.txt")));
                password = bufferReader.readLine();                
                Class.forName("org.postgresql.Driver");
                String s_url="jdbc:postgresql://localhost:5432/?currentSchema="+schema_name;
                System.out.println("surl" + s_url);
                connection = DriverManager.getConnection(s_url, "postgres", password);

            }

        return connection;
    }
	

}
