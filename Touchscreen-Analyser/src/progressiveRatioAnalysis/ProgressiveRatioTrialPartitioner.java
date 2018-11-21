package progressiveRatioAnalysis;

import java.util.ArrayList;

import analysisSets.ITrialPartitioner;
import dataModels.Event;
import dataModels.Trial;
import progressiveRatioAnalysis.analyzerHelpers.ReferenceEvents;

public class ProgressiveRatioTrialPartitioner implements ITrialPartitioner {

	@Override
	public ArrayList<Trial> partition(ArrayList<Event> events) {
	
		ArrayList<Trial> trials = new ArrayList<Trial>();
		
		ArrayList<Event> trialEvents = new ArrayList<Event>();
		
		//while there is still raw events
		while (!events.isEmpty()) {
			
			Event currentEvent = events.remove(0);
			
			//add event
			trialEvents.add(currentEvent);
			
			//if end of trial is detected, form trial and reset list
			if (currentEvent.equals(ReferenceEvents.END_TRIAL_EVENT)) {
				trials.add(new Trial(trialEvents));
				trialEvents = new ArrayList<Event>();
			}
			
			//if session timed out, still form a trial
			if (currentEvent.equals(ReferenceEvents.TIMEOUT_EVENT)) {
				trials.add(new Trial(trialEvents));
				break;
			}
			
		}
		
		return trials;
	}
}
