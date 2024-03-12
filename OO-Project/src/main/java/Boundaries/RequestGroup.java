package Boundaries;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.*;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.awt.event.ActionEvent;

public class RequestGroup extends JDialog {

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
	public RequestGroup(GroupRequestsController myController) {
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
						List<String> selectedValues = requestedGroupList.getSelectedValuesList();
						
					}
				});
				acceptButton.setActionCommand("OK");
				buttonPane.add(acceptButton);
				getRootPane().setDefaultButton(acceptButton);
			}
			
			JButton refuseButton = new JButton("Rifiuta richieste");
			buttonPane.add(refuseButton);
			{
				JButton cancelButton = new JButton("Annulla");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void requestAccepted(List<String> selectedValues) {
		Iterator<String> iterator = selectedValues.iterator();
		while(iterator.hasNext()) {
			iterator.next().subSequence( ABORT, defaultCloseOperation)
		}
	}
}
