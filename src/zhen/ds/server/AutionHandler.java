package zhen.ds.server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import zhen.ds.exception.*;
import zhen.ds.share.AuctionCMD;
import zhen.ds.share.Item;

public class AutionHandler extends Thread {

	Socket socket = null;
	InputStream in = null;
	OutputStream out = null;
	PrintWriter pwt = null;
	BufferedReader brd = null;
	ItemManager iManager = ItemManager.getItemManager();
	ObjectOutputStream oos = null;
	ObjectInputStream ois = null;
	String bidderID;

	public static enum State {
		NOT_START, WAITING, AUCTION, END
	};

	public AutionHandler(Socket socket) throws Exception {
		this.socket = socket;
		checkConnection();
	}

	public void run() {
		Logger.debug("thread running");
		try {
			requestLogin();
			autionLoop();
		} catch (LoginFailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex) {

		}

	}

	private void checkConnection() throws ConnectionFailException {
		if (socket == null) {
			throw new ConnectionFailException("socket is null");
		}
		try {
			in = socket.getInputStream();
			out = socket.getOutputStream();

			oos = new ObjectOutputStream(out);
			ois = new ObjectInputStream(in);

			pwt = new PrintWriter(out, true);
			brd = new BufferedReader(new InputStreamReader(in));
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new ConnectionFailException(
					"fail to create input or output stream");
		}
	}

	private void requestLogin() throws LoginFailException {

		if (iManager.getState() == ItemManager.State.WAITING) {
			pwt.println("WELCOME,WHAT'S YOUR NAME");
			pwt.flush();
		} else {
			pwt.println("AUCTION RUNNING OR ENDED");
			pwt.flush();
			
			try {
				socket.close();
				out.close();
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String bidderID;
		try {
			bidderID = brd.readLine();
			this.bidderID = bidderID;
			Logger.debug("getName " + bidderID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new LoginFailException("unable read from the client");
		}
		if (bidderID.matches("[a-zA-Z].*")) {

		} else {
			throw new LoginFailException("iligel name");
		}
		Logger.debug("request login finish");
	}

	/**
	 * private void initList()throws InitListFailException {
	 * pwt.println("INIT LIST"); String r; try { r = brd.readLine(); } catch
	 * (Exception e) { // TODO Auto-generated catch block throw new
	 * InitListFailException("unable read from the client"); }
	 * if(r.equals("READY")) { Item item = iManager.getCurrentItem();
	 * synchronized (item) { try { (item); oos.flush(); r = brd.readLine(); }
	 * catch (IOException e) { // TODO Auto-generated catch block throw new
	 * InitListFailException("unable to write object to client"); } if(!
	 * r.equals("SUCCESS")) { throw new
	 * InitListFailException("not receiving SUCCESS from client"); } } } else {
	 * throw new InitListFailException("not receiving READY from client"); } }
	 * 
	 * @throws AuctionException
	 **/

	private void autionLoop() throws UpdateListException, IOException,
			AuctionException {
		while (true) {
			Logger.debug("aution loop");
			String cmd = brd.readLine();
			Logger.debug("get something from client " + cmd);
			if (cmd.equals("UPDATE ITEM")) {
				Logger.debug("update item");
				updateItem();
			}
			if (cmd.equals("AUCTION")) {
				Logger.debug("aution command");
				auction();
			}

			if (cmd.equals("UPDATE CLOSED LIST")) {
				updateClosedList();
			}
		}
	}

	private void updateClosedList() {

	}

	private void updateItem() throws UpdateListException {
		try {
			Logger.debug("sending item");
			Item item = (Item) iManager.getCurrentItem();
			Logger.debug("item manager :" + item.getTimeLeft());

			// send the auction state
			if (iManager.getState() == ItemManager.State.END) {
				Logger.debug("send auction end message");
				pwt.println("AUCTION ENDED");
				pwt.flush();
			} else {
				pwt.println("READY");
				pwt.flush();
				oos.writeUnshared(item);
				oos.flush();
				String msg = brd.readLine();
				if (!msg.equals("SUCCESS")) {
					throw new UpdateListException(
							"fail to read success message from client");
			}
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new UpdateListException("output stream error");
		}
	}

	private void auction() throws AuctionException {

		try {
			Logger.debug("reading something");
			AuctionCMD ac = (AuctionCMD) ois.readObject();
			Logger.debug("finished");
			ServerItem item = iManager.getCurrentItem();
			String reason = "";

			synchronized (item) {
				boolean fail = false;
				if (item.getState() != Item.State.AUCTION) {
					fail = true;
					reason = "state not ready\n";
				}
				if (item.getPrice() >= ac.getPrice()) {
					fail = true;
					reason += "price not high enough\n";
				}
				if (!item.getItemID().equals(ac.getItemID())) {
					fail = true;
					reason += "item not match\n";
				}
				if (fail == false) {
					item.setPrice(ac.getPrice());
					item.setHighestBidder(bidderID);
					ac.setResult("OK");
					ac.setReason(reason);
				} else {
					ac.setResult("FAIL");
					ac.setReason(reason);
				}
				oos.writeUnshared(ac);
				Logger.debug("done write object");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AuctionException("can't read from stream");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AuctionException("can't find the the auctionCmd class");
		}

	}
}
