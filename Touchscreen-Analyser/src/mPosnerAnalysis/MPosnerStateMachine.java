package mPosnerAnalysis;

public class MPosnerStateMachine {

	private int currentState;

	private String transitionHistory;

	private int anticipationErrors;
	
	//For stimulus latency
	private Double stimulusDisplayed;
	private Double stimulusResponseLatency;
	
	//For reaction time
	private Double targetDisplayed;
	private Double reactionTime;
	
	//For movement time
	private Double stimulusRealeased;
	private Double movementTime;
	
	//For reward collection latency
	private Double targetTouched;
	private Double rewardCollectionLatency;

	public MPosnerStateMachine() {
		this.currentState = -1;
		this.transitionHistory = "";
		this.anticipationErrors = 0;
		this.movementTime = Double.NaN;
		this.rewardCollectionLatency = Double.NaN;
		this.targetTouched = 0d;
	}
	
	public int getAnticipationErrors() {
		return anticipationErrors;
	}

	public void setStartingState(int group) {
		this.currentState = group;
		this.transitionHistory += group;
	}
	
	public String getTransitionHistory() {
		return this.transitionHistory;
	}
	
	public Double getStimulusResponseLatency() {
		return this.stimulusResponseLatency;
	}

	public void transition(int group, double timestamp) throws Exception {
		if (currentState == group) {
			return;
		}
		
		//System.out.println("Switching to group " + this.currentState + " to " + group + " at time " + timestamp);

		switch (currentState) {
		case 0:
			if (group == 1) {
				currentState = group;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 1:
			if (group == 22) {
				currentState = group;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 22:
			if (group == 23) {
				currentState = group;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 23:
			if (group == 24) {
				currentState = group;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 24:
			if (group == 2) {
				currentState = group;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 2:
			if (group == 3) {
				currentState = group;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 3:
			if (group == 4 || group == 17 || group == 39) {
				currentState = group;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 39:
			throw new Exception("Invalid schedule group transition");
		case 17:
			if (group == 4) {
				currentState = group;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 4:
			if (group == 5) {
				currentState = group;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 5:
			if (group == 15) {
				currentState = group;
				this.stimulusDisplayed = timestamp;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 15:
			if (group == 37 || group == 38 || group == 7) {
				currentState = group;
				stimulusResponseLatency = timestamp - stimulusDisplayed;
				//If entering group 7 this forms the first measurement of reaction time
				if(group == 7) {
					this.targetDisplayed = timestamp;
				}
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 38:
			if (group == 31) {
				currentState = group;
				//Every visit from 38 to 31 represents an anticipation error
				anticipationErrors++;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 31:
			if (group == 11 || group == 10) {
				currentState = group;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 11:
			if (group == 13) {
				currentState = group;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 13:
			if (group == 14) {
				currentState = group;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 14:
			if (group == 10) {
				currentState = group;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 10:
			if (group == 27) {
				currentState = group;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 27:
			if (group == 3 || group == 4) {
				currentState = group;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 37:
			if (group == 38 || group == 16) {
				currentState = group;
				
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 16:
			if (group == 38 || group == 7) {
				currentState = group;
				//If entering group 7 this forms the first measurement of reaction time
				if(group == 7) {
					this.targetDisplayed = timestamp;
				}
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 7:
			if (group == 8 || group == 25) {
				currentState = group;
				this.reactionTime = timestamp - this.targetDisplayed;
				//If switching from 7 to 25, this forms the first measurement of movement time
				if (group == 25) {
					this.stimulusRealeased = timestamp;
				}
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 8:
			if (group == 30) {
				currentState = group;
				//If switching from 8 to 30, this forms the first measurement of movement time
				this.stimulusRealeased = timestamp;
				
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 30:
			if (group == 9 || group == 32 || group == 33) {
				currentState = group;
				//if transitions to correct or incorrect, calculate movement time
				if (group == 9 || group == 32) {
					this.movementTime = timestamp - this.stimulusRealeased;
				}
				//if transition to 9, forms the first measurement of reward latency
				if (group == 9) {
					this.targetTouched = timestamp;
				}
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 25:
			if (group == 9 || group == 32 || group == 33) {
				currentState = group;
				//if transitions to correct or incorrect, calculate movement time
				if (group == 9 || group == 32) {
					this.movementTime = timestamp - this.stimulusRealeased;
				}
				//if transition to 9, forms the first measurement of reward latency
				if (group == 9) {
					this.targetTouched = timestamp;
				}
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 9:
			if (group == 35) {
				currentState = group;
				//always gives reward latency
				this.rewardCollectionLatency = timestamp - this.targetTouched;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 35:
			if (group == 10) {
				currentState = group;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 32:
			if (group == 11) {
				currentState = group;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		case 33:
			if (group == 11) {
				currentState = group;
			} else {
				throw new Exception("Invalid schedule group transition");
			}
			break;
		default:
			throw new Exception("Invalid schedule state encountered");
		}
		transitionHistory += ","+ group;
	}

	public Double getReactionTime() {
		return this.reactionTime;
	}

	public Double getMovementTime() {
		return this.movementTime;
	}

	public Double getRewardCollectionLatency() {
		return this.rewardCollectionLatency;
	}

}
