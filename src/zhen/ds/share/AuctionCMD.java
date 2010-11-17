package zhen.ds.share;

import java.io.Serializable;

public class AuctionCMD  implements Serializable{
	String ItemID;
	int price;
	String result;
	String reason;
	public AuctionCMD(String itemID, int price) {
		super();
		ItemID = itemID;
		this.price = price;
		this.result = result;
		this.reason = reason;
	}
	public String getItemID() {
		return ItemID;
	}
	public int getPrice() {
		return price;
	}
	public String getResult() {
		return result;
	}
	public String getReason() {
		return reason;
	}
	public void setItemID(String itemID) {
		ItemID = itemID;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
	
	

}
