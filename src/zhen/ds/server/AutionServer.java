package zhen.ds.server;
import java.net.*;

import zhen.ds.share.Item;

public class AutionServer  {
	
	//hello world
	
	public static void main (String[] args)throws Exception
	{
		ServerSocket ss = new ServerSocket(8889);
		ItemManager i= ItemManager.getItemManager();
		ServerItem item = new ServerItem("ItemName",29,"0001",13,200);
		i.addItem(item);
		i.startAuction();
		Socket client = ss.accept();
		Logger.debug("connected :"+client.toString());
		AutionHandler ah = new AutionHandler(client);
		ah.start();
		
		
	}

}
