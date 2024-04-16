package Boundaries;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;

import Controllers.InsightsController;
import utils.GroupChart;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Scrollbar;
import java.awt.TextArea;

import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.Dimension;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;

import java.time.Month;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;

public class InsightsFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrPane;
	private InsightsController controller;
	private JTextArea mostLikedPostTextArea;
	private JTextArea leastCommentedPostTextArea;
	private JTextArea leastLikedPostTextArea;
	private JTextArea mostCommentedPostTextArea;
	private JComboBox<String> groupComboBox;
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	
	//Post con più like 
	//post con più commenti
	

	/**
	 * Create the frame.
	 */
	public InsightsFrame(InsightsController myController) {
		setTitle("insights");
		setIconImage(Toolkit.getDefaultToolkit().getImage(InsightsFrame.class.getResource("/resources/_3707e1ea-9c9b-4142-82e2-be32952fd594_res_icon.png")));
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				groupComboBox.setSelectedItem((String)controller.getSelectedGroup());
			}
		});
		setMaximumSize(new Dimension(1440, 1080));
		setMinimumSize(new Dimension(400, 300));
		controller = myController;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 661, 903);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(1200, 800));
		contentPane.setMaximumSize(new Dimension(1200, 900));
		contentPane.setMinimumSize(new Dimension(400, 300));
//		scrPane = new JScrollPane(contentPane);
//		scrPane.setSize(new Dimension(1200, 800));
//		
//		scrPane.setMinimumSize(new Dimension(400, 300));
//		scrPane.setMaximumSize(new Dimension(1200, 800));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		comboBox = new JComboBox<String>();
		comboBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		comboBox.setFont(new Font("Cascadia Code", Font.PLAIN, 11));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int month = comboBox.getSelectedIndex()+1;
					setInsights(month);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre ", "Novembre", "Dicembre"}));
		
		JLabel lblNewLabel = new JLabel("mese di riferimento");
		lblNewLabel.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel lblNewLabel_1 = new JLabel("post con numero maggiore di like");
		lblNewLabel_1.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		
		JLabel lblNewLabel_2 = new JLabel("post con numero maggiore di commenti");
		lblNewLabel_2.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		
		JLabel lblNewLabel_4 = new JLabel("post con minor numero di like");
		lblNewLabel_4.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		
		JLabel lblNewLabel_5 = new JLabel("post con il minor numero di commenti");
		lblNewLabel_5.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JScrollPane scrollPane_3 = new JScrollPane();
		
		JScrollPane scrollPane_4 = new JScrollPane();
		
		JLabel lblNewLabel_6 = new JLabel("gruppo selezionato");
		lblNewLabel_6.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.TRAILING);
		
		try {
			groupComboBox = new JComboBox<String>(setGroupModel());
			groupComboBox.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			groupComboBox.setFont(new Font("Cascadia Code", Font.PLAIN, 11));
			groupComboBox.setSelectedIndex(0);
			groupComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						controller.setSelectedGroup((String)groupComboBox.getSelectedItem());
						setInsights(comboBox.getSelectedIndex()+1);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JButton chartButton = new JButton("mostra grafico ");
		chartButton.setFont(new Font("Cascadia Code", Font.PLAIN, 11));
		chartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					groupChart((String)groupComboBox.getSelectedItem());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel_3 = new JLabel("numero di post per mese");
		lblNewLabel_3.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		lblNewLabel_3.setIcon(new ImageIcon(InsightsFrame.class.getResource("/resources/chart-histogram.png")));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(63)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chartButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 443, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
								.addComponent(lblNewLabel_4)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 234, GroupLayout.PREFERRED_SIZE))
							.addGap(174)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel_6)
									.addGap(18)
									.addComponent(groupComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_6)
						.addComponent(groupComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_5)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(chartButton))
					.addContainerGap(167, Short.MAX_VALUE))
		);
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {lblNewLabel_4, lblNewLabel_3});
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {comboBox, groupComboBox});
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {lblNewLabel, lblNewLabel_6});
		gl_contentPane.linkSize(SwingConstants.VERTICAL, new Component[] {scrollPane, scrollPane_2, scrollPane_3, scrollPane_4});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {comboBox, groupComboBox});
		gl_contentPane.linkSize(SwingConstants.HORIZONTAL, new Component[] {scrollPane, scrollPane_2, scrollPane_3, scrollPane_4});
		
		mostCommentedPostTextArea = new JTextArea();
		mostCommentedPostTextArea.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		scrollPane_4.setViewportView(mostCommentedPostTextArea);
		mostCommentedPostTextArea.setEditable(false);
		
		mostLikedPostTextArea = new JTextArea();
		mostLikedPostTextArea.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		scrollPane.setViewportView(mostLikedPostTextArea);
		mostLikedPostTextArea.setEditable(false);
		
		leastCommentedPostTextArea = new JTextArea();
		leastCommentedPostTextArea.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		scrollPane_3.setViewportView(leastCommentedPostTextArea);
		
		leastLikedPostTextArea = new JTextArea();
		leastLikedPostTextArea.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		scrollPane_2.setViewportView(leastLikedPostTextArea);
		contentPane.setLayout(gl_contentPane);
		
		
	}
	private void setMostLikedPost(int month) throws SQLException {
		ArrayList<String> posts = controller.mostNumberOfLikes(month);
		mostLikedPostTextArea.setText("");
		for(String s : posts) {
			mostLikedPostTextArea.setText(s);
		}
	}
	private void setMostCommentedPost(int month) throws SQLException {
		ArrayList<String> posts = controller.mostNumberOfComments(month);
		mostCommentedPostTextArea.setText("");
		for(String s : posts) {
			mostCommentedPostTextArea.setText(s);
		}
	}
	private void setLeastLikedPost(int month) throws SQLException {
		ArrayList<String> posts = controller.leastNumberOfLikes(month);
		leastLikedPostTextArea.setText("");
		for(String s : posts) {
			leastLikedPostTextArea.setText(s);
		}
	}
	private void setLeastCommentedPost(int month) throws SQLException {
		ArrayList<String> posts = controller.leastNumberOfComments(month);
		leastCommentedPostTextArea.setText("");
		for(String s : posts) {
			leastCommentedPostTextArea.setText(s);
		}
	}
	private String[] setGroupModel() throws SQLException{
		ArrayList<String> list = controller.getUserAdminGroups();
		String[] array = new String[list.size()];
		list.toArray(array);
		return array;
	}
	private void setInsights(int month) throws SQLException {
		setMostLikedPost(month);
		setMostCommentedPost(month);
		setLeastCommentedPost(month);
		setLeastLikedPost(month);
	}
	private void groupChart(String groupName) throws SQLException {
		controller.showChart(groupName);
	}
	
}
