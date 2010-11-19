package zhen.ds.client;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;

import zhen.ds.exception.AuctionEndedException;
import zhen.ds.exception.ConnectionFailException;
import zhen.ds.server.Logger;
import zhen.ds.share.Item;
import zhen.ds.share.ItemPanel;

public class ClientFrame extends JFrame implements ActionListener {

	JButton start = new JButton("Connect to Server");
	IpDialog ipDialog = new IpDialog(this);
	JPanel main = new JPanel(new FlowLayout());
	JLabel yourBid = new JLabel("Enter your Bid");
	JTextField price = new JTextField(15);
	JButton bid = new JButton("Bid Now!");
	ItemPanel itemPanel;
	AuctionHandler ah;
	String host;
	int port;
	String username;
	Timer timer = new Timer(200, this);

	public ClientFrame(String host, int port, String username)
			throws ConnectionFailException {

		this.host = host;
		this.port = port;
		this.username = username;

		try {
			Socket socket = new Socket(InetAddress.getByName(host), port);
			ah = new AuctionHandler(socket, username);
			itemPanel = new ItemPanel();
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
		setSize(500, 400);
		initContentPane();

	}

	/*
	 * @Override public void actionPerformed(ActionEvent arg0) { // TODO
	 * Auto-generated method stub ipDialog.setVisible(true);
	 * Logger.debug("returned"); Socket socket=null; try { socket = new
	 * Socket(InetAddress
	 * .getByName(ipDialog.getIpAddress()),ipDialog.getPort()); ah = new
	 * AuctionHandler(socket, "nameA"); itemPanel = new ItemPanel(ah);
	 * initContentPane(); } catch (UnknownHostException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } catch (IOException e) {
	 * // TODO Auto-generated catch block e.printStackTrace();
	 * Logger.debug("failure to connection host"); } catch
	 * (ConnectionFailException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

	private void initContentPane() {
		
		try {
			Item item = ah.updateItem();

			
			price.setHorizontalAlignment(JTextField.RIGHT);
			price.setEditable(false);
			price.setText("155");
			main.setLayout(new FlowLayout());
			itemPanel.update(item);
			main.add(itemPanel);
			main.add(Box.createHorizontalStrut(3000));
			main.add(yourBid);
			main.add(price);
			main.add(bid);
			main.add(Box.createHorizontalStrut(3000));
			this.setContentPane(main);
			this.repaint();
			timer.start();
		} catch (AuctionEndedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			Logger.debug("exit??");
			System.exit(1);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			Item item = ah.updateItem();
			itemPanel.update(item);
			if (item.getState() != Item.State.AUCTION) {
				bid.setEnabled(false);
			} else {
				bid.setEnabled(true);
			}
			this.repaint();
		} catch (AuctionEndedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
