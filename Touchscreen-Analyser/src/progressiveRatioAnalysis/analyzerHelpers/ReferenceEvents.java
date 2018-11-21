package progressiveRatioAnalysis.analyzerHelpers;

import dataModels.Event;

public abstract class ReferenceEvents {

	public static final Event END_TRIAL_EVENT = 
			new Event("Condition Event", "REWARD NEXT TRIAL DELAY END START NEXT TRIAL",null,null);
	
	public static final Event TIMEOUT_EVENT = 
			new Event("Condition Event", "Inactivity session termination",null,null);
}
