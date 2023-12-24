package DemoUninaSN.OO_Project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Demo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UsernameField;
	private JPasswordField passwordField;
	Controller controller;
	private JLabel UsernameLabel;
	private JLabel passwordLabel;
	private JButton LoginBtn;
	private JLabel CreateAccount;
	private JLabel InsertUsernameLabel;
	private JLabel insertPasswordLabel;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Demo frame = new Demo();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Demo(Controller myController) {
		controller = myController;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(64, 0, 128));
		panel.setBounds(0, 0, 332, 455);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel pictureLabel = new JLabel("");
		pictureLabel.setBounds(28, 128, 276, 198);
		panel.add(pictureLabel);
		pictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pictureLabel.setIcon(new ImageIcon("C:\\Users\\mirko\\Pictures\\_3707e1ea-9c9b-4142-82e2-be32952fd594_res.jpg"));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(329, 0, 385, 455);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		UsernameLabel = new JLabel("username");
		UsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		UsernameLabel.setBounds(131, 74, 105, 26);
		panel_2.add(UsernameLabel);
		
		UsernameField = new JTextField();
		UsernameField.setBounds(131, 111, 163, 20);
		panel_2.add(UsernameField);
		UsernameField.setColumns(10);
		
		passwordLabel = new JLabel("password");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordLabel.setBounds(131, 142, 105, 26);
		panel_2.add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(131, 179, 163, 20);
		panel_2.add(passwordField);
		passwordField.setColumns(10);
		
		LoginBtn = new JButton("Login");
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkUsername();
				checkPassword();
			}
		});
		LoginBtn.setBounds(131, 210, 89, 23);
		panel_2.add(LoginBtn);
		
		CreateAccount = new JLabel("create an account");
		CreateAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Cliccato");
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				System.out.println("Hover");
			}
		});
		CreateAccount.setForeground(SystemColor.textHighlight);
		CreateAccount.setFont(new Font("Tahoma", Font.PLAIN, 13));
		CreateAccount.setBounds(151, 244, 103, 26);
		panel_2.add(CreateAccount);
		
		JLabel textLabel = new JLabel("or");
		textLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textLabel.setBounds(131, 244, 23, 26);
		panel_2.add(textLabel);
		
		InsertUsernameLabel = new JLabel("insert username");
		InsertUsernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		InsertUsernameLabel.setVisible(false);
		InsertUsernameLabel.setForeground(Color.RED);
		InsertUsernameLabel.setBounds(248, 82, 90, 14);
		panel_2.add(InsertUsernameLabel);
		
		insertPasswordLabel = new JLabel("insert password\r\n");
		insertPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		insertPasswordLabel.setVisible(false);
		insertPasswordLabel.setForeground(Color.RED);
		insertPasswordLabel.setBounds(246, 150, 90, 14);
		panel_2.add(insertPasswordLabel);
	}
	private void checkUsername() {
		
			String Username = UsernameField.getText();
			System.out.println(Username);
			if(Username.isBlank()) {
				System.out.println("Inserisci username");
				InsertUsernameLabel.setVisible(true);
			}
			else
				controller.setFrame2();
	}
	
	private void checkPassword() {
			String password = new String(passwordField.getPassword());
			if(password.isBlank()) {
				System.out.println("inserisci password");
			insertPasswordLabel.setVisible(true);
			}
			else
				controller.setFrame2();
		}	
}
