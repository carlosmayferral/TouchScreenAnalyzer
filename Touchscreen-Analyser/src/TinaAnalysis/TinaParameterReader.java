package TinaAnalysis;

import analysisSets.IParameterReader;
import dataModels.Event;
import dataModels.SessionParameters;

public class TinaParameterReader implements IParameterReader {

	@Override
	public SessionParameters readParameters(Event[] events) {
		float cue_time = Float.NaN;
		float hold_time = Float.NaN;
		float target_time = Float.NaN;
		float finish_time = Float.NaN;
		
		boolean cue_time_found = false;
		boolean hold_time_found = false;
		boolean target_time_found = false;

		for (Event event : events) {
			if (event.getItem_Name().equals("Cue_Time")) {
				cue_time = event.getArgumentValue(1);
				cue_time_found = true;
			}
			if (event.getItem_Name().equals("Hold_Time")) {
				hold_time = event.getArgumentValue(1);
				hold_time_found = true;
			}
			if (event.getItem_Name().equals("Target_Time")) {
				target_time = event.getArgumentValue(1);
				target_time_found = true;
			}
			if (cue_time_found && hold_time_found && target_time_found) {
				break;
			}
		}
		if (cue_time_found && hold_time_found && target_time_found) {
			for(int i = events.length-1; i>0; i--) {
				if(events[i].getEvent_Name().equals("Whisker - Display Image")
				&& events[i].getArgumentName(2).equals("CentralStimulus")) {
					finish_time =  events[i].getEvent_Time();
					break;
				}
			}
		return new TinaSessionParameters(cue_time, hold_time, target_time, finish_time);
		}
		System.out.println("Error reading all parameters");
		return null;
	}

}
