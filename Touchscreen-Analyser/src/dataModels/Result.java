package dataModels;

public abstract class Result {
	
	SessionInfo sessionInfo;
	
	public Result(SessionInfo sessionInfo)
	{
		this.sessionInfo = sessionInfo;
	}
	
	public abstract String toString();
	
}
