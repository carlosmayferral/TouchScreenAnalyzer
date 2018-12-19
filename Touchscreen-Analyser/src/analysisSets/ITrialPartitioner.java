package analysisSets;

import java.util.ArrayList;

import dataModels.Event;
import dataModels.Trial;

public interface ITrialPartitioner {

	public ArrayList<Trial> partition(Event[] events);

}
