package touchscreenAnalyzer;

import java.util.ArrayList;

public class TinaTrialPartitioner implements ITrialPartitioner {

	@Override
	public ArrayList<Trial> partition (ArrayList <Event> events) {
		
		ArrayList<Trial> trials = new ArrayList<Trial>();
		
		//While there is events left in the list
		while(!events.isEmpty()) {
			
			Event current_event = events.remove(0);
			
			//This is the logic that determines if a trial has started
			if (current_event.getEvent_Name().equals("Whisker - Display Image")
					&& current_event.getArgumentName(2).equals("CentralStimulus")) {
				
				//Initialize event list for trial
				ArrayList<Event> trialEvents = new ArrayList<Event>();
				
				//add first event
				trialEvents.add(current_event);
				
				//While the trial has not finished... Add events to trial
				while (!events.isEmpty() && !(current_event = events.remove(0)).getItem_Name().equals("Select Trial Type")) {
					trialEvents.add(current_event);
				}
				
				//Add last event to trial, after finish was detected
				if (!events.isEmpty()) {
					trialEvents.add(current_event);
				}
				
				//Ensure trial integrity, verify end of trial was reached.
				if (trialEvents.get(trialEvents.size()-1).getItem_Name().equals("Select Trial Type")) {
					trials.add(new Trial(trialEvents));
				}
				
				
			}
			
			
		}
		return trials;
		
	}

	@Override
	public SessionParameters getParameters(ArrayList<Event> events) {
		
		float cue_time = Float.NaN;
		float hold_time = Float.NaN;
		
		for(Event event: events) {
			if (event.getItem_Name().equals("Cue_Time")) {
				cue_time = event.getArgumentValue(1);
			}
			if (event.getItem_Name().equals("Hold_Time")) {
				hold_time = event.getArgumentValue(1);
			}
			if ((cue_time >= 0) && (hold_time >= 0)) {
				return new TinaSessionParameters(cue_time, hold_time);
			}
		}
		
		return null;
	}

}
