package Boundaries;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.postgresql.util.PSQLException;

import Controllers.LoginController;
import ExceptionPackage.DBconnectionError;

import java.awt.Dialog.ModalityType;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class SearchTag extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton partecipaButton;
	private LoginController controller;
	private JButton cancelButton;
	private JComboBox<String> tagComboBox;
	private JList<String> resultGroupList;

	

	/**
	 * Create the dialog.
	 */
	public SearchTag(LoginController myController) {
		controller = myController;
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 271);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lbTagLabel = new JLabel("Tag");
		lbTagLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbTagLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		
		tagComboBox = new JComboBox<String>();
		tagComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tagComboBox.getSelectedIndex() != -1) {
					try {
						resultGroupList.setModel(showGroupModel((String) tagComboBox.getSelectedItem()));
						resultGroupList.setCellRenderer(new DefaultListCellRenderer());
					} catch (DBconnectionError e1) {
						e1.printStackTrace();
						ShowMessage("Errore", "OPS! qualcosa è andato storto nel caricamento dei gruppi");
					}
					
				}
			}
		});
		DefaultComboBoxModel<String> comboModel = new DefaultComboBoxModel<>();
		try {
			comboModel.addAll(controller.setCategory());
			tagComboBox.setModel(comboModel);
		} catch (ClassNotFoundException | SQLException | IOException | RuntimeException e) {
			e.printStackTrace();
		}
		resultGroupList = new JList<String>();
		resultGroupList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JLabel lblResultLabel = new JLabel("gruppi");
		lblResultLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblResultLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblResultLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lbTagLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(resultGroupList, GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
						.addComponent(tagComboBox, 0, 184, Short.MAX_VALUE))
					.addGap(115))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tagComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbTagLabel))
					.addGap(18)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblResultLabel)
						.addComponent(resultGroupList, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE))
					.addGap(38))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				partecipaButton = new JButton("Partecipa");
				partecipaButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							newGroupRequest();
							ShowInfoMassage("Partecipa", "la richiesta di partecipazione è andata a buon fine");
							back();
						} catch (ClassNotFoundException | IOException | RuntimeException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
							ShowMessage("Errore", "la richiesta non è andata a buon fine!\n"+e1.getMessage());
						}
					}
				});
				partecipaButton.setActionCommand("OK");
				buttonPane.add(partecipaButton);
				getRootPane().setDefaultButton(partecipaButton);
			}
			{
				cancelButton = new JButton("annulla");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						back();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void back() {
		controller.setHomePageFrameFromDialog(this);
	}
	private DefaultListModel<String> showGroupModel(String selectedTag) throws DBconnectionError {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		try {
			listModel.addAll(controller.showGroup(selectedTag));
			return listModel;
		} catch (ClassNotFoundException | SQLException | IOException | RuntimeException e) {
			e.printStackTrace();
			throw new DBconnectionError();
		}
	}
	private void newGroupRequest() throws ClassNotFoundException, SQLException, IOException, RuntimeException {
		controller.newRequest(resultGroupList.getSelectedValue());
	}
	private void ShowMessage(String titolo,String testo) {
		JOptionPane.showMessageDialog(this, testo, titolo, JOptionPane.WARNING_MESSAGE);
	}
	private void ShowInfoMassage(String titolo,String testo) {
		JOptionPane.showMessageDialog(this, testo,titolo,JOptionPane.INFORMATION_MESSAGE);
	}
}
