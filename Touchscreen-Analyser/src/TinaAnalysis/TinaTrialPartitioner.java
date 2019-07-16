package TinaAnalysis;

import java.util.ArrayList;

import analysisSets.ITrialPartitioner;
import dataModels.Event;
import dataModels.Trial;

public class TinaTrialPartitioner implements ITrialPartitioner {

	@Override
	public ArrayList<Trial> partition(Event[] events) {

		ArrayList<Trial> trials = new ArrayList<Trial>();

		int i = 0;
		
		//Search for first trial start
		while(
				!((events[i].getEvent_Name().equals("Whisker - Display Image")) &&
				(events[i].getArgumentName(2).equals("CentralStimulus"))) &&
				i < events.length) {
			i++;
		}
		
		// While there is events left in the list
		while (i < events.length) {

			Event current_event = events[i];

			// This is the logic that determines if a trial has started
			if (current_event.getEvent_Name().equals("Whisker - Display Image")
					&& current_event.getArgumentName(2).equals("CentralStimulus")) {

				// Initialize event list for trial
				ArrayList<Event> trialEvents = new ArrayList<Event>();

				// add first event
				trialEvents.add(current_event);
				i++;

				// While the trial has not finished... Add events to trial
				while (i < events.length && !(current_event = events[i]).getItem_Name().equals("Select Trial Type")) {
					trialEvents.add(current_event);
					i++;
				}

				// Add last event to trial, after finish was detected
				if (i < events.length) {
					trialEvents.add(current_event);
					i++;
				}

				// Ensure trial integrity, verify end of trial was reached.
				if (trialEvents.get(trialEvents.size() - 1).getItem_Name().equals("Select Trial Type")) {
					Event[] eventArray = new Event[trialEvents.size()];
					trials.add(new Trial(trialEvents.toArray(eventArray)));
				}
			}
			else {
				i++;
			}

		}
		return trials;

	}

}
