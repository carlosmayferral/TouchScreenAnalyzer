package dataModels;

import java.util.ArrayList;

public class Trial {
	
	private ArrayList <Event> events;
	
	public Trial(ArrayList<Event> events) {
		this.events = events;
	}
	
	public ArrayList <Event> getEventList(){
		return this.events;
	}
	
	public ArrayList <Event> copyEventList(){
		return new ArrayList<Event>(events);
	}
	
	public Event[] copyEventsAsArray() {
		Event[] eventsArray = new Event[events.size()];
		events.toArray(eventsArray);
		return eventsArray;
	}

}
