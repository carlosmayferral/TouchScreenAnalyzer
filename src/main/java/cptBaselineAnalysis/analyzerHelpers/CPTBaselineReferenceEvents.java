package cptBaselineAnalysis.analyzerHelpers;

import dataModels.Event;

public class CPTBaselineReferenceEvents {
	
	public static final Event ITI_START = new Event("Condition Event", "Start ITI", null, null);
	public static final Event CORRECTION_ITI_START = new Event("Condition Event", "Start correction ITI", null, null);
	public static final Event IMAGE_DISPLAY =
			new Event("Condition Event", "Display Image", null, null);
	public static final Event IMAGE_WITHDRAWN = 
			new Event("Condition Event", "Remove Stimulus Duration", null, null);
	public static final Event HIT_EVENT =
			new Event("Condition Event", "Hit", null, null);
	public static final Event TIMEOUT_EVENT = 
			new Event("Condition Event", "Time_Out", null, null);
}
