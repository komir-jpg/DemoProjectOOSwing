package Boundaries;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.deletePartecipantController;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class DeletePartecipantDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private deletePartecipantController controller;
	private JList<String> groupPartecipantsList; 

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the dialog.
	 */
	public DeletePartecipantDialog(deletePartecipantController myController) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DeletePartecipantDialog.class.getResource("/resources/_3707e1ea-9c9b-4142-82e2-be32952fd594_res_icon.png")));
		setTitle("Rimuovi partecipanti");
		setModalityType(ModalityType.APPLICATION_MODAL);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					groupPartecipantsList.setModel(setGroupPartecipants());
					groupPartecipantsList.setCellRenderer(new DefaultListCellRenderer());
				} catch (SQLException e1) {
					e1.printStackTrace();
					ShowMessage("Errore","OPS!,qualcosa è andato storto");
				}
				
			}
		});
		controller = myController;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel = new JLabel("PARTECIPANTI");
		lblNewLabel.setIcon(new ImageIcon(DeletePartecipantDialog.class.getResource("/resources/delete-user.png")));
		lblNewLabel.setFont(new Font("Cascadia Code", Font.BOLD, 12));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(16, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		groupPartecipantsList = new JList<String>();
		groupPartecipantsList.setSelectionBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(groupPartecipantsList);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton removeBtn = new JButton("Elimina");
				removeBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(groupPartecipantsList.isSelectionEmpty()) {
							ShowMessage("Errore", "devi selezionare un utente prima di rimuoverlo");
						} else
							try {
								deletePartecipant(groupPartecipantsList.getSelectedValue());
								ShowInfoMessage("Info", "la rimozione è avvenuta con successo");
								groupPartecipantsList.repaint();
							} catch (SQLException e1) {
								e1.printStackTrace();
								ShowMessage("Errore", "OPS! Qualcosa è andato storto");
							}
					}
				});
				removeBtn.setActionCommand("OK");
				buttonPane.add(removeBtn);
				getRootPane().setDefaultButton(removeBtn);
			}
			{
				JButton cancelButton = new JButton("Annulla");
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
	private DefaultListModel<String> setGroupPartecipants() throws SQLException{
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.addAll(controller.groupPartecipants());
		return listModel;
	}
	private void ShowMessage(String titolo,String testo) {
		JOptionPane.showMessageDialog(this, testo, titolo, JOptionPane.WARNING_MESSAGE);
	}
	private void ShowInfoMessage(String titolo,String testo) {
		JOptionPane.showMessageDialog(this, testo,titolo,JOptionPane.INFORMATION_MESSAGE);
	}
	private void deletePartecipant(String username) throws SQLException {
		controller.deletePartecipant(username);
	}
	
}
