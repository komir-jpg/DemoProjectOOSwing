package DemoUninaSN.OO_Project;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Controller controller;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public StartPage(Controller myController) {
		controller = myController;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 770);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton LoginBtn = new JButton("Login");
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.loginScreen();
			}
		});
		LoginBtn.setBounds(143, 81, 138, 30);
		contentPane.add(LoginBtn);
		
		JButton SignInBtn = new JButton("Registrati");
		SignInBtn.setBounds(143, 166, 138, 30);
		contentPane.add(SignInBtn);
	}
}
