package dataModels;

import java.util.ArrayList;

public class Trial {
	
	private ArrayList <String> eventList;
	
	public Trial() {
		eventList = new ArrayList<String>();
	}
	
	public String[] getNextEvent(){
		if (eventList.size() > 0) {
			return eventList.remove(0).split(",");
		}
		else {
			return null;
		}
	}

}
