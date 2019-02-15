package fiveChoiceRatAnalysis;

import dataModels.SessionParameters;

class FiveChoiceRatSessionParameters extends SessionParameters {

	private float stimulusDuration;

	private float stimulusDelay;
	
	private float stimulusBrightness;
	
	private float limitedHold;
	
	private float itiLength;
	
	private int delayBlock;
	
	private float timeToDistractor;
	
	private int distractorPlayed;
	
	//Default Values
	public FiveChoiceRatSessionParameters() {
		this.stimulusDuration = Float.NaN;
		this.stimulusDelay = Float.NaN;
		this.itiLength = Float.NaN;
		this.stimulusBrightness = 5;
		this.delayBlock = -1;
		this.timeToDistractor = Float.NaN;	
		this.limitedHold = Float.NaN;
		this.distractorPlayed = -1;
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

	/**
	 * @return the distractorOffset
	 */
	public float getDistractorOffset() {
		return timeToDistractor;
	}

	/**
	 * @param distractorOffset the distractorOffset to set
	 */
	public void setDistractorOffset(float distractorOffset) {
		this.timeToDistractor = distractorOffset;
	}

	/**
	 * @return the limitedHold
	 */
	public float getLimitedHold() {
		return limitedHold;
	}

	/**
	 * @param limitedHold the limitedHold to set
	 */
	public void setLimitedHold(float limitedHold) {
		this.limitedHold = limitedHold;
	}

	/**
	 * @return the timeToDistractor
	 */
	public float getTimeToDistractor() {
		return timeToDistractor;
	}

	/**
	 * @param timeToDistractor the timeToDistractor to set
	 */
	public void setTimeToDistractor(float timeToDistractor) {
		this.timeToDistractor = timeToDistractor;
	}

	/**
	 * @return the distractorPlayed
	 */
	public int getDistractorPlayed() {
		return distractorPlayed;
	}

	/**
	 * @param distractorPlayed the distractorPlayed to set
	 */
	public void setDistractorPlayed(int distractorPlayed) {
		this.distractorPlayed = distractorPlayed;
	}
	
}
