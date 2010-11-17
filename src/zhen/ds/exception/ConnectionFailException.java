package zhen.ds.exception;

@SuppressWarnings("serial")
public class ConnectionFailException extends Exception{
	public ConnectionFailException(String reason)
	{
		super(reason);
	}
	

}
