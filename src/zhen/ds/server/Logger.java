package zhen.ds.server;

import java.io.PrintStream;

public class Logger {
	static PrintStream out= System.out;
	public static void debug(String msg)
	{
		out.println("[DEBUG] "+msg);
	}
	
	public static void error(String msg)
	{
		out.println("[ERROR] "+msg);
	}

}
