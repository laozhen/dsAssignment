package zhen.ds.client;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import zhen.ds.exception.ConnectionFailException;
import zhen.ds.server.Logger;
import zhen.ds.share.AuctionCMD;
import zhen.ds.share.Item;

public class AutionClient {
	
	public static void main (String[] args) throws Throwable
	{
		/*
		InetAddress addr = InetAddress.getLocalHost();
		Socket s = new Socket(addr,8889);
		AutionHandler ah = new AutionHandler(s,"name");
		ah.login();
		Item i =ah.updateItem(); 
		AuctionCMD ac =ah.auction(30, i);
		Logger.debug(ac.getResult()+" "+ac.getReason());
		*/
		
		if(args.length!=3)
		{
			System.exit(0);
		}
		String host = args[0] ;
		int port = Integer.parseInt(args[1]);
		String name = args[2];
		
		try {
			ClientFrame frame = new ClientFrame(host,port,name);
		} catch (ConnectionFailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.error("not able to start the connection,check the host name and port number");
		}
		
		
	}


}
