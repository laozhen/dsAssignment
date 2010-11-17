package zhen.ds.client;
import java.awt.FlowLayout;

import javax.swing.*;
import zhen.ds.share.Item;

public class ItemPanel extends JPanel{
	Item item;
	
	JLabel itemName=new JLabel("ItemName ");
	JLabel itemID = new JLabel("ItemID ");
	JLabel timeLeft = new JLabel("TimeLeft: ??");
	JLabel highestBidder =new JLabel("HighestBidder: ??");
	JLabel currentPrice =new JLabel("currentPrice: ??");
	AuctionHandler ah;
	
	public ItemPanel(AuctionHandler ah)
	{
		super(new FlowLayout());
		this.ah=ah;
		add(itemName);
		add(itemID);
		add(currentPrice);
		add(timeLeft);
		add(highestBidder);
		
		
	}
	public void update()
	{
		item = ah.updateItem();
		itemName.setText("ItemName: "+item.getName());
		itemID.setText("ItemID: "+item.getItemID());
	}

}
