package dataModels;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Event {

	private float event_Time;

	private int event_Id;

	private String event_Name;

	private String item_Name;

	private String alias_Name;

	private int group_Id;

	private EventArgument[] args;

	public Event(String line) {
		String[] splitline = line.split(",");

		this.event_Time = Float.parseFloat(splitline[0]);
		this.event_Id = Integer.parseInt(splitline[1]);
		this.event_Name = splitline[2];
		this.item_Name = splitline[3];
		this.alias_Name = splitline[4];
		this.group_Id = Integer.parseInt(splitline[5]);

		int numberOfArgs = Integer.parseInt(splitline[6]);

		if (numberOfArgs > 0) {
			args = new EventArgument[numberOfArgs];
			for (int i = 0, j = 7; i < numberOfArgs; i++, j += 2) {
				args[i] = new EventArgument(splitline[j], Float.parseFloat(splitline[j + 1]));
			}
		} else {
			args = null;
		}
	}

	// For defining custom reference events
	public Event(String event_name, String item_name, String[] eventNames, float[] eventValues) {
		this.event_Name = event_name;
		this.item_Name = item_name;

		if (eventNames != null) {
			int numberOfArgs = eventNames.length;
			this.args = new EventArgument[numberOfArgs];
			for (int i = 0; i < numberOfArgs; i++) {
				args[i] = new EventArgument(eventNames[i], eventValues[i]);
			}
		} else
			this.args = null;
	}

	/**
	 * @return the event_Time
	 */
	public float getEvent_Time() {
		return event_Time;
	}

	/**
	 * @return the event_Id
	 */
	public int getEvent_Id() {
		return event_Id;
	}

	/**
	 * @return the event_Name
	 */
	public String getEvent_Name() {
		return event_Name;
	}

	/**
	 * @return the item_Name
	 */
	public String getItem_Name() {
		return item_Name;
	}

	/**
	 * @return the alias_Name
	 */
	public String getAlias_Name() {
		return alias_Name;
	}

	/**
	 * @return the group_Id
	 */
	public int getGroup_Id() {
		return group_Id;
	}

	public String getArgumentName(int index) {
		if (args == null || (index - 1) > (args.length - 1)) {
			return null;
		}
		return args[index - 1].getName();
	}

	public float getArgumentValue(int index) {
		if (args == null || (index - 1) > (args.length - 1)) {
			return 0;
		}
		return args[index - 1].getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Event [event_Time=" + event_Time + ", event_Id=" + event_Id + ", event_Name=" + event_Name
				+ ", item_Name=" + item_Name + ", alias_Name=" + alias_Name + ", group_Id=" + group_Id + ", args="
				+ Arrays.toString(args) + "]";
	}

	public boolean equals(Event other) {
		if (this.event_Name.equals(other.getEvent_Name()) && this.item_Name.equals(other.getItem_Name())) {
			if (this.args != null) {
				for (int i = 0; i < (this.args.length); i++) {
					if (!args[i].getName().equals(other.getArgumentName(i))) {
						return false;
					}
				}
			}
			return true;
		} else
			return false;
	}
	
	public static Event[] readEventsFromFile (File file) {
		try {
			Scanner sc = new Scanner(file);
			
			ArrayList <Event> events = new ArrayList<Event>();
			
			//Read in all events
			while(sc.hasNextLine()) {
				events.add(new Event(sc.nextLine()));
			}
			
			Event[] eventArray = new Event[events.size()];
			
			sc.close();
			
			return events.toArray(eventArray);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("Test file not found");
			return null;
		}
	}

}
