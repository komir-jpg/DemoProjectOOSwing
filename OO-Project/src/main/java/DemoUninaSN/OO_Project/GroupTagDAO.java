package DemoUninaSN.OO_Project;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class GroupTagDAO extends getIdDAO{
	private Connection connection;
	private PreparedStatement preparedStatement;
	private Statement statement;

	public GroupTagDAO(Connection myConnection) throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		super(myConnection);
	}

	public void insertTagGroup(GroupTag tag) {
		int groupID = getGroupID(tag.getGroupName());
		try {
			statement = connection.createStatement();
			String insertNewGroupTag = "INSERT INTO progettobd_unina_social_network.Tag_gruppo VALUES"+
									    "("+ 
									    "\""+groupID+"\""+","
									    +"\'"+tag.getCategory()+"\'"+","
									    +"\'"+tag.getGroupName()+"\'"+")";
			statement.executeUpdate(insertNewGroupTag);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

