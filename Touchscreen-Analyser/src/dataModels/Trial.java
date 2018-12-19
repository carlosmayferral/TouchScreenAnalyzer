package dataModels;

import java.util.Arrays;

public class Trial {

	private Event[] events;

	public Trial(Event[] events2) {
		this.events = events2;
	}

	public Event[] copyEventsAsArray() {
		return Arrays.copyOf(events, events.length);
	}

}
