package DemoUninaSN.OO_Project;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.ComponentOrientation;

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
		setBounds(new Rectangle(200, 200, 150, 150));
		controller = myController;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 770);
		contentPane = new JPanel();
		contentPane.setMinimumSize(new Dimension(30, 30));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton LoginBtn = new JButton("Login");
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.loginScreen();
				}catch(Exception exc) {
					ConnectionErrorMessage("Connessione al DB","connessione non riuscita");
				}
			}
		});
		LoginBtn.setBounds(143, 81, 138, 30);
		contentPane.add(LoginBtn);
		
		JButton SignInBtn = new JButton("Registrati");
		SignInBtn.setBounds(143, 166, 138, 30);
		contentPane.add(SignInBtn);
	}
	private void ConnectionErrorMessage(String titolo,String testo) {
		JOptionPane.showMessageDialog(this,testo,titolo,JOptionPane.ERROR_MESSAGE);
	}
}
