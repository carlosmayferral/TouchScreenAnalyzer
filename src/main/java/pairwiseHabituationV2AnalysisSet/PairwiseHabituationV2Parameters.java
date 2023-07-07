package pairwiseHabituationV2AnalysisSet;

import dataModels.SessionParameters;

/**
 * A subclass of SessionParameters that represents the parameters for the pairwise habituation V2 schedule.
 * @author Carlos May
 *
 */
public class PairwiseHabituationV2Parameters extends SessionParameters{

	/**
	 * The delay between stopping feeding and the next feed.
	 */
	private Double delayTime;
	
	/**
	 * The number of milliseconds the feeder pulses when a trial is rewarded (excluding the first trial).
	 */
	private Integer feederPulseTimeMilliseconds;
	
	/**
	 * The number of milliseconds the feeder pulses for the very first reward in a session.
	 */
	private Integer primeFeedPulseTimeMilliseconds;
	
	
	
	public Double getDelayTime() {
		return delayTime;
	}
	public void setDelayTime(Double delayTime) {
		this.delayTime = delayTime;
	}
	public Integer getFeederPulseTimeMilliseconds() {
		return feederPulseTimeMilliseconds;
	}
	public void setFeederPulseTimeMilliseconds(Integer feederPulseTimeMilliseconds) {
		this.feederPulseTimeMilliseconds = feederPulseTimeMilliseconds;
	}
	public Integer getPrimeFeedPulseTimeMilliseconds() {
		return primeFeedPulseTimeMilliseconds;
	}
	public void setPrimeFeedPulseTimeMilliseconds(Integer primeFeedPulseTimeMilliseconds) {
		this.primeFeedPulseTimeMilliseconds = primeFeedPulseTimeMilliseconds;
	}
	
}
