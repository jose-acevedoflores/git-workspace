package with.jfilechooser;


import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * This is class is in charge of displaying the frame that will take all the information to
 * make the ftp transfer successful.  
 * @author Jose
 *
 */
public class FTPMainWFC extends JFrame {


	private static final long serialVersionUID = 1L;

	public FTPMainWFC(String title) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		super(title);
		setSize(500,534);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon img = new ImageIcon("res/FTP-512x512.png");
		this.setIconImage(img.getImage());
		
		JPanel p = new JPanel();
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		
		
		JTextField user = new JTextField(10);
		JPasswordField pass = new JPasswordField(10);
		
		JTextField t1 = new JTextField(3);
		JTextField t2 = new JTextField(3);
		JTextField t3 = new JTextField(3);
		JTextField t4 = new JTextField(3);
		JLabel userL = new JLabel("Username: ");
		JLabel passL = new JLabel("        Password: ");
		JLabel d1 = new JLabel("--");
		JLabel d2 = new JLabel("--");
		JLabel d3 = new JLabel("--");
		JLabel ftpAddress = new JLabel("FTP server Address: ");
		JLabel port = new JLabel("  Port: ");
		JTextField portF = new JTextField("2121", 5);
		JTextArea txtA = new JTextArea(7,42);
		txtA.setEditable(false);
		JScrollPane scroll = new JScrollPane(txtA, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		
		FilePicker f = new FilePicker(user , pass, t1, t2, t3, t4, portF, txtA );
		
		p.add(userL);
		p.add(user);
		p.add(passL);
		p.add(pass);
		p.add(ftpAddress);
		p.add(t1);
		p.add(d1);
		p.add(t2);
		p.add(d2);
		p.add(t3);
		p.add(d3);
		p.add(t4);
		p.add(port);
		p.add(portF);
		
		p.add(f);
		p.add(scroll);
		add(p);
		
		setVisible(true);
	}
	

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		new FTPMainWFC("FTP File Transfer");
	}
	

}
