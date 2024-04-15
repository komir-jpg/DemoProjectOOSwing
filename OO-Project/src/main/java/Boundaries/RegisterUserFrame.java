package Boundaries;
import ExceptionPackage.*;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.LineBorder;
import com.sanctionco.jmail.InvalidEmailException;
import com.sanctionco.jmail.JMail;

import Controllers.RegisterUserController;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowStateListener;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Toolkit;

public class RegisterUserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField emailField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JComboBox<String> SexComboBox;
	//private String phoneNumber;
	private final int minHeigth = 350;
	private final int minWidth = 640;
	RegisterUserController controller;
	private int idIncrement = 0; //sol temporanea creare un trigger di autoincremento per tutte le pk

	/*TODO 
	 * controllare numero di telefono se inserito 
	 * matricola se ci sono errori lanciare un eccezione
	 * creare un package con le classi di tutte le eccezioni userdefined
	 * al text field posso castare ad intero e se lancia un'eccezione la posso gestire
	 * lo si può fare per tutti i text field
	 * inserire campo data di nascita e rimuovere il campo matricola
	*/
	/**
	 * Create the frame.
	 */
	public RegisterUserFrame(RegisterUserController myController) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterUserFrame.class.getResource("/resources/_3707e1ea-9c9b-4142-82e2-be32952fd594_res_icon.png")));
		setTitle("registrazione");
		setName("registrati");
		controller = myController;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 416);
		contentPane = new JPanel();
		contentPane.setMinimumSize(new Dimension(500, 500));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		setMinimumSize(new Dimension(500, 500));
		
		JLabel lbName = new JLabel("nome");
		lbName.setHorizontalAlignment(SwingConstants.TRAILING);
		lbName.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		
		nameField = new JTextField();
		nameField.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		nameField.setBorder(new LineBorder(new Color(0, 0, 0)));
		nameField.setCaretColor(new Color(0, 0, 0));
		nameField.setBackground(new Color(255, 255, 255));
		nameField.setColumns(10);
		
		JLabel lblSurname = new JLabel("cognome");
		lblSurname.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSurname.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		
		surnameField = new JTextField();
		surnameField.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		surnameField.setBorder(new LineBorder(new Color(0, 0, 0)));
		surnameField.setColumns(10);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		
		emailField = new JTextField();
		emailField.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		emailField.setBorder(new LineBorder(new Color(0, 0, 0)));
		emailField.setColumns(10);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		usernameField.setBorder(new LineBorder(new Color(0, 0, 0)));
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		passwordField.setBorder(new LineBorder(new Color(0, 0, 0)));
		passwordField.setColumns(10);
		
		
		JLabel lbSex = new JLabel("sesso");
		lbSex.setHorizontalAlignment(SwingConstants.TRAILING);
		lbSex.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		
		SexComboBox = new JComboBox<String>();
		SexComboBox.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		SexComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"M", "F", "Altro"}));
		
		JLabel ImageLabel = new JLabel("");
		ImageLabel.setIcon(new ImageIcon(RegisterUserFrame.class.getResource("/resources/MSN_Messenger_23101.png")));
		
		JButton createAccoutnBtn = new JButton("Crea");
		createAccoutnBtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		createAccoutnBtn.setBackground(Color.WHITE);
		createAccoutnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					checkTextField(nameField);
					checkTextField(surnameField);
					checkTextField(emailField);
					checkTextField(usernameField);
					checkTextField(passwordField);
					checkEmailFormat();
					setNewUser();
					ShowInfoMessage("account creato correttamente","info");
					controller.setLoginFrame(RegisterUserFrame.this);
				}catch(InvalidInsertion InvalidInsertionExc) {
					ShowMessage("Errore: inserire un valore nei campi","Errore");
				}catch (DBconnectionError DBconnectionError) {
					ShowMessage("Errore: connessione al db non riuscita","Errore");
				}catch(InvalidEmailException InvalidEmail) {
					ShowMessage("Inserire una mail valida", "Errore");	
				}
				
			}
		});
		createAccoutnBtn.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		
		JButton backBtn = new JButton("indietro");
		backBtn.setBackground(Color.WHITE);
		backBtn.setBorder(new LineBorder(new Color(0, 0, 0)));
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setLoginFrame(RegisterUserFrame.this);
			}
		});
		backBtn.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		
		JLabel lblNewLabel = new JLabel("Foto label");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lbName, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblSurname, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(emailField, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
										.addComponent(surnameField, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(usernameField, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
										.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))))
							.addGap(33))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(createAccoutnBtn, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbSex, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(SexComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 88, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addComponent(ImageLabel, GroupLayout.PREFERRED_SIZE, 256, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lbName, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
										.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblSurname, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
										.addComponent(surnameField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(emailField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
										.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE))
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(lbSex, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
										.addComponent(SexComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(142)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(createAccoutnBtn, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
										.addComponent(backBtn, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
								.addComponent(ImageLabel, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(142)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}


	private void checkTextField(JTextField field) throws InvalidInsertion{
		String text = field.getText();
		if(text.isBlank())
			throw new InvalidInsertion();
	}
	private void ShowMessage(String testo,String titolo) {
		JOptionPane.showMessageDialog(this, testo, titolo, JOptionPane.WARNING_MESSAGE);
	}
	private void ShowInfoMessage(String testo,String titolo) {
		JOptionPane.showMessageDialog(this,testo,titolo,JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void checkEmailFormat() throws InvalidEmailException{
		String email = emailField.getText();
		JMail.enforceValid(email);
	}
	

	private String getNameField() {
		return nameField.getText();
	}
	private String getSurnameField() {
		return surnameField.getText();
	}
	private String getSexField() {
		return SexComboBox.getItemAt(SexComboBox.getSelectedIndex());
	}
	private String getUsernameField() {
		return usernameField.getText();
	}
	private String getEmailField() {
		return emailField.getText();
	}
	private String getPasswordField() {
		return new String(passwordField.getPassword());
	}
	
	
	private void setNewUser() throws DBconnectionError{
		try {
			controller.InsertNewUser(
									getNameField(),
									getSurnameField(),
									getSexField(),
									getUsernameField(),
									getEmailField(),
									getPasswordField());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBconnectionError();
		}
	}
}
