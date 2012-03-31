package com.maggen.udp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {

	private DatagramSocket socket;
	private InetAddress address;
	private int port;
	
	public UDPClient(String ip)
	{
		//default port
		port = 9876;
		try {
			socket = new DatagramSocket();
			address = InetAddress.getByName(ip);
		} catch (SocketException e) {
			
			e.printStackTrace();
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	public void updatePointer(int x , int y)
	{
		byte[] data = new byte[1024];
		String str = x+","+y;
		
		data = str.getBytes();
		
		DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
		
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
