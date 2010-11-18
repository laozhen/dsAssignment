package zhen.ds.share;

import java.io.Serializable;

public class Item  implements Serializable{
	protected String name;
	protected int price;
	protected String itemID;
	protected int currentPrice;
	protected int timeLeft;
	protected State state;
	protected String highestBidder;
	
	
	public static enum State {BEFORE_START,AUCTION,BEFORE_END,END}
	
	public Item(String name, int price, String productID,
			int currentPrice) {
		super();
		this.name = name;
		this.price = price;
		this.itemID = productID;
		this.currentPrice = currentPrice;
		this.state=State.BEFORE_START;
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

	public State getState() {
		return state;
	}

	public String getHighestBidder() {
		return highestBidder;
	}
	
}
