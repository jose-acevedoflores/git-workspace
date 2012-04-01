package com.maggen.WiMouseServer;

public class WiMouseServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		UDPServer server = new UDPServer(9476);
		
		System.out.println("Server Running");
		while(true)
		{
			System.out.println(server.start());
		}

	}

}
