package pairwiseHabituationV2AnalysisSet;

import java.util.ArrayList;

import analysisSets.ITrialPartitioner;
import dataModels.Event;
import dataModels.Trial;

public class PairwiseHabituationV2TrialPartitioner implements ITrialPartitioner {
	
	public static final String PRIME_FEED_CONDITION = "Prime Feed";
	private static final Object FEED_AGAIN_CONDITION = "Feed again";

	@Override
	public ArrayList<Trial> partition(Event[] events) {
		// return null if no events, something went wrong
		if (events.length == 0) {
			return null;
		}
		
		//Initialize result
		ArrayList<Trial> trials = new ArrayList<Trial>();
		
		//Remember if first trial or no
		boolean trialsHaveStarted = false;
		
		//Initialize event buffer
		ArrayList<Event> trialEvents = new ArrayList<>();
		
		//Scan through events
		for (Event event : events) {
			
			//Search for start of trial
			if((event.getItem_Name().equals(PRIME_FEED_CONDITION) || event.getItem_Name().equals(FEED_AGAIN_CONDITION))) {
				if(!trialsHaveStarted) {
					trialsHaveStarted = true;
				}
				else {
					//Add current events ro trial
					Event[] eventArray = new Event[trialEvents.size()];
					trials.add(new Trial(trialEvents.toArray(eventArray)));
					//Reset Events
					trialEvents= new ArrayList<Event>();
				}
			}
			
			//Keep searching for first trial
			else if (!trialsHaveStarted) {
				continue;
			}
			
			//Otherwise trials must have started
			trialEvents.add(event);
		}
		
		//Add orphan events to last trial (if there is extra events)
		//Actually drop the incomplete trial as it has integrity issues
//		if(trialEvents.size()>0) {
//			Event[] eventArray = new Event[trialEvents.size()];
//			trials.add(new Trial(trialEvents.toArray(eventArray)));
//		}
		
		
		return trials;
	}

}
