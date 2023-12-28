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
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterUserFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField emailField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JTextField nuberField;
	private JComboBox <String>numberComboBox;
	private JComboBox<String> comboBox;
	private JLabel lbMatricule;
	private final int minHeigth = 330;
	private final int minWidth = 640;
	Controller controller;
	private JTextField matriculeField;

	/**
	 * Launch the application.
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
		
		nuberField = new JTextField();
		nuberField.setBorder(new LineBorder(new Color(0, 0, 0)));
		nuberField.setColumns(10);
		
		JLabel lbSex = new JLabel("sesso");
		lbSex.setHorizontalAlignment(SwingConstants.TRAILING);
		lbSex.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"M", "F", "Altro"}));
		
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
				}catch(InvalidUsername exc) {
					ShowMessage();
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
							.addComponent(nuberField, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
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
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(88))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(createAccoutnBtn)
											.addPreferredGap(ComponentPlacement.UNRELATED)))
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
						.addComponent(nuberField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbSex, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(createAccoutnBtn)
						.addComponent(backBtn))
					.addContainerGap(101, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(185, Short.MAX_VALUE)
					.addComponent(ImageLabel, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	private Dimension setMinDimension() {
		Dimension dimension = new Dimension();
		dimension.setSize(minWidth,minHeigth);
		return dimension;
	}

	private void checkTextField(JTextField field) throws InvalidUsername{
		String text = field.getText();
		if(text.isBlank())
			throw new InvalidUsername();
	}
	private void ShowMessage() {
		JOptionPane.showMessageDialog(this, "Errore: inserire un valore nei campi", "Errore", JOptionPane.WARNING_MESSAGE);
	}
	//TODO controllare numero di telefono se inserito e matricola se ci sono errori lanciare un eccezione(creare un package con le classi di tutte le eccezioni userdefined)
}
