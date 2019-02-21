package pdAnalysis;

import analysisSets.IParameterReader;
import dataModels.Event;
import dataModels.SessionParameters;

class PdParameterReader implements IParameterReader {
	
	private int correctImageIndex;
	
	public PdParameterReader() {
		correctImageIndex = -1;
	}

	@Override
	public SessionParameters readParameters(Event[] events) {
		
		PdSessionParameters parameters = new PdSessionParameters();
		
		for(Event event :events) {
			if (event.getItem_Name().equals("ITI")) {
				parameters.setItiLength(event.getArgumentValue(1));
			}
			if (event.getItem_Name().equals("Time_Out")) {
				parameters.setTimeoutLength(event.getArgumentValue(1));
			}
			if (event.getItem_Name().equals("Correct_Image")) {
				this.correctImageIndex = ((int)event.getArgumentValue(1));
			}
			if (event.getItem_Name().equals("TrayLight #1")) {
				break;
			}
			if (event.getEvent_Name().equals("List Array - Write") 
					&& event.getItem_Name().equals("Images")){
				//if the first argument corresponds to the image index
				if (event.getArgumentValue(1) == this.correctImageIndex) {
					parameters.setSpPlusId(event.getArgumentName(1));
				}
				else {
					parameters.setSpPlusId(event.getArgumentName(2));
				}
			}
		}
		
		return parameters;
	}

}
