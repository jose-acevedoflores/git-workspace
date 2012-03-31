package com.maggen.udp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.util.Log;

public class UDPClient {

	
	DatagramSocket socket;
	private InetAddress address;
	private int port;

	public UDPClient(String ip)
	{
		//default port
		port = 9876;
		try {
			socket = new DatagramSocket();
			address = InetAddress.getByName(ip);
			Log.d("UDP", "creation");
		}
		catch (UnknownHostException e) {
			Log.d("UDP", "host exception");

			e.printStackTrace();
		} catch (SocketException e) {
			
			e.printStackTrace();
		}

	}

	public void updatePointer(float x , float y)
	{
		byte[] data = new byte[1024];
		String str = x+","+y;

		data = str.getBytes();

		
		DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
		
		try {
			socket.send(packet);
		} catch (IOException e) {
			Log.d("UDP", "Exception in send packet");
			e.printStackTrace();
		}
	}


}
