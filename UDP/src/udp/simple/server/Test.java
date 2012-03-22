package udp.simple.server;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		
		
		InetAddress w = InetAddress.getByName("localhost");
		InetAddress p = InetAddress.getLocalHost();
		
		System.out.println("P - test\n");
		
		System.out.println(p);
		System.out.println("Is Loopback address? "+ p.isLoopbackAddress());
		System.out.println("HostName "+ p.getHostName());
		
		System.out.println("\nW - test\n");
		
		System.out.println(w);
		System.out.println("Is Loopback address? "+ w.isLoopbackAddress());
		System.out.println("HostName "+ w.getHostName());
		
	}

}
