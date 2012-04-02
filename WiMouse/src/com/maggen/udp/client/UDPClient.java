package com.maggen.udp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.content.SharedPreferences;
import android.util.Log;

public class UDPClient {

	private DatagramSocket socket;
	private InetAddress address;
	private int portI;

	public UDPClient(String ip, SharedPreferences prefs) throws SocketException, UnknownHostException
	{
		
		String port = prefs.getString("port", "9876");
		//default port 9876
		try{
			portI = Integer.parseInt(port);
		}
		catch (NumberFormatException e) {
			portI = 9876;
		}
		
		socket = new DatagramSocket();
		address = InetAddress.getByName(ip);
		Log.d("UDP", "creation");

	}

	public void updatePointer(int x , int y, int prevX, int prevY) throws IOException
	{
		byte[] data = new byte[1024];
		String str = x+","+y+","+prevX+","+prevY;

		data = str.getBytes();

		DatagramPacket packet = new DatagramPacket(data, data.length, address, portI);

		socket.send(packet);

	}


}
