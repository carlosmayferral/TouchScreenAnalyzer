package TinaAnalysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import analysisSets.ITrialAnalyzer;
import dataModels.Event;
import dataModels.Result;
import dataModels.SessionInfo;
import dataModels.SessionParameters;
import dataModels.Trial;

public class tinaTrialAnalyzer implements ITrialAnalyzer {
	
	private float currentCueTime;
	
	private float currentHoldTime;
	
	private TinaSessionParameters parameters;
	
	private final String resultHeader = "Trial_Number,"
			+ "Cue_Type,"
			+ "Cue_Validity,"
			+ "Cue_Time,"
			+ "Hold_Time,"
			+ "Correct_Selection,"
			+ "AnticipationError,"
			+ "CommissionError,"
			+ "OmissionError,"
			+ "Reaction_Time,"
			+ "Movement_Time";
	
	@Override
	public void setParameters(SessionParameters parameters) {
		this.parameters = (TinaSessionParameters)parameters;
		this.currentCueTime = this.parameters.getCueTime();
		this.currentHoldTime = this.parameters.getHoldTime();
	}

	@Override
	public Result analyzeTrial(Trial trial, int counter, SessionInfo sessionInfo) {
		
		//The trial counter
		int trialNumber = counter;
		
		//Cue type (whether it is left or right)
		String cueType = this.determineCueType(trial);
		
		//Cue validity (whether anything follows)
		String cueValidity = this.determineCueValidity(trial);
		
		//Predetermined cue time per trial
		float cueTime = this.determineCueTime(trial);
		
		//Predetermined cue time per trial
		float holdTime = this.determineHoldTime(trial);
		
		//Correct target selection
		int correctSelection = this.determineCorrectSelection(trial);
		
		//Whether mouse was patient enough to wait for target to appear
		int anticipationError = this.determineAnticipationError(trial);
		
		//Whether the mouse made a comission error
		int comissionError = this.determineComissionError(trial);
		
		//Whether the mouse omitted entirely
		int omissionError = this.determineOmissionError(trial);
		
		//Response time
		float responseTime = this.calculateResponseTime(trial);
		
		//Movement time, the time from cue release to target selection
		float movementTime = this.calculateMovementTime(trial);
		
		String resultContent = trialNumber + ","
		+ cueType + ','
		+ cueValidity + ','
		+ cueTime + ','
		+ holdTime + ','
		+ correctSelection + ','
		+ anticipationError+ ','
		+ comissionError + ','
		+ omissionError + ','
		+ responseTime + ','
		+ movementTime;
		
		return new Result(sessionInfo, resultContent, resultHeader);
		
		
	}
	
	private float calculateMovementTime(Trial trial) {
		
		ArrayList<Event> events = trial.copyEventList();
		
		float releaseTime = 0;
		
		float touchTime = 0;
		
		boolean imageIsReleased = false;
		
		boolean targetIsDisplayed = false;
		
		for (Event event : events) {
			
			if (event.getEvent_Name().equals("Whisker - Display Image") &&
					event.getArgumentName(2).equals("Target") && !targetIsDisplayed) {
				targetIsDisplayed = true;
			}
			
			if (event.getEvent_Name().equals("Touch Down Event") &&
					(event.getArgumentValue(1) == 1 ||
					event.getArgumentValue(1) == 3) 
					&& imageIsReleased){
				touchTime = event.getEvent_Time();
				return touchTime - releaseTime;
			}
			
			if (event.getEvent_Name().equals("Touch Up Event") &&
					!imageIsReleased && targetIsDisplayed){
				imageIsReleased = true;
				releaseTime = event.getEvent_Time();
			}
			
		}
		
		return Float.NaN;
		
	}

	private float calculateResponseTime(Trial trial) {
		
		ArrayList<Event> events = trial.getEventList();
		
		float displayTime = 0;
		
		float releaseTime = 0;
		
		boolean imageIsDisplayed = false;
		
		for (Event event : events) {
			
			if (event.getEvent_Name().equals("Whisker - Display Image") &&
					event.getArgumentName(2).equals("Target") && !imageIsDisplayed) {
				imageIsDisplayed = true;
				displayTime = event.getEvent_Time();
			}
			
			if (event.getEvent_Name().equals("Touch Up Event") &&
					imageIsDisplayed){
				releaseTime = event.getEvent_Time();
				return releaseTime - displayTime;
			}
			
		}
		
		return Float.NaN;
	}

	private int determineOmissionError(Trial trial) {
		
		ArrayList<Event> events = trial.getEventList();
		
		for (Event event: events) {
			if (event.getItem_Name().equals("Increment_OmissionError")){
				return 1;
			}
		}
		
		return 0;
	}
	
	private int determineComissionError(Trial trial) {
		
		ArrayList<Event> events = trial.getEventList();
		
		for (Event event: events) {
			if (event.getItem_Name().equals("Increment_IncorrectError")){
				return 1;
			}
		}
		
		return 0;
	}
	
	private int determineCorrectSelection(Trial trial) {
		
		ArrayList<Event> events = trial.getEventList();
		
		for (Event event: events) {
			if (event.getItem_Name().equals("Increment Correct_Counter")) {
				return 1;
			}
		}
		
		return 0;
	}

	private int determineAnticipationError(Trial trial) {
		
		ArrayList<Event> events = trial.getEventList();
		
		for (Event event: events) {
			if (event.getEvent_Name().equals("Whisker - Display Image") &&
					event.getArgumentName(2).equals("Target")) {
				return 0;
			}
		}
		
		return 1;
	}

	private float determineHoldTime(Trial trial) {
		
		ArrayList<Event> events = trial.getEventList();
		
		for (Event event: events) {
			if (event.getItem_Name().equals("Hold_Time")) {
				this.currentHoldTime = event.getArgumentValue(1);
				return this.currentHoldTime;
			}
		}
		
		return this.currentHoldTime;
	}

	private float determineCueTime(Trial trial) {
		
		ArrayList<Event> events = trial.getEventList();
		
		for (Event event: events) {
			if (event.getItem_Name().equals("Cue_Time")) {
				this.currentCueTime = event.getArgumentValue(1);
				return this.currentCueTime;
			}
		}
		
		return this.currentCueTime;
	}

	private String determineCueValidity(Trial trial) {
		
		ArrayList<Event> events = trial.getEventList();
				
				for (Event event: events) {
					if (event.getItem_Name().equals("aTrial_Set")) {
						switch ((int)event.getArgumentValue(1)) {
						case 4531:
						case 14513:
						case 331:
						case 113:
							return "Valid";
						case 4513:
						case 14531:
						case 313:
						case 131:
							return "Invalid";
						case 4500:
						case 14500:
						case 300:
						case 100:
							return "CatchTrial";
						default:
							return "NA";
						}
					}
				}
		return "NA";
	}

	private String determineCueType(Trial trial) {
		
		ArrayList<Event> events = trial.getEventList();
		
		for (Event event: events) {
			if (event.getItem_Name().equals("aTrial_Set")) {
				switch ((int)event.getArgumentValue(1)) {
				case 4531:
				case 4513:
				case 4500:
				case 331:
				case 313:
				case 300:
					return "Left";
				case 14531:
				case 14513:
				case 14500:
				case 113:
				case 131:
				case 100:
					return "Right";
				case 913:
				case 931:
					return "Neutral";
				default:
					return "NA";
				}
			}
		}
		return "NA";
	}

}