package zhen.ds.client;

import java.net.Socket;
import java.util.ArrayList;
import java.io.*;

import zhen.ds.exception.*;
import zhen.ds.server.Logger;
import zhen.ds.share.*;

public class AuctionHandler {
	Socket socket = null;
	OutputStream out;
	InputStream in;
	BufferedReader brd;
	PrintWriter pwt;
	String name;
	Item currentItem = null;
	ObjectInputStream ois;
	ObjectOutputStream oos;

	public AuctionHandler(Socket s, String name)
			throws ConnectionFailException, IOException {
		System.out.print("create something");
		this.socket = s;
		this.name = name;
		try {
			in = s.getInputStream();
			out = s.getOutputStream();
			Logger.debug("got input stream");
			ois = new ObjectInputStream(in);
			oos = new ObjectOutputStream(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ConnectionFailException("unable to open stream");
		}
		brd = new BufferedReader(new InputStreamReader(in));
		pwt = new PrintWriter(out, true);
		login();

	}

	public void login() throws IOException {
		Logger.debug("ready to login");
		String welcome = brd.readLine();
		Logger.debug("read from server " + welcome);
		if (welcome.equals("WELCOME,WHAT'S YOUR NAME")) {
			Logger.debug("sent name");
			pwt.println(name);
			pwt.flush();
		}
		else
		{
			socket.close();
			in.close();
			out.close();
			Logger.error("auction is running or ended ,not able to login");
			
		}
	}

	public Item updateItem() throws AuctionEndedException, IOException {

		pwt.println("UPDATE ITEM");
		pwt.flush();
		String msg = brd.readLine();
		if (msg.equals("AUCTION ENDED")) {
			throw new AuctionEndedException("Auction ended");
		} else {
			Logger.debug("sent update item message");
			if (msg.equals("READY")) {
				try {
					Logger.debug("read item");
					Item item = (Item) ois.readObject();
					pwt.println("SUCCESS");
					Logger.debug("success");
					Logger.debug("ac time left is " + item.getTimeLeft());
					return item;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}

	}

	public AuctionCMD auction(int newPrice, Item item) throws AuctionException,
			ClassNotFoundException {
		pwt.println("AUCTION");
		AuctionCMD ac = new AuctionCMD(item.getItemID(), newPrice);
		try {
			oos.writeUnshared(ac);
			AuctionCMD result = (AuctionCMD) ois.readObject();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AuctionException("");
		}

	}

	public ArrayList<Item> updateClosedItems() {
		pwt.println("UPDATE CLOSED ITEM");

		try {
			ArrayList<Item> closedItems = (ArrayList<Item>) ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
