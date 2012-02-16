package main;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class FTPMain extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JTextArea txtA;
	private FileTransfer fileTransfer;
	
	public FTPMain(String title) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		super(title);
		setSize(540,525);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//This sets the freaking icon in linux at run time
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("FTP-512x512.png")));

		JPanel all = new JPanel();
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
		txtA = new JTextArea(8,44);
		txtA.setEditable(false);
		txtA.setDragEnabled(true);
		JScrollPane scroll = new JScrollPane(txtA, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );

		ImageIcon background = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pic2.jpg") ) );

		JLabel dropArea = new JLabel(background);
		dropArea.setToolTipText("Drag file to transfer");

		JButton acceptB = new JButton("Transfer");
		acceptB.addActionListener(this);


		dropArea.setDropTarget(new DropTarget(dropArea, new DropListener()));

		all.add(userL);
		all.add(user);
		all.add(passL);
		all.add(pass);
		all.add(ftpAddress);
		all.add(t1);
		all.add(d1);
		all.add(t2);
		all.add(d2);
		all.add(t3);
		all.add(d3);
		all.add(t4);
		all.add(port);
		all.add(portF);

		all.add(dropArea);
		all.add(acceptB);
		all.add(scroll);

		add(all);

		fileTransfer = new FileTransfer(user, pass, t1, t2 ,t3 ,t4, portF, txtA,null);
		
		setVisible(true);

	}

	private class DropListener implements DropTargetListener
	{

		@Override
		public void dragEnter(DropTargetDragEvent arg0) {}
		@Override
		public void dragExit(DropTargetEvent arg0) {}
		@Override
		public void dragOver(DropTargetDragEvent arg0) {}
		@Override
		public void dropActionChanged(DropTargetDragEvent arg0) {}


		@Override
		public void drop(DropTargetDropEvent event)
		{
			event.acceptDrop(DnDConstants.ACTION_COPY);

			// Get the transfer which can provide the dropped item data
			Transferable transferable = event.getTransferable();


			DataFlavor nixDataFlavor = null;
			try {
				nixDataFlavor = new DataFlavor("text/uri-list;class=java.lang.String");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			if(transferable.isDataFlavorSupported(nixDataFlavor))
			{
				String path = null;

				try
				{
					path = transferable.getTransferData(nixDataFlavor).toString();

				} catch (UnsupportedFlavorException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				try {

					File fileToTransfer = new File(new URI(path.trim()));
					txtA.append("File to transfer: "+fileToTransfer.getAbsolutePath() +"\n" );
					fileTransfer.changeFileToSend(fileToTransfer);

				} catch (URISyntaxException e) {

					e.printStackTrace();
				}

			}

			// Inform that the drop is complete
			event.dropComplete(true);
		}

	}
	


	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

		//FTPMain2 m = 
		new FTPMain("FTP File Transfer");
	}



	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(!fileTransfer.isFileNull())
		{
			fileTransfer.send();
		}
		else
			txtA.append("File is null\n");
		
	}

}
