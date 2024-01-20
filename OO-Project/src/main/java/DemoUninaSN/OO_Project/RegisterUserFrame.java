package DemoUninaSN.OO_Project;

import ExceptionPackage.*;

import java.awt.Dimension;
import java.awt.EventQueue;

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
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class RegisterUserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField emailField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField numberField;
	private JComboBox <String>numberComboBox;
	private JComboBox<String> SexComboBox;
	private JLabel lbMatricule;
	//private String phoneNumber;
	private final int minHeigth = 350;
	private final int minWidth = 640;
	Controller controller;
	private JTextField matriculeField;
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
	public RegisterUserFrame(Controller myController) {
		controller = myController;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 416);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		setMinimumSize(setMinDimension());
		
		JLabel lbName = new JLabel("nome");
		lbName.setHorizontalAlignment(SwingConstants.TRAILING);
		lbName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		nameField = new JTextField();
		nameField.setBorder(new LineBorder(new Color(0, 0, 0)));
		nameField.setCaretColor(new Color(0, 0, 0));
		nameField.setBackground(new Color(255, 255, 255));
		nameField.setColumns(10);
		
		JLabel lblSurname = new JLabel("cognome");
		lblSurname.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		surnameField = new JTextField();
		surnameField.setBorder(new LineBorder(new Color(0, 0, 0)));
		surnameField.setColumns(10);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		emailField = new JTextField();
		emailField.setBorder(new LineBorder(new Color(0, 0, 0)));
		emailField.setColumns(10);
		
		JLabel lblUsername = new JLabel("username");
		lblUsername.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		usernameField = new JTextField();
		usernameField.setBorder(new LineBorder(new Color(0, 0, 0)));
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("password");
		lblPassword.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new LineBorder(new Color(0, 0, 0)));
		passwordField.setColumns(10);
		
		JLabel lbPhoneNum = new JLabel("telefono");
		lbPhoneNum.setHorizontalAlignment(SwingConstants.TRAILING);
		lbPhoneNum.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		numberComboBox = new JComboBox<String>();
		numberComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"+00", "+39", "+20"}));
		
		numberField = new JTextField();
		numberField.setBorder(new LineBorder(new Color(0, 0, 0)));
		numberField.setColumns(10);
		
		
		JLabel lbSex = new JLabel("sesso");
		lbSex.setHorizontalAlignment(SwingConstants.TRAILING);
		lbSex.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		SexComboBox = new JComboBox<String>();
		SexComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"M", "F", "Altro"}));
		
		JLabel ImageLabel = new JLabel("");
		ImageLabel.setIcon(new ImageIcon("C:\\Users\\mirko\\Pictures\\MSN_Messenger_23101.png"));
		
		JButton createAccoutnBtn = new JButton("Crea");
		createAccoutnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					checkTextField(nameField);
					checkTextField(surnameField);
					checkTextField(emailField);
					checkTextField(usernameField);
					checkTextField(passwordField);
					checkTextField(matriculeField);
					checkTelefono(numberField);
					controller.getDBConnection();
					getFields();
					setNewUser();
				}catch(InvalidInsertion InvalidInsertionExc) {
					ShowMessage("Errore: inserire un valore nei campi","Errore");
				}catch(InvalidNumber InvalidNumberExc) {
					ShowMessage("Errore: numero di telefono non valido","Errore");
				} catch (DBconnectionError DBconnectionError) {
					ShowMessage("Errore: connessione al db non riuscita","Errore");
				}
				
			}
		});
		createAccoutnBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton backBtn = new JButton("indietro");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setLoginPage();
			}
		});
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		lbMatricule = new JLabel("matricola");
		lbMatricule.setHorizontalAlignment(SwingConstants.TRAILING);
		lbMatricule.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		matriculeField = new JTextField();
		matriculeField.setColumns(10);
		matriculeField.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JButton tempBTN = new JButton("temp");
		tempBTN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				try {
//					controller.getDBConnection();
//					
//				} catch (ClassNotFoundException | SQLException | IOException | RuntimeException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} catch (DBconnectionError e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
				
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbPhoneNum, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(numberComboBox, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(numberField, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lbName, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(usernameField, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(emailField, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblSurname, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(surnameField, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbMatricule, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbSex, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(matriculeField, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(createAccoutnBtn)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(tempBTN)
												.addComponent(SexComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addGap(46)))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(backBtn)))))
					.addGap(22)
					.addComponent(ImageLabel, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
					.addGap(27))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lbName, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSurname, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(surnameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbMatricule, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(matriculeField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbPhoneNum, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
								.addComponent(numberComboBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(numberField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(SexComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lbSex, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addGap(22)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(createAccoutnBtn)
								.addComponent(backBtn))
							.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
							.addComponent(tempBTN)
							.addGap(35))
						.addComponent(ImageLabel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)))
		);
		contentPane.setLayout(gl_contentPane);
	}

	private Dimension setMinDimension() {
		Dimension dimension = new Dimension();
		dimension.setSize(minWidth,minHeigth);
		return dimension;
	}

	private void checkTextField(JTextField field) throws InvalidInsertion{
		String text = field.getText();
		if(text.isBlank())
			throw new InvalidInsertion();
	}
	private void ShowMessage(String testo,String titolo) {
		JOptionPane.showMessageDialog(this, testo, titolo, JOptionPane.WARNING_MESSAGE);
	}
	
	private void checkTelefono(JTextField telephoneField) throws InvalidNumber{
		String telephone = telephoneField.getText();
		final int LENGTH_PHONE_NUMBER = 9;
		
		if(!telephone.isBlank() && telephone.length() != LENGTH_PHONE_NUMBER) {
			telephoneField.setText(null);
			throw new InvalidNumber();
		}else {
			checkInt(telephone);
		}
		
	}
	private void checkInt(String telephone)throws InvalidNumber {
		
		try{
			int phoneNumber = new Integer(telephone);
		}catch(NumberFormatException e) {
			throw new InvalidNumber();
		}
		
	}
	private void getFields() {
		controller.newUser();
		controller.getNameField(nameField.getText());
		controller.getSurnameField(surnameField.getText());
		controller.getSexField(SexComboBox.getItemAt(SexComboBox.getSelectedIndex()));
		controller.getUsername(usernameField.getText());
		controller.getEmailField(emailField.getText());
		controller.getPasswordField(new String(passwordField.getPassword()));
		
	}
	private void setNewUser() throws DBconnectionError{
		try {
			controller.InsertNewUser(idIncrement++);
		} catch (ClassNotFoundException | SQLException | IOException | RuntimeException e) {
			e.printStackTrace();
			throw new DBconnectionError();
		}
	}
}
