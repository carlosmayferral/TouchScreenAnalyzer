package dataModels;


public class MetaData {
	
	private static String[] headers;

	private String [] data;
	
	public MetaData() {
		this.data = new String[headers.length];
		//set all data values to empty strings
		for (int i = 0; i< headers.length; i++) {
			data[i] = "";
		}
	}
	
	public static void setHeaders(String[] headerArray) {
		headers = headerArray;
	}
	
	public void addData(String[] data) {
		this.data = data;
	}
	
	public static String getHeaderString() {
		String headerString = "";
		for (String header : headers) {
			headerString += header + ",";
		}
		return headerString;
	}
	
	public String getDataString() {
		String dataString = "";
		for(String datum : data) {
			dataString += datum + ",";
		}
		return dataString;
	}
	
}
