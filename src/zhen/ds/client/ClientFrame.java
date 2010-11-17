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

public class ClientFrame extends JFrame implements ActionListener {
	
	JButton start=new JButton("Connect to Server");
	IpDialog ipDialog = new IpDialog(this);
	JPanel main = new JPanel(new FlowLayout());
	ItemPanel itemPanel ;
	AuctionHandler ah ;
	
	public ClientFrame()
	{
		setVisible(true);
		setSize(500,400);
		start.addActionListener(this);
		main.add(start);
		setContentPane(main);
		
	}
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

	}
	
	private void initContentPane()
	{
		main.remove(start);
		main.setLayout(new GridLayout(3,1));
		itemPanel.update();
		main.add(itemPanel);
		this.setContentPane(main);
		this.repaint();
	}
	

}
