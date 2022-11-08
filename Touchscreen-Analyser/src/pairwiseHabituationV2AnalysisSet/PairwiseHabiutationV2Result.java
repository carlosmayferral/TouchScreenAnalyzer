package pairwiseHabituationV2AnalysisSet;

/**
 * Represents the results from a single pairwise habitation V2 trial.
 * @author Carlos May
 *
 */
public class PairwiseHabiutationV2Result {
	
	/**
	 * The header to go at the start of the result sheet for the pairwise habituation v2 trial type.
	 */
	private static final String header = "Timestamp,"
			+ "Trial_Number,"
			+ "Prime_Feed_Time_Milliseconds,"
			+ "Pulse_Feed_Time_Milliseconds,"
			+ "Feeding_Latency,"
			+ "Feeding_time,"
			+ "Effective_Delay_Time,"
			+ "Delay_Period_Tray_Beams,"
			+ "Delay_Period_Front_Beams,"
			+ "Delay_Period_Back_Beams,"
			+ "Total_Tray_Beams,"
			+ "Total_Front_Beams,"
			+ "Total_Back_Beams";
	
	private static final String resultTemplate = "%f,"
			+ "%d,"
			+ "%d,"
			+ "%d,"
			+ "%f,"
			+ "%f,"
			+ "%f,"
			+ "%d,"
			+ "%d,"
			+ "%d,"
			+ "%d,"
			+ "%d,"
			+ "%d";
	
	private Double timestamp;
	private Integer trialNumber;
	private Integer pulseFeedTimeMs;
	private Integer primeFeedTimeMs;
	private Double feedingLatency;
	private Double feedingTime;
	private Double effectiveDelayTime;
	private Integer delayPeriodTrayBeams;
	private Integer delayPeriodFrontBeams;
	private Integer delayPeriodBackBeams;
	private Integer totalTrayBeams;
	private Integer totalFrontBeams;
	private Integer totalBackBeams;
	
	public PairwiseHabiutationV2Result() {
		this.timestamp = null;
		this.trialNumber = null;
		this.pulseFeedTimeMs = null;
		this.primeFeedTimeMs = null;
		this.feedingLatency = Double.NaN;
		this.feedingTime = Double.NaN;
		this.effectiveDelayTime = Double.NaN;
		this.delayPeriodTrayBeams = 0;
		this.delayPeriodFrontBeams = 0;
		this.delayPeriodBackBeams = 0;
		this.totalTrayBeams = 0;
		this.totalFrontBeams = 0;
		this.totalBackBeams = 0;
	}
	

	public String getHeader() {
		return header;
	}
	
	public String toString() {
		return String.format(resultTemplate, 
				timestamp, 
				trialNumber, 
				primeFeedTimeMs, 
				pulseFeedTimeMs, 
				feedingLatency,
				feedingTime,
				effectiveDelayTime,
				delayPeriodTrayBeams,
				delayPeriodFrontBeams,
				delayPeriodBackBeams,
				totalTrayBeams,
				totalFrontBeams,
				totalBackBeams);
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


	public Integer getPulseFeedTimeMs() {
		return pulseFeedTimeMs;
	}


	public void setPulseFeedTimeMs(Integer pulseFeedTimeMs) {
		this.pulseFeedTimeMs = pulseFeedTimeMs;
	}


	public Integer getPrimeFeedTimeMs() {
		return primeFeedTimeMs;
	}


	public void setPrimeFeedTimeMs(Integer primeFeedTimeMs) {
		this.primeFeedTimeMs = primeFeedTimeMs;
	}


	public Double getFeedingLatency() {
		return feedingLatency;
	}


	public void setFeedingLatency(Double feedingLatency) {
		this.feedingLatency = feedingLatency;
	}


	public Double getFeedingTime() {
		return feedingTime;
	}


	public void setFeedingTime(Double feedingTime) {
		this.feedingTime = feedingTime;
	}


	public Double getEffectiveDelayTime() {
		return effectiveDelayTime;
	}


	public void setEffectiveDelayTime(Double effectiveDelayTime) {
		this.effectiveDelayTime = effectiveDelayTime;
	}


	public Integer getDelayPeriodTrayBeams() {
		return delayPeriodTrayBeams;
	}


	public void setDelayPeriodTrayBeams(Integer delayPeriodTrayBeams) {
		this.delayPeriodTrayBeams = delayPeriodTrayBeams;
	}


	public Integer getDelayPeriodFrontBeams() {
		return delayPeriodFrontBeams;
	}


	public void setDelayPeriodFrontBeams(Integer delayPeriodFrontBeams) {
		this.delayPeriodFrontBeams = delayPeriodFrontBeams;
	}


	public Integer getDelayPeriodBackBeams() {
		return delayPeriodBackBeams;
	}


	public void setDelayPeriodBackBeams(Integer delayPeriodBackBeams) {
		this.delayPeriodBackBeams = delayPeriodBackBeams;
	}


	public Integer getTotalTrayBeams() {
		return totalTrayBeams;
	}


	public void setTotalTrayBeams(Integer totalTrayBeams) {
		this.totalTrayBeams = totalTrayBeams;
	}


	public Integer getTotalFrontBeams() {
		return totalFrontBeams;
	}


	public void setTotalFrontBeams(Integer totalFrontBeams) {
		this.totalFrontBeams = totalFrontBeams;
	}


	public Integer getTotalBackBeams() {
		return totalBackBeams;
	}


	public void setTotalBackBeams(Integer totalBackBeams) {
		this.totalBackBeams = totalBackBeams;
	}

}
