package zhen.ds.server;
import java.net.*;

public class AutionServer  {
	
	//hello world
	
	public static void main (String[] args)throws Exception
	{
		ServerSocket ss = new ServerSocket(8889);
		ItemManager i= ItemManager.getItemManager();
		Item item = new Item("ItemName",29,"0001",13,200);
		i.addItem(item);
		Socket client = ss.accept();
		Logger.debug("connected :"+client.toString());
		AutionHandler ah = new AutionHandler(client);
		ah.start();
		
		
	}

}
