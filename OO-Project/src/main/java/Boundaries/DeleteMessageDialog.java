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
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(messageList, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(25)
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
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
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
	private void ShowInfoMassage(String titolo,String testo) {
		JOptionPane.showMessageDialog(this, testo,titolo,JOptionPane.INFORMATION_MESSAGE);
	}

}
