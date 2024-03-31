package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import DAO.*;
public class GroupChart extends JPanel {

	private static final long serialVersionUID = 1L;
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ChartFrame frame;
	
	public GroupChart() throws SQLException {
		super();
		ConnectionToDB connectionToDB = new ConnectionToDB();
		connection = connectionToDB.getConnection();
		//getChart(groupName);
	}
	
	private ArrayList<DataBaseResult> numPostByMonth(String groupName) throws SQLException{
		String query = "select count(*) as numpost,\r\n"
				+ "       extract(YEAR FROM datapost) as anno,\r\n"
				+ "       extract(Month FROM datapost) as mese\r\n"
				+ "       from progettobd_unina_social_network.post\r\n"
				+ "where gruppo = ? \r\n"
				+ "group by mese,anno order by mese;";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, groupName);
		ResultSet queryRS = preparedStatement.executeQuery();
		ArrayList<DataBaseResult> result = new ArrayList<DataBaseResult>();
		while(queryRS.next())
		{
			DataBaseResult dbres = new DataBaseResult(queryRS.getInt("numpost"),
													  queryRS.getInt("anno"),
													  queryRS.getInt("mese"));
			result.add(dbres);
		}
		preparedStatement.close();
		queryRS.close();
		return result;
	}
	
	public ChartFrame getChart(String groupName) throws SQLException {
		ArrayList<DataBaseResult> numberOfPosts = numPostByMonth(groupName);
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(DataBaseResult dbres : numberOfPosts) {
			dataset.addValue(dbres.getCount(), groupName, dbres.convertMonth());
		}
		JFreeChart lineChart = ChartFactory.createLineChart("post per mese", "mesi", "numero di post", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		frame = new ChartFrame("Result",lineChart);
		return frame;
	}
	
	

}
