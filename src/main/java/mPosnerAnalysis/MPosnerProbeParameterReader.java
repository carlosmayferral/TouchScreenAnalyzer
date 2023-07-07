package mPosnerAnalysis;

import analysisSets.IParameterReader;
import dataModels.Event;
import dataModels.SessionParameters;

public class MPosnerProbeParameterReader implements IParameterReader{
	
	//Parameters to collect (before trial begins)
	
	//is exogenous?
	private static final String CUE_POSITION_ITEM_NAME = "Cue_Position";
	
	//time cue is displayed
	private static final String CUE_TIME_ITEM_NAME = "Cue_Time";
	
	//Initial hold time
	private static final String HOLD_TIME_ITEM_NAME = "Hold_Time";
	
	//Target display time
	private static final String TARGET_DISPLAY_TIME = "Target_Time";

	@Override
	public SessionParameters readParameters(Event[] events) {
		//Initiate result object
		MPosnerProbeParameters parameters = new MPosnerProbeParameters();

		for (Event event : events) {
			
			//stop if group is no longer zero, set defaults
			if(event.getGroup_Id() != 0) {
				if(parameters.getTrialType().equals(PosnerTrialType.Undefined.name())) {
					parameters.setTrialType(PosnerTrialType.Endogenous);
				}
				return parameters;
			}
			
			//else, group must be zero (parameters stage)
			
			if(event.getItem_Name().equals(CUE_POSITION_ITEM_NAME)) {
				parameters.setTrialType(PosnerTrialType.Exogenous);
			}
			
			if(event.getItem_Name().equals(CUE_TIME_ITEM_NAME)) {
				Double cueTime = (double) event.getArgumentValue(1);
				parameters.setCueTime(cueTime);
			}
			
			if(event.getItem_Name().equals(HOLD_TIME_ITEM_NAME)) {
				Double holdTime = (double) event.getArgumentValue(1);
				parameters.setHoldTime(holdTime);
			}
			
			if(event.getItem_Name().equals(TARGET_DISPLAY_TIME)) {
				Double displayTime = (double) event.getArgumentValue(1);
				parameters.setTargetTime(displayTime);
			}
		}
		
		return parameters;
	}

}
