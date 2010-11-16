package zhen.ds.server.exception;

@SuppressWarnings("serial")
public class ConnectionFailException extends Exception{
	public ConnectionFailException(String reason)
	{
		super(reason);
	}
	

}
