package com.maggen.WiMouseServer;

import java.awt.AWTException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WiMouseServer {

	private UDPServer server;
	private MouseMover mover;
	private int port;
	private JTextField portField;
	private JPanel panel;

	public WiMouseServer()
	{
		JFrame frame = new JFrame("WiMouse Server");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		frame.setLocationRelativeTo(null);
		
		this.initFileds();
		frame.add(panel);
		frame.setVisible(true);
	}
	
	private void initFileds()
	{
		//Initialize the panel
		panel = new JPanel();
		
		//initialize the text field
		this.portField = new JTextField(8);
		
		panel.add(portField);
		
		//default port
		port = 9476;
		
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
