package mPosnerAnalysis;

import java.util.ArrayList;

import analysisSets.ITrialPartitioner;
import dataModels.Event;
import dataModels.Trial;

public class MPosnerProbeTrialPartitioner implements ITrialPartitioner {

	private static final Object NEXT_TRIAL ="Next trial";

	@Override
	public ArrayList<Trial> partition(Event[] events) {

		// Initialize result
		ArrayList<Trial> trials = new ArrayList<Trial>();

		// Initialize event buffer
		ArrayList<Event> trialEvents = new ArrayList<>();
		
		//Is it the first trial?
		boolean firstTrial = true;
		
		for (Event event : events) {
			
			//Ignore if group is 0
			if(event.getGroup_Id() == 0) {
				continue;
			}
			
			//Otherwise add events to trial
			trialEvents.add(event);
			
			//If next trial is encountered, create new trial
			if(event.getItem_Name().equals(NEXT_TRIAL)) {
				//Add current events to trial
				Event[] eventArray = new Event[trialEvents.size()];
				trials.add(new Trial(trialEvents.toArray(eventArray)));
				//Reset Events
				trialEvents= new ArrayList<Event>();
			}
			
		}

		return trials;
	}

}
