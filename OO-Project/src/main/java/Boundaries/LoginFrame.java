package Boundaries;
import ExceptionPackage.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Cursor;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import Controllers.LoginController;
import java.awt.Toolkit;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UsernameField;
	private JPasswordField passwordField;
	private JLabel UsernameLabel;
	private JLabel passwordLabel;
	private JButton LoginBtn;
	private JLabel CreateAccount;
	private final int minHeight = 490;
	private final int minWidth = 700;
	private LoginController controller;

	

	/**
	 * Create the frame.
	 */
	public LoginFrame(LoginController myController) {
		lookAndFeel();
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/resources/_3707e1ea-9c9b-4142-82e2-be32952fd594_res_icon.png")));
		setTitle("Unina Social Group Login");
		controller = myController;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setMinimumSize(setMinDimension());
		

		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 64));
		
		JLabel pictureLabel = new JLabel("");
		pictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pictureLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/resources/_3707e1ea-9c9b-4142-82e2-be32952fd594_res.jpg")));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(192, 192, 192));
		panel_2.setBorder(null);
		
		UsernameLabel = new JLabel("username");
		UsernameLabel.setForeground(new Color(0, 0, 64));
		UsernameLabel.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		
		UsernameField = new JTextField();
		UsernameField.setBorder(null);
		UsernameField.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		UsernameField.setColumns(10);
		
		passwordLabel = new JLabel("password");
		passwordLabel.setForeground(new Color(0, 0, 64));
		passwordLabel.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		
		passwordField = new JPasswordField();
		passwordField.setBorder(null);
		passwordField.setColumns(10);
		
		LoginBtn = new JButton("Login");
		LoginBtn.setBackground(new Color(212, 212, 212));
		LoginBtn.setForeground(new Color(0, 0, 64));
		LoginBtn.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		LoginBtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					checkUsername();
					checkLogIn();
				}catch(DBconnectionError ecx) {
					passwordField.setText(null);
					UsernameField.setText(null);
					ShowMessage("ERRORE", "connessione al DB non riuscita");
				}catch(InvalidUsername UserInsertEx) {
					ShowMessage("Errore","inserisci un valore valido nei campi");
				}catch(LogInErrorExc LogInExc) {
					passwordField.setText(null);
					UsernameField.setText(null);
					ShowMessage("Errore", "username o password non corretti");
				}
			}
		});
		
		CreateAccount = new JLabel("crea un account");
		CreateAccount.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		CreateAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.setRegisterFrame(LoginFrame.this);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				CreateAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
			}
		});
		CreateAccount.setForeground(new Color(0, 0, 255));
		CreateAccount.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		
		JLabel textLabel = new JLabel("o");
		textLabel.setForeground(new Color(0, 0, 64));
		textLabel.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
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
		
		JLabel lblNewLabel = new JLabel("Benvenuto su Unina Social Group");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Cascadia Code", Font.PLAIN, 16));
		lblNewLabel.setForeground(Color.WHITE);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(28)
							.addComponent(pictureLabel, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(pictureLabel, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(131)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(UsernameLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_2.createSequentialGroup()
								.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_panel_2.createSequentialGroup()
								.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel_2.createSequentialGroup()
										.addComponent(UsernameField, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
										.addGap(44))
									.addGroup(gl_panel_2.createSequentialGroup()
										.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
										.addGap(44))
									.addGroup(gl_panel_2.createSequentialGroup()
										.addComponent(LoginBtn, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
										.addGap(118))
									.addGroup(gl_panel_2.createSequentialGroup()
										.addComponent(textLabel, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(CreateAccount, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
								.addGap(39)))))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(79)
					.addComponent(UsernameLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(UsernameField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(LoginBtn, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(textLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(CreateAccount, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
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
	}
	
	private void checkLogIn() throws LogInErrorExc, DBconnectionError{
		boolean LogInResult;
		String username = UsernameField.getText();
		String password = new String(passwordField.getPassword());
		try {
			LogInResult = controller.CheckUserLogIn(username,password);
			if(LogInResult) {
				controller.userLogIn(username);
				controller.setHomePageFrame(this);
			}
			else {
				throw new LogInErrorExc();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBconnectionError();
		}
		
	}
	
	
	private Dimension setMinDimension() {
		Dimension Dimension = new Dimension();
		Dimension.setSize(minWidth,minHeight);
		return Dimension;
	}
	private void ShowMessage(String titolo,String testo) {
		JOptionPane.showMessageDialog(this, testo, titolo, JOptionPane.WARNING_MESSAGE);
	}
	private void lookAndFeel() {
		String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}