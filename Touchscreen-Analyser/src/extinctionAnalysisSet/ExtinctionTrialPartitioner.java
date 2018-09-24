package extinctionAnalysisSet;

import java.util.ArrayList;

import analysisSets.ITrialPartitioner;
import dataModels.Event;
import dataModels.Trial;

public class ExtinctionTrialPartitioner implements ITrialPartitioner {

	@Override
	public ArrayList<Trial> partition(ArrayList<Event> events) {
		
		//Initiate trial list
		ArrayList<Trial> trials = new ArrayList<Trial>();
		
		//While there is events left in the list
		while(!events.isEmpty()) {
			
			Event current_event = events.remove(0);
			
			//This is the logic that determines if a trial has started
			if (current_event.getEvent_Name().equals("Whisker - Display Image")
					&& current_event.getArgumentName(2).equals("Image 1")) {
				
				//Initialize event list for trial
				ArrayList<Event> trialEvents = new ArrayList<Event>();
				
				//add first event
				trialEvents.add(current_event);
				
				//While the trial has not finished... Add events to trial
				while (!events.isEmpty() && !(current_event = events.remove(0)).getItem_Name().equals("Next trial")) {
					trialEvents.add(current_event);
				}
				
				//Add last event to trial, after finish was detected
				if (!events.isEmpty()) {
					trialEvents.add(current_event);
				}
				
				//Ensure trial integrity, verify end of trial was reached.
				if (trialEvents.get(trialEvents.size()-1).getItem_Name().equals("Next trial")) {
					trials.add(new Trial(trialEvents));
				}
				
				
			}
			
			
		}
		return trials;
	}

}
