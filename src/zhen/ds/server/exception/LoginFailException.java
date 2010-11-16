package zhen.ds.server.exception;

public class LoginFailException extends Exception{

	public LoginFailException(String reason)
	{
		super(reason);
	}
}
