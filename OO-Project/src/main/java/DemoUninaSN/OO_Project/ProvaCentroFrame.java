package DemoUninaSN.OO_Project;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class ProvaCentroFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Dimension d = new Dimension();
	private JTextField textField;
	private JTextField textField_1;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProvaCentroFrame frame = new ProvaCentroFrame();
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
	public ProvaCentroFrame() {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				d = contentPane.getSize();
				System.out.println(d);
				//textField.setLocation( (d.width)/2, (d.height)/2);
				

			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[46px][89px][][][][grow]", "[23px][][][][][][][][]"));
		
		JButton btnNewButton = new JButton("New button");
		contentPane.add(btnNewButton, "cell 5 4,alignx center,aligny center");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 2 6 4 1,alignx center,aligny center");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		contentPane.add(textField_1, "cell 2 8 4 1,alignx center,aligny center");
		textField_1.setColumns(10);
	}
}
