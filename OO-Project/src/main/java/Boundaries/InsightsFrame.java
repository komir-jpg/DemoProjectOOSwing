package Boundaries;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.InsightsController;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.Scrollbar;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.DefaultComboBoxModel;
import java.time.Month;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsightsFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrPane;
	private InsightsController controller;
	JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	
	//Post con più like 
	//post con più commenti
	

	/**
	 * Create the frame.
	 */
	public InsightsFrame(InsightsController myController) {
		setMinimumSize(new Dimension(400, 300));
		controller = myController;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 661, 903);
		contentPane = new JPanel();
		contentPane.setMinimumSize(new Dimension(400, 300));
		scrPane = new JScrollPane(contentPane);
		scrPane.setMinimumSize(new Dimension(400, 300));
		scrPane.setMaximumSize(new Dimension(1200, 800));
//		getContentPane().add(scrPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(scrPane);
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(comboBox.getSelectedIndex()+1);
			}
		});
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre ", "Novembre", "Dicembre"}));
		
		JLabel lblNewLabel = new JLabel("mese di riferimento");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		
		JLabel lblNewLabel_1 = new JLabel("post con numero maggiore di like");
		
		JLabel lblNewLabel_2 = new JLabel("post con numero maggiore di commenti");
		
		JLabel lblNewLabel_3 = new JLabel("");
		
		JLabel lblNewLabel_4 = new JLabel("post con minor numero di like");
		
		JLabel lblNewLabel_5 = new JLabel("post con il minor numero di commenti");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(63)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addContainerGap())
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(723, Short.MAX_VALUE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 269, GroupLayout.PREFERRED_SIZE)
										.addGap(353))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED))
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE)
												.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addComponent(lblNewLabel)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE))
												.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)))
										.addGap(169)))
								.addGap(303))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(scrollPane_3, Alignment.LEADING)
									.addComponent(scrollPane_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE))
								.addContainerGap()))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_3)
					.addGap(44)
					.addComponent(lblNewLabel_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_5)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addGap(274))
		);
		
		JTextArea textArea_3 = new JTextArea();
		scrollPane_3.setViewportView(textArea_3);
		
		JTextArea textArea_2 = new JTextArea();
		scrollPane_2.setViewportView(textArea_2);
		
		JTextArea textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		contentPane.setLayout(gl_contentPane);
	}
}
