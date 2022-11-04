package tunlAnalysisSet;

/**
 * The purpose of the TunlResult class is to hold the following information
 * @author carlo
 *
 */

public class TunlResult {
	
	/*
	 * The tunl result holds the following information:
	 * - the number of the trial
	 * - the separation level of the trial
	 * - whether a trial's choice event was left or right of the sample
	 * - correct or not
	 * - the sample touch latency
	 * - the back beam latency
	 * - the choice latency
	 * - the reward collection latency
	 */
	
	private int trialCounter;
	
	private int correctionTrial;
	
	private int separationLevel;
	
	private String leftOrRightChoice;
	
	private int centerSample;
	
	private int correct;
	
	private double sampleLatency;
	
	private double backBeamLatency;
	
	private double choiceLatency;
	
	private double rewardCollectionLatency;
	
	private static String header = 
			"trial,"
			+ "correction_trial,"
			+ "separation,"
			+ "leftOrRight,"
			+ "center_sample,"
			+ "correct,"
			+ "sampleLatency,"
			+ "backBeamLatency,"
			+ "choiceLatency,"
			+ "rewardLatency";
	
	
			
	
	
	public TunlResult() {
		this.trialCounter = -1;
		this.setCorrectionTrial(-1);
		this.separationLevel = 0;
		this.leftOrRightChoice = "";
		this.centerSample = -1;
		this.correct = -1;
		this.sampleLatency = Double.NaN;
		this.backBeamLatency = Double.NaN;
		this.choiceLatency = Double.NaN;
		this.rewardCollectionLatency = Double.NaN;
	}

	@Override
	public String toString() {
		return
				trialCounter + "," +
				correctionTrial + "," +
				separationLevel + "," +
				leftOrRightChoice + "," +
				centerSample + "," +
				correct  + "," +
				sampleLatency  + "," +
				backBeamLatency  + "," +
				choiceLatency  + "," +
				rewardCollectionLatency;
	}

	public String getHeader() {
		return header;
	}

	public int getTrialCounter() {
		return trialCounter;
	}

	public void setTrialCounter(int trialCounter) {
		this.trialCounter = trialCounter;
	}

	public int getSeparationLevel() {
		return separationLevel;
	}

	public void setSeparationLevel(int separationLevel) {
		this.separationLevel = separationLevel;
	}

	public String getLeftOrRightChoice() {
		return leftOrRightChoice;
	}

	public void setLeftOrRightChoice(String leftOrRightChoice) {
		this.leftOrRightChoice = leftOrRightChoice;
	}

	public int getCorrect() {
		return correct;
	}

	public void setCorrect(int correct) {
		this.correct = correct;
	}

	public double getSampleLatency() {
		return sampleLatency;
	}

	public void setSampleLatency(double sampleLatency) {
		this.sampleLatency = sampleLatency;
	}

	public double getBackBeamLatency() {
		return backBeamLatency;
	}

	public void setBackBeamLatency(double backBeamLatency) {
		this.backBeamLatency = backBeamLatency;
	}

	public double getChoiceLatency() {
		return choiceLatency;
	}

	public void setChoiceLatency(double choiceLatency) {
		this.choiceLatency = choiceLatency;
	}

	public double getRewardCollectionLatency() {
		return rewardCollectionLatency;
	}

	public void setRewardCollectionLatency(double rewardCollectionLatency) {
		this.rewardCollectionLatency = rewardCollectionLatency;
	}

	public int getCorrectionTrial() {
		return correctionTrial;
	}

	public void setCorrectionTrial(int correctionTrial) {
		this.correctionTrial = correctionTrial;
	}

	public int getCenterSample() {
		return centerSample;
	}

	public void setCenterSample(int centerSample) {
		this.centerSample = centerSample;
	}

}
