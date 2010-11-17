package zhen.ds.server;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import zhen.ds.exception.*;
import zhen.ds.share.Item;
public class AutionHandler extends Thread{
	
	Socket socket=null;
	InputStream in=null;
	OutputStream out =null;
	PrintWriter pwt = null;
	BufferedReader brd = null;
	ItemManager iManager = ItemManager.getItemManager();
	public AutionHandler (Socket socket)throws Exception
	{
		this.socket=socket;
		checkConnection();
	}
	
	
	public void run()
	{
		Logger.debug("thread running");
		try {
			requestLogin();
			autionLoop();
		}catch (LoginFailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception ex)
		{
			
		}
		
	}
	
	private void checkConnection() throws ConnectionFailException
	{
		if(socket==null)
		{
			throw new ConnectionFailException("socket is null");
		}
		try
		{
			in = socket.getInputStream();
			out=socket.getOutputStream();
			pwt = new PrintWriter(out,true);
			brd = new BufferedReader(new InputStreamReader(in));
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			throw new ConnectionFailException("fail to create input or output stream");
		}
	}
	
	private void requestLogin() throws LoginFailException
	{
		pwt.println("WELCOME,WHAT'S YOUR NAME");
		String name;
		try {
			name = brd.readLine();
			Logger.debug("getName "+name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new LoginFailException("unable read from the client");
		}
		if(name.matches("[a-zA-Z].*"))
		{
			
		}
		else
		{
			throw new LoginFailException("iligel name");
		}
		Logger.debug("request login finish");
	}
	
	
	/**
	private void initList()throws InitListFailException
	{
		pwt.println("INIT LIST");
		String r;
		try {
			r = brd.readLine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new InitListFailException("unable read from the client");
		}
		if(r.equals("READY"))
		{
			Item item = iManager.getCurrentItem();
			synchronized (item)
			{
				try {
					oos.writeObject(item);
					oos.flush();
					r = brd.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					throw new InitListFailException("unable to write object to client");
				}
				if(! r.equals("SUCCESS"))
				{
					throw new InitListFailException("not receiving SUCCESS from client");
				}
			}
		}
		else
		{
			throw new InitListFailException("not receiving READY from client");
		}
	}
	**/
	
	private void autionLoop()throws UpdateListException,IOException
	{
		while(true)
		{
			Logger.debug("aution loop");
		String cmd=brd.readLine();
		Logger.debug("get something from client "+cmd);
		if(cmd.equals("UPDATE ITEM"))
		{
			Logger.debug("update item");
			updateItem();
		}
		if(cmd =="AUCTION CMD")
		{
			Logger.debug("aution command");
		}
		}
	}
	private void updateItem()throws UpdateListException
	{
		try {
			Logger.debug("sending item");
			ObjectOutputStream oos = new ObjectOutputStream(out);
			Item i = iManager.getCurrentItem();
			oos.writeObject(i);
			oos.flush();
			String msg =brd.readLine();
			if(!msg.equals("SUCCESS"))
			{
				throw new UpdateListException("fail to read success message from client");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void auction()throws AuctionException
	{
		
	}

}
