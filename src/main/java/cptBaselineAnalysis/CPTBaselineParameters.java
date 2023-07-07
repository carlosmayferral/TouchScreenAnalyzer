package cptBaselineAnalysis;

import dataModels.SessionParameters;

public class CPTBaselineParameters extends SessionParameters {

	private int correctImageIndex;
	
	public CPTBaselineParameters() {
		correctImageIndex = -1;
	}
	
	public void setCorrectImageIndex(int imageIndex) {
		this.correctImageIndex = imageIndex;
	}
	
	public int getCorrectImageIndex() {
		return this.correctImageIndex;
	}
	
}
