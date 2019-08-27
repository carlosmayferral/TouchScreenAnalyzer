package pdAnalysisOtago;

public class PdOtagoResult {
	
	private int trialNumber;
	
	private float timeStamp;
	
	private int correctionTrial;
	
	private int correct;
	
	public PdOtagoResult() {
		this.timeStamp = Float.NaN;
		this.trialNumber = -1;
		this.correct = -1;
		this.correctionTrial = -1;
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

	public int getCorrectionTrial() {
		return correctionTrial;
	}

	public void setCorrectionTrial(int correctionTrial) {
		this.correctionTrial = correctionTrial;
	}

	public int getCorrect() {
		return correct;
	}

	public void setCorrect(int correct) {
		this.correct = correct;
	}

	public String getHeader() {
		// TODO Auto-generated method stub
		return "Trial_Number,"
				+ "TimeStamp,"
				+ "Correction_Trial,"
				+ "Correct_Response";
	}
	
	@Override
	public String toString() {
		return
				trialNumber + "," +
				timeStamp + "," +
				correctionTrial + "," +
				correct;
	}

}
