package zhen.ds.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import zhen.ds.server.ItemManager.State;
import zhen.ds.share.Item;
import javax.swing.Timer;

public class ItemManager implements ActionListener{

	static ItemManager i = null;
	LinkedList<ServerItem> open = new LinkedList<ServerItem>();
	LinkedList<ServerItem> closed = new LinkedList<ServerItem>();
	ServerItem currentItem;
	State state = State.NOT_START;
	Timer timer = new Timer(1000,this);
	public enum State {
		NOT_START, WAITING, AUCTION, END
	};

	private ItemManager() {
	}

	public static ItemManager getItemManager() {
		if (i == null) {
			i = new ItemManager();
		}
		return i;
	}

	synchronized public ServerItem getCurrentItem() {
		return currentItem;
	}

	public void addItem(ServerItem item) {
		open.addLast(item);
		currentItem = item;

	}

	public LinkedList<ServerItem> getClosedList() {
		return open;
	}

	public void startAuction() {
		timer.start();
	}

	public void pauseAuction() {
		timer.stop();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		synchronized (currentItem) {
			if (currentItem == null) {
				currentItem = open.getFirst();
				open.removeFirst();
			}
			if (currentItem.getState() == Item.State.END) {
				closed.addLast(currentItem);
				currentItem = open.getFirst();
				open.removeFirst();
			}
			currentItem.update();
		}
		
	}
}
