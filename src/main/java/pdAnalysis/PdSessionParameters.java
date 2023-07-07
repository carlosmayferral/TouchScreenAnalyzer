package pdAnalysis;

import dataModels.SessionParameters;

public class PdSessionParameters extends SessionParameters {

	private float timeoutLength;
	
	private float itiLength;
	
	private String sPlusId;
	
	public PdSessionParameters() {
		timeoutLength = Float.NaN;
		itiLength = Float.NaN;
		sPlusId = "";
	}

	/**
	 * @return the timeoutLength
	 */
	public float getTimeoutLength() {
		return timeoutLength;
	}

	/**
	 * @param timeoutLength the timeoutLength to set
	 */
	public void setTimeoutLength(float timeoutLength) {
		this.timeoutLength = timeoutLength;
	}

	/**
	 * @return the itiLength
	 */
	public float getItiLength() {
		return itiLength;
	}

	/**
	 * @param itiLength the itiLength to set
	 */
	public void setItiLength(float itiLength) {
		this.itiLength = itiLength;
	}

	/**
	 * @return the spPlusId
	 */
	public String getSpPlusId() {
		return sPlusId;
	}

	/**
	 * @param string the spPlusId to set
	 */
	public void setSpPlusId(String string) {
		this.sPlusId = string;
	}
	
	
	
}
