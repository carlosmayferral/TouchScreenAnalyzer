package fiveChoiceAnalysis;

import java.util.ArrayList;

import analysisSets.ITrialPartitioner;
import dataModels.Event;
import dataModels.Trial;

class FiveChoiceTrialPartitioner implements ITrialPartitioner {

	@Override
	public ArrayList<Trial> partition(Event[] events) {
		
		//Skip until start of first trial 
		int iterator = 0;
		
		while (iterator < events.length
				&&
				!events[iterator].getItem_Name().equals("TrayLight #1")) {
			iterator++;
		}
		
		//Initialize trial array
		ArrayList<Trial> trials = new ArrayList<Trial>();
		
		//Initialize event buffer
		ArrayList<Event> trialEvents = new ArrayList<Event>();
		
		//While there is trials to go
		while (iterator < events.length) {
			
			//Add them to the buffer
			if (!events[iterator].getItem_Name().contains("ITI End")) {
				trialEvents.add(events[iterator]);
			}
			
			//Unless an ITI end is encountered, then create a trial
			else {
				//Add new trial with buffered events
				Event[] finalizedEvents = new Event[trialEvents.size()];
				Trial trial = new Trial(trialEvents.toArray(finalizedEvents));
				trials.add(trial);
				
				//Reset buffer
				trialEvents = new ArrayList<Event>();
			}
			
			//finally increase iterator
			iterator++;
		}
		
		//Verify integrity of last trial by checking it has an ITI timer item
		for (Event event : trialEvents) {
			if (event.getItem_Name().equals("ITI_Timer")) {
				Event[] finalizedEvents = new Event[trialEvents.size()];
				Trial trial = new Trial(trialEvents.toArray(finalizedEvents));
				trials.add(trial);
				break;
			}
		}
		
		
		//Return list of trials
		return trials;
		
	}

}
