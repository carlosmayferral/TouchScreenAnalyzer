package tunlAnalysisSet;

import java.util.ArrayList;

import analysisSets.ITrialPartitioner;
import dataModels.Event;
import dataModels.Trial;

public class TunlTrialPartitioner implements ITrialPartitioner {

	@Override
	public ArrayList<Trial> partition(Event[] events) {
		
		ArrayList<Trial> trials = new ArrayList<>();
		
		//start of events
		int eventNumber = 0;
		
		//while start of trial is not encountered
		while(eventNumber<events.length && !events[eventNumber].getItem_Name().equals("Tray Entry starts first trial")
				) {
			eventNumber++;
		}
		
		//if end of event list was encountered, return empty ArrayList
		if (eventNumber == events.length-1) {
			return trials;
		}
		
		ArrayList<Event> trialEvents = new ArrayList<>();
		
		//for each remaining event, add to trial unless it's a trial transition
		while(eventNumber<events.length) {
			trialEvents.add(events[eventNumber]);
			if (events[eventNumber].getItem_Name().equals("Next trial")) {
				Event[] trialArray = new Event[trialEvents.size()];
				trialEvents.toArray(trialArray);
				trials.add(new Trial((Event[]) trialArray));
				trialEvents = new ArrayList<>();
			}
			eventNumber++;
		}
		return trials;
	}

}
