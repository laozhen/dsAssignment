package zhen.ds.server;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

	static ItemManager p = null;
	ArrayList<Item> plist = new ArrayList<Item>();
	private ItemManager()
	{
	}
	
	
	public static ItemManager getProductManager ()
	{
		if(p==null)
		{
			p = new ItemManager();
		}
		return p;
	}
	
	
	public void addProduct(Item p)
	{
		
	}
	public ArrayList<Item> getProductList()
	{
		return plist;
	}
}
