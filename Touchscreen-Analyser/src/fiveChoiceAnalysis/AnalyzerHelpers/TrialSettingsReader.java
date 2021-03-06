package fiveChoiceAnalysis.AnalyzerHelpers;


import dataModels.Event;

 public class TrialSettingsReader {

	private float stimulusDuration;
	
	private float stimulusDelay;
	
	private float stimulusBrightness;
	
	private int targetLocation;
	
	private int playDistracter;
	
	private float timeToDistractor;
	
	public TrialSettingsReader() {
		this.stimulusBrightness = Float.NaN;
		this.stimulusDelay = Float.NaN;
		this.stimulusDuration = Float.NaN;
		this.targetLocation = -1;
		this.playDistracter = -1;
		this.timeToDistractor = Float.NaN;
	}
	
	public void scanEvents(Event[] events) {
		
		for (Event event : events) {
			switch(event.getItem_Name()) {
			case "Stimulus_Duration":
				this.stimulusDuration = event.getArgumentValue(1);
				continue;
			case "Delay":
				this.stimulusDelay = event.getArgumentValue(1);
				continue;
			case "Location":
				this.targetLocation = (int)event.getArgumentValue(1);
				continue;
			case "Dim_Value":
				this.stimulusBrightness = event.getArgumentValue(2);
				continue;
			case "Grid_Location":
				if (event.getEvent_Name().equals("List Change - Next Value")) {
				this.targetLocation = (int)event.getArgumentValue(2);
				}
				continue;
			case "Time_to_Distracter_Value":
				this.timeToDistractor = event.getArgumentValue(1);
				continue;
			case "Play_Distracter":
				if (this.playDistracter == -1) {
					this.playDistracter = (int)event.getArgumentValue(1);
				}
				continue;
			case "Bussey Mouse Operant Mode 5 x 1 x low":
			case "Bussey Rat Operant Mode 5 x 1 x low (Portrait)":
				if (event.getEvent_Name().equals("Whisker - Display Image")) {
					break;
				}
			}
		}
		
	}

	/**
	 * @return the stimulusDuration
	 */
	public float getStimulusDuration() {
		return stimulusDuration;
	}

	/**
	 * @return the stimulusDelay
	 */
	public float getStimulusDelay() {
		return stimulusDelay;
	}

	/**
	 * @return the stimulusBrightness
	 */
	public float getStimulusBrightness() {
		return stimulusBrightness;
	}

	/**
	 * @return the targetLocation
	 */
	public int getTargetLocation() {
		return targetLocation;
	}

	/**
	 * @return the playDistracter
	 */
	public int getPlayDistracter() {
		return playDistracter;
	}

	/**
	 * @return the timeToDistractor
	 */
	public float getTimeToDistractor() {
		return timeToDistractor;
	}
}
