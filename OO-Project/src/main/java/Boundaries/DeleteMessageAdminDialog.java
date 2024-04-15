package Boundaries;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.*;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;

public class DeleteMessageAdminDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	DeleteMessageAdminController controller;
	JList<String> deleteMessageList;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the dialog.
	 */
	public DeleteMessageAdminDialog(DeleteMessageAdminController myController) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DeleteMessageAdminDialog.class.getResource("/resources/_3707e1ea-9c9b-4142-82e2-be32952fd594_res_icon.png")));
		setTitle("elimina messaggi");
		setMinimumSize(new Dimension(455, 306));
		setModalityType(ModalityType.APPLICATION_MODAL);
		controller = myController;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					deleteMessageList.setModel(setListModel());
					deleteMessageList.setCellRenderer(new DefaultListCellRenderer());
				} catch (SQLException e1) {
					e1.printStackTrace();
					ShowMessage("Errore", "OPS! Qualcosa è andato storto");
				}
			}
		});
		
		setBounds(100, 100, 455, 306);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel deleteMessageLabel = new JLabel("messaggi");
		deleteMessageLabel.setFont(new Font("Cascadia Code", Font.BOLD, 13));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(deleteMessageLabel, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(deleteMessageLabel)
					.addGap(3)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		deleteMessageList = new JList<String>();
		scrollPane.setViewportView(deleteMessageList);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String[] splittedString = splitMessageString(deleteMessageList.getSelectedValue());
						try {
							deleteMessage(splittedString);
							ShowInfoMassage("Messsaggio eliminato", "messaggio eliminato con successo");
						} catch (SQLException e1) {
							ShowMessage("Errore", "OPS! qualcosa è andato storto");
							e1.printStackTrace();
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
	private DefaultListModel<String> setListModel() throws SQLException{
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.addAll(controller.showMessages());
		return listModel;
	}
	private String[] splitMessageString(String selectedMessage) {
		String messageString = selectedMessage;
		String[] splittedString = messageString.split("    ");
		return splittedString;
	}
	private void deleteMessage(String[] splittedString) throws SQLException {
		String user = splittedString[0];
		String message = splittedString[1];
		String date = splittedString[2];
		controller.deleteMessage(user, message, date);
	}
	private void ShowMessage(String titolo,String testo) {
		JOptionPane.showMessageDialog(this, testo, titolo, JOptionPane.WARNING_MESSAGE);
	}
	private void ShowInfoMassage(String titolo,String testo) {
		JOptionPane.showMessageDialog(this, testo,titolo,JOptionPane.INFORMATION_MESSAGE);
	}
}
