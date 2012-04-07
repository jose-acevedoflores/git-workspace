package com.maggen.WiMouseServer;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WiMouseServer {

	private UDPServer server;
	private MouseMover mover;
	private int port;
	private JTextField portField;
	private JPanel panel;
	private JLabel backgroundLabel;
	private JLabel portLabel;
	private JButton startButton;
	private JLayeredPane pane;
	
	
	public WiMouseServer()
	{
		JFrame frame = new JFrame("WiMouse Server");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		frame.setLocationRelativeTo(null);
		
		//Code to set the icon in windows
		ImageIcon icon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png") ) );
		frame.setIconImage(icon.getImage());
		
		this.initFileds();
		frame.add(panel);
		frame.setVisible(true);
	}
	
	private void initFileds()
	{
		pane = new JLayeredPane();
		pane.setPreferredSize(new Dimension(400, 400));
		
		ImageIcon background = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("background.png") ) );
		this.backgroundLabel = new JLabel(background);
		
		//Initialize the panel
		panel = new JPanel();
		
		//initialize the text field
		this.portField = new JTextField();
		this.portField.setText("9876");
		
		//initialize the port label
		this.portLabel = new JLabel("Port number: ");
		
		//Initialize the start button
		this.startButton = new JButton("Start Server");
		
		
		//50 is the background number
		pane.add(backgroundLabel, 50);
		//100 is the portField number, this component is relative to 50 (background)
		pane.add(portField, 100, 50);
		pane.add(portLabel, 100, 50);
		pane.add(startButton, 100, 50);
		
		this.panel.add(pane);
		this.backgroundLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		this.portField.setBounds(295, 330, 100, 30);
		this.portLabel.setBounds(210, 330, 100, 30);
		this.startButton.setBounds(150, 100, 100, 50);
		
		this.startButton.addActionListener(new StartButtonListener());
		
		//default port
		port = 9876;
		
		//server on default port
		server = new UDPServer(port);
		
		//sets the mover to null (just in case)
		mover=null;	
	}
	
	/**
	 * This methods is a while loop that executes until the user quits the server.
	 * It waits for the udp server to receive the coordinates to pass to the robot for the 
	 * pointer movement.
	 */
	public void run()
	{
		try {
			mover = new MouseMover();
		} catch (AWTException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		String movement="";
		while(true)
		{
			movement = server.receive();
			mover.updatePointer(movement);
		}
	}
	
	private class StartButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) 
		{	
			try{
				int newPort = Integer.parseInt(portField.getText());
				server = new UDPServer(newPort);
				System.out.println("Server restarted");
			}
			catch (NumberFormatException w) {
				//Illegal port number
				//server stays with the default port
				portField.setText("9876");
			}
		}
		
	}
	
	
	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		
		//Show gui
		WiMouseServer server = new WiMouseServer();
		
		System.out.println("Server Running");
		server.run();
		
	}

}
