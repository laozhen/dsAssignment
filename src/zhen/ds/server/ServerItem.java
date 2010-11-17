package zhen.ds.server;

public class ServerItem extends zhen.ds.share.Item{
	
	public ServerItem(String name, int price, String productID, int currentPrice,
			int timeLeft) {
		super(name, price, productID, currentPrice, timeLeft);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public void setCurrentPrice(int currentPrice) {
		this.currentPrice = currentPrice;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setHighestBidder(String highestBidder) {
		this.highestBidder = highestBidder;
	}
	
	
	

}
