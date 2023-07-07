package dataModels;

import java.util.ArrayList;
import java.util.Arrays;

public class Trial {

	private Event[] events;

	public Trial(Event[] events2) {
		this.events = events2;
	}

	public Event[] copyEventsAsArray() {
		return Arrays.copyOf(events, events.length);
	}
	
	/**
	 * Searches a list of trials for one that should contain a certain time stamp.
	 * Returns null if no such trial exists.
	 * @param trials
	 * @param timeStamp
	 * @return A trial that should contain the specified time stamp. 
	 */
	public static Trial getTrialByTimeStamp(ArrayList<Trial> trials, float timeStamp) {
		for (int i = 1; i < trials.size(); i++) {
			if (trials.get(i).events[0].getEvent_Time() > timeStamp) {
				return trials.get(i-1);
			}
		}
		return null;
	}

}
