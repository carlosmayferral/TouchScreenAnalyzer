package pdAnalysis;

import java.util.ArrayList;

import analysisSets.ITrialPartitioner;
import dataModels.Event;
import dataModels.Trial;

class PdTrialPartitioner implements ITrialPartitioner {

	@Override
	public ArrayList<Trial> partition(Event[] events) {
		
		//For a trial to be intact, it must have an ITI in it
		boolean  trialCanEnd = false;
		ArrayList<Event> trialEvents = new ArrayList<>();
		ArrayList<Trial> trials = new ArrayList<>();
		
		int iterator = 0;
		
		//scan events to detect start of first trial
		while (!events[iterator].getItem_Name().equals("TrayLight #1")){
			iterator++;
		}
		
		//While there are events to go
		while (iterator < events.length) {
			//Record if trial is ready to end
			if (events[iterator].getEvent_Name().equals("Timer Event") && events[iterator].getItem_Name().equals("ITI_Timer")) {
				trialCanEnd = true;
			}
			//Record if trial ends, record trial, reset trial events
			else if (trialCanEnd && events[iterator].getItem_Name().equals("TrayLight #1")) {
				Event[] trialEventsArray = new Event[trialEvents.size()];
				trialEventsArray = trialEvents.toArray(trialEventsArray);
				trials.add(new Trial(trialEventsArray));
				trialEvents = new ArrayList<>();
				trialCanEnd = false;
				continue;
			}
			//Otherwise it hasn't ended, add event to trial
			trialEvents.add(events[iterator]);
			iterator++;
		}
		return trials;
	}

}
