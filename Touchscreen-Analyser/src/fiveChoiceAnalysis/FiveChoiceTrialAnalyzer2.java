package fiveChoiceAnalysis;

import analysisSets.BeamBreakCounter;
import analysisSets.ITrialAnalyzer;
import dataModels.Event;
import dataModels.MetaData;
import dataModels.Result;
import dataModels.SessionInfo;
import dataModels.SessionParameters;
import dataModels.Trial;
import fiveChoiceAnalysis.AnalyzerHelpers.FiveChoiceTouchRecorder;
import fiveChoiceAnalysis.AnalyzerHelpers.TrialSettingsReader;

public class FiveChoiceTrialAnalyzer2 implements ITrialAnalyzer {
	
	private float bufferedStimulusDuration;
	
	private float bufferedStimulusDelay;
	
	private float bufferedStimulusBrightness;
	
	private int delayBlock;
	
	public FiveChoiceTrialAnalyzer2() {
		this.bufferedStimulusBrightness = Float.NaN;
		this.bufferedStimulusDelay = Float.NaN;
		this.bufferedStimulusDuration = Float.NaN;
		this.delayBlock = -1;
	}

	public FiveChoiceResult generateResult(Trial trial, int counter, SessionInfo sessionInfo) {
		FiveChoiceResult result = new FiveChoiceResult();
		Event[] events = trial.copyEventsAsArray();
		
		//TimeStamp
		result.setTimeStamp(events[0].getEvent_Time());
		
		//Trial Number
		result.setTrialNumber(counter);
		
		//Initialize settings structure
		TrialSettingsReader settings =new TrialSettingsReader();
		settings.scanEvents(events);
		
		//Stimulus duration
		if (!((Float)settings.getStimulusDuration()).isNaN()) {
			result.setStimulusDuration(settings.getStimulusDuration());
			bufferedStimulusDuration = settings.getStimulusDuration();
		} else result.setStimulusDuration(bufferedStimulusDuration);
		
		//Stimulus delay
		if (!((Float)settings.getStimulusDelay()).isNaN()) {
			result.setStimulusDelay(settings.getStimulusDelay());
			bufferedStimulusDelay = settings.getStimulusDelay();
		} else {
			result.setStimulusDelay(bufferedStimulusDelay);
		}
		
		//Stimulus brightness
		if (!((Float)settings.getStimulusBrightness()).isNaN()) {
			result.setStimulusBrightness(settings.getStimulusBrightness());
			bufferedStimulusBrightness = settings.getStimulusBrightness();
		} else {
			result.setStimulusBrightness(bufferedStimulusBrightness);
		}
		
		result.setTargetPosition(settings.getTargetLocation());
		
		result.setDelayBlock(this.delayBlock);
		
		result.setPremature(this.determinePrematureTouch(events));
		
		result.setResponse(this.determineIfResponded(events));
		
		result.setCorrect(this.determineIfCorrectResponse(events));
		
		result.setInitiationLatency(this.calculateInitiationLatency(events));
		
		result.setResponseLatency(this.calculateResponseLatency(events));
		
		result.setRewardCollectionLatency(this.calculateRewardLatency(events));
		//Initialize touch and latency scanner
//		FiveChoiceTouchRecorder touchRecorder = new FiveChoiceTouchRecorder();
//		touchRecorder.scanTouches(events, settings.getTargetLocation());
//		
//		result.setInitiationLatency(touchRecorder.getInitiation_latency());
//		
//		result.setResponseLatency(touchRecorder.getResponse_latency());
//		
//		result.setRewardCollectionLatency(touchRecorder.getResponse_to_tray_latency());
//		
//		result.setInitiationPeriodTouches(touchRecorder.getInitiationPeriodTouches());
//		
//		result.setTotalPostStimulusPeriodTouches(touchRecorder.getPostStimulusTouches());
//		
//		result.setPerseverativeTouches(touchRecorder.getPerseverativeTouches());
//		
//		result.setTotalTouchesDuringITI(touchRecorder.getItiTouches());
		
		BeamBreakCounter beams = new BeamBreakCounter();
		
		beams.countBeamBreaks(events);
		
		result.setFrontBeamBreaks(beams.getFrontBeamBreaks());
		
		result.setBackBeamBreaks(beams.getBackBeamBreaks());
		
		result.setTrayBeamBreaks(beams.getTrayBeamBreaks());
		
		//Extra touches
		FiveChoiceTouchRecorder touches = new FiveChoiceTouchRecorder();
		touches.scanTouches(events);
		
		result.setInitiationPeriodTouches(touches.getInitiationPeriodTouches());
		
		result.setTotalPostStimulusPeriodTouches(touches.getPostStimulusTouches());
		
		result.setTotalTouchesDuringITI(touches.getItiTouches());
		
		result.setTimeInTrial(events[events.length-1].getEvent_Time() - events[0].getEvent_Time());
		
		//ERROR CHECKING HERE!!!!
		
		//if trial is premature, it can't be a response or correct
		if (result.isPremature() && (result.isResponse() || result.isCorrect())) {
			System.out.println("Trial inconsistency detected, trial " + counter + " in  session " + sessionInfo.getSessionId());
		}
		
		//if trial is responded to, it cant not have a response latency
		if (result.isResponse() && ((Float)result.getResponseLatency()).isNaN()){
			System.out.println("Trial inconsistency detected, trial " + counter + " in  session " + sessionInfo.getSessionId());
		}
		
		//if trial is correct, it must be a response and it must have a collection latency
		if (result.isCorrect() && ((!result.isResponse()) || ((Float)result.getRewardCollectionLatency()).isNaN()))
			System.out.println("Trial inconsistency detected, trial " + counter + " in  session " + sessionInfo.getSessionId());
		return result;
	}

	private float calculateRewardLatency(Event[] events) {
		boolean trialStarted = false;
		boolean feederActivated = false;
		float feederActivationTime = Float.NaN;
		for(Event event : events) {
			if (event.getItem_Name().equals("Start Trial")) {
				trialStarted = true;
			}
			else if (event.getItem_Name().equals("Feeder #1")
					&&
					trialStarted) {
				feederActivated = true;
				feederActivationTime = event.getEvent_Time();
			}
			else if (event.getEvent_Name().contains("Input Transition") 
					&&
					event.getItem_Name().equals("Tray #1")
					&&
					feederActivated) {
				return event.getEvent_Time() - feederActivationTime;
			}
			else if (event.getEvent_Name().equals("Timer Event")
					&&
					event.getItem_Name().equals("TimeOut_Timer")) {
				break;
			}
		}
		return Float.NaN;
	}

	//To calculate response latency, measure time from image presentation to touch event on valid area of screen,
	//stop the search if an omission is encountered
	private float calculateResponseLatency(Event[] events) {
		boolean imageDisplayed = false;
		float imageDisplayTime = Float.NaN;
		for(Event event : events) {
			if(event.getItem_Name().equals("Start Trial")) {
				imageDisplayed = true;
				imageDisplayTime = event.getEvent_Time();
			}
			else if (event.getEvent_Name().equals("Touch Down Event")
					&&
					event.getArgumentValue(1) > 0
					&&
					event.getArgumentValue(1) < 6
					&&
					imageDisplayed) {
				return event.getEvent_Time() - imageDisplayTime;
			}
			else if (event.getItem_Name().contains("Omission Error")) {
				return Float.NaN;
			}
		}
		return Float.NaN;
	}

	//By default, initiation latency is NaN. from the first event to when the delay starts
	private float calculateInitiationLatency(Event[] events) {
		float startTime = events[0].getEvent_Time();
		for (Event event : events) {
			if (event.getItem_Name().equals("Start Delay")
					||
					event.getItem_Name().equals("TrayReleaseNoted")) {
				return event.getEvent_Time() - startTime;
			}
		}
		return Float.NaN;
	}

	/**
	 * To safely determine if a trial was correctly responded to:
	 * >>Correct event must be encountered
	 * >>Food pump was activated
	 * >>There is no incorrect, premature, or omission events
	 * @param events
	 * @return
	 */
	private boolean determineIfCorrectResponse(Event[] events) {
		boolean correctEventEncountered = false;
		boolean foodpumpActivated = false;
		for (Event event : events) {
			if (event.getItem_Name().contains("Premature Punishment")
				||
				event.getItem_Name().contains("Incorrect Response HL")
				||
				event.getItem_Name().contains("Omission Error")) {
				return false;
			}
			else if (event.getItem_Name().contains("Correct Response")) {
				correctEventEncountered = true;
			}
			else if (event.getItem_Name().equals("Feeder #1")) {
				foodpumpActivated = true;
			}
		}
		if (correctEventEncountered != foodpumpActivated) {
			System.out.println("DEBUG: correct and food delivery don't add up");
		}
		return (correctEventEncountered && foodpumpActivated);
	}

	/**
	 * Safely determine if trial was premature by 
	 * >> Looking for the premature punishment event
	 * >> Images should never be displayed (trial should never start)
	 * >> Timeout start event should be encountered
	 * @param events
	 * @return Whether the trial was premature or not.
	 */
	private boolean determinePrematureTouch(Event[] events) {
		boolean prematureEventRecorded = false;
		boolean timeOutStartEncountered = false;
		for (Event event : events) {
			if (event.getItem_Name().contains("Premature Punishment")) {
				prematureEventRecorded = true;
			}
			//End search condition
			else if (event.getItem_Name().equals("Start Trial")) {
				return false;
			}
			//End search confirmation condition
			else if (event.getEvent_Name().equals("Timer Event")
					&&
					event.getItem_Name().equals("TimeOut_Timer")) {
				timeOutStartEncountered = true;
				break;
			}
		}
		return (prematureEventRecorded && timeOutStartEncountered) ? true : false;
	}
	/**
	 * Safely determine if there was a response by looking for 
	 * >> A correct or incorrect event
	 * >> No omission events
	 * >> A touch event after trial has started
	 * @param events
	 * @return
	 */
	private boolean determineIfResponded(Event[] events) {
		boolean trialStarted = false;
		boolean trialOutcomeEventRecorded = false;
		boolean touchAfterTrialStart = false;
		
		for (Event event : events) {
			if (event.getItem_Name().equals("Start Trial")) {
				trialStarted = true;
			}
			else if (event.getItem_Name().contains("Omission Error")) {
				return false;
			}
			else if (event.getEvent_Name().equals("Touch Down Event")
					&&
					event.getArgumentValue(1) > 0
					&&
					event.getArgumentValue(1) < 6
					&&
					trialStarted) {
				touchAfterTrialStart = true;
			}
			else if (event.getItem_Name().contains("Correct Response") 
					||
					event.getItem_Name().contains("Incorrect Response HL on")) {
				trialOutcomeEventRecorded = true;
			}
		}
		
		return (trialOutcomeEventRecorded && touchAfterTrialStart);
	}

	@Override
	public void setParameters(SessionParameters rawParameters) {
		FiveChoiceSessionParameters parameters;
		parameters = (FiveChoiceSessionParameters) rawParameters;
		this.bufferedStimulusBrightness = parameters.getStimulusBrightness();
		this.bufferedStimulusDelay = parameters.getStimulusDelay();
		this.bufferedStimulusDuration = parameters.getStimulusDuration();
		this.delayBlock = parameters.getDelayBlock();
	}

	@Override
	public Result analyzeTrial(Trial trial, int counter, SessionInfo sessionInfo, MetaData metaData) {
		FiveChoiceResult result = this.generateResult(trial,counter,sessionInfo);
		return new Result(sessionInfo, metaData, result.toString(), result.getHeader());
	}

}
