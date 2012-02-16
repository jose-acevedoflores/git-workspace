package with.jfilechooser;

import javax.swing.JFileChooser;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class modifies the JFileChooser to take control of the approvedSelection() method. 
 * @author Jose
 *
 */
public class FilePicker extends JFileChooser{
	
	private static final long serialVersionUID = 2L;
	private JTextField username;
	private JPasswordField password;
	private JTextField ip1;
	private JTextField ip2;
	private JTextField ip3;
	private JTextField ip4;
	private JTextField port;
	private JTextArea textArea;

	/**
	 * 
	 * @param username the field that takes the username.
	 * @param password the field that takes the password.
	 * @param ip1 part of the ip.
	 * @param ip2 part of the ip.
	 * @param ip3 part of the ip.
	 * @param ip4 part of the ip.
	 * @param port the field that takes the port.
	 * @param textArea the text area to show the log.
	 */
	public FilePicker(JTextField username, JPasswordField password, JTextField ip1, JTextField ip2, 
			JTextField ip3, JTextField ip4, JTextField port, JTextArea textArea)
	{
		super();
		setApproveButtonText("Transfer");
		this.username = username;
		this.password = password;
		this.ip1 = ip1;
		this.ip2 = ip2;
		this.ip3 = ip3;
		this.ip4 = ip4;
		this.port = port;
		this.textArea =textArea;
	}

	@Override
	public void approveSelection() 
	{
		super.approveSelection();
		
		String user = username.getText();
		String passw = new String(password.getPassword());
		String ip = ip1.getText() +"."+ip2.getText() +"." +ip3.getText()+"."+ip4.getText();
		int portI = Integer.parseInt(port.getText());
		
//		System.out.println("ip= "+ip);
//		System.out.println("username = " + user);
//		System.out.println("password = "+ passw);
//		System.out.println("port = "+ portI);
		
		FileTransferWFC ft = new FileTransferWFC(user, passw, ip, portI, this.getSelectedFile(), textArea);
		ft.send();
		
	}

	
}
