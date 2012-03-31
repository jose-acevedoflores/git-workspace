package com.maggen.udp.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import android.util.Log;

public class UDPClient {

	private static final int port = 9876;
	private DatagramSocket socket;
	private InetAddress address;
	private int portI;

	public UDPClient(String ip) throws SocketException, UnknownHostException
	{
		//default port
		portI = 9876;

		socket = new DatagramSocket();
		address = InetAddress.getByName(ip);
		Log.d("UDP", "creation");

	}

	public void updatePointer(float x , float y) throws IOException
	{
		byte[] data = new byte[1024];
		String str = x+","+y;

		data = str.getBytes();

		DatagramPacket packet = new DatagramPacket(data, data.length, address, portI);

		socket.send(packet);

	}


}
