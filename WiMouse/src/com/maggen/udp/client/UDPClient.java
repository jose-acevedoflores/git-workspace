package com.maggen.udp.client;

import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPClient {

	DatagramSocket socket;
	
	public UDPClient()
	{
		try {
			socket = new DatagramSocket();
		} catch (SocketException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	
}
