package fiveChoiceRatAnalysis;

import analysisSets.IParameterReader;
import dataModels.Event;
import dataModels.SessionParameters;

class FiveChoiceRatParameterReader implements IParameterReader {

	@Override
	public SessionParameters readParameters(Event[] events) {
		
		FiveChoiceRatSessionParameters parameters = new FiveChoiceRatSessionParameters();
		
		for(Event event : events) {
			switch(event.getItem_Name()) {
			case "Delay":
				parameters.setStimulusDelay(event.getArgumentValue(1));
				continue;
			case "ITI":
				parameters.setItiLength(event.getArgumentValue(1));
				continue;
			case "Stimulus_Duration":
			case "Stimulus_Duration_Value":
				parameters.setStimulusDuration(event.getArgumentValue(1));
				continue;
			case "Dim_Value":
				if (event.getEvent_Name().equals("List Change - Set")) {
					parameters.setStimulusBrightness(event.getArgumentValue(2));
				}
				continue;
			case "ShortDelayflg":
				int block = event.getArgumentValue(1) == 1 ? 0 : 1;
				parameters.setDelayBlock(block);
				continue;
			case "LongDelays":
				if (event.getEvent_Name().equals("List Change - Set") 
						&&
						parameters.getDelayBlock() == 1) {
					parameters.setStimulusDelay(event.getArgumentValue(2));
				}
				continue;
			case "ShortDelays":
				if (event.getEvent_Name().equals("List Change - Set") 
						&&
						parameters.getDelayBlock() == 0) {
					parameters.setStimulusDelay(event.getArgumentValue(2));
				}	
				continue;
			case "Play_Distracter":
				int distracter = event.getArgumentValue(1) == 1 ? 1 : 0;
				parameters.setDistractorPlayed(distracter);
				continue;
			case "Time_to_Distracter":
				if (event.getEvent_Name().equals("List Change - Set") 
						&&
						parameters.getDistractorPlayed() == 1) {
					parameters.setTimeToDistractor((event.getArgumentValue(2)));
				}	
				continue;
			case "TrayLight #1":
				break;
			}
		}
		
		return parameters;
	}

}
