package extinctionAnalysisSet;

import analysisSets.ITrialAnalyzer;
import dataModels.Event;
import dataModels.MetaData;
import dataModels.Result;
import dataModels.SessionInfo;
import dataModels.SessionParameters;
import dataModels.Trial;

public class ExtinctionTrialAnalyzer implements ITrialAnalyzer {

	private final String resultHeader = "Trial_Number," + "TimeStamp," + "Response," + "Omission," + "Left_ITI_touches,"
			+ "Center_ITI_touches," + "Right_ITI_touches," + "Tray_Entries," + "Back_Beam_Breaks,"
			+ "Front_Beam_Breaks," + "Time_In_Trial";

	private enum BeamType {
		FRONT, BACK, TRAY
	}

	@Override
	public Result analyzeTrial(Trial trial, int counter, SessionInfo sessionInfo , MetaData metaData) {

		// Extract events
		Event[] events = trial.copyEventsAsArray();

		// The trial counter
		int trialNumber = counter;

		// The time stamp
		float timeStamp = events[0].getEvent_Time();

		// The time spent in a trial
		float timeInTrial = this.getTimeInTrial(events);

		// Whether a response was made or not
		int response = this.getIfResponded(events);

		// Whether a mouse omitted or not
		int omission = this.getIfOmitted(events);

		// Left ITI touches
		int leftITItouches = this.getItiTouches(events, 1);

		// Center ITI touches
		int centerITItouches = this.getItiTouches(events, 2);

		// Right ITI touches
		int rightITItouches = this.getItiTouches(events, 3);

		// Tray entries
		int trayEntries = this.getBeamBreaks(events, BeamType.TRAY);

		// Back Beam Breaks
		int backBeamBreaks = this.getBeamBreaks(events, BeamType.BACK);

		// Front Beam Breaks
		int frontBeamBreaks = this.getBeamBreaks(events, BeamType.FRONT);

		String resultContent = trialNumber + "," + timeStamp + "," + response + "," + omission + "," + leftITItouches
				+ "," + centerITItouches + "," + rightITItouches + "," + trayEntries + "," + backBeamBreaks + ","
				+ frontBeamBreaks + "," + timeInTrial;

		return new Result(sessionInfo, metaData, resultContent, resultHeader);
	}

	private int getBeamBreaks(Event[] events, BeamType type) {
		int counter = 0;

		String beam_event_name = "Input Transition On Event";

		String beam_item_name;

		switch (type) {
		case TRAY:
			beam_item_name = "Tray #1";
			break;
		case FRONT:
			beam_item_name = "FIRBeam #1";
			break;
		case BACK:
			beam_item_name = "BIRBeam #1";
			break;
		default:
			beam_item_name = null;
		}

		for (Event event : events) {
			if (event.getEvent_Name().equals(beam_event_name) && event.getItem_Name().equals(beam_item_name)) {
				counter++;
			}
		}

		return counter;
	}

	private int getItiTouches(Event[] events, int position) {

		boolean ITI_effective = false;
		int touch_counter = 0;
		for (Event event : events) {
			if (event.getItem_Name().equals("ITI_Timer")) {
				ITI_effective = true;
			}
			if (ITI_effective && event.getEvent_Name().equals("Touch Down Event")
					&& event.getArgumentValue(1) == position) {
				touch_counter++;
			}
		}
		return touch_counter;
	}

	private int getIfOmitted(Event[] events) {

		for (Event event : events) {
			if (event.getItem_Name().equals("Image display time end")) {
				return 1;
			}
		}
		return 0;
	}

	private int getIfResponded(Event[] events) {

		for (Event event : events) {
			if (event.getItem_Name().equals("Image Touched")) {
				return 1;
			}
		}
		return 0;
	}

	private float getTimeInTrial(Event[] events) {

		float firstEventTime = events[0].getEvent_Time();
		float lastEventTime = events[events.length - 1].getEvent_Time();

		return lastEventTime - firstEventTime;

	}

	@Override
	public void setParameters(SessionParameters parameters) {
		// TODO Auto-generated method stub
	}

}
