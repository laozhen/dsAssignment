package zhen.ds.server;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import zhen.ds.server.exception.*;
public class AutionHandler implements Runnable{
	
	Socket socket=null;
	InputStream in=null;
	OutputStream out =null;
	PrintWriter pwt = null;
	BufferedReader brd = null;
	ItemManager pManager = ItemManager.getProductManager();
	public AutionHandler (Socket socket)
	{
		
	}
	
	
	public void run()
	{
		checkConnection();
		requestLogin();
		initList();
		
	}
	
	private void checkConnection() throws Exception
	{
		if(socket==null)
		{
			throw new ConnectionFailException("socket is null");
		}
		try
		{
			in = socket.getInputStream();
			out=socket.getOutputStream();
			pwt = new PrintWriter(out);
			brd = new BufferedReader(new InputStreamReader(in));
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
			throw new ConnectionFailException("fail to create input or output stream");
		}
	}
	
	private void requestLogin() throws Exception
	{
		pwt.println("WELCOME,WHAT'S YOUR NAME");
		String name= brd.readLine();
		if(name.matches("[a-zA-Z].*"))
		{
			
		}
		else
		{
			throw new LoginFailException("iligel name");
		}
	}
	
	private void initList()throws Exception
	{
		pwt.println("INIT LIST");
		String r = brd.readLine();
		if(r.equals("READY"))
		{
			ArrayList<Item> plist = pManager.getProductList();
			synchronized (plist)
			{
				ObjectOutputStream oos = new ObjectOutputStream (out);
				oos.writeObject(plist);
				r = brd.readLine();
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
	
	private void autionLoop()throws Exception
	{
		
	}

}
