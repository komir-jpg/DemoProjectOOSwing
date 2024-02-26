package Boundaries;
import DemoUninaSN.OO_Project.*;
import ExceptionPackage.DBconnectionError;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JMenu;
import java.awt.Button;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.ScrollPane;
import java.awt.TextArea;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTabbedPane;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class HomePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final int minHeigth = 350;
	private final int minWidth = 640;
	Controller controller;
	private JTextField textFieldMessage;
	private JMenuItem mntmCreateGroupMenuItem;
	private JMenuBar menuBar;
	private JMenu mnSearchMenu;
	private JMenuItem mntmSearchUsernameMenuItem;
	private JMenuItem mntmSearchGroupByTagItem;
	private JMenuItem mntmSearchGrpupByNameMenuItem;
	private JMenu mnStatusMenu;
	private JMenuItem mntmOnlineMenuItem;
	private JMenuItem mntmOffilneItem;
	private JMenuItem mntmNotOnPCItem;
	private JMenuItem mntmNotAviableItem;
	private JMenu mnDeleteMenu;
	private JMenuItem mntmDeleteMsgMenuItem;
	private JMenuItem mntmDeleteLastItem;
	private JMenu mnGroupMenu;
	private JMenuItem mntmLeaveGroupMenuItem;
	private JMenuItem mntmSilenceGroupMenuItem;
	private JTextField textField;
	private JList GroupTabList;
	private JList<String> adminGroupList;
	private JMenu mnGroupManagmentMenu;
	private JMenuItem mntmRequestStatusItem;
	private JMenuItem mntmDeleteGroupItem;
	private JMenuItem mntmDeletePartecipantItem;
	private JMenuItem mntmDeleteMessageItem;
	
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public HomePage(Controller myController) {
		
		
		controller = myController;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnSearchMenu = new JMenu("cerca");
		menuBar.add(mnSearchMenu);
		
		mntmSearchUsernameMenuItem = new JMenuItem("Cerca nomeutente");
		mntmSearchUsernameMenuItem.setIcon(new ImageIcon("C:\\Users\\mirko\\Pictures\\noun-contacts-1126684.png"));
		mnSearchMenu.add(mntmSearchUsernameMenuItem);
		
		mntmSearchGroupByTagItem = new JMenuItem("Cerca  tag");
		mntmSearchGroupByTagItem.setIcon(new ImageIcon("C:\\Users\\mirko\\Pictures\\noun-label-1126708.png"));
		mnSearchMenu.add(mntmSearchGroupByTagItem);
		
		mntmSearchGrpupByNameMenuItem = new JMenuItem("Cerca gruppo");
		mntmSearchGrpupByNameMenuItem.setIcon(new ImageIcon("C:\\Users\\mirko\\Pictures\\noun-search-1126639.png"));
		mnSearchMenu.add(mntmSearchGrpupByNameMenuItem);
		
		mnStatusMenu = new JMenu("stato");
		menuBar.add(mnStatusMenu);
		
		mntmOnlineMenuItem = new JMenuItem("Online");
		mntmOnlineMenuItem.setIcon(new ImageIcon("C:\\Users\\mirko\\Pictures\\noun-avatar-1126670.png"));
		mnStatusMenu.add(mntmOnlineMenuItem);
		
		mntmOffilneItem = new JMenuItem("Offline");
		mntmOffilneItem.setIcon(new ImageIcon("C:\\Users\\mirko\\Pictures\\noun-remove-avatar-1126634.png"));
		mnStatusMenu.add(mntmOffilneItem);
		
		mntmNotOnPCItem = new JMenuItem("Non al pc");
		mntmNotOnPCItem.setIcon(new ImageIcon("C:\\Users\\mirko\\Pictures\\noun-remove-contact-1126739.png"));
		mnStatusMenu.add(mntmNotOnPCItem);
		
		mntmNotAviableItem = new JMenuItem("Non disponibile");
		mntmNotAviableItem.setIcon(new ImageIcon("C:\\Users\\mirko\\Pictures\\noun-invisible-1126706.png"));
		mnStatusMenu.add(mntmNotAviableItem);
		
		mnDeleteMenu = new JMenu("elimina");
		menuBar.add(mnDeleteMenu);
		
		mntmDeleteMsgMenuItem = new JMenuItem("Elimina messaggio");
		mntmDeleteMsgMenuItem.setIcon(new ImageIcon("C:\\Users\\mirko\\Pictures\\noun-delete-message-1167872.png"));
		mnDeleteMenu.add(mntmDeleteMsgMenuItem);
		
		mntmDeleteLastItem = new JMenuItem("Elimina ultimo messaggio");
		mntmDeleteLastItem.setIcon(new ImageIcon("C:\\Users\\mirko\\Pictures\\noun-delete-message-1167872.png"));
		mnDeleteMenu.add(mntmDeleteLastItem);
		
		mnGroupMenu = new JMenu("gruppo");
		menuBar.add(mnGroupMenu);
		
		mntmCreateGroupMenuItem = new JMenuItem("Crea nuovo gruppo");
		mntmCreateGroupMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setCreateGroupDialog();
			}
		});
		mntmCreateGroupMenuItem.setSelected(true);
		
		mntmCreateGroupMenuItem.setIcon(new ImageIcon("C:\\Users\\mirko\\Pictures\\createGroup.png"));
		mnGroupMenu.add(mntmCreateGroupMenuItem);
		
		mntmLeaveGroupMenuItem = new JMenuItem("Abbandona gruppo");
		mntmLeaveGroupMenuItem.setIcon(new ImageIcon("C:\\Users\\mirko\\Pictures\\leaveGroup.png"));
		mnGroupMenu.add(mntmLeaveGroupMenuItem);
		
		mntmSilenceGroupMenuItem = new JMenuItem("Silenzia gruppo");
		mntmSilenceGroupMenuItem.setIcon(new ImageIcon("C:\\Users\\mirko\\Pictures\\noun-mute-1126720.png"));
		mntmSilenceGroupMenuItem.setSelectedIcon(new ImageIcon("C:\\Users\\mirko\\Pictures\\noun-mute-1076301.png"));
		mnGroupMenu.add(mntmSilenceGroupMenuItem);
		
		mnGroupManagmentMenu = new JMenu("gestione gruppo");
		mnGroupManagmentMenu.setEnabled(false);
		menuBar.add(mnGroupManagmentMenu);
		
		mntmRequestStatusItem = new JMenuItem("gestione richieste");
		mnGroupManagmentMenu.add(mntmRequestStatusItem);
		
		mntmDeleteGroupItem = new JMenuItem("elimina gruppo");
		mntmDeleteGroupItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = ConfirmDialog();
				String selectedValue = adminGroupList.getSelectedValue();
				if(result == 0 && selectedValue != null) {
					try {
						
						controller.deleteGroup(selectedValue);
					} catch (ClassNotFoundException | SQLException | IOException | RuntimeException e1) {
						e1.printStackTrace();
						ShowMessage("Errore", "OPS! Qualcosa è andato storto nella cancellazione del gruppo");
					}
				}
				else if (result == 0 && selectedValue == null)
					ShowMessage("Errore", "OPS! devi selezionare un gruppo per poterlo cancellare");
				
			}
		});
		mnGroupManagmentMenu.add(mntmDeleteGroupItem);
		
		mntmDeletePartecipantItem = new JMenuItem("elimina partecipante");
		mnGroupManagmentMenu.add(mntmDeletePartecipantItem);
		
		mntmDeleteMessageItem = new JMenuItem("elimina messaggio");
		mnGroupManagmentMenu.add(mntmDeleteMessageItem);
		
		
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		setMinimumSize(setMinDimension());
		
		JLabel GroupLabel = new JLabel("Gruppi");
		GroupLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textFieldMessage = new JTextField();
		textFieldMessage.setColumns(10);
		
		JButton btnSend = new JButton("invia");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(GroupLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(textFieldMessage, GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSend))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)))))
					.addGap(44))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addComponent(GroupLabel, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
						.addComponent(textField, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldMessage, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSend)))
		);
		
		GroupTabList = new JList();
		GroupTabList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabbedPane.addTab("Gruppi", null, GroupTabList, null);
		tabbedPane.setEnabledAt(0, true);
		
		adminGroupList = new JList<String>();
		adminGroupList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				mnGroupManagmentMenu.setEnabled(true);
			}
		});
		adminGroupList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		tabbedPane.addTab("Gruppi admin", null, adminGroupList, null);
		contentPane.setLayout(gl_contentPane);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					
					adminGroupList.setModel(showListModel());
					adminGroupList.setCellRenderer(new DefaultListCellRenderer());
					
					
				}catch(DBconnectionError exc) {
					ShowMessage("Errore", "OPS! Qualcosa è andato storto nel caricamento dei gruppi");
				}
			}
		});
	}
	
	private Dimension setMinDimension() {
		Dimension dimension = new Dimension();
		dimension.setSize(minWidth,minHeigth);
		return dimension;
	}
	private void ShowMessage(String titolo,String testo) {
		JOptionPane.showMessageDialog(this, testo, titolo, JOptionPane.WARNING_MESSAGE);
	}
	private DefaultListModel<String> showListModel() throws DBconnectionError {
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		try {
			ArrayList<String> modelGroupName = controller.setUserAdminGroup();
			Iterator<String> iteratorGroupList = modelGroupName.iterator();
			while(iteratorGroupList.hasNext()) {
				listModel.addElement(iteratorGroupList.next());
				
			}
			return listModel;
		}catch (ClassNotFoundException | SQLException | IOException | RuntimeException e) {
			e.printStackTrace();
			throw new DBconnectionError();
		}
	}
	private int ConfirmDialog() {
		int a;
		a = JOptionPane.showConfirmDialog(this, "Sicuro? la cancellazione del gruppo comporta l'eliminazione di tutti i messaggi");
		return a;
	}
	
}
