package zhen.ds.client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import zhen.ds.server.Logger;

public class ClientFrame extends JFrame implements ActionListener {
	
	JButton start=new JButton("Connect to Server");
	JDialog ipDialog = new IpDialog(this);
	JPanel main = new JPanel(new FlowLayout());
	
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
		
		
		
	}
	

}
