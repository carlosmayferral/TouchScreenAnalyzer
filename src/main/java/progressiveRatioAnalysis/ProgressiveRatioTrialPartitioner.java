package progressiveRatioAnalysis;

import java.util.ArrayList;

import analysisSets.ITrialPartitioner;
import dataModels.Event;
import dataModels.Trial;
import progressiveRatioAnalysis.analyzerHelpers.ProgressiveRatioReferenceEvents;

public class ProgressiveRatioTrialPartitioner implements ITrialPartitioner {

	@Override
	public ArrayList<Trial> partition(Event[] events) {

		ArrayList<Trial> trials = new ArrayList<Trial>();

		ArrayList<Event> trialEvents = new ArrayList<Event>();

		int i = 0;

		// while there is still raw events
		while (i < events.length) {

			Event currentEvent = events[i];

			// add event
			trialEvents.add(currentEvent);

			// if end of trial is detected, form trial and reset list
			if (currentEvent.equals(ProgressiveRatioReferenceEvents.END_TRIAL_EVENT)) {
				Event[] eventsCopy = new Event[trialEvents.size()];
				trials.add(new Trial((Event[]) trialEvents.toArray(eventsCopy)));
				trialEvents = new ArrayList<Event>();
			}

			// if session timed out, still form a trial
			if (currentEvent.equals(ProgressiveRatioReferenceEvents.TIMEOUT_EVENT)
					|| currentEvent.equals(ProgressiveRatioReferenceEvents.SCHEDULE_SHUTDOWN_EVENT)) {
				Event[] eventsCopy = new Event[trialEvents.size()];
				trials.add(new Trial((Event[]) trialEvents.toArray(eventsCopy)));
				break;
			}
			i++;
		}

		return trials;
	}
}
