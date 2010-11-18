package zhen.ds.server;

import zhen.ds.share.Item;

class ServerItem extends zhen.ds.share.Item{
	
	int reservePrice;
	
	public ServerItem(String name, int price, String productID, int currentPrice,
			int reservePrice) {
		super(name, price, productID, currentPrice);
		timeLeft = 20;
		state = State.BEFORE_START;
	}
	
	
	
	public void update ()
	{
		timeLeft --;
		if(timeLeft<=0)
		{
			switch (state)
			{
			case BEFORE_START:
				timeLeft=120;
				state = State.AUCTION;
				break;
			case AUCTION:
				timeLeft = 5;
				state=State.BEFORE_END;
				break;
				
			case BEFORE_END:
				state = State.END;
				break;
			case END:
				break;
			
			}
		}
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

	public void setState(State state) {
		this.state = state;
	}

	public void setHighestBidder(String highestBidder) {
		this.highestBidder = highestBidder;
	}
	
	
	

}
