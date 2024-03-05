package Boundaries;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.*;
import ExceptionPackage.DBconnectionError;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CreateGroupDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private HomePageController controller;
	private JTextField groupNameTextField;
	private JTextArea descriptionTextArea;
	private JComboBox<String> TagComboBox;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public CreateGroupDialog(HomePageController myController) {
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		controller = myController;
		setBounds(100, 100, 543, 389);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel CreateGroupLabel = new JLabel("Crea Gruppo");
		CreateGroupLabel.setHorizontalAlignment(SwingConstants.CENTER);
		CreateGroupLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		groupNameTextField = new JTextField();
		groupNameTextField.setColumns(10);
		
		TagComboBox = new JComboBox<String>();
		TagComboBox.setEditable(true);
		DefaultComboBoxModel<String> comboModel= new DefaultComboBoxModel<String>();
		try {
			comboModel.addAll(controller.setCategory());
			TagComboBox.setModel(comboModel);
		} catch (ClassNotFoundException | SQLException | IOException | RuntimeException e) {
			
			e.printStackTrace();
		}
		
		
		JLabel GroupNameLabel = new JLabel("nome");
		GroupNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		GroupNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel TagLabel = new JLabel("categoria");
		TagLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		TagLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("descrizione");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addComponent(TagLabel)
						.addComponent(GroupNameLabel, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(TagComboBox, 0, 260, Short.MAX_VALUE)
						.addComponent(groupNameTextField, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
						.addComponent(scrollPane))
					.addGap(160))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addComponent(CreateGroupLabel, GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(CreateGroupLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(GroupNameLabel, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addComponent(groupNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(TagComboBox, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(TagLabel))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
					.addGap(20))
		);
		
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
							if(TagComboBox.getSelectedIndex() == -1)
								createTag();
							createNewGroup();
							ShowInfoMassage("Creazione Gruppo", "gruppo creato correttamente");
							BackFrame();
						} catch (DBconnectionError DBerror) {
							ShowMessage("Errore", "qualcosa è andato storto");
							DBerror.printStackTrace();
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
		controller.setHomePageFrameFromDialog(this);
	}
	private void createNewGroup() throws DBconnectionError {
		try {
			controller.NewGroup(groupNameTextField.getText(),descriptionTextArea.getText(),(String)TagComboBox.getSelectedItem());
		} catch (ClassNotFoundException | SQLException | IOException | RuntimeException e) {
			e.printStackTrace();
			throw new DBconnectionError();
		}
	}
	private void createTag() throws DBconnectionError {
		try {
			controller.newTag((String)TagComboBox.getSelectedItem());
		} catch (ClassNotFoundException | SQLException | IOException | RuntimeException e) {
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
	
}
