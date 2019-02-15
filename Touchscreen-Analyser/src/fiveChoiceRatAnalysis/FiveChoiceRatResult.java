package fiveChoiceRatAnalysis;

class FiveChoiceRatResult {

	public static final String HEADER = 
			"Time_Stamp" + "," +
			"Trial_Number" + "," +
			"Stimulus_Duration" + "," +
			"Stimulus_Delay" + "," +
			"Delay_Block" + "," +
			"Stimulus_Brightness" + "," +
			"Distractor_Played" + "," +
			"Time_To_Distracter" + "," +
			"Target_Position" + "," +
			"Premature_Trial" + "," +
			"Responded" + "," +
			"Correct" + "," +
			"Initiation_Latency" + "," +
			"Response_Latency" + "," +
			"Time_In_Trial" + "," +
			"Reward_Collection_Latency" + "," +
			"Total_Pre_Trial_Touches" + "," +
			"Perseverative_Touches" + "," +
			"Total_Post_Stimulus_Touches" + "," +
			"Total_Touches_During_ITI" + "," +
			"Front_Beam_Breaks" + "," +
			"Back_Beam_Breaks" + "," +
			"Tray_Beam_Breaks"; 
	
	private float timeStamp;
	private int trialNumber;
	private float stimulusDuration;
	private float stimulusDelay;
	private int delayBlock;
	private float stimulusBrightness;
	private int distractorPlayed;
	private float timeToDistractor;
	private int targetPosition;
	private boolean premature;
	private boolean response;
	private boolean correct;
	private float initiationLatency;
	private float responseLatency;
	private float rewardCollectionLatency;
	private float timeInTrial;
	private int initiationPeriodTouches;
	private int perseverativeTouches;
	private int totalPostStimulusPeriodTouches;
	private float totalTouchesDuringITI;
	private int frontBeamBreaks;
	private int backBeamBreaks;
	private int trayBeamBreaks;
	
	public FiveChoiceRatResult(){
		this.timeStamp = Float.NaN;
		this.trialNumber = -1;
		this.stimulusDuration = Float.NaN;
		this.stimulusDelay = Float.NaN;
		this.delayBlock = -1;
		this.stimulusBrightness = -1;
		this.distractorPlayed = -1;
		this.timeToDistractor = Float.NaN;
		this.targetPosition = -1;
		this.initiationLatency = Float.NaN;
		this.responseLatency = Float.NaN;
		this.rewardCollectionLatency = Float.NaN;
		this.timeInTrial= Float.NaN;
		this.initiationPeriodTouches = -1;
		this.perseverativeTouches = -1;
		this.totalPostStimulusPeriodTouches = -1;
		this.totalTouchesDuringITI = -1;
		this.frontBeamBreaks = -1;
		this.backBeamBreaks = -1;
		this.trayBeamBreaks = -1;
	}
	
	public String toString() {
		return
				this.timeStamp + "," +
				this.trialNumber + "," +
				this.stimulusDuration + "," +
				this.stimulusDelay + "," +
				this.delayBlock + "," +
				this.stimulusBrightness + "," +
				this.distractorPlayed + "," +
				this.timeToDistractor + "," +
				this.targetPosition + "," +
				this.premature + "," +
				this.response + "," +
				this.correct + "," +
				this.initiationLatency + "," +
				this.responseLatency + "," +
				this.timeInTrial + "," +
				this.rewardCollectionLatency + "," +
				this.initiationPeriodTouches + "," +
				this.perseverativeTouches + "," +
				this.totalPostStimulusPeriodTouches + "," +
				this.totalTouchesDuringITI + "," +
				this.frontBeamBreaks + "," +
				this.backBeamBreaks + "," +
				this.trayBeamBreaks;
	}

	/**
	 * @return the timeStamp
	 */
	public float getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(float timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * @return the trialNumber
	 */
	public int getTrialNumber() {
		return trialNumber;
	}

	/**
	 * @param trialNumber the trialNumber to set
	 */
	public void setTrialNumber(int trialNumber) {
		this.trialNumber = trialNumber;
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
	 * @return the targetPosition
	 */
	public int getTargetPosition() {
		return targetPosition;
	}

	/**
	 * @param targetPosition the targetPosition to set
	 */
	public void setTargetPosition(int targetPosition) {
		this.targetPosition = targetPosition;
	}

	/**
	 * @return the premature
	 */
	public boolean isPremature() {
		return premature;
	}

	/**
	 * @param premature the premature to set
	 */
	public void setPremature(boolean premature) {
		this.premature = premature;
	}

	/**
	 * @return the response
	 */
	public boolean isResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(boolean response) {
		this.response = response;
	}

	/**
	 * @return the correct
	 */
	public boolean isCorrect() {
		return correct;
	}

	/**
	 * @param correct the correct to set
	 */
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	/**
	 * @return the initiationLatency
	 */
	public float getInitiationLatency() {
		return initiationLatency;
	}

	/**
	 * @param initiationLatency the initiationLatency to set
	 */
	public void setInitiationLatency(float initiationLatency) {
		this.initiationLatency = initiationLatency;
	}

	/**
	 * @return the responseLatency
	 */
	public float getResponseLatency() {
		return responseLatency;
	}

	/**
	 * @param responseLatency the responseLatency to set
	 */
	public void setResponseLatency(float responseLatency) {
		this.responseLatency = responseLatency;
	}

	/**
	 * @return the rewardCollectionLatency
	 */
	public float getRewardCollectionLatency() {
		return rewardCollectionLatency;
	}

	/**
	 * @param rewardCollectionLatency the rewardCollectionLatency to set
	 */
	public void setRewardCollectionLatency(float rewardCollectionLatency) {
		this.rewardCollectionLatency = rewardCollectionLatency;
	}

	/**
	 * @return the initiationPeriodTouches
	 */
	public int getInitiationPeriodTouches() {
		return initiationPeriodTouches;
	}

	/**
	 * @param initiationPeriodTouches the initiationPeriodTouches to set
	 */
	public void setInitiationPeriodTouches(int initiationPeriodTouches) {
		this.initiationPeriodTouches = initiationPeriodTouches;
	}

	/**
	 * @return the perseverativeTouches
	 */
	public int getPerseverativeTouches() {
		return perseverativeTouches;
	}

	/**
	 * @param perseverativeTouches the perseverativeTouches to set
	 */
	public void setPerseverativeTouches(int perseverativeTouches) {
		this.perseverativeTouches = perseverativeTouches;
	}

	/**
	 * @return the totalPostStimulusPeriodTouches
	 */
	public int getTotalPostStimulusPeriodTouches() {
		return totalPostStimulusPeriodTouches;
	}

	/**
	 * @param totalPostStimulusPeriodTouches the totalPostStimulusPeriodTouches to set
	 */
	public void setTotalPostStimulusPeriodTouches(int totalPostStimulusPeriodTouches) {
		this.totalPostStimulusPeriodTouches = totalPostStimulusPeriodTouches;
	}

	/**
	 * @return the totalTouchesDuringITI
	 */
	public float getTotalTouchesDuringITI() {
		return totalTouchesDuringITI;
	}

	/**
	 * @param totalTouchesDuringITI the totalTouchesDuringITI to set
	 */
	public void setTotalTouchesDuringITI(float totalTouchesDuringITI) {
		this.totalTouchesDuringITI = totalTouchesDuringITI;
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
