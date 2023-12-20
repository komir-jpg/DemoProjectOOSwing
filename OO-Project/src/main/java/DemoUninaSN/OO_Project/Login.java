package DemoUninaSN.OO_Project;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton backBtn;
	private JButton LogInDBconnBtn;
	Controller controller;

	/**
	 * Create the frame.
	 */
	public Login(Controller myController) {
		controller = myController;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		LogInDBconnBtn = new JButton("login");
		LogInDBconnBtn.setBackground(new Color(255, 255, 255));
		LogInDBconnBtn.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		LogInDBconnBtn.setBounds(169, 199, 96, 29);
		contentPane.add(LogInDBconnBtn);
		
		JLabel lblNewLabel = new JLabel("username");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		lblNewLabel.setBounds(75, 34, 76, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("password");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(75, 97, 76, 22);
		contentPane.add(lblNewLabel_1);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		usernameField.setBounds(169, 34, 168, 22);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		passwordField.setBounds(169, 97, 168, 22);
		contentPane.add(passwordField);
		
		backBtn = new JButton("indietro");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.startPage();
			}
		});
		backBtn.setBackground(new Color(250, 250, 250));
		backBtn.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		backBtn.setBounds(361, 242, 96, 29);
		contentPane.add(backBtn);
	}
}
