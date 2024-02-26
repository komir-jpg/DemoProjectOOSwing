package Boundaries;
import DemoUninaSN.OO_Project.*;
import ExceptionPackage.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ActionMap;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Cursor;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private final int minWidth = 700;
	private Controller controller;

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
				try {
					checkUsername();
					controller.getDBConnection();
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
				controller.setRegisterFrame();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				CreateAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				
			}
		});
		CreateAccount.setForeground(SystemColor.textHighlight);
		CreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel textLabel = new JLabel("o");
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
							.addComponent(UsernameField, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
							.addGap(44))
						.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
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
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(textLabel, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(CreateAccount, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
		);
		panel_2.setLayout(gl_panel_2);
		contentPane.setLayout(gl_contentPane);
	}
	//redere generica passando il componente per parametro 
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
	
	private void checkLogIn() throws LogInErrorExc, DBconnectionError {
		boolean LogInResult;
		String username = UsernameField.getText();
		String password = new String(passwordField.getPassword());
		try {
			LogInResult = controller.CheckUserLogIn(username,password);
			if(LogInResult) {
				controller.userLogIn(username);
				controller.setHomePageFrame();
			}
			else {
				throw new LogInErrorExc();
			}
		} catch (ClassNotFoundException | SQLException | IOException | RuntimeException e) {
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
	
}