package fiveChoiceAnalysis;

import dataModels.SessionParameters;

class FiveChoiceSessionParameters extends SessionParameters {

	private float stimulusDuration;

	private float stimulusDelay;
	
	private float stimulusBrightness;
	
	private float itiLength;
	
	private int delayBlock;
	
	//Default Values
	public FiveChoiceSessionParameters() {
		this.stimulusDuration = Float.NaN;
		this.stimulusDelay = Float.NaN;
		this.itiLength = Float.NaN;
		this.stimulusBrightness = 5;
		this.delayBlock = -1;
	}

	/**
	 * @return the stimulusDuration
	 */
	public float getStimulusDuration() {
		return stimulusDuration;
	}

	/**
	 * @param stimulusDuration the stimulusDuration to set
	 */
	public void setStimulusDuration(float stimulusDuration) {
		this.stimulusDuration = stimulusDuration;
	}

	/**
	 * @return the stimulusDelay
	 */
	public float getStimulusDelay() {
		return stimulusDelay;
	}

	/**
	 * @param stimulusDelay the stimulusDelay to set
	 */
	public void setStimulusDelay(float stimulusDelay) {
		this.stimulusDelay = stimulusDelay;
	}

	/**
	 * @return the stimulusBrightness
	 */
	public float getStimulusBrightness() {
		return stimulusBrightness;
	}

	/**
	 * @param stimulusBrightness the stimulusBrightness to set
	 */
	public void setStimulusBrightness(float stimulusBrightness) {
		this.stimulusBrightness = stimulusBrightness;
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
	 * @return the delayBlock
	 */
	public int getDelayBlock() {
		return delayBlock;
	}

	/**
	 * @param delayBlock the delayBlock to set
	 */
	public void setDelayBlock(int delayBlock) {
		this.delayBlock = delayBlock;
	}
	
	
}
