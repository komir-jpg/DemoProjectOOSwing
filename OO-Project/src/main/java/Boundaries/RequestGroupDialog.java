package Boundaries;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.*;
import Entities.Request;
import ExceptionPackage.DBconnectionError;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;

public class RequestGroupDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private GroupRequestsController controller;
	private JList<String> requestedGroupList;

	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the dialog.
	 */
	public RequestGroupDialog(GroupRequestsController myController) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RequestGroupDialog.class.getResource("/resources/_3707e1ea-9c9b-4142-82e2-be32952fd594_res_icon.png")));
		setTitle("richieste");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					requestedGroupList.setModel(setListModel());
					requestedGroupList.setCellRenderer(new DefaultListCellRenderer());
				} catch (DBconnectionError e1) {
					ShowMessage("Errore", "Qualcosa è andato storto nel caricare le richieste");
				}
			}
		});
		controller = myController;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		requestedGroupList = new JList<String>();
		
		
		
		JLabel FriendLabel = new JLabel("richieste di partecipazione");
		FriendLabel.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(FriendLabel, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
						.addComponent(requestedGroupList, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(FriendLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(requestedGroupList, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton acceptButton = new JButton("Accetta richieste");
				acceptButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							List<String> selectedValues = requestedGroupList.getSelectedValuesList();
							if(selectedValues.isEmpty())
								ShowMessage("Errore", "devi selezionare prima una richiesta");
							requestAccepted(selectedValues);
						} catch (SQLException e1) {
							e1.printStackTrace();
							ShowMessage("Errore", "Ops!,Qualcosa è andato storto");
						}
					}
				});
				acceptButton.setActionCommand("OK");
				buttonPane.add(acceptButton);
				getRootPane().setDefaultButton(acceptButton);
			}
			
			JButton refuseButton = new JButton("Rifiuta richieste");
			refuseButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						List<String> selectedValues = requestedGroupList.getSelectedValuesList();
						if(selectedValues.isEmpty())
							ShowMessage("Errore", "devi selezionare prima una richiesta");
							requestDenied(selectedValues);
					} catch (SQLException e1) {
						e1.printStackTrace();
						ShowMessage("Errore", "Ops!,Qualcosa è andato storto");
					}
				}
			});
			buttonPane.add(refuseButton);
			{
				JButton cancelButton = new JButton("Annulla");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void requestAccepted(List<String> selectedValues) throws SQLException {
		Iterator<String> iterator = selectedValues.iterator();
		String username;
		while(iterator.hasNext()) {
			username = splitUserNameString(iterator.next());
			controller.requestAccepted(username);
		}
	}
	private void requestDenied(List<String> selectedValues) throws SQLException {
		Iterator<String> iterator = selectedValues.iterator();
		String username;
		while(iterator.hasNext()) {
			username = splitUserNameString(iterator.next());
			controller.requestDenied(username);
		}
	}
	/**
	 * this method splits, the request string, in the {@link JList}
	 * and return a string containing only the username.
	 * <p>First the request string is split by the ":", see {@link Request#toString()},
	 * so the result would be: [<b>[username]   data di iscrizione</b>.]
	 * Then the result string is split again removing the space and data di iscrizione
	 * @param string
	 * @return
	 */
	private String splitUserNameString(String string) {
		String[] stringArray = string.split(":");
		String splittedString = stringArray[1];
		stringArray = splittedString.split("   ");
		String resultString = stringArray[0].strip();
		System.out.println(resultString);
		return resultString;
	}
	private DefaultListModel<String> setListModel() throws DBconnectionError {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		try {
			listModel.addAll(controller.getRequestList());
			return listModel;
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
}
