package fiveChoiceAnalysis.AnalyzerHelpers;

import dataModels.Event;

public class FiveChoiceTouchRecorder {
	
	private int initiationPeriodTouches;
	
	private int postStimulusTouches;
	
	private int itiTouches;
	
	private enum State {
		INITIATION_PERIOD,
		DELAY_PERIOD,
		STIMULUS_PERIOD,
		POST_STIMULUS_PERIOD,
		ITI
	}
	
	private State state;
	
	public FiveChoiceTouchRecorder() {
		state = State.INITIATION_PERIOD;
		this.initiationPeriodTouches = 0;
		this.postStimulusTouches = 0;
		this.itiTouches = 0;
	}
	
	public void scanTouches(Event[] events) {
		
		for (Event event : events) {
			//state change logic
			switch (event.getItem_Name()) {
			case "Start Delay":
			case "TrayReleaseNoted":
				state = State.DELAY_PERIOD;
				break;
			case "Start Trial":
				state = State.STIMULUS_PERIOD;
				break;
			case "Correct Response":
			case "Incorrect Response HL on":
			case "Omission Error HL on":
			case "Premature Punishment HL on":
				state = State.POST_STIMULUS_PERIOD;
				break;
			case "ITI_Timer":
				state = State.ITI;
				break;
			}
			//event counting
			if (event.getEvent_Name().equals("Touch Down Event")) {
				switch (state) {
				case INITIATION_PERIOD:
					this.initiationPeriodTouches++;
					break;
				case DELAY_PERIOD:
					break;
				case STIMULUS_PERIOD:
					break;
				case POST_STIMULUS_PERIOD:
					this.postStimulusTouches++;
					break;
				case ITI:
					this.itiTouches++;
					break;
				default:
					break;
				}
			}
		}
	}

	/**
	 * @return the initiationPeriodTouches
	 */
	public int getInitiationPeriodTouches() {
		return initiationPeriodTouches;
	}

	/**
	 * @return the postStimulusTouches
	 */
	public int getPostStimulusTouches() {
		return postStimulusTouches;
	}


	/**
	 * @return the itiTouches
	 */
	public int getItiTouches() {
		return itiTouches;
	}
	
}
