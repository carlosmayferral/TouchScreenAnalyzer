package mPosnerAnalysis;

public class MPosnerResult {
	
	private static final String header = "Timestamp,"
			+ "Trial_Number,"
			+ "Trial_Type,"
			+ "Cue_Side,"
			+ "Cue_Validity,"
			+ "Cue_Brightness,"
			+ "Cue_Time,"
			+ "Hold_Time,"
			+ "Target_Time,"
			+ "Anticipation_Errors,"
			+ "Correct,"
			+ "Comission Error,"
			+ "Omission Error,"
			+ "Stimulus_Response_Latency,"
			+ "Reaction_Time,"
			+ "Movement_Time,"
			+ "Reward_Collection_Latency,"
			+ "Touches_During_ITI,"
			+ "Touchscreen_Error,"
			+ "Front_Beam_Breaks,"
			+ "Back_Beam_breaks,"
			+ "Tray_Beam_Breaks";
	
	public Integer getFrontBeamBreaks() {
		return frontBeamBreaks;
	}


	public void setFrontBeamBreaks(Integer frontBeamBreaks) {
		this.frontBeamBreaks = frontBeamBreaks;
	}


	public Integer getBackBeamBreaks() {
		return backBeamBreaks;
	}


	public void setBackBeamBreaks(Integer backBeamBreaks) {
		this.backBeamBreaks = backBeamBreaks;
	}


	public Integer getTrayBeamBreaks() {
		return trayBeamBreaks;
	}


	public void setTrayBeamBreaks(Integer trayBeamBreaks) {
		this.trayBeamBreaks = trayBeamBreaks;
	}


	private static final String resultTemplate = "%f,"
			+ "%d,"
			+ "%s,"
			+ "%s,"
			+ "%s,"
			+ "%f,"
			+ "%f,"
			+ "%f,"
			+ "%f,"
			+ "%d,"
			+ "%d,"
			+ "%d,"
			+ "%d,"
			+ "%f,"
			+ "%f,"
			+ "%f,"
			+ "%f,"
			+ "%d,"
			+ "%d,"
			+ "%d,"
			+ "%d,"
			+ "%d";
	
	private Double timestamp;
	private Integer trialNumber;
	private String trialType;
	private String cueSide;
	private String cueValidity;
	private Double cueBrightness;
	private Double cueTime;
	private Double holdTime;
	private Double targetTime;
	private Integer anticipationErrors;
	private Integer correct;
	private Integer comissionError;
	private Integer omissionError;
	private Double responseLatency;
	private Double reactionTime;
	private Double movementTime;
	private Double rewardCollectionLatency;
	private Integer touchesDuringITI;
	private Integer touchscreenError;
	private Integer frontBeamBreaks;
	private Integer backBeamBreaks;
	private Integer trayBeamBreaks;
	
	//Initialize defaults that are not obvious
	public MPosnerResult() {
		this.trialNumber = -1;
		this.correct = -1;
		this.comissionError = -1;
		this.omissionError = -1;
		this.anticipationErrors = -1;
		this.responseLatency = Double.NaN;
		this.reactionTime = Double.NaN;
		this.movementTime = Double.NaN;
		this.rewardCollectionLatency = Double.NaN;
		this.touchscreenError = -1;
		this.trayBeamBreaks = 0;
		this.frontBeamBreaks = 0;
		this.backBeamBreaks = 0;
	}


	public String getHeader() {
		return header;
	}
	
	public String toString() {
		return String.format(resultTemplate, 
				timestamp,
				trialNumber,
				trialType,
				cueSide,
				cueValidity,
				cueBrightness,
				cueTime,
				holdTime,
				targetTime,
				anticipationErrors,
				correct,
				comissionError,
				omissionError,
				responseLatency,
				reactionTime,
				movementTime,
				rewardCollectionLatency,
				touchesDuringITI,
				touchscreenError,
				frontBeamBreaks,
				backBeamBreaks,
				trayBeamBreaks
				);
	}


	public Double getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Double timestamp) {
		this.timestamp = timestamp;
	}


	public Integer getTrialNumber() {
		return trialNumber;
	}


	public void setTrialNumber(Integer trialNumber) {
		this.trialNumber = trialNumber;
	}


	public String getTrialType() {
		return trialType;
	}


	public void setTrialType(String trialType) {
		this.trialType = trialType;
	}


	public String getCueSide() {
		return cueSide;
	}


	public void setCueSide(String cueSide) {
		this.cueSide = cueSide;
	}


	public String getCueValidity() {
		return cueValidity;
	}


	public void setCueValidity(String cueValidity) {
		this.cueValidity = cueValidity;
	}


	public Double getCueBrightness() {
		return cueBrightness;
	}


	public void setCueBrightness(Double cueBrightness) {
		this.cueBrightness = cueBrightness;
	}


	public Double getCueTime() {
		return cueTime;
	}


	public void setCueTime(Double cueTime) {
		this.cueTime = cueTime;
	}


	public Double getHoldTime() {
		return holdTime;
	}


	public void setHoldTime(Double holdTime) {
		this.holdTime = holdTime;
	}


	public Double getTargetTime() {
		return targetTime;
	}


	public void setTargetTime(Double targetTime) {
		this.targetTime = targetTime;
	}


	public Integer getAnticipationErrors() {
		return anticipationErrors;
	}


	public void setAnticipationErrors(Integer anticipationErrors) {
		this.anticipationErrors = anticipationErrors;
	}


	public Integer getCorrect() {
		return correct;
	}


	public void setCorrect(Integer correct) {
		this.correct = correct;
	}


	public Integer getComissionError() {
		return comissionError;
	}


	public void setComissionError(Integer comissionError) {
		this.comissionError = comissionError;
	}


	public Integer getOmissionError() {
		return omissionError;
	}


	public void setOmissionError(Integer omissionError) {
		this.omissionError = omissionError;
	}


	public Double getResponseLatency() {
		return responseLatency;
	}


	public void setResponseLatency(Double responseLatency) {
		this.responseLatency = responseLatency;
	}


	public Double getReactionTime() {
		return reactionTime;
	}


	public void setReactionTime(Double reactionTime) {
		this.reactionTime = reactionTime;
	}


	public Double getMovementTime() {
		return movementTime;
	}


	public void setMovementTime(Double movementTime) {
		this.movementTime = movementTime;
	}


	public Double getRewardCollectionLatency() {
		return rewardCollectionLatency;
	}


	public void setRewardCollectionLatency(Double rewardCollectionLatency) {
		this.rewardCollectionLatency = rewardCollectionLatency;
	}


	public Integer getTouchscreenError() {
		return touchscreenError;
	}


	public void setTouchscreenError(Integer touchscreenError) {
		this.touchscreenError = touchscreenError;
	}


	public Integer getTouchesDuringITI() {
		return touchesDuringITI;
	}


	public void setTouchesDuringITI(Integer touchesDuringITI) {
		this.touchesDuringITI = touchesDuringITI;
	}
	
	

}
