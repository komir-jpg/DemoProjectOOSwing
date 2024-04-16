package Boundaries;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.DeleteMessageController;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class DeleteMessageDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DeleteMessageController controller;
	private JList<String> messageList;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public DeleteMessageDialog(DeleteMessageController myController) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteMessageDialog.class.getResource("/resources/_3707e1ea-9c9b-4142-82e2-be32952fd594_res_icon.png")));
		setTitle("elimina messaggi");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					messageList.setModel(setUserMessages());
					messageList.setCellRenderer(new DefaultListCellRenderer());
				} catch (SQLException e1) {
					ShowMessage("Errore", "OPS! Qualcosa è andato storto");
					e1.printStackTrace();
				}
				
			}
		});
		controller = myController;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		messageList = new JList<String>();
		messageList.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		messageList.setSelectionBackground(Color.LIGHT_GRAY);
		JLabel lblNewLabel = new JLabel("MESSAGGI");
		lblNewLabel.setIcon(new ImageIcon(DeleteMessageDialog.class.getResource("/resources/noun-delete-message-1167872.png")));
		lblNewLabel.setFont(new Font("Cascadia Code", Font.BOLD, 13));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
						.addComponent(messageList, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(messageList, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String[] splltedString = splitMessageString(messageList.getSelectedValue());
						try {
							deleteMessage(splltedString);
							ShowInfoMessage("Messaggio eliminato", "messaggio eliminato con successo");
						} catch (SQLException e1) {
							e1.printStackTrace();
							ShowMessage("Error", "OPS! Qualcosa è andato storto");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.setHomePageFrame();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private DefaultListModel<String>setUserMessages() throws SQLException{
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.addAll(controller.showUserMessages());
		return listModel;
	}
	private void ShowMessage(String titolo,String testo) {
		JOptionPane.showMessageDialog(this, testo, titolo, JOptionPane.WARNING_MESSAGE);
	}
	private void ShowInfoMessage(String titolo,String testo) {
		JOptionPane.showMessageDialog(this, testo,titolo,JOptionPane.INFORMATION_MESSAGE);
	}
	private String[] splitMessageString(String selectedMessage){
		String messageString = selectedMessage;
		String[] splittedString = messageString.split("    ");
		return splittedString;
	}
	private void deleteMessage(String[] splittedString) throws SQLException {
		String message = splittedString[0];
		String date = splittedString[1];
		controller.deleteMessage(message,date);
		
	}

}
