package Boundaries;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controllers.CreateGroupController;
import ExceptionPackage.DBconnectionError;
import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CreateGroupDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private CreateGroupController controller;
	private JTextField groupNameTextField;
	private JTextArea descriptionTextArea;
	JList<String> tagList;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public CreateGroupDialog(CreateGroupController myController) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					tagList.setModel(setListModel());
					tagList.setCellRenderer(new DefaultListCellRenderer());
				} catch (SQLException e1) {
					e1.printStackTrace();
					ShowMessage("Errore", "OPS!,qualcosa è andato storto nel caricare i tag");
				}
				
			}
		});
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		controller = myController;
		setBounds(100, 100, 543, 434);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel CreateGroupLabel = new JLabel("Crea Gruppo");
		CreateGroupLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CreateGroupLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		groupNameTextField = new JTextField();
		groupNameTextField.setColumns(10);
		DefaultComboBoxModel<String> comboModel= new DefaultComboBoxModel<String>();
		try {
			comboModel.addAll(controller.setTag());
		} catch ( SQLException e) {
			
			e.printStackTrace();
		}
		
		
		JLabel GroupNameLabel = new JLabel("nome");
		GroupNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		GroupNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel TagLabel = new JLabel("seleziona tag esistente");
		TagLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		TagLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("descrizione");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel lblNewLabel_1 = new JLabel("oppure");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblNewTagLabel = new JLabel("inserisci nuovo tag");
		lblNewTagLabel.setForeground(SystemColor.textHighlight);
		lblNewTagLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					showInputDialog();
				} catch (SQLException e1) {
					e1.printStackTrace();
					ShowMessage("Errore", "c'e stato un errore nell'inserimento del tag");
				} catch(InputMismatchException e2) {
					ShowMessage("Errore", "inserisci un tag valido");
				}
			}
		});
		lblNewTagLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewTagLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel)
								.addComponent(TagLabel)
								.addComponent(GroupNameLabel, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
								.addComponent(groupNameTextField, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblNewLabel_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblNewTagLabel)))
							.addGap(160))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(CreateGroupLabel, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(CreateGroupLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(groupNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(GroupNameLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(TagLabel)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewTagLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
						.addComponent(lblNewLabel))
					.addContainerGap())
		);
		
		tagList = new JList<String>();
		tagList.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tagList.repaint();
			}
		});
		tagList.setValueIsAdjusting(true);
		scrollPane_1.setViewportView(tagList);
		
		descriptionTextArea = new JTextArea();
		descriptionTextArea.setLineWrap(true);
		scrollPane.setViewportView(descriptionTextArea);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							createNewGroup();
							selectedTag(tagList.getSelectedValuesList());
							ShowInfoMassage("Creazione Gruppo", "gruppo creato correttamente");
							BackFrame();
						} catch (DBconnectionError DBerror) {
							ShowMessage("Errore", "qualcosa è andato storto");
							DBerror.printStackTrace();
						} catch (SQLException e1) {
							ShowMessage("Errore","qualcosa è andato storto");
							e1.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Annulla");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						BackFrame();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void BackFrame() {
		controller.setHomePageFrameFromDialog();
	}
	private void createNewGroup() throws DBconnectionError {
		try {
			controller.newGroup(groupNameTextField.getText(),descriptionTextArea.getText());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBconnectionError();
		}
	}
	
	private void ShowMessage(String titolo,String testo) {
		JOptionPane.showMessageDialog(this, testo, titolo, JOptionPane.WARNING_MESSAGE);
	}
	private void ShowInfoMassage(String titolo,String testo) {
		JOptionPane.showMessageDialog(this, testo,titolo,JOptionPane.INFORMATION_MESSAGE);
	}
	private DefaultListModel<String>setListModel() throws SQLException{
		DefaultListModel<String> listModel =  new DefaultListModel<String>();
		listModel.addAll(controller.setTag());
		return listModel;
	}
	private void showInputDialog() throws SQLException,InputMismatchException {
		String userInput;
		userInput = JOptionPane.showInputDialog(this, "inserisci un nuovo tag");
		setNewTag(userInput);
		
	}
	private void setNewTag(String tag) throws SQLException,InputMismatchException {
		if(tag != null) {
			controller.checkTag(tag);
			controller.newTag(tag);	
		}
		
	}
	private void selectedTag(List<String> selectedValues) throws SQLException {
		Iterator<String> listIterator = selectedValues.iterator();
		while(listIterator.hasNext()) {
			controller.setGroupTags(groupNameTextField.getText(),listIterator.next());
		}
		
	}
}
