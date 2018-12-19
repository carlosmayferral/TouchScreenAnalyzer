package analysisSets;

import dataModels.Event;
import dataModels.SessionParameters;

public interface IParameterReader {

	public SessionParameters readParameters(Event[] events);

}
