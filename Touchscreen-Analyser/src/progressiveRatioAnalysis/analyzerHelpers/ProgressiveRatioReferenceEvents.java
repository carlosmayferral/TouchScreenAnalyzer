package progressiveRatioAnalysis.analyzerHelpers;

import dataModels.Event;

public abstract class ProgressiveRatioReferenceEvents {

	public static final Event END_TRIAL_EVENT = new Event("Condition Event",
			"REWARD NEXT TRIAL DELAY END START NEXT TRIAL", null, null);

	public static final Event TIMEOUT_EVENT = new Event("Condition Event", "Inactivity session termination", null,
			null);
	
	public static final Event SCHEDULE_SHUTDOWN_EVENT = new Event("Schedule Shutdown Event","(SYSTEM)",null,null);
}
