package Boundaries;
import ExceptionPackage.DBconnectionError;
import ExceptionPackage.InvalidInsertion;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.event.ListSelectionListener;

import Controllers.HomePageController;
import javax.swing.event.ListSelectionEvent;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.MatteBorder;

public class HomePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final int minHeigth = 350;
	private final int minWidth = 640;
	private HomePageController controller;
	private JMenuItem mntmCreateGroupMenuItem;
	private JMenuBar menuBar;
	private JMenu mnSearchMenu;
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
	private JList<String> GroupTabList;
	private JList<String> adminGroupList;
	private JMenu mnGroupManagmentMenu;
	private JMenuItem mntmRequestStatusItem;
	private JMenuItem mntmDeleteGroupItem;
	private JMenuItem mntmDeletePartecipantItem;
	private JMenuItem mntmDeleteMessageItem;
	private ImageIcon searchIcon;
	private JLabel lblStateLabel;
	private JLabel lblStatusLabel;
	private JTextArea showMessageTextArea;
	private JButton btnSend;
	private JTabbedPane tabbedPane;
	private JMenuItem mntmInsightsMenuItem;
	private JTextArea sendMessageTextArea;
	private JMenuItem mntmNewMenuItem;
	/**
	 * Launch the application.
	 */
	/*
	 * TODO inviare messaggio 
	 * TODO mi piace commento e share 
	 * 
	 * */
	/**
	 * Create the frame.
	 */
	public HomePage(HomePageController myController) {
		setTitle("Unina Social Group");
		controller = myController;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		
		menuBar = new JMenuBar();
		menuBar.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		setJMenuBar(menuBar);
		
		mnSearchMenu = new JMenu("cerca");
		mnSearchMenu.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		menuBar.add(mnSearchMenu);
		
		mntmSearchGroupByTagItem = new JMenuItem("Cerca  tag");
		mntmSearchGroupByTagItem.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		mntmSearchGroupByTagItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setSearchTagDialog();
			}
		});
		mntmSearchGroupByTagItem.setIcon(new ImageIcon(HomePage.class.getResource("/resources/noun-label-1126708.png")));
		mnSearchMenu.add(mntmSearchGroupByTagItem);
		
		mntmSearchGrpupByNameMenuItem = new JMenuItem("Cerca gruppo");
		mntmSearchGrpupByNameMenuItem.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		mntmSearchGrpupByNameMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					showSearchDialog();
				} catch (InvalidInsertion e1) {
					ShowMessage("Errore", "il nome che hai inserito non corrisponde ad alcun gruppo");
				} catch (SQLException e1) {
					ShowMessage("Errore", "la richiesta non è andata a buon fine\n"+e1.getMessage());
				}
			}
		});
		mntmSearchGrpupByNameMenuItem.setIcon(new ImageIcon(HomePage.class.getResource("/resources/noun-search-1126639.png")));
		mnSearchMenu.add(mntmSearchGrpupByNameMenuItem);
		
		mnStatusMenu = new JMenu("stato");
		mnStatusMenu.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		menuBar.add(mnStatusMenu);
		
		mntmOnlineMenuItem = new JMenuItem("Online");
		mntmOnlineMenuItem.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		mntmOnlineMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStatusLabel.setText("Online");
				lblStatusLabel.setIcon(new ImageIcon(HomePage.class.getResource("/resources/noun-online-status-3864663.png")));
				lblStatusLabel.setForeground(new Color(0, 128, 64));
			}
		});
		mntmOnlineMenuItem.setIcon(new ImageIcon(HomePage.class.getResource("/resources/noun-avatar-1126670.png")));
		mnStatusMenu.add(mntmOnlineMenuItem);
		
		mntmOffilneItem = new JMenuItem("Offline");
		mntmOffilneItem.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		mntmOffilneItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStatusLabel.setText("Offilne");
				lblStatusLabel.setIcon(new ImageIcon(HomePage.class.getResource("/resources/noun-remove-avatar-1126634.png")));
				lblStatusLabel.setForeground(new Color(206, 0, 0));
			}
		});
		mntmOffilneItem.setIcon(new ImageIcon(HomePage.class.getResource("/resources/noun-remove-avatar-1126634.png")));
		mnStatusMenu.add(mntmOffilneItem);
		
		mntmNotOnPCItem = new JMenuItem("Non al pc");
		mntmNotOnPCItem.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		mntmNotOnPCItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStatusLabel.setText("Non al pc");
				lblStatusLabel.setIcon(new ImageIcon(HomePage.class.getResource("/resources/noun-remove-contact-1126739.png")));
				lblStatusLabel.setForeground(new Color(255, 200, 0));
			}
		});
		mntmNotOnPCItem.setIcon(new ImageIcon(HomePage.class.getResource("/resources/noun-remove-contact-1126739.png")));
		mnStatusMenu.add(mntmNotOnPCItem);
		
		mntmNotAviableItem = new JMenuItem("Non disponibile");
		mntmNotAviableItem.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		mntmNotAviableItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStatusLabel.setText("Non disponibile");
				lblStatusLabel.setIcon(new ImageIcon(HomePage.class.getResource("/resources/noun-invisible-1126706.png")));
				lblStatusLabel.setForeground(new Color(128, 128, 128));
			}
		});
		mntmNotAviableItem.setIcon(new ImageIcon(HomePage.class.getResource("/resources/noun-invisible-1126706.png")));
		mnStatusMenu.add(mntmNotAviableItem);
		
		mntmNewMenuItem = new JMenuItem("esci");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setLoginScreen();
			}
		});
		mntmNewMenuItem.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		mnStatusMenu.add(mntmNewMenuItem);
		
		mnDeleteMenu = new JMenu("elimina");
		mnDeleteMenu.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		menuBar.add(mnDeleteMenu);
		
		mntmDeleteMsgMenuItem = new JMenuItem("Elimina messaggio");
		mntmDeleteMsgMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(adminGroupList.getSelectedIndex() != -1 || GroupTabList.getSelectedIndex() != -1) {
					controller.deleteMessageDialog();
					showMessageTextArea.repaint();
				}
				else
					ShowMessage("Errore", "devi prima selezionare un gruppo");
			}
		});
		mntmDeleteMsgMenuItem.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		mntmDeleteMsgMenuItem.setIcon(new ImageIcon(HomePage.class.getResource("/resources/noun-delete-message-1167872.png")));
		mnDeleteMenu.add(mntmDeleteMsgMenuItem);
		
		mntmDeleteLastItem = new JMenuItem("Elimina ultimo messaggio");
		mntmDeleteLastItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(GroupTabList.getSelectedIndex() != -1 || adminGroupList.getSelectedIndex() != -1)
					try {
						deleteLastMessage();
						showMessageTextArea.repaint();
					} catch (SQLException e1) {
						e1.printStackTrace();
						ShowMessage("Errore", "OPS! Qualcosa è andato storto");
					}
				else
					ShowMessage("Errore","devi selezionare prima un gruppo");
			}
		});
		mntmDeleteLastItem.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		mntmDeleteLastItem.setIcon(new ImageIcon(HomePage.class.getResource("/resources/noun-delete-message-1167872.png")));
		mnDeleteMenu.add(mntmDeleteLastItem);
		
		mnGroupMenu = new JMenu("gruppo");
		mnGroupMenu.setIcon(null);
		mnGroupMenu.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		menuBar.add(mnGroupMenu);
		
		mntmCreateGroupMenuItem = new JMenuItem("Crea nuovo gruppo");
		mntmCreateGroupMenuItem.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		mntmCreateGroupMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setCreateGroupDialog();
			}
		});
		mntmCreateGroupMenuItem.setSelected(true);
		
		mntmCreateGroupMenuItem.setIcon(new ImageIcon(HomePage.class.getResource("/resources/createGroup.png")));
		mnGroupMenu.add(mntmCreateGroupMenuItem);
		
		mntmLeaveGroupMenuItem = new JMenuItem("Abbandona gruppo");
		mntmLeaveGroupMenuItem.setEnabled(false);
		mntmLeaveGroupMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(GroupTabList.getSelectedValue() != null) {
						leaveGroup();
					}else
						ShowMessage("Errore", "devi selezionare prima un gruppo");
				} catch (SQLException e1) {
					ShowMessage("Errore", "OPS! Qualcosa è andato storto");
					e1.printStackTrace();
				}
			}
		});
		mntmLeaveGroupMenuItem.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		mntmLeaveGroupMenuItem.setIcon(new ImageIcon(HomePage.class.getResource("/resources/leaveGroup.png")));
		mnGroupMenu.add(mntmLeaveGroupMenuItem);
		
		mnGroupManagmentMenu = new JMenu("gestione gruppo");
		mnGroupManagmentMenu.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		mnGroupManagmentMenu.setEnabled(false);
		menuBar.add(mnGroupManagmentMenu);
		
		mntmRequestStatusItem = new JMenuItem("gestione richieste");
		mntmRequestStatusItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(adminGroupList.isSelectionEmpty())
					ShowMessage("Errore", "devi selezionare un gruppo per poter accedere alle richieste");
				else {
					controller.setGroupRequestDialog();
				}
			}
		});
		mntmRequestStatusItem.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		mnGroupManagmentMenu.add(mntmRequestStatusItem);
		
		mntmDeleteGroupItem = new JMenuItem("elimina gruppo");
		mntmDeleteGroupItem.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		mntmDeleteGroupItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = ConfirmDialog();
				String selectedValue = adminGroupList.getSelectedValue();
				if(result == 0 && selectedValue != null) {
					try {
						controller.deleteGroup();
						showMessageTextArea.setText("");
					} catch (SQLException e1) {
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
		mntmDeletePartecipantItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deletePartecipantDialog();
			}
		});
		mntmDeletePartecipantItem.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		mnGroupManagmentMenu.add(mntmDeletePartecipantItem);
		
		mntmDeleteMessageItem = new JMenuItem("elimina messaggio");
		mntmDeleteMessageItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteMessageAdminDialog();
				showMessageTextArea.repaint();
			}
		});
		mntmDeleteMessageItem.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		mnGroupManagmentMenu.add(mntmDeleteMessageItem);
		
		mntmInsightsMenuItem = new JMenuItem("insigths");
		mntmInsightsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.setInsightsFrame();
			}
		});
		mntmInsightsMenuItem.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		mnGroupManagmentMenu.add(mntmInsightsMenuItem);
		
		
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		setMinimumSize(setMinDimension());
		
		JLabel GroupLabel = new JLabel("GRUPPI");
		GroupLabel.setIcon(new ImageIcon("C:\\Users\\mirko\\Documents\\group-profile-users_icon-icons.com_73540.png"));
		GroupLabel.setFont(new Font("Cascadia Code", Font.PLAIN, 14));
		
		btnSend = new JButton("invia");
		btnSend.setEnabled(false);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!sendMessageTextArea.getText().isBlank())
					try {
						sendTextToScreen(sendMessageTextArea.getText());
						sendMessageTextArea.setText(null);
					} catch (SQLException e1 ) {
						e1.printStackTrace();
						ShowMessage("Errore", "OPS! Qualcosa è andato storto nell'invio del messagio "+e1.getMessage());
					}
				else
					System.out.println("NO");
			}
		});
		btnSend.setFont(new Font("Cascadia Code", Font.PLAIN, 11));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(tabbedPane.getSelectedIndex() == 0) { 
					GroupTabList.clearSelection();
					btnSend.setEnabled(false);
				}
				else if(tabbedPane.getSelectedIndex() == 1)
					adminGroupList.clearSelection();
					btnSend.setEnabled(false);
			}
		});

		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		
		lblStateLabel = new JLabel("stato:");
		lblStateLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStateLabel.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		
		lblStatusLabel = new JLabel("online");
		lblStatusLabel.setForeground(new Color(0, 128, 64));
		lblStatusLabel.setIcon(new ImageIcon(HomePage.class.getResource("/resources/noun-online-status-3864663.png")));
		lblStatusLabel.setFont(new Font("Cascadia Code", Font.PLAIN, 13));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(130, 135, 144), 2, true));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new LineBorder(new Color(130, 135, 144), 2, true));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
						.addComponent(GroupLabel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblStateLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblStatusLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSend, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(29)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStateLabel)
								.addComponent(lblStatusLabel)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(GroupLabel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnSend)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		
		sendMessageTextArea = new JTextArea();
		sendMessageTextArea.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		sendMessageTextArea.setColumns(10);
		scrollPane_1.setViewportView(sendMessageTextArea);
		
		showMessageTextArea = new JTextArea();
		showMessageTextArea.setBorder(new LineBorder(new Color(0, 0, 0), 0, true));
		showMessageTextArea.setEditable(false);
		showMessageTextArea.setFont(new Font("Cascadia Code", Font.PLAIN, 12));
		showMessageTextArea.setLineWrap(true);
		scrollPane.setViewportView(showMessageTextArea);
		
		GroupTabList = new JList<String>();
		GroupTabList.setSelectionBackground(Color.LIGHT_GRAY);
		GroupTabList.setFont(new Font("Cascadia Code", Font.BOLD, 12));
		GroupTabList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()) {
					btnSend.setEnabled(true);
					mntmLeaveGroupMenuItem.setEnabled(true);
					mnGroupManagmentMenu.setEnabled(false);
					try {
						showMessageTextArea.setText("");
						controller.selectedGroup(GroupTabList.getSelectedValue());
						showGroupPosts();
					} catch (SQLException e1) {
						e1.printStackTrace();
						ShowMessage("Errore", "OPS! Qualcosa è andato storto nel caricamento del gruppo");
					}
				}
				
			}
		});
		GroupTabList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabbedPane.addTab("Gruppi", null, GroupTabList, null);
		tabbedPane.setEnabledAt(0, true);
		
		adminGroupList = new JList<String>();
		adminGroupList.setSelectionBackground(Color.LIGHT_GRAY);

		
		adminGroupList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				mnGroupManagmentMenu.setEnabled(true);
				if(e.getValueIsAdjusting()) {
					btnSend.setEnabled(true);
					mntmLeaveGroupMenuItem.setEnabled(false);
					mnGroupManagmentMenu.setEnabled(true);
					try {
						showMessageTextArea.setText("");
						controller.selectedGroup(adminGroupList.getSelectedValue());
						showGroupPosts();
					} catch (SQLException e1) {
						e1.printStackTrace();
						ShowMessage("Errore", "OPS! Qualcosa è andato storto nel caricamento del gruppo");
					}
					
				}
			}
		});
		adminGroupList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		tabbedPane.addTab("Gruppi admin", new ImageIcon("C:\\Users\\mirko\\Pictures\\kisspng-icon-design-admin-settings-male-icon-free-download-png-and-5d4039ea986130.5491207615644902186242.jpg"), adminGroupList, null);
		contentPane.setLayout(gl_contentPane);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				try {
					adminGroupList.setModel(showListModelAdmin());
					adminGroupList.setCellRenderer(new DefaultListCellRenderer());
					GroupTabList.setModel(showListModelGroups());
					GroupTabList.setCellRenderer(new DefaultListCellRenderer());
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
	private void ShowInfoMassage(String titolo,String testo) {
		JOptionPane.showMessageDialog(this, testo,titolo,JOptionPane.INFORMATION_MESSAGE);
	}
	private DefaultListModel<String> showListModelAdmin() throws DBconnectionError {
		
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		try {
			listModel.addAll(controller.getUserAdminGroups());
			return listModel;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DBconnectionError();
		}
	}
	private DefaultListModel<String> showListModelGroups() throws DBconnectionError{
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		try {
			listModel.addAll(controller.getUserGroups());
			return listModel;
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DBconnectionError();
		}
	}
	private int ConfirmDialog() {
		int result;
		result = JOptionPane.showConfirmDialog(this, "Sicuro? la cancellazione del gruppo comporta l'eliminazione di tutti i messaggi");
		return result;
	}
	private void showSearchDialog() throws InvalidInsertion, SQLException {
		searchIcon = new ImageIcon("C:\\Users\\mirko\\Pictures\\noun-magnifying-glass-1497539.png");
		boolean result;
		String userInput = (String) JOptionPane.showInputDialog(this,"nome del gruppo","cerca",JOptionPane.DEFAULT_OPTION,searchIcon,null,null);
		System.out.println(userInput);
		if(userInput != null) {
			result = controller.checkGroupName(userInput);
			if(result == false)
				throw new InvalidInsertion();
			else {
				requestGroup(userInput);
			}
		}
			
		
	}
	private void requestGroup(String groupName) throws SQLException, InvalidInsertion {
		int result;
		result = JOptionPane.showConfirmDialog(this, "vuoi inviare una richiesta di partecipazione a questo gruppo?");
		if (result == 0) {
			controller.newRequest(groupName);
			ShowInfoMassage("Richiesta", "richiesta di partecipazione al gruppo andata a buon fine");
		}
		else if (result == 1)
			showSearchDialog();
		else
			showSearchDialog();	
	}
	
	
	
	private void setGroupPosts() throws SQLException {
		ArrayList<String>post = controller.getGroupPosts();
		Iterator<String> postIterator = post.iterator();
		while(postIterator.hasNext()) {
			String currentPost = postIterator.next();
				showMessageTextArea.append(currentPost);
				showMessageTextArea.append("\n\n");
			}
			
	}
	private void showGroupPosts() {
		try{
			setGroupPosts();
		}catch (SQLException e1) {
			e1.printStackTrace();
			ShowMessage("Errore","si è verificato un errore nel selezionare il gruppo "+e1.getMessage());
		}
	}
	private void sendTextToScreen(String message) throws SQLException {
		String post;
		post = controller.newPost(message);
		showMessageTextArea.append(post);
		showMessageTextArea.append("\n\n");
	}
	private void leaveGroup() throws SQLException {
		int userInput;
		userInput = JOptionPane.showConfirmDialog(this, "sicuro di voler lasciare il gruppo?", "abbandona", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(userInput == 0) {
			controller.leaveGroup();
			ShowInfoMassage("info", "hai abbandonato il gruppo");
		}
	}
	private void deleteLastMessage() throws SQLException {
		int userInput;
		userInput = JOptionPane.showConfirmDialog(this, "vuoi eliminare il tuo ultimo messaggio?","elimina messaggio",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
		if(userInput == 0) {
			controller.deleteLastMessage();
		}
	}
}
