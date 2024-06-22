package Boundaries;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controllers.*;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ImageIcon;

public class SearchTagDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton partecipaButton;
	private SearchTagController controller;
	private JButton cancelButton;
	private JScrollPane scrollPane;
	private JList<String> tagList;
	private JScrollPane scrollPane_1;
	private JList<String> resultGroupList;

	

	/**
	 * Create the dialog.
	 */
	public SearchTagDialog(SearchTagController myController) {
		setMinimumSize(new Dimension(500, 400));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					tagList.setModel(showTags());
					tagList.setCellRenderer(new DefaultListCellRenderer());
				} catch (SQLException e1) {
					e1.printStackTrace();
					ShowMessage("Errore", "ops! qualcosa è andato storto");
				}
				
			}
		});
		controller = myController;
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 450, 271);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lbTagLabel = new JLabel("TAG");
		lbTagLabel.setIcon(new ImageIcon(SearchTagDialog.class.getResource("/resources/noun-label-1126708.png")));
		lbTagLabel.setFont(new Font("Cascadia Code", Font.BOLD, 13));
		lbTagLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		JLabel lblResultLabel = new JLabel("GRUPPI");
		lblResultLabel.setIcon(new ImageIcon(SearchTagDialog.class.getResource("/resources/createGroup.png")));
		lblResultLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblResultLabel.setFont(new Font("Cascadia Code", Font.BOLD, 13));
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new LineBorder(new Color(0, 0, 0), 2, true));
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
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
					.addGap(115))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lbTagLabel)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblResultLabel))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(14)
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))
					.addContainerGap())
		);
		
		resultGroupList = new JList<String>();
		resultGroupList.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		
		resultGroupList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(resultGroupList);
		
		tagList = new JList<String>();
		tagList.setSelectionBackground(Color.LIGHT_GRAY);
		tagList.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		tagList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tagList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()){
					try {
						resultGroupList.setModel(showGroupModel((ArrayList<String>) tagList.getSelectedValuesList()));
						resultGroupList.setCellRenderer(new DefaultListCellRenderer());
					} catch (SQLException e1) {
						ShowMessage("Errore","OPS!,Qualcosa è andato storto");
						e1.printStackTrace();
					}
				}
				
			}
		});
		tagList.setVisibleRowCount(3);
		scrollPane.setViewportView(tagList);
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
							if(resultGroupList.isSelectionEmpty())
								ShowMessage("Errore", "devi selezionare un gruppo");
							else {
								newGroupRequest();
								ShowInfoMassage("Partecipa", "la richiesta di partecipazione è andata a buon fine");
								back();
							}
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
		controller.setHomePageFrameFromDialog();
	}
	private DefaultListModel<String>showTags() throws SQLException{
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.addAll(controller.getTags());
		return listModel;
	}
	private DefaultListModel<String> showGroupModel(ArrayList<String> selectedTags) throws SQLException {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
			listModel.addAll(controller.showGroup(selectedTags));
			return listModel;
	}
	 
	
	
	private void newGroupRequest() throws SQLException{
		controller.newGroupRequest(resultGroupList.getSelectedValue());
	}
	private void ShowMessage(String titolo,String testo) {
		JOptionPane.showMessageDialog(this, testo, titolo, JOptionPane.WARNING_MESSAGE);
	}
	private void ShowInfoMassage(String titolo,String testo) {
		JOptionPane.showMessageDialog(this, testo,titolo,JOptionPane.INFORMATION_MESSAGE);
	}
}
