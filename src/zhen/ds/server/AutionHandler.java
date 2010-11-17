package zhen.ds.server;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import zhen.ds.exception.*;
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
			initList();
		}catch (LoginFailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InitListFailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			ArrayList<Item> plist = iManager.getItemList();
			synchronized (plist)
			{
				try {
					ObjectOutputStream oos = new ObjectOutputStream (out);
					oos.writeObject(plist);
					oos.flush();
					r = "SUCCESS";
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
	
	private void autionLoop()throws Exception
	{
		
	}

}
