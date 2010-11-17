package zhen.ds.client;
import java.net.Socket;
import java.util.ArrayList;
import java.io.*;

import zhen.ds.exception.*;
import zhen.ds.server.Logger;
import zhen.ds.share.Item;

public class AutionHandler {
	Socket socket = null;
	OutputStream out;
	InputStream in;
	BufferedReader brd;
	PrintWriter pwt;
	String name;
	ObjectInputStream ois=null;
	ObjectOutputStream oos=null;
	public AutionHandler (Socket s,String name) throws ConnectionFailException, IOException
	{
		System.out.print("create something");
		this.socket =s;
		this.name=name;
		try {
			in=s.getInputStream();
			out=s.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ConnectionFailException("unable to open stream");
		}
		brd=  new BufferedReader(new InputStreamReader(in));
		pwt = new PrintWriter(out,true);
		
	}
	
	
	public void login() throws IOException
	{
		Logger.debug("ready to login");
		String welcome=brd.readLine();
		Logger.debug("read from server "+welcome);
		if(welcome.equals("WELCOME,WHAT'S YOUR NAME"))
		{
			Logger.debug("sent name");
			pwt.println(name);
		}
	}
	
	
	/**
	
	public ArrayList<Item> initAutionItem()throws InitListFailException
	{
		String msg;
		try {
			msg = brd.readLine();
			Logger.debug("read "+msg+" from server");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new InitListFailException("unable to read init list command from server");
		}
		if(msg.equals("INIT LIST"))
		{
			pwt.println("READY");
			Logger.debug("READY sent");
			try {
				ois = new ObjectInputStream(in);
				@SuppressWarnings("unchecked")
				ArrayList<Item> iList=(ArrayList<Item>)ois.readObject();
				Logger.debug("read object finish");
				ois.close();
				for(Item i : iList)
				{
					Logger.debug("item i is " + i.getName());
				}
				pwt.println("SUCCESS");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			throw new InitListFailException("fail to get init list command from the server");
		}
		return null;
	}
	
	**/
	
	public Item updateItem()
	{
		
		
		pwt.println("UPDATE ITEM");
		pwt.flush();
		try {
			Logger.debug("read item");
			ois = new ObjectInputStream(in);
			Item item = (Item)ois.readObject();
			//TODO mighit be a problem
			pwt.println("SUCCESS");
			Logger.debug("success");
			return item;
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