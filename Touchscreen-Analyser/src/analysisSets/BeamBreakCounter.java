package analysisSets;

import dataModels.Event;

public class BeamBreakCounter{
	private int frontBeamBreaks;
	private int backBeamBreaks;
	private int trayBeamBreaks;
	
	public BeamBreakCounter(Event[] events) {
		this.frontBeamBreaks = 0;
		this.backBeamBreaks = 0;
		this.trayBeamBreaks = 0;
		
		for (Event event : events) {
			if (event.getEvent_Name().equals("Input Transition On Event")) {
				switch (event.getItem_Name()) {
				case "FIRBeam #1":
					this.frontBeamBreaks++;
					continue;
				case "BIRBeam #1":
					this.backBeamBreaks++;
					continue;
				case "Tray #1":
					this.trayBeamBreaks++;
					continue;
				}
			}
		}
		
	}
	
	public int getFrontBeamBreaks() {
		return frontBeamBreaks;
	}
	
	public int getBackBeamBreaks() {
		return backBeamBreaks;
	}
	
	public int getTrayBeamBreaks() {
		return this.trayBeamBreaks;
	}
	
}