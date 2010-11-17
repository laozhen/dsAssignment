package zhen.ds.client;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class AutionClient {
	
	public static void main (String[] args) throws Throwable
	{
		InetAddress addr = InetAddress.getLocalHost();
		Socket s = new Socket(addr,8889);
		AutionHandler ah = new AutionHandler(s,"name");
		ah.login();
		ah.initList();
		
	}


}
