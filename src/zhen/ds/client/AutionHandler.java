package zhen.ds.client;
import java.net.Socket;
import java.util.ArrayList;
import java.io.*;

import zhen.ds.exception.*;
import zhen.ds.server.Item;
import zhen.ds.server.Logger;

public class AutionHandler {
	Socket socket = null;
	OutputStream out;
	InputStream in;
	BufferedReader brd;
	PrintWriter pwt;
	String name;
	public AutionHandler (Socket s,String name) throws ConnectionFailException
	{
		System.out.print("create staff");
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
		String welcome=brd.readLine();
		Logger.debug("read from server "+welcome);
		if(welcome.equals("WELCOME,WHAT'S YOUR NAME"))
		{
			Logger.debug("sent name");
			pwt.println(name);
		}
	}
	
	public void initList()throws InitListFailException
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
				ObjectInputStream ois = new ObjectInputStream(in);
				@SuppressWarnings("unchecked")
				ArrayList<Item> iList =(ArrayList<Item>)ois.readObject();
				Logger.debug("read object finish");
				for(Item i : iList)
				{
					Logger.debug("item i is " + i.getName());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
