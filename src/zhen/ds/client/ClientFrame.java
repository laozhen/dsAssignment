package zhen.ds.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

import zhen.ds.exception.ConnectionFailException;
import zhen.ds.server.Logger;

public class ClientFrame extends JFrame   {
	
	JButton start=new JButton("Connect to Server");
	IpDialog ipDialog = new IpDialog(this);
	JPanel main = new JPanel(new FlowLayout());
	ItemPanel itemPanel ;
	AuctionHandler ah ;
	String host;
	int port;
	String username;
	
	public ClientFrame(String host,int port,String username) throws ConnectionFailException
	{
		
		this.host=host;
		this.port=port;
		this.username=username;
		
		try {
			 Socket socket = new Socket(InetAddress.getByName(host),port);
			 ah = new AuctionHandler(socket, username);
			 itemPanel = new ItemPanel(ah);
			 initContentPane();
		} catch (UnknownHostException e) {
			throw new ConnectionFailException("host is invalid");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Logger.error("failure to connection host");
		} catch (ConnectionFailException e) {
			// TODO Auto-generated catch block
			throw new ConnectionFailException("");
		}
		
		setVisible(true);
		setSize(500,400);
		initContentPane();
		
		
		
		
	}
	
	/*
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		ipDialog.setVisible(true);
		Logger.debug("returned");
		Socket socket=null;
		try {
			 socket = new Socket(InetAddress.getByName(ipDialog.getIpAddress()),ipDialog.getPort());
			 ah = new AuctionHandler(socket, "nameA");
			 itemPanel = new ItemPanel(ah);
			 initContentPane();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.debug("failure to connection host");
		} catch (ConnectionFailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/
	
	private void initContentPane()
	{
		main.setLayout(new GridLayout(3,1));
		itemPanel.update();
		main.add(itemPanel);
		this.setContentPane(main);
		this.repaint();
	}
	

}
