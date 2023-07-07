package mPosnerAnalysis;

import dataModels.SessionParameters;

public class MPosnerProbeParameters extends SessionParameters {
	
	public MPosnerProbeParameters() {
		this.trialType = PosnerTrialType.Undefined;
	}
	
	public String getTrialType() {
		return trialType.name();
	}

	public void setTrialType(PosnerTrialType trialType) {
		this.trialType = trialType;
	}

	public Double getCueTime() {
		return cueTime;
	}

	public void setCueTime(Double cueTime) {
		this.cueTime = cueTime;
	}

	public Double getHoldTime() {
		return holdTime;
	}

	public void setHoldTime(Double holdTime) {
		this.holdTime = holdTime;
	}

	public Double getTargetTime() {
		return targetTime;
	}

	public void setTargetTime(Double targetTime) {
		this.targetTime = targetTime;
	}

	private PosnerTrialType trialType;
	
	private Double cueTime;
	
	private Double holdTime;
	
	private Double targetTime;

}


