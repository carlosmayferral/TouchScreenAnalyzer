package fiveChoiceAnalysis.AnalyzerHelpers;

import dataModels.Event;

public class FiveChoiceTouchRecorder {

	private boolean premature;
	
	private boolean responded;
	
	private boolean correct;
	
	private int target_location;
	
	private float initiation_latency;
	
	private float response_latency;
	
	private float response_to_tray_latency;
	
	private boolean firstTrayEntryDetected;
	
	private int initiationPeriodTouches;
	
	private int postStimulusTouches;
	
	private int attemptedLocation;
	
	private int perseverativeTouches;
	
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
		target_location = -1;
		this.initiation_latency = Float.NaN;
		this.response_latency = Float.NaN;
		this.response_to_tray_latency = Float.NaN;
		this.initiationPeriodTouches = 0;
		this.postStimulusTouches = 0;
		this.attemptedLocation = -1;
		this.perseverativeTouches = 0;
		this.itiTouches = 0;
	}
	
	public void scanTouches(Event[] events, int targetLocation) {
		target_location = targetLocation;
		
		float timeTrialStarted = events[0].getEvent_Time();
		
		float timeImageWasDisplayed = Float.NaN;
		
		float timePostStimulusPeriodWasEntered = Float.NaN;
		
		for (Event event : events) {
			//state changes first
			switch (event.getItem_Name()) {
			case "Start Delay":
			case "TrayReleaseNoted":
				state = State.DELAY_PERIOD;
				this.initiation_latency = event.getEvent_Time() - timeTrialStarted;
				break;
			case "Start Trial":
				state = State.STIMULUS_PERIOD;
				timeImageWasDisplayed = event.getEvent_Time();
				break;
			case "Omission Error HL on":
				state = State.POST_STIMULUS_PERIOD;
				timePostStimulusPeriodWasEntered = event.getEvent_Time();
				break;
			case "ITI_Timer":
				state = State.ITI;
			}
			//event counting
			if (event.getEvent_Name().equals("Touch Down Event")) {
				switch (state) {
				case INITIATION_PERIOD:
					this.initiationPeriodTouches++;
					break;
				case DELAY_PERIOD:
					premature = true;
					responded = false;
					state = State.POST_STIMULUS_PERIOD;
					timePostStimulusPeriodWasEntered = event.getEvent_Time();
					break;
				case STIMULUS_PERIOD:
					responded = true;
					this.response_latency = event.getEvent_Time() - timeImageWasDisplayed;
					if (event.getArgumentValue(1) == target_location) {
						correct = true;
					}
					this.attemptedLocation = (int)event.getArgumentValue(1);
					state = State.POST_STIMULUS_PERIOD;
					timePostStimulusPeriodWasEntered = event.getEvent_Time();
					break;
				case POST_STIMULUS_PERIOD:
					if (event.getArgumentValue(1) == this.attemptedLocation) {
						this.perseverativeTouches++;
					}
					this.postStimulusTouches++;
					break;
				case ITI:
					this.itiTouches++;
					break;
				default:
					break;
				}
			}
			if (event.getItem_Name().equals("Tray #1")) {
				switch (state) {
				case POST_STIMULUS_PERIOD:
					//calculate reward latency
					if(!((Float)timePostStimulusPeriodWasEntered).isNaN()
							&&
					!this.firstTrayEntryDetected) {
						this.response_to_tray_latency = event.getEvent_Time() - timePostStimulusPeriodWasEntered;
						this.firstTrayEntryDetected = true;
					}
					break;
				default:
					break;
				}
			}
		}
	}

	/**
	 * @return the trialIsPremature
	 */
	public boolean isTrialPremature() {
		return premature;
	}

	/**
	 * @return the responded
	 */
	public boolean isResponded() {
		return responded;
	}

	/**
	 * @return the correct
	 */
	public boolean isCorrect() {
		return correct;
	}

	/**
	 * @return the initiation_latency
	 */
	public float getInitiation_latency() {
		return initiation_latency;
	}

	/**
	 * @return the response_latency
	 */
	public float getResponse_latency() {
		return response_latency;
	}

	/**
	 * @return the response_to_tray_latency
	 */
	public float getResponse_to_tray_latency() {
		return response_to_tray_latency;
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
	 * @return the perseverativeTouches
	 */
	public int getPerseverativeTouches() {
		return perseverativeTouches;
	}

	/**
	 * @return the itiTouches
	 */
	public int getItiTouches() {
		return itiTouches;
	}
	
}
