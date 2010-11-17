package zhen.ds.server;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

	static ItemManager i = null;
	ArrayList<Item> iList = new ArrayList<Item>();
	private ItemManager()
	{
	}
	
	
	public static ItemManager getItemManager ()
	{
		if(i==null)
		{
			i = new ItemManager();
		}
		return i;
	}
	
	
	public void addItem(Item item)
	{
		iList.add(item);
		
	}
	public ArrayList<Item> getItemList()
	{
		return iList;
	}
}
