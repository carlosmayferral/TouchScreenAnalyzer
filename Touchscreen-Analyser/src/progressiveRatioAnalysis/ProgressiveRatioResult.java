package progressiveRatioAnalysis;

class ProgressiveRatioResult {

	private int trialNumber;
	
	private float timeStamp;
	
	private int requiredRatio;
	
	private int validTouches;
	
	//Touches during the pause between image presentations
	private int itiTouches;
	
	//Touches during the pause between ratios
	private int interRatioTouches;
	
	private int rewardPeriodTouches;
	
	private float firstTouchLatency;
	
	private float rewardCollectionLatency;
	
	private float averageTimeBetweenValidTouches;
	
	private float medianTimeBetweenValidTouches;
	
	//Center touches are touches to center hole during a ratio (includes ITI)
	private float averageTimeBetweenCenterTouches;
	
	private float medianTimeBetweenCenterTouches;
	
	private int frontBeamBreaks;
	
	private int backBeamBreaks;
	
	private int trayBeamBreaks;
	
	private float timeInTrial;
	
	private String resultHeader = 
					"Trial_Number" + ","
					+ "TimeStamp" + ","
					+ "Required_Ratio" + ","
					+ "Valid_Touches" + ","
					+ "ITI_Touches" + "," 
					+ "Inter_Ratio_Touches" + ","
					+ "Reward_Period_Touches" + ","
					+ "First_Response_Latency" + "," 
					+ "Reward_Collection_Latency" + ","
					+ "Average_Time_Between_Valid_Touches" + "," 
					+ "Median_Time_Between_Valid_Touches" + ","
					+ "Average_Time_Between_Center_Touches" + ","
					+ "Median_Time_Between_Center_Touches" + ","
					+ "Front_Beam_Breaks" + "," 
					+ "Back_Beam_Breaks" + "," 
					+ "Tray_Beam_Breaks" + ","
					+ "Time_Spent_In_Trial";

	public ProgressiveRatioResult() {
		this.trialNumber = -1;
		this.timeStamp = Float.NaN;
		this.requiredRatio = -1;
		this.validTouches = -1;
		this.itiTouches = -1;
		this.interRatioTouches = -1;
		this.rewardPeriodTouches = -1;
		this.firstTouchLatency = Float.NaN;
		this.rewardCollectionLatency = Float.NaN;
		this.averageTimeBetweenValidTouches = Float.NaN;
		this.medianTimeBetweenValidTouches = Float.NaN;
		this.averageTimeBetweenCenterTouches = Float.NaN;
		this.medianTimeBetweenCenterTouches = Float.NaN;
		this.frontBeamBreaks = -1;
		this.backBeamBreaks = -1;
		this.trayBeamBreaks = -1;
		this.timeInTrial = Float.NaN;
	}
	
	@Override
	public String toString() {
		return
				this.trialNumber + "," +
				this.timeStamp + "," +
				this.requiredRatio + "," +
				this.validTouches + "," +
				this.itiTouches + "," +
				this.interRatioTouches + "," +
				this.rewardPeriodTouches + "," +
				this.firstTouchLatency + "," +
				this.rewardCollectionLatency + "," +
				this.averageTimeBetweenValidTouches + "," +
				this.medianTimeBetweenValidTouches + "," +
				this.averageTimeBetweenCenterTouches + "," +
				this.medianTimeBetweenCenterTouches + "," +
				this.frontBeamBreaks + "," +
				this.backBeamBreaks + "," +
				this.trayBeamBreaks + "," +
				this.timeInTrial;
	}

	public int getTrialNumber() {
		return trialNumber;
	}

	public void setTrialNumber(int trialNumber) {
		this.trialNumber = trialNumber;
	}

	public float getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(float timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getRequiredRatio() {
		return requiredRatio;
	}

	public void setRequiredRatio(int requiredRatio) {
		this.requiredRatio = requiredRatio;
	}

	public int getValidTouches() {
		return validTouches;
	}

	public void setValidTouches(int validTouches) {
		this.validTouches = validTouches;
	}

	public int getItiTouches() {
		return itiTouches;
	}

	public void setItiTouches(int itiTouches) {
		this.itiTouches = itiTouches;
	}

	public int getInterRatioTouches() {
		return interRatioTouches;
	}

	public void setInterRatioTouches(int interRatioTouches) {
		this.interRatioTouches = interRatioTouches;
	}

	public int getRewardPeriodTouches() {
		return rewardPeriodTouches;
	}

	public void setRewardPeriodTouches(int rewardPeriodTouches) {
		this.rewardPeriodTouches = rewardPeriodTouches;
	}

	public float getFirstTouchLatency() {
		return firstTouchLatency;
	}

	public void setFirstTouchLatency(float firstTouchLatency) {
		this.firstTouchLatency = firstTouchLatency;
	}

	public float getRewardCollectionLatency() {
		return rewardCollectionLatency;
	}

	public void setRewardCollectionLatency(float rewardCollectionLatency) {
		this.rewardCollectionLatency = rewardCollectionLatency;
	}

	public float getAverageTimeBetweenValidTouches() {
		return averageTimeBetweenValidTouches;
	}

	public void setAverageTimeBetweenValidTouches(float averageTimeBetweenValidTouches) {
		this.averageTimeBetweenValidTouches = averageTimeBetweenValidTouches;
	}

	public float getMedianTimeBetweenValidTouches() {
		return medianTimeBetweenValidTouches;
	}

	public void setMedianTimeBetweenValidTouches(float medianTimeBetweenValidTouches) {
		this.medianTimeBetweenValidTouches = medianTimeBetweenValidTouches;
	}

	public float getAverageTimeBetweenCenterTouches() {
		return averageTimeBetweenCenterTouches;
	}

	public void setAverageTimeBetweenCenterTouches(float averageTimeBetweenCenterTouches) {
		this.averageTimeBetweenCenterTouches = averageTimeBetweenCenterTouches;
	}

	public float getMedianTimeBetweenCenterTouches() {
		return medianTimeBetweenCenterTouches;
	}

	public void setMedianTimeBetweenCenterTouches(float medianTimeBetweenCenterTouches) {
		this.medianTimeBetweenCenterTouches = medianTimeBetweenCenterTouches;
	}

	public float getTimeInTrial() {
		return timeInTrial;
	}

	public void setTimeInTrial(float timeInTrial) {
		this.timeInTrial = timeInTrial;
	}
	
	public int getFrontBeamBreaks() {
		return frontBeamBreaks;
	}

	public void setFrontBeamBreaks(int frontBeamBreaks) {
		this.frontBeamBreaks = frontBeamBreaks;
	}

	public int getBackBeamBreaks() {
		return backBeamBreaks;
	}

	public void setBackBeamBreaks(int backBeamBreaks) {
		this.backBeamBreaks = backBeamBreaks;
	}

	public int getTrayBeamBreaks() {
		return trayBeamBreaks;
	}

	public void setTrayBeamBreaks(int trayBeamBreaks) {
		this.trayBeamBreaks = trayBeamBreaks;
	}

	public String getResultHeader() {
		return resultHeader;
	}
	
	
	
}
