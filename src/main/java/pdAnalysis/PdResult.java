package pdAnalysis;

class PdResult {
	
	private int trialNumber;
	
	private int correctionTrial;
	
	private String sPlusId;
	
	private int sPlusLocation;
	
	private float timeoutLength;
	
	private float itiLength;
	
	private float initiationLatency;
	
	private float headWithdrawalTime;
	
	private float responseLatency;
	
	private int correct;
	
	private float rewardCollectionLatency;
	
	private float feedingTime;
	
	private float timeInTrial;
	
	private int initiationTouches;
	
	private int postStimulusTouches;
	
	private int itiTouches;
	
	private int errorCheck;

	private int frontBeamBreaks;

	private int backBeamBreaks;

	private int trayBeamBreaks;

	private float timeStamp;
	
	public PdResult() {
		trialNumber = -1;
		timeStamp = Float.NaN;
		correctionTrial = -1;
		sPlusId = "";
		sPlusLocation = -1;
		timeoutLength = Float.NaN;
		itiLength = Float.NaN;
		initiationLatency = Float.NaN;
		headWithdrawalTime = Float.NaN;
		responseLatency = Float.NaN;
		correct = -1;
		rewardCollectionLatency = Float.NaN;
		feedingTime = Float.NaN;
		timeInTrial = Float.NaN;
		initiationTouches = -1;
		postStimulusTouches = -1;
		itiTouches = -1;
		setFrontBeamBreaks(-1);
		setBackBeamBreaks(-1);
		setTrayBeamBreaks(-1);
		errorCheck = 0;
	}
	
	@Override
	public String toString() {
		return
				trialNumber + "," +
				timeStamp + "," +
				correctionTrial + "," +
				sPlusId + "," +
				sPlusLocation + "," +
				timeoutLength + "," +
				itiLength + "," +
				initiationLatency + "," +
				headWithdrawalTime + "," +
				responseLatency + "," +
				correct + "," +
				rewardCollectionLatency + "," +
				feedingTime + "," +
				timeInTrial + "," +
				initiationTouches + "," +
				postStimulusTouches + "," +
				itiTouches + "," +
				frontBeamBreaks + "," +
				backBeamBreaks + "," +
				trayBeamBreaks + "," +
				errorCheck;
	}
	
	public String getHeader() {
		return 
		"Trial_Number,"
		+ "TimeStamp,"
		+ "Correction_Trial,"
		+ "S_Plus,"
		+ "S_Plus_Location,"
		+ "Timeout_Length,"
		+ "ITI_Length,"
		+ "Initiation_Latency,"
		+ "Time_To_Exit_Tray,"
		+ "Response_Latency,"
		+ "Correct_Response,"
		+ "Reward_Collection_Latency,"
		+ "Feeding_Time,"
		+ "Time_In_Trial,"
		+ "Initiation_Period_Touches,"
		+ "Post_Stimulus_Touches,"
		+ "ITI_Touches,"
		+ "Front_Beam_Breaks,"
		+ "Back_Beam_Breaks,"
		+ "Tray_Beam_Breaks,"
		+ "Check_for_errors";
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
	 * @return the correctionTrial
	 */
	public int getCorrectionTrial() {
		return correctionTrial;
	}

	/**
	 * @param correctionTrial the correctionTrial to set
	 */
	public void setCorrectionTrial(int correctionTrial) {
		this.correctionTrial = correctionTrial;
	}

	/**
	 * @return the sPlusLocation
	 */
	public int getsPlusLocation() {
		return sPlusLocation;
	}

	/**
	 * @param sPlusLocation the sPlusLocation to set
	 */
	public void setsPlusLocation(int sPlusLocation) {
		this.sPlusLocation = sPlusLocation;
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
	 * @return the headWithdrawalTime
	 */
	public float getHeadWithdrawalTime() {
		return headWithdrawalTime;
	}

	/**
	 * @param headWithdrawalTime the headWithdrawalTime to set
	 */
	public void setHeadWithdrawalTime(float headWithdrawalTime) {
		this.headWithdrawalTime = headWithdrawalTime;
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
	 * @return the correct
	 */
	public int getCorrect() {
		return correct;
	}

	/**
	 * @param correct the correct to set
	 */
	public void setCorrect(int correct) {
		this.correct = correct;
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
	 * @return the feedingTime
	 */
	public float getFeedingTime() {
		return feedingTime;
	}

	/**
	 * @param feedingTime the feedingTime to set
	 */
	public void setFeedingTime(float feedingTime) {
		this.feedingTime = feedingTime;
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

	/**
	 * @return the initiationTouches
	 */
	public int getInitiationTouches() {
		return initiationTouches;
	}

	/**
	 * @param initiationTouches the initiationTouches to set
	 */
	public void setInitiationTouches(int initiationTouches) {
		this.initiationTouches = initiationTouches;
	}

	/**
	 * @return the postStimulusTouches
	 */
	public int getPostStimulusTouches() {
		return postStimulusTouches;
	}

	/**
	 * @param postStimulusTouches the postStimulusTouches to set
	 */
	public void setPostStimulusTouches(int postStimulusTouches) {
		this.postStimulusTouches = postStimulusTouches;
	}

	/**
	 * @return the itiTouches
	 */
	public int getItiTouches() {
		return itiTouches;
	}

	/**
	 * @param itiTouches the itiTouches to set
	 */
	public void setItiTouches(int itiTouches) {
		this.itiTouches = itiTouches;
	}

	/**
	 * @return the errorCheck
	 */
	public int getErrorCheck() {
		return errorCheck;
	}

	/**
	 * @param errorCheck the errorCheck to set
	 */
	public void setErrorCheck(int errorCheck) {
		this.errorCheck = errorCheck;
	}

	/**
	 * @return the sPlusId
	 */
	public String getsPlusId() {
		return sPlusId;
	}

	/**
	 * @param sPlusId2 the sPlusId to set
	 */
	public void setsPlusId(String sPlusId2) {
		this.sPlusId = sPlusId2;
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

	public void setTimeStamp(float event_Time) {
		// TODO Auto-generated method stub
		this.timeStamp = event_Time;
	}
	
	

}
