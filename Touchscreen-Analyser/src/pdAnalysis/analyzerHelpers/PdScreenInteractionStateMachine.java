package pdAnalysis.analyzerHelpers;

import dataModels.Event;

public class PdScreenInteractionStateMachine {
	
	
	private float initiationLatency;
	
	private float headWithdrawalTime;
	
	private float responseLatency;
	
	private int correct;
	
	private int correctAccordingToEvent;
	
	private float rewardCollectionLatency;
	
	private float feedingTime;
	
	private float totalWastedTime;
	
	private int correctLocation;
	
	private int initiationPeriodTouches;
	
	private int postStimulusTouches;
	
	private int itiTouches;
	
	private enum TrialState{
		INITIATION_PERIOD,
		INITIATING,
		RESPONSE_PERIOD,
		REWARD_PERIOD,
		CONSUMING_REWARD,
		TIMEOUT_PERIOD,
		ITI
	}
	
	private TrialState state;
	
	public PdScreenInteractionStateMachine() {
		initiationLatency = Float.NaN;
		headWithdrawalTime = Float.NaN;
		responseLatency = Float.NaN;
		correct = -1;
		rewardCollectionLatency = Float.NaN;
		feedingTime = Float.NaN;
		state = TrialState.INITIATION_PERIOD;
		correctLocation = -1;
		totalWastedTime = Float.NaN;
		setCorrectAccordingToEvent(0);
	}
	
	public void scanEvents(Event[] events, String sPlusId) {
		
		float referenceTime = events[0].getEvent_Time();
		
		for (Event event : events) {
			//State transition logic
			switch(state) {
			case INITIATION_PERIOD:
				//Tray beam gets triggered
				if (event.getItem_Name().equals("Tray #1")) {
					//Animal enters
					if (event.getEvent_Name().equals("Input Transition On Event")) {
						this.initiationLatency = event.getEvent_Time() - referenceTime;
						this.state = TrialState.INITIATING;
						referenceTime = event.getEvent_Time();
					}
					//Animal exits in initiation period
					else {
						this.initiationLatency = 0;
						this.headWithdrawalTime = event.getEvent_Time() - referenceTime;
						this.state = TrialState.RESPONSE_PERIOD;
					}
				}
				break;
			case INITIATING:
				if (event.getItem_Name().equals("Tray #1")) {
					this.headWithdrawalTime = event.getEvent_Time() - referenceTime;
					this.state = TrialState.RESPONSE_PERIOD;
				}
				break;
			case RESPONSE_PERIOD:
				//Check where correct image was displayed
				if (event.getEvent_Name().equals("Whisker - Display Image") 
						&&
						event.getArgumentName(2).equals(sPlusId)) {
					correctLocation = (int)event.getArgumentValue(1);
					referenceTime = event.getEvent_Time();
				}
				if (event.getEvent_Name().equals("Touch Down Event")) {
					if (event.getArgumentValue(1) == correctLocation) {
						this.correct = 1;
						this.state = TrialState.REWARD_PERIOD;
					}
					//Special case where they touch outside the bounds of the screen
					else if((int)event.getArgumentValue(1) == 0){
						continue;
					}
					else {
						this.correct = 0;
						this.state = TrialState.TIMEOUT_PERIOD;
					}
					this.responseLatency = event.getEvent_Time() - referenceTime;
					referenceTime = event.getEvent_Time();
				}
				break;
			case REWARD_PERIOD:
				//If mouse triggers tray beam
				if (event.getItem_Name().equals("Tray #1")) {
					this.rewardCollectionLatency = event.getEvent_Time() - referenceTime;
					this.state = TrialState.CONSUMING_REWARD;
					referenceTime = event.getEvent_Time();
				}
				break;
			case CONSUMING_REWARD:
				//If mouse triggers tray beam
				if(event.getItem_Name().equals("Tray #1")) {
					this.feedingTime = event.getEvent_Time() - referenceTime;
					this.state = TrialState.ITI;
				}
				break;
			case TIMEOUT_PERIOD:
				//If ITI starts
				if (event.getEvent_Name().equals("Timer Event") 
						&&
						event.getItem_Name().equals("ITI_Timer")) {
					this.state = TrialState.ITI;
				}
				break;
			case ITI:
				break;
			}
			
			if (event.getEvent_Name().equals("Touch Down Event")) {
				switch(state) {
				case INITIATION_PERIOD:
					this.initiationPeriodTouches++;
					break;
				case RESPONSE_PERIOD:
					break;
				case REWARD_PERIOD:
					this.postStimulusTouches++;
					break;
				case TIMEOUT_PERIOD:
					this.postStimulusTouches++;
					break;
				case CONSUMING_REWARD:
					this.itiTouches++;
					break;
				case ITI:
					this.itiTouches++;
					break;
					default:
				}
			}
			
			if (event.getItem_Name().equals("Correct")) {
				this.setCorrectAccordingToEvent(1);
			}
			
			//feeding time does not affect passage of time
			totalWastedTime = this.initiationLatency + this.headWithdrawalTime + 
					this.responseLatency + this.rewardCollectionLatency;
			
		}
		
		
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
	 * @return the totalTime
	 */
	public float getTotalTime() {
		return totalWastedTime;
	}

	/**
	 * @param totalTime the totalTime to set
	 */
	public void setTotalTime(float totalTime) {
		this.totalWastedTime = totalTime;
	}

	/**
	 * @return the correctLocation
	 */
	public int getCorrectLocation() {
		return correctLocation;
	}

	/**
	 * @param correctLocation the correctLocation to set
	 */
	public void setCorrectLocation(int correctLocation) {
		this.correctLocation = correctLocation;
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
	 * @return the iniTouches
	 */
	public int getIniTouches() {
		return itiTouches;
	}

	/**
	 * @param iniTouches the iniTouches to set
	 */
	public void setIniTouches(int iniTouches) {
		this.itiTouches = iniTouches;
	}

	/**
	 * @return the correctAccordingToEvent
	 */
	public int getCorrectAccordingToEvent() {
		return correctAccordingToEvent;
	}

	/**
	 * @param correctAccordingToEvent the correctAccordingToEvent to set
	 */
	public void setCorrectAccordingToEvent(int correctAccordingToEvent) {
		this.correctAccordingToEvent = correctAccordingToEvent;
	}


	

}
