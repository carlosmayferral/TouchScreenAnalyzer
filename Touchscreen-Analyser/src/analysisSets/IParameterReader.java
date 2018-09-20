package analysisSets;

import java.util.ArrayList;

import dataModels.Event;
import dataModels.SessionParameters;

public interface IParameterReader {

	public SessionParameters readParameters(ArrayList<Event> events);
	
}
