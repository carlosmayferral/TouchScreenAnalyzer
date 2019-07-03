package cptBaselineAnalysis;

import java.util.ArrayList;

import analysisSets.ITrialPartitioner;
import cptBaselineAnalysis.analyzerHelpers.CPTBaselineReferenceEvents;
import dataModels.Event;
import dataModels.Trial;

class CPTBaselineTrialPartitioner implements ITrialPartitioner {

	@Override
	public ArrayList<Trial> partition(Event[] events) {
		
		ArrayList <Trial>trials = new ArrayList<Trial>();
		
		int iterator = 0;
		
		//Iterate through array until the start of trials is found
		while ((!events[iterator].getItem_Name().contains("Start ITI")) && (iterator+1) < events.length) {
			iterator++;
		}
		
		ArrayList<Event> eventsInTrial = new ArrayList<Event>();
		
		//Add first ITI event
		eventsInTrial.add(events[iterator]);
		iterator++;
		
		//While there is still events to go...
		while (iterator < events.length) {
			
			//If event is an end of trial, must accommodate for different starts in the distracter
			if (events[iterator].equals(CPTBaselineReferenceEvents.ITI_START) || 
					events[iterator].equals(CPTBaselineReferenceEvents.CORRECTION_ITI_START)
						|| events[iterator].getItem_Name().contains("Start ITI-")
							|| events[iterator].getItem_Name().contains("Start ITI -")) {
				//do not add event...
				
				//create trial with current events
				Event[] eventArray = new Event[eventsInTrial.size()];
				trials.add(new Trial(eventsInTrial.toArray(eventArray)));
				
				//reset events in trial
				eventsInTrial = new ArrayList<Event> ();
				
				//Add the event and increase iterator
				eventsInTrial.add(events[iterator]);
				iterator++;
			}
			//Else, add the event and continue
			else {
				eventsInTrial.add(events[iterator]);
				iterator++;
			}
			
			
			
		}
		
		return trials;
		
	}

}
