package cptBaselineAnalysis;

public class CPTBaselineResult{
	
	private float timeStamp;
	
	private int trialNumber;
	
	private boolean correctionTrial;
	private boolean correctionTrialHasBeenSet;
	
	private int correctImage;
	
	//Variables needed for probe analysis
	private int imageShown;
	private float stimulusDuration;
	private float stimulusContrast;
	private float itiDuration;
	private boolean distracter;
	private boolean distracterhasBeenSet;
	private boolean congruentDistracter;
	private boolean congruentDistracterHasBeenSet;

	private boolean correct;
	private boolean correctHasBeenSet;
	
	private int itiCenterTouches;
	
	private int totalTouchesDuringSD;
	
	private int totalTouchesDuringLH;
	
	private int postTrialCenterTouches;
	
	//Latencies
	private float responseLatency;
	
	private float rewardCollectionLatency;
	
	//Beam breaks
	private int frontBeamBreaks;
	private int backBeamBreaks;
	private int trayBeamBreaks;
	
	//Make sure header contains everything
	private final static String HEADER = 
			"TimeStamp" + "," +
			"Trial_Number" + "," + 
			"Correct_Image" + "," + 
			"Correction_Trial" + "," +
			"Image_Displayed"  + "," +
			"Stimulus_Duration" + "," +
			"Stimulus_Contrast" + "," +
			"ITI_Duration" + "," +
			"Distracted_Trial" + "," +
			"Congruent_Distracter" + "," +
			"Correct"  + "," +
			"Center_Touches_ITI"  + "," +
			"Total_Touches_SD"  + "," +
			"Total_Touches_LH"  + "," +
			"Total_Touches_Post_Trial"  + "," +
			"Response_Latency"  + "," +
			"Reward_Collection_Latency"  + "," +
			"Front_Beam_Breaks"  + "," +
			"Back_Beam_Breaks"  + "," +
			"Tray_Beam_Breaks";
	
	public CPTBaselineResult() {
		timeStamp = Float.NaN;
		trialNumber = -1;
		correctionTrial = Boolean.FALSE;
		this.correctImage = -1;
		this.correctionTrialHasBeenSet = false;
		this.imageShown = -1;
		this.stimulusDuration = Float.NaN;
		this.stimulusContrast = Float.NaN;
		this.itiDuration= Float.NaN;
		this.distracter = false;
		this.distracterhasBeenSet = false;
		this.congruentDistracter = false;
		this.congruentDistracterHasBeenSet = false;
		this.correct = Boolean.FALSE;
		this.correctHasBeenSet = false;
		this.itiCenterTouches = -1;
		this.totalTouchesDuringSD = -1;
		this.totalTouchesDuringLH = -1;
		this.postTrialCenterTouches = -1;
		this.responseLatency = Float.NaN;
		this.rewardCollectionLatency = Float.NaN;
		this.frontBeamBreaks = -1;
		this.backBeamBreaks = -1;
		this.trayBeamBreaks = -1;
	}
	
	@Override
	public String toString() {
		return timeStamp + "," + 
				trialNumber  + "," +
				this.correctImage + "," +
				(correctionTrial ? "1" : "0")  + "," +
				imageShown  + "," +
				stimulusDuration + "," +
				stimulusContrast + "," +
				itiDuration + "," +
				(distracter ? "1": "0") + "," +
				(congruentDistracter? "1" : "0") + "," +
				(correct ? "1" : "0")  + "," +
				itiCenterTouches  + "," +
				this.totalTouchesDuringSD  + "," +
				this.totalTouchesDuringLH  + "," +
				this.postTrialCenterTouches  + "," +
				this.responseLatency  + "," +
				this.rewardCollectionLatency  + "," +
				this.frontBeamBreaks  + "," +
				this.backBeamBreaks  + "," +
				this.trayBeamBreaks;
	}
	
	public boolean isDistracter() throws Exception {
		if (distracterhasBeenSet) {
			return distracter;
		}
		else throw new Exception("Attempting to retrieve unset distracter value");
	}

	public void setDistracter(boolean distracter) {
		this.distracter = distracter;
		this.distracterhasBeenSet = true;
	}

	public boolean isCongruentDistracter() throws Exception {
		if (this.congruentDistracterHasBeenSet) {
			return congruentDistracter;
		}
		else throw new Exception("Attempting to retrieve unset congruent distracter value");
	}

	public void setCongruentDistracter(boolean congruentDistracter) {
		this.congruentDistracter = congruentDistracter;
		this.congruentDistracterHasBeenSet = true;
	}

	public String getHeader() {
		return HEADER;
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
	 * @return the correctionTrial
	 * @throws Exception 
	 */
	public boolean isCorrectionTrial() throws Exception {
		if (!this.correctionTrialHasBeenSet) {
			throw new Exception("Correction Trial has not been set");
		}
		return correctionTrial;
	}

	public void setCorrectImage(int imageIndex) {
		this.correctImage = imageIndex;
	}
	
	/**
	 * @param correctionTrial the correctionTrial to set
	 */
	public void setCorrectionTrial(boolean correctionTrial) {
		this.correctionTrialHasBeenSet = true;
		this.correctionTrial = correctionTrial;
	}

	/**
	 * @return the hit
	 * @throws Exception 
	 */
	public boolean isCorrect() throws Exception {
		if (!this.correctHasBeenSet) {
			throw new Exception("Correct has not been set yet");
		} else {
		return correct;
		}
	}

	/**
	 * @param hit the hit to set
	 */
	public void setCorrect(boolean hit) {
		this.correctHasBeenSet = true;
		this.correct = hit;
	}

	/**
	 * @return the imageShown
	 */
	public int getImageShown() {
		return imageShown;
	}

	/**
	 * @param imageShown the imageShown to set
	 */
	public void setImageShown(int imageShown) {
		this.imageShown = imageShown;
	}

	/**
	 * @return the itiCenterTouches
	 */
	public int getItiCenterTouches() {
		return itiCenterTouches;
	}

	/**
	 * @param itiCenterTouches the itiCenterTouches to set
	 */
	public void setItiCenterTouches(int itiCenterTouches) {
		this.itiCenterTouches = itiCenterTouches;
	}

	/**
	 * @return the totalTouchesDuringSD
	 */
	public int getTotalTouchesDuringSD() {
		return totalTouchesDuringSD;
	}

	/**
	 * @param totalTouchesDuringSD the totalTouchesDuringSD to set
	 */
	public void setTotalTouchesDuringSD(int totalTouchesDuringSD) {
		this.totalTouchesDuringSD = totalTouchesDuringSD;
	}

	/**
	 * @return the totalTouchesDuringLH
	 */
	public int getTotalTouchesDuringLH() {
		return totalTouchesDuringLH;
	}

	/**
	 * @param totalTouchesDuringLH the totalTouchesDuringLH to set
	 */
	public void setTotalTouchesDuringLH(int totalTouchesDuringLH) {
		this.totalTouchesDuringLH = totalTouchesDuringLH;
	}

	/**
	 * @return the postTrialCenterTouches
	 */
	public int getPostTrialCenterTouches() {
		return postTrialCenterTouches;
	}

	/**
	 * @param postTrialCenterTouches the postTrialCenterTouches to set
	 */
	public void setPostTrialCenterTouches(int postTrialCenterTouches) {
		this.postTrialCenterTouches = postTrialCenterTouches;
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
	 * @return the stimulusContrast
	 */
	public float getStimulusContrast() {
		return stimulusContrast;
	}

	/**
	 * @param stimulusContrast the stimulusContrast to set
	 */
	public void setStimulusContrast(float stimulusContrast) {
		this.stimulusContrast = stimulusContrast;
	}
	
	public float getItiDuration() {
		return itiDuration;
	}

	public void setItiDuration(float itiDuration) {
		this.itiDuration = itiDuration;
	}
	

}
