package zhen.ds.client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetSocketAddress;
import java.util.regex.Pattern;

import javax.print.attribute.standard.JobMessageFromOperator;
import javax.swing.*;

import zhen.ds.server.Logger;

public class IpDialog extends JDialog implements ActionListener{
	String ipAddress;
	int port=18888;
	JTextField ipText = new JTextField();
	JLabel lip = new JLabel("ip address");
	JLabel lp = new JLabel("port");
	JTextField portText = new JTextField();
	JPanel main = new JPanel(new GridLayout(3,2));
	JButton ok = new JButton("confirm");
	public IpDialog(JFrame frame)
	{
		super(frame,true);
		ipText.setText("127.0.0.1");
		portText.setText("18888");
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
	
	public int getPort()
	{
		return port;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		String ip = ipText.getText().trim();
		String port =portText.getText().trim();
		String a ="b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).)" 
            + "{3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)b";
		
		Logger.debug(ip);
		if(Pattern.compile(a).matcher(ip).find())
		{
			//JobMessageFromOperator
			JOptionPane.showMessageDialog(this, "Ip format is not valid");
			return ;
		}
		else
		{
			this.ipAddress=ip;
		}
		try
		{
			int p = Integer.parseInt(port);
			this.port=p;
			if(p<1000||p>65535)
			{
				JOptionPane.showMessageDialog(this, "pick a port that's form 1000 to 65535");
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(this, "port format is not valid");
			return ;
		}
		setVisible(false);
		
		
	}

}
