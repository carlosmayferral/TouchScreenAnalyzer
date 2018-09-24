package TinaAnalysis;

import java.util.ArrayList;

import analysisSets.IParameterReader;
import dataModels.Event;
import dataModels.SessionParameters;

public class TinaParameterReader implements IParameterReader {

	@Override
	public SessionParameters readParameters(ArrayList<Event> events) {
		float cue_time = Float.NaN;
		float hold_time = Float.NaN;
		
		for(Event event: events) {
			if (event.getItem_Name().equals("Cue_Time")) {
				cue_time = event.getArgumentValue(1);
			}
			if (event.getItem_Name().equals("Hold_Time")) {
				hold_time = event.getArgumentValue(1);
			}
			if ((cue_time >= 0) && (hold_time >= 0)) {
				return new TinaSessionParameters(cue_time, hold_time);
			}
		}
		
		return null;
	}

}
