package zhen.ds.share;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.*;
import javax.swing.border.Border;

import zhen.ds.server.Logger;

public class ItemPanel extends JPanel{
	Item item;
	
	JLabel itemName=new JLabel("ItemName ");
	JLabel itemID = new JLabel("ItemID ");
	JLabel timeLeft = new JLabel("TimeLeft: ??");
	JLabel highestBidder =new JLabel("HighestBidder: ??");
	JLabel currentPrice =new JLabel("currentPrice: ??");
	;
	
	public ItemPanel()
	{
		super();
		setLayout(new GridLayout(1,8));
		Border border = BorderFactory.createLineBorder(Color.black);
		itemName.setBackground(Color.BLUE);
		itemID.setBorder(border);
		currentPrice.setBorder(border);
		timeLeft.setBorder(border);
		highestBidder.setBorder(border);
	
		add(itemName);
		add(itemID);
		add(currentPrice);
		add(timeLeft);
		add(highestBidder);
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	public void update(Item item)
	{
		itemName.setText("ItemName: "+item.getName());
		itemID.setText("ItemID: "+item.getItemID());
		timeLeft.setText("Time Left:"+item.getTimeLeft());
		repaint();
		Logger.debug("time left is:"+item.getTimeLeft());
		
	}
//change in user 2
//resue the changes

}
