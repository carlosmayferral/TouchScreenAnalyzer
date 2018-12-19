package dataModels;

public class Result {

	private String content;

	SessionInfo sessionInfo;

	private String contentHeader;

	public Result(SessionInfo sessionInfo, String content, String resultHeader) {
		this.sessionInfo = sessionInfo;
		this.content = content;
		this.contentHeader = resultHeader;
	}

	public String toString() {
		return this.sessionInfo.toString() + content;
	}

	public String getHeader() {
		return sessionInfo.getHeader() + contentHeader;
	}

}
