package udp.simple.server;

import java.net.*;
import java.util.Collections;
import java.util.Enumeration;

public class Test {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 * @throws SocketException 
	 */
	public static void main(String[] args) throws UnknownHostException, SocketException {


		InetAddress w = InetAddress.getByName("jose-System");
		InetAddress p = InetAddress.getLocalHost();

		System.out.println("P - test\n");

		System.out.println(p);
		System.out.println("Is Loopback address? "+ p.isLoopbackAddress());
		System.out.println("HostName "+ p.getHostName());

		System.out.println("\nW - test\n");

		System.out.println(w);
		System.out.println("Is Loopback address? "+ w.isLoopbackAddress());
		System.out.println("HostName "+ w.getHostName());

		Enumeration<NetworkInterface> nets =  NetworkInterface.getNetworkInterfaces();

		for (NetworkInterface netint : Collections.list(nets)) {
			System.out.println("\nInterface Name : " + netint.getDisplayName());

			Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();

			for (InetAddress inetAddress : Collections.list(inetAddresses)) {

				System.out.println("Local host Name\t: " + inetAddress.getHostName());
				System.out.println("Host Address\t: " + inetAddress.getHostAddress()); 
			}
		}


	}

}
