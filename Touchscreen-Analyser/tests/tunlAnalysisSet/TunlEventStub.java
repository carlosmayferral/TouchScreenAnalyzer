package tunlAnalysisSet;

import dataModels.Event;

public class TunlEventStub extends Event {
	
	private boolean transitionEvent;
	
	private boolean trialStart;
	
	private int randomInt;

	private String eventName;

	private String itemName;
	
	private static String[][] possibleEvents = 
		{{"Evnt_Name","Item_Name"},
			{"Input Definition","(SYSTEM)"},
			{"Output Definition","(SYSTEM)"},
			{"Variable Event","_Schedule_Timer"},
			{"Variable Event","_Trial_Counter"},
			{"Variable Event","_Trial_Timer"},
			{"Variable Event","A1Sound1"},
			{"Variable Event","A2Sound2"},
			{"Variable Event","A3Sound3"},
			{"Variable Event","All_Trials_Count"},
			{"Variable Event","Analysed"},
			{"Variable Event","aTrial_Set"},
			{"Variable Event","Centre_touches_during_ITI"},
			{"Variable Event","Corr_ITI"},
			{"Variable Event","Correct_Counter"},
			{"Variable Event","Correct_Percent"},
			{"Variable Event","Correct_Position"},
			{"Variable Event","Correction_Counter"},
			{"Variable Event","Correction_Trial"},
			{"Variable Event","Correction_Trial_Correct_Counter"},
			{"Variable Event","Correction_Trials_Set"},
			{"Variable Event","Delay"},
			{"Variable Event","Delay_Timer"},
			{"Variable Event","Feeder_Pulse_Time"},
			{"Variable Event","First_Analysis"},
			{"Variable Event","Houselight_Normally_On"},
			{"Variable Event","Image_alert_sound_on"},
			{"Variable Event","Increment_this_trial"},
			{"Variable Event","ITI"},
			{"Variable Event","ITI_Timer"},
			{"Variable Event","Left_Touches_during_ITI"},
			{"Variable Event","No_Incorrects_before_Correction_Trial"},
			{"Variable Event","NonRewarded"},
			{"Variable Event","Right_touches_during_ITI"},
			{"Variable Event","Sample_Position"},
			{"Variable Event","Sample_reward_pulse"},
			{"Variable Event","Sample_Rewarded"},
			{"Variable Event","Separation_Level"},
			{"Variable Event","Sequential_Correction_Trials"},
			{"Variable Event","SkipTrial_inc"},
			{"Variable Event","Time_Out"},
			{"Variable Event","Time_Out_Timer"},
			{"Variable Event","Tone_Duration"},
			{"Variable Event","Total_No_Correction_Trials"},
			{"Variable Event","Tray_Light_Pulse"},
			{"Variable Event","zBB_ITI"},
			{"Variable Event","zFB_ITI"},
			{"Variable Event","zPsCr_to_Cr"},
			{"Variable Event","zPsIC_to_IC"},
			{"Image List Definition","Background"},
			{"List Array - Write","Background"},
			{"List Change - Set","Background"},
			{"Image List Definition","Image"},
			{"List Array - Write","Image"},
			{"List Change - Set","Image"},
			{"List Array - Write","Reward_Sample"},
			{"List Change - Set","Reward_Sample"},
			{"List Array - Write","Trial_Type"},
			{"List Change - Set","Trial_Type"},
			{"Schedule Startup Event","(SYSTEM)"},
			{"Timer Event","_Schedule_Timer"},
			{"Group Change Event","Group Change"},
			{"Condition Event","Set Blank Images"},
			{"Whisker - Display Image","Bussey Mouse Operant Mode 5 x 1 x low"},
			{"Output On Event","White_Noise_Off #1"},
			{"Condition Event","Reset Sound Levels to Zero"},
			{"Output Off Event","Sound #1"},
			{"Output Off Event","Sound #2"},
			{"Output Off Event","Sound #3"},
			{"Condition Event","Adjust Sound Level 2"},
			{"Output On Event","Sound #2"},
			{"Condition Event","NoTone Fall Through"},
			{"Condition Event","Tone set"},
			{"Condition Event","Free Feed"},
			{"Output On Event","TrayLight #1"},
			{"Pulse Output Event","Feeder #1"},
			{"Pulse Output Event","Sound_On #1"},
			{"Input Transition On Event","FIRBeam #1"},
			{"Input Transition Off Event","FIRBeam #1"},
			{"Input Transition On Event","BIRBeam #1"},
			{"Input Transition On Event","Tray #1"},
			{"Output Off Event","TrayLight #1"},
			{"Condition Event","Select Trial Type"},
			{"List Change - Next Value","Trial_Type"},
			{"Condition Event","Trial Set 43"},
			{"Condition Event","Display Sample"},
			{"Input Transition Off Event","Tray #1"},
			{"Input Transition Off Event","BIRBeam #1"},
			{"Touch Down Event","Bussey Mouse Operant Mode 5 x 1 x low"},
			{"Condition Event","Start Delay"},
			{"Timer Event","Delay_Timer"},
			{"List Change - Next Value","Reward_Sample"},
			{"Condition Event","Sample Touch not rewarded"},
			{"Condition Event","Delay End"},
			{"Touch Up Event","Bussey Mouse Operant Mode 5 x 1 x low"},
			{"Condition Event","Choice Stage"},
			{"Condition Event","Correct"},
			{"Condition Event","Increment counter"},
			{"Group Maintain Event","Group Change"},
			{"Condition Event","Reward Collected Start ITI"},
			{"Timer Event","ITI_Timer"},
			{"Condition Event","Front beam breaks during ITI"},
			{"Condition Event","Left Screen Touches"},
			{"Condition Event","Right Screen Touches"},
			{"Condition Event","Centre Screen Touches"},
			{"Condition Event","Next trial non corrected Trial"},
			{"Condition Event","Tray Entry and Increment Trial"},
			{"Condition Event","Trial set 23"},
			{"Condition Event","Trial set 35"},
			{"Condition Event","Incorrect"},
			{"Timer Event","Time_Out_Timer"},
			{"Condition Event","TO Houselight On"},
			{"Output On Event","HouseLight #1"},
			{"Condition Event","Not correcting"},
			{"Condition Event","Incorrect Trails to Correction Trial"},
			{"Condition Event","Change ITI time"},
			{"Condition Event","Perse.Incorrect to Incorrect"},
			{"Condition Event","Time Out End"},
			{"Condition Event","Reset Houselight off"},
			{"Output Off Event","HouseLight #1"},
			{"Condition Event","Start Correction Trial"},
			{"Condition Event","Tray Entry Noted Trial Increment"},
			{"Condition Event","Tray Exit Starts Correction Trial"},
			{"Condition Event","Sequential Correction Trials"},
			{"Condition Event","Back beam breaks during ITI"},
			{"Condition Event","Tray Entry Noted"},
			{"Condition Event","Reset Correction Flag"},
			{"Condition Event","Tray Entry no Trial Increment"},
			{"Condition Event","Trial set 31"},
			{"Condition Event","Trial set 13"},
			{"Condition Event","Trial set 53"},
			{"Condition Event","Trial Set 32"},
			{"Condition Event","Trial Set 34"},
			{"Condition Event","Perse.Correct to Correct"},
			{"Schedule Shutdown Event","(SYSTEM)"}};

	
	public TunlEventStub(int rand) {
		super(null, null, null, null);
		transitionEvent = false;
		randomInt = rand;
		
		int randIndex = Math.abs(randomInt % possibleEvents.length);
		
		eventName = possibleEvents[randIndex][0];
		itemName = possibleEvents[randIndex][1];
	}
	
	public void setTrialStart() {
		trialStart=true;
	}

	@Override
	public String getItem_Name() {
		if (transitionEvent) return "Next trial";
		else if (trialStart) return "Tray Entry starts first trial";
		
		return itemName;
	}
	
	

	public void setTrialTransition() {
		this.transitionEvent = true;
	}

	@Override
	public String getEvent_Name() {
		return eventName;
	}
	
	
	
	
	
}
