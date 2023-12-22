package DemoUninaSN.OO_Project;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;

public class DemoTre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int MinimumWidth = 250;
	private int MinimumHeight = 250;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DemoTre frame = new DemoTre();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DemoTre() {
		
		setMinimumSize(new Dimension(MinimumWidth,MinimumHeight));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(250, 250));
		contentPane.setBorder(null);
		contentPane.setMinimumSize(new Dimension(MinimumWidth,MinimumHeight));
		
		
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\mirko\\Pictures\\_465693e9-ae7f-46bb-8727-6f515978c48f.jpg"));
		
		JButton btnNewButton = new JButton("New button");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(212))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(162)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton))
					.addGap(162))
		);
		contentPane.setLayout(gl_contentPane);
	}
	private void setMaxSize() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Dimension dimension = getSize();
				//System.out.println(dimension);
				if(dimension.height >= 1000 ) {
					setSize(new Dimension(1000,1000));
					repaint();
					revalidate();
				}
				if(dimension.width >= 1000) {
					setSize(new Dimension(1000,1000));
					repaint();
					revalidate();
				}
				super.componentResized(e);
			}
			
		});
	}
}
