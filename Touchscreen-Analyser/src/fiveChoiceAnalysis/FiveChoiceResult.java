package fiveChoiceAnalysis;

public class FiveChoiceResult {
	
	private float timeStamp;
	
	private int trialNumber;
	
	private float stimulusDuration;
	
	private float stimulusDelay;
	
	private int delayBlock;
	
	private float stimulusBrightness;
	
	private int targetPosition;
	
	private boolean premature;
	
	private boolean response;
	
	private boolean correct;
	
	private float initiationLatency;
	
	private float responseLatency;
	
	private float rewardCollectionLatency;
	
	private int initiationPeriodTouches;
	
	private int perseverativeTouches;
	
	private int totalPostStimulusPeriodTouches;
	
	private float totalTouchesDuringITI;
	
	private int frontBeamBreaks;
	
	private int backBeamBreaks;
	
	private int trayBeamBreaks;
	
	private float timeInTrial;
	
	
	public FiveChoiceResult(){
		this.timeStamp = Float.NaN;
		this.trialNumber = -1;
		this.stimulusDuration = Float.NaN;
		this.stimulusDelay = Float.NaN;
		this.delayBlock = -1;
		this.stimulusBrightness = -1;
		this.targetPosition = -1;
		this.initiationLatency = Float.NaN;
		this.responseLatency = Float.NaN;
		this.rewardCollectionLatency = Float.NaN;
		this.initiationPeriodTouches = -1;
		this.perseverativeTouches = -1;
		this.totalPostStimulusPeriodTouches = -1;
		this.totalTouchesDuringITI = -1;
		this.frontBeamBreaks = -1;
		this.backBeamBreaks = -1;
		this.trayBeamBreaks = -1;
		this.setTimeInTrial(Float.NaN);
	}
	
	public String toString() {
		return
				this.timeStamp + "," +
				this.trialNumber + "," +
				this.stimulusDuration + "," +
				this.stimulusDelay + "," +
				this.delayBlock + "," +
				this.stimulusBrightness + "," +
				this.targetPosition + "," +
				this.premature + "," +
				this.response + "," +
				this.correct + "," +
				this.initiationLatency + "," +
				this.responseLatency + "," +
				this.rewardCollectionLatency + "," +
				this.initiationPeriodTouches + "," +
				this.perseverativeTouches + "," +
				this.totalPostStimulusPeriodTouches + "," +
				this.totalTouchesDuringITI + "," +
				this.frontBeamBreaks + "," +
				this.backBeamBreaks + "," +
				this.trayBeamBreaks + "," +
				this.timeInTrial;
	}
	
	public String getHeader() {
		return
				"Time_Stamp" + "," +
				"Trial_Number" + "," +
				"Stimulus_Duration" + "," +
				"Stimulus_Delay" + "," +
				"Delay_Block" + "," +
				"Stimulus_Brightness" + "," +
				"Target_Position" + "," +
				"Premature_Trial" + "," +
				"Responded" + "," +
				"Correct" + "," +
				"Initiation_Latency" + "," +
				"Response_Latency" + "," +
				"Reward_Collection_Latency" + "," +
				"Total_Pre_Trial_Touches" + "," +
				"Perseverative_Touches" + "," +
				"Total_Post_Stimulus_Touches" + "," +
				"Total_Touches_During_ITI" + "," +
				"Front_Beam_Breaks" + "," +
				"Back_Beam_Breaks" + "," +
				"Tray_Beam_Breaks" + "," +
				"Time_In_Trial"; 
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(float timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @param trialNumber the trialNumber to set
	 */
	public void setTrialNumber(int trialNumber) {
		this.trialNumber = trialNumber;
	}

	/**
	 * @param stimulusDuration the stimulusDuration to set
	 */
	public void setStimulusDuration(float stimulusDuration) {
		this.stimulusDuration = stimulusDuration;
	}

	/**
	 * @param stimulusDelay the stimulusDelay to set
	 */
	public void setStimulusDelay(float stimulusDelay) {
		this.stimulusDelay = stimulusDelay;
	}

	/**
	 * @param delayBlock the delayBlock to set
	 */
	public void setDelayBlock(int delayBlock) {
		this.delayBlock = delayBlock;
	}

	/**
	 * @param stimulusBrightness the stimulusBrightness to set
	 */
	public void setStimulusBrightness(float stimulusBrightness) {
		this.stimulusBrightness = stimulusBrightness;
	}

	/**
	 * @param targetPosition the targetPosition to set
	 */
	public void setTargetPosition(int targetPosition) {
		this.targetPosition = targetPosition;
	}

	/**
	 * @param premature the premature to set
	 */
	public void setPremature(boolean premature) {
		this.premature = premature;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(boolean response) {
		this.response = response;
	}

	/**
	 * @param correct the correct to set
	 */
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	/**
	 * @param initiationLatency the initiationLatency to set
	 */
	public void setInitiationLatency(float initiationLatency) {
		this.initiationLatency = initiationLatency;
	}

	/**
	 * @param responseLatency the responseLatency to set
	 */
	public void setResponseLatency(float responseLatency) {
		this.responseLatency = responseLatency;
	}

	/**
	 * @param rewardCollectionLatency the rewardCollectionLatency to set
	 */
	public void setRewardCollectionLatency(float rewardCollectionLatency) {
		this.rewardCollectionLatency = rewardCollectionLatency;
	}

	/**
	 * @param initiationPeriodTouches the initiationPeriodTouches to set
	 */
	public void setInitiationPeriodTouches(int initiationPeriodTouches) {
		this.initiationPeriodTouches = initiationPeriodTouches;
	}

	/**
	 * @param perseverativeTouches the perseverativeTouches to set
	 */
	public void setPerseverativeTouches(int perseverativeTouches) {
		this.perseverativeTouches = perseverativeTouches;
	}

	/**
	 * @param totalPostStimulusPeriodTouches the totalPostStimulusPeriodTouches to set
	 */
	public void setTotalPostStimulusPeriodTouches(int totalPostStimulusPeriodTouches) {
		this.totalPostStimulusPeriodTouches = totalPostStimulusPeriodTouches;
	}

	/**
	 * @param totalTouchesDuringITI the totalTouchesDuringITI to set
	 */
	public void setTotalTouchesDuringITI(float totalTouchesDuringITI) {
		this.totalTouchesDuringITI = totalTouchesDuringITI;
	}

	/**
	 * @return the timeStamp
	 */
	public float getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @return the trialNumber
	 */
	public int getTrialNumber() {
		return trialNumber;
	}

	/**
	 * @return the stimulusDuration
	 */
	public float getStimulusDuration() {
		return stimulusDuration;
	}

	/**
	 * @return the stimulusDelay
	 */
	public float getStimulusDelay() {
		return stimulusDelay;
	}

	/**
	 * @return the delayBlock
	 */
	public int getDelayBlock() {
		return delayBlock;
	}

	/**
	 * @return the stimulusBrightness
	 */
	public float getStimulusBrightness() {
		return stimulusBrightness;
	}

	/**
	 * @return the targetPosition
	 */
	public int getTargetPosition() {
		return targetPosition;
	}

	/**
	 * @return the premature
	 */
	public boolean isPremature() {
		return premature;
	}

	/**
	 * @return the response
	 */
	public boolean isResponse() {
		return response;
	}

	/**
	 * @return the correct
	 */
	public boolean isCorrect() {
		return correct;
	}

	/**
	 * @return the initiationLatency
	 */
	public float getInitiationLatency() {
		return initiationLatency;
	}

	/**
	 * @return the responseLatency
	 */
	public float getResponseLatency() {
		return responseLatency;
	}

	/**
	 * @return the rewardCollectionLatency
	 */
	public float getRewardCollectionLatency() {
		return rewardCollectionLatency;
	}

	/**
	 * @return the initiationPeriodTouches
	 */
	public int getInitiationPeriodTouches() {
		return initiationPeriodTouches;
	}

	/**
	 * @return the perseverativeTouches
	 */
	public int getPerseverativeTouches() {
		return perseverativeTouches;
	}

	/**
	 * @return the totalPostStimulusPeriodTouches
	 */
	public int getTotalPostStimulusPeriodTouches() {
		return totalPostStimulusPeriodTouches;
	}

	/**
	 * @return the totalTouchesDuringITI
	 */
	public float getTotalTouchesDuringITI() {
		return totalTouchesDuringITI;
	}

	/**
	 * @return the frontBeamBreaks
	 */
	public int getFrontBeamBreaks() {
		return frontBeamBreaks;
	}

	/**
	 * @param frontBeamBreaks the frontBeamBreaks to set
	 */
	public void setFrontBeamBreaks(int frontBeamBreaks) {
		this.frontBeamBreaks = frontBeamBreaks;
	}

	/**
	 * @return the backBeamBreaks
	 */
	public int getBackBeamBreaks() {
		return backBeamBreaks;
	}

	/**
	 * @param backBeamBreaks the backBeamBreaks to set
	 */
	public void setBackBeamBreaks(int backBeamBreaks) {
		this.backBeamBreaks = backBeamBreaks;
	}

	/**
	 * @return the trayBeamBreaks
	 */
	public int getTrayBeamBreaks() {
		return trayBeamBreaks;
	}

	/**
	 * @param trayBeamBreaks the trayBeamBreaks to set
	 */
	public void setTrayBeamBreaks(int trayBeamBreaks) {
		this.trayBeamBreaks = trayBeamBreaks;
	}

	/**
	 * @return the timeInTrial
	 */
	public float getTimeInTrial() {
		return timeInTrial;
	}

	/**
	 * @param timeInTrial the timeInTrial to set
	 */
	public void setTimeInTrial(float timeInTrial) {
		this.timeInTrial = timeInTrial;
	}



}
