package TinaAnalysis;

public class PostProcessedResult {

	private String original;
	
	private String animalId;
	
	private String sessionID;
	
	private int anticipationError;
	
	private int errorTrainPreTrial;
	
	private int errorTrainPostTrial;
	
	private float percentageAnticipationErrors;
	
	private float percentageAnticipationErrorsPerMouse;

	private int touchscreenError;
	
	public PostProcessedResult(String original) {
		this.original = original;
		this.animalId = original.split(",")[1];
		this.sessionID = original.split(",")[6];
		this.anticipationError = Integer.parseInt(original.split(",")[16]);
		this.touchscreenError = Integer.parseInt(original.split(",")[21]);
		this.errorTrainPostTrial = -1;
		this.errorTrainPostTrial = -1;
		this.percentageAnticipationErrorsPerMouse = Float.NaN;
	}
	
	public boolean isSameSession(PostProcessedResult other) {
		if (this.animalId.equals(other.getAnimalId()) 
				&&
				this.sessionID.equals(other.getSessionID())) {
			return true;
		}
		else return false;
	}

	/**
	 * @param errorTrainPreTrial the errorTrainPreTrial to set
	 */
	public void setErrorTrainPreTrial(int errorTrainPreTrial) {
		this.errorTrainPreTrial = errorTrainPreTrial;
	}

	/**
	 * @param errorTrainPostTrial the errorTrainPostTrial to set
	 */
	public void setErrorTrainPostTrial(int errorTrainPostTrial) {
		this.errorTrainPostTrial = errorTrainPostTrial;
	}

	/**
	 * @param percentageAnticipationErrors the percentageAnticipationErrors to set
	 */
	public void setPercentageAnticipationErrors(float percentageAnticipationErrors) {
		this.percentageAnticipationErrors = percentageAnticipationErrors;
	}
	
	public void setPercentageAnticipationErrorsPerMouse(float percentageAnticipationErrorsPerMouse) {
		this.percentageAnticipationErrorsPerMouse = percentageAnticipationErrorsPerMouse;
	}

	/**
	 * @return the animalId
	 */
	public String getAnimalId() {
		return animalId;
	}

	/**
	 * @return the sessionID
	 */
	public String getSessionID() {
		return sessionID;
	}
	
	public String generateResultString() {
		return original 
				+ "," + this.errorTrainPreTrial
				+ "," +  this.errorTrainPostTrial
				+ "," + this.percentageAnticipationErrors
				+ "," + this.percentageAnticipationErrorsPerMouse;
	}

	/**
	 * @return the anticipationError
	 */
	public int getAnticipationError() {
		return anticipationError;
	}

	public int getIfTouchscreenError() {
		return touchscreenError;
	}
	
	
	
}
