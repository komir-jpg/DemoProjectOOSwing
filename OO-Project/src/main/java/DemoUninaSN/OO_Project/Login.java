package DemoUninaSN.OO_Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Cursor;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UsernameField;
	private JPasswordField passwordField;
	private JLabel UsernameLabel;
	private JLabel passwordLabel;
	private JButton LoginBtn;
	private JLabel CreateAccount;
	private final int minHeight = 490;
	private final int minWidth = 810;
	Controller controller;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Login(Controller myController) {
		controller = myController;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setMinimumSize(setMinDimension());

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(64, 0, 128));
		
		JLabel pictureLabel = new JLabel("");
		pictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pictureLabel.setIcon(new ImageIcon("C:\\Users\\mirko\\Pictures\\_3707e1ea-9c9b-4142-82e2-be32952fd594_res.jpg"));
		
		JPanel panel_2 = new JPanel();
		
		UsernameLabel = new JLabel("username");
		UsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		UsernameField = new JTextField();
		UsernameField.setColumns(10);
		
		passwordLabel = new JLabel("password");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		
		LoginBtn = new JButton("Login");
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkInsertion();
			}
		});
		
		CreateAccount = new JLabel("create an account");
		CreateAccount.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		CreateAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Cliccato");
				controller.setRegisterFrame();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("Hover");
				CreateAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
			}
		});
		CreateAccount.setForeground(SystemColor.textHighlight);
		CreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel textLabel = new JLabel("or");
		textLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(329)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(374, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
		);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(28)
					.addComponent(pictureLabel, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(128)
					.addComponent(pictureLabel, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(131)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(UsernameLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(UsernameField, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
							.addGap(44))
						.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
							.addGap(44))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(LoginBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addGap(118))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(20)
							.addComponent(CreateAccount, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addGap(84))
						.addComponent(textLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(39))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(74)
					.addComponent(UsernameLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(UsernameField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(LoginBtn, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(CreateAccount, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(textLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
		);
		panel_2.setLayout(gl_panel_2);
		contentPane.setLayout(gl_contentPane);
	}
	private void checkUsername() throws InvalidUsername{
		
			String Username = UsernameField.getText();
			if(Username.isBlank()) {
				throw new InvalidUsername();
			}
			else
				checkPassword();
	}
	
	private void checkPassword() throws InvalidPassword{
			String password = new String(passwordField.getPassword());
			if(password.isBlank()) {
				throw new InvalidPassword();
			}
			else
				controller.setHomePageFrame();
		}
	//usa popup
	private void checkInsertion() {
		try {
			checkUsername();
		}catch(InvalidUsername UserInsertEx) {
			ShowMessage();
		}
	}
	private Dimension setMinDimension() {
		Dimension tempDimension = new Dimension();
		tempDimension.setSize(minWidth,minHeight);
		return tempDimension;
	}
	private void ShowMessage() {
		JOptionPane.showMessageDialog(this, "Errore: inserire un valore nei campi", "Errore", JOptionPane.WARNING_MESSAGE);
	}
}