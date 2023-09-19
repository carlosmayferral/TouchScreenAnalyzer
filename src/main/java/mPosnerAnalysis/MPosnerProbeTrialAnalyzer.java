package mPosnerAnalysis;

import analysisSets.ITrialAnalyzer;
import dataModels.Event;
import dataModels.MetaData;
import dataModels.Result;
import dataModels.SessionInfo;
import dataModels.SessionParameters;
import dataModels.Trial;
import pairwiseHabituationV2AnalysisSet.PairwiseHabiutationV2Result;

import java.util.List;

public class MPosnerProbeTrialAnalyzer implements ITrialAnalyzer {

	private static final Double DEFAULT_BRIGHTNESS = 100d;
	// From parameters
	private PosnerTrialType trialType;
	private Double cueTime;
	private Double first_targetTime;
	private Double first_holdTime;

	private static final Object TRAY_BEAM_ITEM_NAME = "Tray #1";

	private static final Object INPUT_ON_EVENT_NAME = "Input Transition On Event";

	private static final Object FRONT_BEAM_ITEM_NAME = "FIRBeam #1";

	private static final Object BACK_BEAM_ITEM_NAME = "BIRBeam #1";

	private static final List<Integer> ALERTING_TRIAL_CODES = List.of(931,913);

	private static final List<Integer> NO_CUE_TRIAL_CODES = List.of(813,831);

	@Override
	public Result analyzeTrial(Trial trial, int counter, SessionInfo sessionInfo, MetaData metaData) {
		MPosnerResult result = this.generateResult(trial, counter, sessionInfo);
		return new Result(sessionInfo, metaData, result.toString(), result.getHeader());
	}

	public MPosnerResult generateResult(Trial trial, int counter, SessionInfo sessionInfo) {
		// Get events from trial
		Event[] events = trial.copyEventsAsArray();

		// Initiate result object
		MPosnerResult result = new MPosnerResult();

		// Trial number is passed as argument;
		result.setTrialNumber(counter);

		// Timestamp is timestamp of first event
		result.setTimestamp((double) events[0].getEvent_Time());

		// Set trial type from parameters
		result.setTrialType(trialType.name());

		// Set cue time from parameters (doesn't change)
		result.setCueTime(cueTime);

		// Set hold time.
		result.setHoldTime(determineHoldTime(events));

		// Set target time.
		result.setTargetTime(determineTargetTime(events));

		// Set brightness
		result.setCueBrightness(determineCueBrightness(events));

		// Set cue side
		result.setCueSide(determineCueSide(events));

		// Set validity
		result.setCueValidity(determineCueValidity(events, result.getCueSide()));

		// Set if alerting
		result.setAlerting(determineAlerting(events));

		// Set if distracted
		result.setDistractor(determineIfDistracted(events));

		// Create posner state machine
		MPosnerStateMachine stateMachine = new MPosnerStateMachine();
		stateMachine.setStartingState(events[0].getGroup_Id());

		// Run state machine
		for (Event event : events) {
			try {
				if (event.getItem_Name().equals("Group Change")) {
					stateMachine.transition((int) event.getArgumentValue(1), (double) event.getEvent_Time());
				} 
				//Events that are not explicit group changes are usually laggy events and don't represent a group change
//				else {
//					stateMachine.transition(event.getGroup_Id(), (double) event.getEvent_Time());
//				}
			} catch (Exception e) {
				System.out.println(
						"Invalid transition between schedule groups. Transition to group " + event.getGroup_Id()
						+ " from group " + stateMachine.getCurrentState()+
						 " at timestamp " + event.getEvent_Time());
				System.out.println("Transition history for trial: " + stateMachine.getTransitionHistory());
				e.printStackTrace();
				System.exit(-1);
			}
		}

		// Set number of anticipation errors
		result.setAnticipationErrors(stateMachine.getAnticipationErrors());

		// trial is correct if it goes through group 35
		if (stateMachine.getTransitionHistory().contains(",35,")) {
			result.setCorrect(1);
		} else {
			result.setCorrect(0);
		}

		// trial is comission if it goes through group 32
		if (stateMachine.getTransitionHistory().contains(",32,")) {
			result.setComissionError(1);
		} else {
			result.setComissionError(0);
		}

		// trial is omission if it goes through group 33
		if (stateMachine.getTransitionHistory().contains(",33,")) {
			result.setOmissionError(1);
		} else {
			result.setOmissionError(0);
		}

		// stimulus response latency is calculated by the state machine
		result.setResponseLatency(stateMachine.getStimulusResponseLatency());

		// reaction time is calculated by the state machine
		result.setReactionTime(stateMachine.getReactionTime());

		// movement time is calculated by the state machine
		result.setMovementTime(stateMachine.getMovementTime());

		// reward latency is calculated by the state machine
		result.setRewardCollectionLatency(stateMachine.getRewardCollectionLatency());

		// determine ITI touches
		result.setTouchesDuringITI(determineItiTouches(events));

		calculateBeamBreaks(result, events);
		
		// determine "touchscreen error" with legacy code
		result.setTouchscreenError(this.determineIfError(events));

		return result;
	}

	private String determineIfDistracted(Event[] events) {
		for (Event event : events){
			if(event.getItem_Name().equals("Distractor") && event.getArgumentName(1).equals("Value") && (((int)event.getArgumentValue(1)) == 1)){
				return("1");
			}
		}
		return("0");
	}

	private void calculateBeamBreaks(MPosnerResult result, Event[] events) {

		for (Event event : events) {
			if (event.getEvent_Name().equals(INPUT_ON_EVENT_NAME)) {

				if (event.getItem_Name().equals(TRAY_BEAM_ITEM_NAME)) {
					result.setTrayBeamBreaks(result.getTrayBeamBreaks() + 1);
				}
				if (event.getItem_Name().equals(FRONT_BEAM_ITEM_NAME)) {
					result.setFrontBeamBreaks(result.getFrontBeamBreaks() + 1);
				}
				if (event.getItem_Name().equals(BACK_BEAM_ITEM_NAME)) {
					result.setBackBeamBreaks(result.getBackBeamBreaks() + 1);
				}

			}
		}

	}

	private Integer determineItiTouches(Event[] events) {
		Integer result = 0;
		for (Event event : events) {
			if (event.getEvent_Name().equals("Touch Down Event") && event.getGroup_Id() == 10) {
				result++;
			}
		}
		return result;
	}

	private String determineCueValidity(Event[] events, String cueSide) {
		String result = "Invalid!";
		String targetPosition = "";
		for (Event event : events) {
			//if these codes appear, override normal logic
			if (event.getItem_Name().equals("Trial_Type") && ALERTING_TRIAL_CODES.contains((int)(event.getArgumentValue(1)))){
				return "Alerting";
			}

			//if no cue codes appear, override normal logic
			if (event.getItem_Name().equals("Trial_Type") && NO_CUE_TRIAL_CODES.contains((int)(event.getArgumentValue(1)))){
				return "No Cue";
			}
			// look for target position
			if (event.getGroup_Id() == 5 && event.getItem_Name().equals("Target_Position")) {
				if ((double) event.getArgumentValue(1) == 1d) {
					targetPosition = "Left";
				} else if ((double) event.getArgumentValue(1) == 3d) {
					targetPosition = "Right";
				}
				if (cueSide.equals(targetPosition)) {
					return "Valid";
				} else if (!cueSide.equals(targetPosition)) {
					return "Invalid";
				}
			}
		}
		return result;
	}

	private String determineAlerting(Event[] events){
		String result = "Error";
		for (Event event : events){
			if(event.getEvent_Name().equals("Condition Event") && event.getItem_Name().equals("First 10 trials")){
				result = "First 10 trials";
			}
			if(event.getGroup_Id() == 17 && event.getItem_Name().equals("AlertingCue")){
				if ((double)event.getArgumentValue(1) == 1d){
					result = "Alerting";
				}
				else if ((double)event.getArgumentValue(1) == 0d){
					result = "Non Alerting";
				}
			}
		}
		return result;
	}

	private String determineCueSide(Event[] events) {
		String result = "Invalid!";
		for (Event event : events) {
			// cue side is always determined in group 5, position parameter if exogenous
			if (event.getGroup_Id() == 5 && event.getItem_Name().equals("Cue_Position")) {
				if ((double) event.getArgumentValue(1) == 1d) {
					result = "Left";
				} else if ((double) event.getArgumentValue(1) == 3d) {
					result = "Right";
				}
				return result;
			}
			// cue side is always determined in group 5, left argument if endogenous
			if (event.getGroup_Id() == 5 && event.getItem_Name().equals("Cue_Left")) {
				if ((double) event.getArgumentValue(1) == 1d) {
					result = "Left";
				}
				return result;
			}
			// cue side is always determined in group 5, left argument if endogenous
			if (event.getGroup_Id() == 5 && event.getItem_Name().equals("Cue_Right")) {
				if ((double) event.getArgumentValue(1) == 1d) {
					result = "Right";
				}
				return result;
			}
			// or in endogenous target time probe, it might be coded as the value of "cue"
			if (event.getGroup_Id() == 5 && event.getItem_Name().equals("Cue")) {
				if ((double) event.getArgumentValue(1) == 2d) {
					result = "Left";
				}
				else if ((double) event.getArgumentValue(1) == 1d) {
					result = "Right";
				}
				return result;
			}
		}
		return result;
	}

	private Double determineCueBrightness(Event[] events) {

		// On probe trials
		Double result = Double.NaN;
		for (Event event : events) {
			// brightness is determined on group 3 (first 10 trials)
			if (event.getGroup_Id() == 3 && event.getItem_Name().equals("Cue_Brightness")) {
				result = (double) event.getArgumentValue(1);
				return result;
			}
			// Or group 17 (more than 10 trials)
			if (event.getGroup_Id() == 17 && event.getItem_Name().equals("Cue_Brightness")) {
				result = (double) event.getArgumentValue(1);
				return result;
			}
		}

		// By default cue brightness is one
		return DEFAULT_BRIGHTNESS;
	}

	private Double determineTargetTime(Event[] events) {
		// On probe trials
		Double result = Double.NaN;
		for (Event event : events) {
			// target time is determined on group 3 (first 10 trials)
			if (event.getGroup_Id() == 3 && event.getItem_Name().equals("Target_Time")) {
				result = (double) event.getArgumentValue(1);
				return result;
			}
			// Or group 17 (more than 10 trials)
			if (event.getGroup_Id() == 17 && event.getItem_Name().equals("Target_Time")) {
				result = (double) event.getArgumentValue(1);
				return result;
			}
		}

		// Target time is determined from parameters on non probe trials (fall through)
		return this.first_targetTime;
	}

	private Double determineHoldTime(Event[] events) {
		// Hold time is determined in group 4 in every trial (doesn't change if an
		// anticipation error precedes it)
		Double result = Double.NaN;
		for (Event event : events) {
			if (event.getGroup_Id() == 4 && event.getItem_Name().equals("Hold_Time")) {
				result = (double) event.getArgumentValue(1);
			}
		}
		return result;
	}

	private int determineIfError(Event[] events) {
		boolean targetIsDisplayed = false;

		for (Event event : events) {

			if (event.getEvent_Name().equals("Whisker - Display Image") && event.getArgumentName(2).equals("Target")
					&& !targetIsDisplayed) {
				targetIsDisplayed = true;
				continue;
			}

			// If target has been displayed, only return 0 (valid) if there is...

			if (targetIsDisplayed) {

				// A touch up in position 2
				if (event.getEvent_Name().equals("Touch Up Event") && (event.getArgumentValue(1) == 2)) {
					return 0;
				}

				// A touch up in position 4 (the expanded position 2)
				if (event.getEvent_Name().equals("Touch Up Event") && (event.getArgumentValue(1) == 4)) {
					return 0;
				}

				// A touch up in position 1
				if (event.getEvent_Name().equals("Touch Up Event") && (event.getArgumentValue(1) == 3)) {
					return 0;
				}

				// A touch up in position 2
				if (event.getEvent_Name().equals("Touch Up Event") && (event.getArgumentValue(1) == 1)) {
					return 0;
				}

				// Ignore if touch down in center
				// if (event.getEvent_Name().equals("Touch Down Event") &&
				// (event.getArgumentValue(1) == 2)){
				// continue;
				// }

				// Ignore if touch down on 4
				// if (event.getEvent_Name().equals("Touch Down Event") &&
				// (event.getArgumentValue(1) == 4)){
				// continue;
				// }

				// Any other kind of touch must be an error
				if (event.getEvent_Name().equals("Touch Down Event")
						|| event.getEvent_Name().equals("Touch Up Event")) {
					return 1;
				}

			}

		}

		return 1;

	}

	@Override
	public void setParameters(SessionParameters parameters) {
		MPosnerProbeParameters posnerParameters = (MPosnerProbeParameters) parameters;
		this.trialType = PosnerTrialType.valueOf(posnerParameters.getTrialType());
		this.cueTime = posnerParameters.getCueTime();
		this.first_holdTime = posnerParameters.getHoldTime();
		this.first_targetTime = posnerParameters.getTargetTime();
	}

}
