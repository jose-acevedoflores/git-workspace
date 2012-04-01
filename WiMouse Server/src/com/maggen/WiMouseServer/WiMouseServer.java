package com.maggen.WiMouseServer;

import java.awt.AWTException;

public class WiMouseServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		UDPServer server = new UDPServer(9476);
		MouseMover mover=null;
		try {
			mover = new MouseMover();
		} catch (AWTException e) {
			e.printStackTrace();
			System.exit(-1);
		}

		System.out.println("Server Running");
		String movement="";
		while(true)
		{
			System.out.println("Updating");
			movement  =server.receive();
			mover.updatePointer(movement);
		}

	}

}
