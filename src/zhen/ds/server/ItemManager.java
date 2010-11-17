package zhen.ds.server;

import java.util.ArrayList;
import java.util.List;

import zhen.ds.share.Item;

public class ItemManager {

	static ItemManager i = null;
	ArrayList<Item> iList = new ArrayList<Item>();
	static Item currentItem;
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
	
	public static Item getCurrentItem()
	{
		return currentItem;
	}
	
	
	public void addItem(Item item)
	{
		iList.add(item);
		currentItem=item;
		
	}
	public ArrayList<Item> getItemList()
	{
		return iList;
	}
}
