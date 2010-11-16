package zhen.ds.server;

import java.io.PrintStream;

public class Logger {
	PrintStream out= System.out;
	public void debug()
	{
		
	}
	
	public void error()
	{
		out.println("error at")
	}

}
