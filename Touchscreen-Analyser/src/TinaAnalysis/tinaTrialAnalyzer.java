package TinaAnalysis;

import analysisSets.ITrialAnalyzer;
import dataModels.Event;
import dataModels.Result;
import dataModels.SessionInfo;
import dataModels.SessionParameters;
import dataModels.Trial;

public class TinaTrialAnalyzer implements ITrialAnalyzer {

	private float currentCueTime;

	private float currentHoldTime;
	
	private float currentTargetTime;

	private TinaSessionParameters parameters;

	private final String resultHeader = 
	"TimeStamp," +
	"Trial_Number," +
	"Cue_Type," +
	"Cue_Validity," +
	"Cue_Time," + 
	"Hold_Time," + 
	"Target_Time," +
	"Correct_Selection," + 
	"AnticipationError," + 
	"CommissionError," +
	"OmissionError," +
	"Reaction_Time," +
	"Movement_Time," + 
	"Touchscreen_error";

	@Override
	public void setParameters(SessionParameters parameters) {
		this.parameters = (TinaSessionParameters) parameters;
		this.currentCueTime = this.parameters.getCueTime();
		this.currentHoldTime = this.parameters.getHoldTime();
		this.currentTargetTime = this.parameters.getTargetTime();
	}

	@Override
	public Result analyzeTrial(Trial trial, int counter, SessionInfo sessionInfo) {

		// Timestamp
		float timeStamp = trial.copyEventsAsArray()[0].getEvent_Time();

		// The trial counter
		int trialNumber = counter;

		// Cue type (whether it is left or right)
		String cueType = this.determineCueType(trial);

		// Cue validity (whether anything follows)
		String cueValidity = this.determineCueValidity(trial);

		// Predetermined cue time per trial
		float cueTime = this.determineCueTime(trial);

		// Predetermined hold time per trial
		float holdTime = this.determineHoldTime(trial);
		
		// Predetermined target time per trial
		float targetTime = this.determineTargetTime(trial);

		// Correct target selection
		int correctSelection = this.determineCorrectSelection(trial);

		// Whether mouse was patient enough to wait for target to appear
		int anticipationError = this.determineAnticipationError(trial);

		// Whether the mouse made a comission error
		int comissionError = this.determineComissionError(trial);

		// Whether the mouse omitted entirely
		int omissionError = this.determineOmissionError(trial);

		// Response time
		float responseTime = this.calculateResponseTime(trial);

		// Movement time, the time from cue release to target selection
		float movementTime = this.calculateMovementTime(trial);

		// Determine if there was a hardware error (no registered touch up)
		int touchscreenError = this.determineIfError(trial, omissionError, anticipationError, comissionError);

		String resultContent = timeStamp + "," + trialNumber + "," + cueType + ',' + cueValidity + ',' + cueTime + ','
				+ holdTime + ',' + targetTime + ',' + correctSelection + ',' + anticipationError + ',' + comissionError + ','
				+ omissionError + ',' + responseTime + ',' + movementTime + ',' + touchscreenError;

		return new Result(sessionInfo, resultContent, resultHeader);

	}

	private float determineTargetTime(Trial trial) {

		Event[] events = trial.copyEventsAsArray();
		
		for(Event event : events) {
			if (event.getItem_Name().equals("Target_Time")){
				currentTargetTime = event.getArgumentValue(1);
			}
		}
		
		return currentTargetTime;
	}

	private int determineIfError(Trial trial, int omission, int anticipationError, int comissionError) {

		if (anticipationError == 1) {
			return 0;
		}

		else {
			Event[] events = trial.copyEventsAsArray();

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
						return 2;
					}

					// A touch up in position 2
					if (event.getEvent_Name().equals("Touch Up Event") && (event.getArgumentValue(1) == 1)) {
						return 2;
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
	}

	private float calculateMovementTime(Trial trial) {

		Event[] events = trial.copyEventsAsArray();

		float releaseTime = 0;

		float touchTime = 0;

		boolean imageIsReleased = false;

		boolean targetIsDisplayed = false;

		for (Event event : events) {

			if (event.getEvent_Name().equals("Whisker - Display Image") && event.getArgumentName(2).equals("Target")
					&& !targetIsDisplayed) {
				targetIsDisplayed = true;
			}

			if (event.getEvent_Name().equals("Touch Down Event")
					&& (event.getArgumentValue(1) == 1 || event.getArgumentValue(1) == 3) && imageIsReleased) {
				touchTime = event.getEvent_Time();
				return touchTime - releaseTime;
			}

			if (event.getEvent_Name().equals("Touch Up Event") && !imageIsReleased && targetIsDisplayed) {
				imageIsReleased = true;
				releaseTime = event.getEvent_Time();
			}

		}

		return Float.NaN;

	}

	private float calculateResponseTime(Trial trial) {

		Event[] events = trial.copyEventsAsArray();

		float displayTime = 0;

		float releaseTime = 0;

		boolean imageIsDisplayed = false;

		for (Event event : events) {

			if (event.getEvent_Name().equals("Whisker - Display Image") && event.getArgumentName(2).equals("Target")
					&& !imageIsDisplayed) {
				imageIsDisplayed = true;
				displayTime = event.getEvent_Time();
			}

			if (event.getEvent_Name().equals("Touch Up Event") && imageIsDisplayed) {
				releaseTime = event.getEvent_Time();
				return releaseTime - displayTime;
			}

		}

		return Float.NaN;
	}

	private int determineOmissionError(Trial trial) {

		Event[] events = trial.copyEventsAsArray();

		for (Event event : events) {
			if (event.getItem_Name().equals("Increment_OmissionError")) {
				return 1;
			}
		}

		return 0;
	}

	private int determineComissionError(Trial trial) {

		Event[] events = trial.copyEventsAsArray();

		for (Event event : events) {
			if (event.getItem_Name().equals("Increment_IncorrectError")) {
				return 1;
			}
		}

		return 0;
	}

	private int determineCorrectSelection(Trial trial) {

		Event[] events = trial.copyEventsAsArray();

		for (Event event : events) {
			if (event.getItem_Name().equals("Increment Correct_Counter")) {
				return 1;
			}
		}

		return 0;
	}

	private int determineAnticipationError(Trial trial) {

		Event[] events = trial.copyEventsAsArray();

		for (Event event : events) {
			if (event.getEvent_Name().equals("Whisker - Display Image") && event.getArgumentName(2).equals("Target")) {
				return 0;
			}
		}

		return 1;
	}

	private float determineHoldTime(Trial trial) {

		Event[] events = trial.copyEventsAsArray();

		for (Event event : events) {
			if (event.getItem_Name().equals("Hold_Time")) {
				this.currentHoldTime = event.getArgumentValue(1);
				return this.currentHoldTime;
			}
		}

		return this.currentHoldTime;
	}

	private float determineCueTime(Trial trial) {

		Event[] events = trial.copyEventsAsArray();

		for (Event event : events) {
			if (event.getItem_Name().equals("Cue_Time")) {
				this.currentCueTime = event.getArgumentValue(1);
				return this.currentCueTime;
			}
		}

		return this.currentCueTime;
	}

	private String determineCueValidity(Trial trial) {

		Event[] events = trial.copyEventsAsArray();

		for (Event event : events) {
			if (event.getItem_Name().equals("aTrial_Set")) {
				switch ((int) event.getArgumentValue(1)) {
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

		Event[] events = trial.copyEventsAsArray();

		for (Event event : events) {
			if (event.getItem_Name().equals("aTrial_Set")) {
				switch ((int) event.getArgumentValue(1)) {
				case 4531:
				case 4513:
				case 4500:
				case 331:
				case 313:
				case 300:
					return "Right";
				case 14531:
				case 14513:
				case 14500:
				case 113:
				case 131:
				case 100:
					return "Left";
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
