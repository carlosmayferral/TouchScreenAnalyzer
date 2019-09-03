package dataModels;

public class Result {

	private String content;

	SessionInfo sessionInfo;
	
	private MetaData metaData;

	private String contentHeader;

	public Result(SessionInfo sessionInfo, MetaData metaData, String content, String resultHeader) {
		this.sessionInfo = sessionInfo;
		this.metaData = metaData;
		this.content = content;
		this.contentHeader = resultHeader;
	}

	public String toString() {
		return this.sessionInfo.toString() + ((metaData != null) ? metaData.getDataString() : "") + content;
	}

	public String getHeader() {
		return sessionInfo.getHeader() + MetaData.getHeaderString() + contentHeader;
	}

}
