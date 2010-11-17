package zhen.ds.server;

import java.io.Serializable;

public class Item  implements Serializable{
	protected String name;
	protected int price;
	protected String itemID;
	protected int currentPrice;
	protected int timeLeft;
	protected int state;
	
	public Item(String name, int price, String productID,
			int currentPrice, int timeLeft) {
		super();
		this.name = name;
		this.price = price;
		this.itemID = productID;
		this.currentPrice = currentPrice;
		this.timeLeft = timeLeft;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public String getItemID() {
		return itemID;
	}

	public int getCurrentPrice() {
		return currentPrice;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public int getState() {
		return state;
	}
	
	

	
}
