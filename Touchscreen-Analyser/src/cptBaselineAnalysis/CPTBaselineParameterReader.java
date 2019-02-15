package cptBaselineAnalysis;

import analysisSets.IParameterReader;
import dataModels.Event;
import dataModels.SessionParameters;

class CPTBaselineParameterReader implements IParameterReader {

	@Override
	public SessionParameters readParameters(Event[] events) {
		CPTBaselineParameters parameters = new CPTBaselineParameters();
		
		for(Event event : events) {
			if (event.getItem_Name().equals("Correct_Image")) {
				parameters.setCorrectImageIndex((int)event.getArgumentValue(1));
			}
		}
		
		return parameters;
	}

}
