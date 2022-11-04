package tunlAnalysisSet;

import java.util.Random;

import dataModels.Event;
import dataModels.Trial;

public class TunlTrialStub extends Trial {
	
	private Event[] events;
	private int randomInt;

	public TunlTrialStub(int randomInt, int sizeOfTrial) {
		//invoke constructor with nothing
		super(null);
		
		this.randomInt = randomInt;
		
		events = new Event[sizeOfTrial];
		
		//rng for generating each trial
		Random rng = new Random(randomInt);
		
		for (int i = 0; i < sizeOfTrial; i++) {
			Event newEvent = new TunlEventStub(rng.nextInt());
			events[i] = newEvent;
		}
		
	}

	public void replaceEventWith(int position, Event event) {
		events[position] = event;
	}
	
	@Override
	public Event[] copyEventsAsArray() {
		return events;
	}

	public void removeEvent(Event event) {
		
		Random rng = new Random(randomInt);
		rng.nextInt();
		
		for (int i = 0; i< events.length; i++) {
			while(events[i].getItem_Name().equals(event.getItem_Name()) &&
					events[i].getEvent_Name().equals(event.getEvent_Name())) {
				events[i] = new TunlEventStub(rng.nextInt());
			}
		}
		
	}
	
	public void replaceAllEvents(Event event, Event newEvent) {
		for (int i = 0; i< events.length; i++) {
			while(events[i].getItem_Name().equals(event.getItem_Name()) &&
					events[i].getEvent_Name().equals(event.getEvent_Name())) {
				events[i] = new Event(newEvent.getEvent_Name(), newEvent.getItem_Name(), null, null);
			}
		}
	}
	
	public void printEvents() {
		for (Event event: events) {
			System.out.println(event.getEvent_Name() + " , " + event.getItem_Name());
		}
	}
	
	

}
