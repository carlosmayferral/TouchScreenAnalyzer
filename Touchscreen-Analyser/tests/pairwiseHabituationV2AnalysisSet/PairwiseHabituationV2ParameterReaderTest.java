package pairwiseHabituationV2AnalysisSet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataModels.Event;

class PairwiseHabituationV2ParameterReaderTest {
	
	private PairwiseHabituationV2ParameterReader pr;

	@BeforeEach
	void setUp() throws Exception {
		pr = new PairwiseHabituationV2ParameterReader();
	}

	@Test
	void readParametersReturnsEmptyParametersForEmptyTrial() {
		String input = "";
		Event[] events = Event.readEventsFromString(input);
		PairwiseHabituationV2Parameters parameters = (PairwiseHabituationV2Parameters) pr.readParameters(events);
		assertEquals(null,parameters.getDelayTime());
		assertEquals(null,parameters.getFeederPulseTimeMilliseconds());
		assertEquals(null,parameters.getPrimeFeedPulseTimeMilliseconds());
	}
	
	@Test
	void readParametersReturnsEmptyParametersForIrrelevantTrials() {
		String input = "0.051,3,Output On Event,TrayLight #1,,2,0,,,,,,,,,,\r\n"
				+ "0.051,7,Pulse Output Event,Feeder #1,,2,1,Duration,6,,,,,,,,\r\n"
				+ "0.051,1,Condition Event,Pulse Tone if required,,2,0,,,,,,,,,,\r\n"
				+ "0.051,7,Pulse Output Event,Sound_On #1,,2,1,Duration,1,,,,,,,,\r\n"
				+ "0.051,21,Group Change Event,Group Change,,2,1,New Group,3,,,,,,,,\r\n"
				+ "6.356,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "8.32,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "8.32,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "8.322,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,";
		Event[] events = Event.readEventsFromString(input);
		PairwiseHabituationV2Parameters parameters = (PairwiseHabituationV2Parameters) pr.readParameters(events);
		assertEquals(null,parameters.getDelayTime());
		assertEquals(null,parameters.getFeederPulseTimeMilliseconds());
		assertEquals(null,parameters.getPrimeFeedPulseTimeMilliseconds());
	}
	
	@Test
	void readParametersReadsDelayTime() {
		String input = "0,28,Input Definition,(SYSTEM),,0,5,Tray #1,0,BIRBeam #1,1,FIRBeam #1,2,RightFIRBeam #1,3,FeederFault #1,4\r\n"
				+ "0,28,Input Definition,(SYSTEM),,0,1,FeederReport #1,5,,,,,,,,\r\n"
				+ "0,27,Output Definition,(SYSTEM),,0,5,HouseLight #1,0,TrayLight #1,1,Feeder #1,2,Sound_On #1,3,White_Noise_Off #1,4\r\n"
				+ "0,27,Output Definition,(SYSTEM),,0,4,Sound #1,5,Sound #2,6,Sound #3,7,Feeder #2,8,,\r\n"
				+ "0,16,Variable Event,_Schedule_Timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,_Trial_Counter,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,_Trial_Timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,A1Sound1,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,A2Sound2,,0,1,Value,1,,,,,,,,\r\n"
				+ "0,16,Variable Event,A3Sound3,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Acclimatisation_time,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Acclimatisation_timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Delay_Time,,0,1,Value,10,,,,,,,,\r\n"
				+ "0,16,Variable Event,Delay_Timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Feeder_Pulse_Time,,0,1,Value,280,,,,,,,,\r\n"
				+ "0,16,Variable Event,Houselight_Normally_On,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Prime_Feed_Time,,0,1,Value,6000,,,,,,,,\r\n"
				+ "0,16,Variable Event,Pulse_Tone,,0,1,Value,1,,,,,,,,\r\n"
				+ "0,16,Variable Event,Tone_Duration,,0,1,Value,1000,,,,,,,,\r\n"
				+ "0,37,Image List Definition,Background,,0,1,Bussey Mouse Operant Mode 2 x 1 x low,3,,,,,,,,";
		Event[] events = Event.readEventsFromString(input);
		PairwiseHabituationV2Parameters parameters = (PairwiseHabituationV2Parameters) pr.readParameters(events);
		assertEquals(10.0,parameters.getDelayTime());
	}
	
	@Test
	void readParametersReadsPulseTime() {
		String input = "0,28,Input Definition,(SYSTEM),,0,5,Tray #1,0,BIRBeam #1,1,FIRBeam #1,2,RightFIRBeam #1,3,FeederFault #1,4\r\n"
				+ "0,28,Input Definition,(SYSTEM),,0,1,FeederReport #1,5,,,,,,,,\r\n"
				+ "0,27,Output Definition,(SYSTEM),,0,5,HouseLight #1,0,TrayLight #1,1,Feeder #1,2,Sound_On #1,3,White_Noise_Off #1,4\r\n"
				+ "0,27,Output Definition,(SYSTEM),,0,4,Sound #1,5,Sound #2,6,Sound #3,7,Feeder #2,8,,\r\n"
				+ "0,16,Variable Event,_Schedule_Timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,_Trial_Counter,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,_Trial_Timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,A1Sound1,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,A2Sound2,,0,1,Value,1,,,,,,,,\r\n"
				+ "0,16,Variable Event,A3Sound3,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Acclimatisation_time,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Acclimatisation_timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Delay_Time,,0,1,Value,10,,,,,,,,\r\n"
				+ "0,16,Variable Event,Delay_Timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Feeder_Pulse_Time,,0,1,Value,280,,,,,,,,\r\n"
				+ "0,16,Variable Event,Houselight_Normally_On,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Prime_Feed_Time,,0,1,Value,6000,,,,,,,,\r\n"
				+ "0,16,Variable Event,Pulse_Tone,,0,1,Value,1,,,,,,,,\r\n"
				+ "0,16,Variable Event,Tone_Duration,,0,1,Value,1000,,,,,,,,\r\n"
				+ "0,37,Image List Definition,Background,,0,1,Bussey Mouse Operant Mode 2 x 1 x low,3,,,,,,,,";
		Event[] events = Event.readEventsFromString(input);
		PairwiseHabituationV2Parameters parameters = (PairwiseHabituationV2Parameters) pr.readParameters(events);
		assertEquals(280,parameters.getFeederPulseTimeMilliseconds());
	}
	
	@Test
	void readParametersReadsPrimeTime() {
		String input = "0,28,Input Definition,(SYSTEM),,0,5,Tray #1,0,BIRBeam #1,1,FIRBeam #1,2,RightFIRBeam #1,3,FeederFault #1,4\r\n"
				+ "0,28,Input Definition,(SYSTEM),,0,1,FeederReport #1,5,,,,,,,,\r\n"
				+ "0,27,Output Definition,(SYSTEM),,0,5,HouseLight #1,0,TrayLight #1,1,Feeder #1,2,Sound_On #1,3,White_Noise_Off #1,4\r\n"
				+ "0,27,Output Definition,(SYSTEM),,0,4,Sound #1,5,Sound #2,6,Sound #3,7,Feeder #2,8,,\r\n"
				+ "0,16,Variable Event,_Schedule_Timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,_Trial_Counter,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,_Trial_Timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,A1Sound1,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,A2Sound2,,0,1,Value,1,,,,,,,,\r\n"
				+ "0,16,Variable Event,A3Sound3,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Acclimatisation_time,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Acclimatisation_timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Delay_Time,,0,1,Value,10,,,,,,,,\r\n"
				+ "0,16,Variable Event,Delay_Timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Feeder_Pulse_Time,,0,1,Value,280,,,,,,,,\r\n"
				+ "0,16,Variable Event,Houselight_Normally_On,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Prime_Feed_Time,,0,1,Value,6000,,,,,,,,\r\n"
				+ "0,16,Variable Event,Pulse_Tone,,0,1,Value,1,,,,,,,,\r\n"
				+ "0,16,Variable Event,Tone_Duration,,0,1,Value,1000,,,,,,,,\r\n"
				+ "0,37,Image List Definition,Background,,0,1,Bussey Mouse Operant Mode 2 x 1 x low,3,,,,,,,,";
		Event[] events = Event.readEventsFromString(input);
		PairwiseHabituationV2Parameters parameters = (PairwiseHabituationV2Parameters) pr.readParameters(events);
		assertEquals(6000,parameters.getPrimeFeedPulseTimeMilliseconds());
	}
	
	@Test
	void readParametersOnlyReadsFirstIteration() {
		String input = "0,28,Input Definition,(SYSTEM),,0,5,Tray #1,0,BIRBeam #1,1,FIRBeam #1,2,RightFIRBeam #1,3,FeederFault #1,4\r\n"
				+ "0,28,Input Definition,(SYSTEM),,0,1,FeederReport #1,5,,,,,,,,\r\n"
				+ "0,27,Output Definition,(SYSTEM),,0,5,HouseLight #1,0,TrayLight #1,1,Feeder #1,2,Sound_On #1,3,White_Noise_Off #1,4\r\n"
				+ "0,27,Output Definition,(SYSTEM),,0,4,Sound #1,5,Sound #2,6,Sound #3,7,Feeder #2,8,,\r\n"
				+ "0,16,Variable Event,_Schedule_Timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,_Trial_Counter,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,_Trial_Timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,A1Sound1,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,A2Sound2,,0,1,Value,1,,,,,,,,\r\n"
				+ "0,16,Variable Event,A3Sound3,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Acclimatisation_time,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Acclimatisation_timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Delay_Time,,0,1,Value,10,,,,,,,,\r\n"
				+ "0,16,Variable Event,Delay_Timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Feeder_Pulse_Time,,0,1,Value,280,,,,,,,,\r\n"
				+ "0,16,Variable Event,Houselight_Normally_On,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Prime_Feed_Time,,0,1,Value,6000,,,,,,,,\r\n"
				+ "0,16,Variable Event,Pulse_Tone,,0,1,Value,1,,,,,,,,\r\n"
				+ "0,16,Variable Event,Tone_Duration,,0,1,Value,1000,,,,,,,,\r\n"
				+ "0,16,Variable Event,Delay_Time,,0,1,Value,16,,,,,,,,\r\n"
				+ "0,16,Variable Event,Delay_Timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Feeder_Pulse_Time,,0,1,Value,280,,,,,,,,\r\n"
				+ "0,16,Variable Event,Houselight_Normally_On,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Prime_Feed_Time,,0,1,Value,6000,,,,,,,,\r\n"
				+ "0,37,Image List Definition,Background,,0,1,Bussey Mouse Operant Mode 2 x 1 x low,3,,,,,,,,";
		Event[] events = Event.readEventsFromString(input);
		PairwiseHabituationV2Parameters parameters = (PairwiseHabituationV2Parameters) pr.readParameters(events);
		assertEquals(10.0,parameters.getDelayTime());
	}
	
	@Test
	void readParametersReadsAllThreeThings() {
		String input = "0,28,Input Definition,(SYSTEM),,0,5,Tray #1,0,BIRBeam #1,1,FIRBeam #1,2,RightFIRBeam #1,3,FeederFault #1,4\r\n"
				+ "0,28,Input Definition,(SYSTEM),,0,1,FeederReport #1,5,,,,,,,,\r\n"
				+ "0,27,Output Definition,(SYSTEM),,0,5,HouseLight #1,0,TrayLight #1,1,Feeder #1,2,Sound_On #1,3,White_Noise_Off #1,4\r\n"
				+ "0,27,Output Definition,(SYSTEM),,0,4,Sound #1,5,Sound #2,6,Sound #3,7,Feeder #2,8,,\r\n"
				+ "0,16,Variable Event,_Schedule_Timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,_Trial_Counter,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,_Trial_Timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,A1Sound1,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,A2Sound2,,0,1,Value,1,,,,,,,,\r\n"
				+ "0,16,Variable Event,A3Sound3,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Acclimatisation_time,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Acclimatisation_timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Delay_Time,,0,1,Value,10,,,,,,,,\r\n"
				+ "0,16,Variable Event,Delay_Timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Feeder_Pulse_Time,,0,1,Value,280,,,,,,,,\r\n"
				+ "0,16,Variable Event,Houselight_Normally_On,,0,1,Value,0,,,,,,,,\r\n"
				+ "0,16,Variable Event,Prime_Feed_Time,,0,1,Value,6000,,,,,,,,\r\n"
				+ "0,16,Variable Event,Pulse_Tone,,0,1,Value,1,,,,,,,,\r\n"
				+ "0,16,Variable Event,Tone_Duration,,0,1,Value,1000,,,,,,,,\r\n"
				+ "0,37,Image List Definition,Background,,0,1,Bussey Mouse Operant Mode 2 x 1 x low,3,,,,,,,,";
		Event[] events = Event.readEventsFromString(input);
		PairwiseHabituationV2Parameters parameters = (PairwiseHabituationV2Parameters) pr.readParameters(events);
		assertEquals(10.0,parameters.getDelayTime());
		assertEquals(280,parameters.getFeederPulseTimeMilliseconds());
		assertEquals(6000,parameters.getPrimeFeedPulseTimeMilliseconds());
	}

}
