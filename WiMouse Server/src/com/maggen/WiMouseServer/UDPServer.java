package com.maggen.WiMouseServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {
	
	private DatagramSocket socket;
	private int port;
	
	public UDPServer(int port)
	{
		//Default
		this.port = port;
		try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	
	public String start()
	{
		byte[] dataReceived = new byte[1024];
		DatagramPacket packet = new DatagramPacket(dataReceived, dataReceived.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new String(packet.getData());
	}
	

}
