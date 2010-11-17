package zhen.ds.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;

public class IpDialog extends JDialog implements ActionListener{
	String ipAddress;
	String port;
	JTextField ipText = new JTextField();
	JLabel lip = new JLabel("ip address");
	JLabel lp = new JLabel("port");
	JTextField portText = new JTextField();
	JPanel main = new JPanel(new GridLayout(3,2));
	JButton ok = new JButton("confirm");
	public IpDialog(JFrame frame)
	{
		super(frame,true);
		setSize(300,100);
		ok.addActionListener(this);
		main.add(lip);
		main.add(ipText);
		main.add(lp);
		main.add(portText);
		main.add(new JPanel());
		main.add(ok);
		setContentPane(main);
	}
	
	
	public String getIpAddress()
	{
		return ipAddress;
	}
	
	public String getPort()
	{
		return port;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		String ip = ipText.getText().trim();
		if(ip.matches("b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).)" 
                + "{3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)b"))
		{
			//JobMessageFromOperator
		}
		
	}

}
