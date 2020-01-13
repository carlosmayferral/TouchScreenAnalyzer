package tunlAnalysisSet;

import dataModels.Event;

public class TunlEventStub extends Event {
	
	private boolean transitionEvent;
	
	private boolean trialStart;
	
	private int randomInt;
	
	public TunlEventStub(int rand) {
		super(null, null, null, null);
		transitionEvent = false;
		randomInt = rand;
	}
	
	public void setTrialStart() {
		trialStart=true;
	}

	@Override
	public String getItem_Name() {
		if (transitionEvent) return "Next trial";
		else if (trialStart) return "Tray Entry starts first trial";
		
		String[] itemNameOptions = {"Delay_Timer",
				"Group Change",
				"Bussey Mouse Operant Mode 5 x 1 x low",
				"FIRBeam #1",
				"BIRBeam #1",
				"Choice Stage",
				"TrayLight #1",
				"Sound_On #1",
				"Correct",
				"Feeder #1",
				"Correction_Counter",
				"Increment counter",
				"Correct_Counter",
				"First_Analysis",
				"Tray #1",
				"Reward Collected Start ITI",
				"ITI_Timer",
				"Front beam breaks during ITI",
				"zFB_ITI",
				"SkipTrial_inc",
				"ITI",
				"Next trial non corrected Trial",
		};
		
		return itemNameOptions[Math.abs(randomInt % itemNameOptions.length)];
	}

	public void setTrialTransition() {
		this.transitionEvent = true;
	}
	
	
	
	
	
}
