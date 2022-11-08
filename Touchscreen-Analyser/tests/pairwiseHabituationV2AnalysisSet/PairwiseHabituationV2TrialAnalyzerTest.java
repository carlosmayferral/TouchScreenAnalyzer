package pairwiseHabituationV2AnalysisSet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataModels.Event;
import dataModels.Trial;

class PairwiseHabituationV2TrialAnalyzerTest {
	
	private PairwiseHabituationV2TrialAnalyzer analyzer;
	private static PairwiseHabituationV2Parameters premadeParameters;

	@BeforeEach
	void setUp() throws Exception {
		analyzer = new PairwiseHabituationV2TrialAnalyzer();
	}
	
	@BeforeAll
	static void getParameters() {
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
				+ "0,16,Variable Event,Tone_Duration,,0,1,Value,1000,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		premadeParameters = (PairwiseHabituationV2Parameters) new PairwiseHabituationV2ParameterReader().readParameters(trial.copyEventsAsArray());
	}
	
	@Test
	void analyzerReturnsNullWhenEmptyTrialProvided() {
		String input = "";
		Trial trial = new Trial(Event.readEventsFromString(input));
		PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		assertEquals(null,result);
	}

	@Test
	void analyzerAddsTimestamp() {
		String input = "627.724,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "627.724,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "627.724,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "627.724,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "627.724,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "627.724,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "633.148,30,Touch Down Event,Bussey Mouse Operant Mode 2 x 1 x low,,3,2,Position,1,Image 1,0,,,,,,\r\n"
				+ "634.105,31,Touch Up Event,Bussey Mouse Operant Mode 2 x 1 x low,,3,2,Position,1,Image 1,0,,,,,,\r\n"
				+ "634.105,30,Touch Down Event,Bussey Mouse Operant Mode 2 x 1 x low,,3,2,Position,1,Image 1,0,,,,,,\r\n"
				+ "634.278,31,Touch Up Event,Bussey Mouse Operant Mode 2 x 1 x low,,3,2,Position,1,Image 1,0,,,,,,\r\n"
				+ "634.778,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "635.716,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "635.716,38,Input Transition On Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "635.717,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "635.717,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "635.717,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "635.717,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "639.483,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "639.483,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "639.484,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "639.484,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "639.484,16,Variable Event,_Trial_Counter,,4,1,Value,30,,,,,,,,\r\n"
				+ "639.484,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "639.995,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "639.998,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "640.092,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "640.094,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "642.219,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		assertEquals(627.724,result.getTimestamp(),0.001);
	}
	
	@Test
	void analyzerAddsTrialNumber() {
		String input = "649.484,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "649.484,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "649.484,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "649.484,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "649.484,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "649.484,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "652.853,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "653.562,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "653.564,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "653.564,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "653.564,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "653.564,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "653.564,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "656.144,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "656.144,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "656.145,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "656.145,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "656.145,16,Variable Event,_Trial_Counter,,4,1,Value,31,,,,,,,,\r\n"
				+ "656.145,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "660.146,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 10, null);
		assertEquals(10,result.getTrialNumber());
	}
	
	@Test
	void analyzerGetsParameters() {
		String input = "0,16,Variable Event,_Schedule_Timer,,0,1,Value,0,,,,,,,,\r\n"
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
				+ "0,37,Image List Definition,Background,,0,1,Bussey Mouse Operant Mode 2 x 1 x low,3,,,,,,,,\r\n"
				+ "0,36,List Array - Write,Background,,0,1,Image 1,1,,,,,,,,\r\n"
				+ "0,17,List Change - Set,Background,,0,2,Index,1,Value,1,,,,,,\r\n"
				+ "0,0,Schedule Startup Event,(SYSTEM),,0,0,,,,,,,,,,\r\n"
				+ "0,29,Timer Event,_Schedule_Timer,,0,1,Value,0,,,,,,,,\r\n"
				+ "0.024,1,Condition Event,Adjust Sound Level 2,,0,0,,,,,,,,,,\r\n"
				+ "0.024,3,Output On Event,Sound #2,,0,0,,,,,,,,,,\r\n"
				+ "0.024,21,Group Change Event,Group Change,,0,1,New Group,1,,,,,,,,\r\n"
				+ "0.043,1,Condition Event,Set Blank Images,,1,0,,,,,,,,,,\r\n"
				+ "0.043,3,Output On Event,White_Noise_Off #1,,1,0,,,,,,,,,,\r\n"
				+ "0.043,1,Condition Event,Adjust Sound Level 2,,1,0,,,,,,,,,,\r\n"
				+ "0.043,3,Output On Event,Sound #2,,1,0,,,,,,,,,,\r\n"
				+ "0.043,32,Whisker - Display Image,Bussey Mouse Operant Mode 2 x 1 x low,Background,1,2,Position,1,Image 1,0,,,,,,\r\n"
				+ "0.043,32,Whisker - Display Image,Bussey Mouse Operant Mode 2 x 1 x low,Background,1,2,Position,2,Image 1,0,,,,,,\r\n"
				+ "0.043,29,Timer Event,Acclimatisation_timer,,1,1,Value,0,,,,,,,,\r\n"
				+ "0.043,21,Group Change Event,Group Change,,1,1,New Group,2,,,,,,,,\r\n"
				+ "0.051,1,Condition Event,Prime Feed,,2,0,,,,,,,,,,\r\n"
				+ "0.051,3,Output On Event,TrayLight #1,,2,0,,,,,,,,,,\r\n"
				+ "0.051,7,Pulse Output Event,Feeder #1,,2,1,Duration,6,,,,,,,,\r\n"
				+ "0.051,1,Condition Event,Pulse Tone if required,,2,0,,,,,,,,,,\r\n"
				+ "0.051,7,Pulse Output Event,Sound_On #1,,2,1,Duration,1,,,,,,,,\r\n"
				+ "0.051,21,Group Change Event,Group Change,,2,1,New Group,3,,,,,,,,\r\n"
				+ "6.356,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "8.32,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "8.32,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "8.322,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "8.322,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "8.322,16,Variable Event,,,3,1,Value,10,,,,,,,,\r\n"
				+ "8.322,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "17.643,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "17.643,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "17.645,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "17.645,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "17.645,16,Variable Event,_Trial_Counter,,4,1,Value,1,,,,,,,,\r\n"
				+ "17.645,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "18.405,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "22.871,38,Input Transition On Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "24.349,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "24.351,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "27.645,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "27.645,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "27.645,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "28.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "28.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "28.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "29.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "29.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "29.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "30.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "30.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "30.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "31.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "31.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "31.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "32.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "32.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "32.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "33.026,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "33.026,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		PairwiseHabituationV2Parameters parameters = (PairwiseHabituationV2Parameters) new PairwiseHabituationV2ParameterReader().readParameters(trial.copyEventsAsArray());
		analyzer.setParameters(parameters);
		PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		assertEquals(280,result.getPulseFeedTimeMs());
		assertEquals(6000,result.getPrimeFeedTimeMs());
	}
	
	@Test
	void analyzerCalculatesFeedingLatencyFirstTrial() {
		String input  = "0.051,1,Condition Event,Prime Feed,,2,0,,,,,,,,,,\r\n"
				+ "0.051,3,Output On Event,TrayLight #1,,2,0,,,,,,,,,,\r\n"
				+ "0.051,7,Pulse Output Event,Feeder #1,,2,1,Duration,6,,,,,,,,\r\n"
				+ "0.051,1,Condition Event,Pulse Tone if required,,2,0,,,,,,,,,,\r\n"
				+ "0.051,7,Pulse Output Event,Sound_On #1,,2,1,Duration,1,,,,,,,,\r\n"
				+ "0.051,21,Group Change Event,Group Change,,2,1,New Group,3,,,,,,,,\r\n"
				+ "6.356,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "8.32,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "8.32,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "8.322,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "8.322,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "8.322,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "8.322,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		assertEquals(8.271,result.getFeedingLatency(),0.001);
	}
	
	@Test
	void analyzerCalculatesFeedingLatencyNthTrial() {
		String input = "122.306,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "122.306,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "122.306,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "122.306,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "122.306,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "122.306,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "122.323,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "122.323,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "122.325,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "122.325,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "122.325,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "122.325,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "122.404,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "122.406,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "122.407,1,Condition Event,Start Delay,,4,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		assertEquals(0.019,result.getFeedingLatency(),0.001);
	}
	
	@Test
	void analyzerCalculatesFeedingLatencyInterrupted() {
		String input = "122.306,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "122.306,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "122.306,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "122.306,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "122.306,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "122.306,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		assertTrue(Double.isNaN(result.getFeedingLatency()));
	}
	
	@Test
	void analyzeCalculatesFeedingTimeFirstTrial() {
		String input = "0.051,1,Condition Event,Prime Feed,,2,0,,,,,,,,,,\r\n"
				+ "0.051,3,Output On Event,TrayLight #1,,2,0,,,,,,,,,,\r\n"
				+ "0.051,7,Pulse Output Event,Feeder #1,,2,1,Duration,6,,,,,,,,\r\n"
				+ "0.051,1,Condition Event,Pulse Tone if required,,2,0,,,,,,,,,,\r\n"
				+ "0.051,7,Pulse Output Event,Sound_On #1,,2,1,Duration,1,,,,,,,,\r\n"
				+ "0.051,21,Group Change Event,Group Change,,2,1,New Group,3,,,,,,,,\r\n"
				+ "6.356,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "8.32,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "8.32,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "8.322,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "8.322,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "8.322,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "8.322,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "17.643,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "17.643,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "17.645,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "17.645,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "17.645,16,Variable Event,_Trial_Counter,,4,1,Value,1,,,,,,,,\r\n"
				+ "17.645,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "18.405,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "22.871,38,Input Transition On Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "24.349,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		assertEquals(9.323,result.getFeedingTime(),.001);
	}
	
	@Test
	void analyzeCalculatesFeedingTimeNthTrial() {
		String input = "252.595,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "252.595,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "252.595,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "252.595,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "252.595,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "252.595,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "253.048,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "254.128,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "254.13,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "254.131,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "254.131,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "254.131,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "254.131,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "256.965,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "256.967,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "256.968,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "256.968,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "256.968,16,Variable Event,_Trial_Counter,,4,1,Value,12,,,,,,,,\r\n"
				+ "256.968,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "258.197,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "258.367,38,Input Transition On Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "259.048,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "259.179,38,Input Transition On Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "261.098,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		assertEquals(2.837,result.getFeedingTime(),.001);
	}
	
	@Test
	void analyzeCalculatesFeedingTimeInterruptedTrial() {
		String input = "266.968,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "266.968,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "266.968,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "266.968,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "266.968,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "266.968,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "283.74,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "284.45,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "284.45,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "284.453,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "284.453,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "284.453,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "284.453,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "288.697,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "288.699,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		assertTrue(Double.isNaN(result.getFeedingTime()));
	}
	
	@Test
	void analyzeCalculatesFeedingTimeNeverEntersTray() {
		String input = "298.709,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "298.709,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "298.709,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "298.709,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "298.709,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "298.709,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "303.553,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "304.563,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "304.565,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "304.566,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "304.566,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "304.566,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "307.487,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "307.489,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "307.49,1,Condition Event,Start Delay,,4,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		try {
			PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		}
		catch(RuntimeException e) {
			return;
		}
		fail("No exception");
	}
	
	//Testing effective delay time
	@Test
	void analyzeCalculatesTimeInDelayFirstTrial() {
		String input = "0.051,1,Condition Event,Prime Feed,,2,0,,,,,,,,,,\r\n"
				+ "0.051,3,Output On Event,TrayLight #1,,2,0,,,,,,,,,,\r\n"
				+ "0.051,7,Pulse Output Event,Feeder #1,,2,1,Duration,6,,,,,,,,\r\n"
				+ "0.051,1,Condition Event,Pulse Tone if required,,2,0,,,,,,,,,,\r\n"
				+ "0.051,7,Pulse Output Event,Sound_On #1,,2,1,Duration,1,,,,,,,,\r\n"
				+ "0.051,21,Group Change Event,Group Change,,2,1,New Group,3,,,,,,,,\r\n"
				+ "6.356,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "8.32,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "8.32,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "8.322,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "8.322,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "8.322,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "8.322,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "17.643,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "17.643,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "17.645,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "17.645,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "17.645,16,Variable Event,_Trial_Counter,,4,1,Value,1,,,,,,,,\r\n"
				+ "17.645,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "18.405,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "22.871,38,Input Transition On Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "24.349,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "24.351,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "27.645,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "27.645,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "27.645,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "28.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "28.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "28.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "29.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "29.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "29.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "30.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "30.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "30.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "31.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "31.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "31.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "32.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "32.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "32.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "33.026,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "33.026,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "33.646,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "33.646,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "33.646,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "33.646,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "33.646,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "33.646,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "37.914,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "37.914,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		assertEquals(16.0,result.getEffectiveDelayTime());
	}
	
	@Test
	void analyzeCalculatesTimeInDelayNthTrialZeroWaits() {
		String input = "317.49,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "317.49,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "317.49,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "317.49,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "317.49,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "317.49,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "318.633,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "318.635,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "318.636,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "318.636,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "318.636,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "318.636,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "318.689,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "318.689,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "318.692,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "318.692,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "318.692,16,Variable Event,_Trial_Counter,,4,1,Value,15,,,,,,,,\r\n"
				+ "318.692,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "318.885,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "318.887,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "322.124,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "322.124,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "322.183,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "322.183,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "323.15,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "323.15,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "325.152,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		assertEquals(10.0,result.getEffectiveDelayTime());
	}
	
	@Test
	void analyzeCalculatesTimeInDelayNthTrialNWaits() {
		String input = "388.392,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "388.392,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "388.392,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "388.392,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "388.392,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "388.392,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "389.237,39,Input Transition Off Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "390.903,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "391.781,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "391.781,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "391.783,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "391.783,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "391.783,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "391.783,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "394.244,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "394.246,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "394.246,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "394.246,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "394.246,16,Variable Event,_Trial_Counter,,4,1,Value,18,,,,,,,,\r\n"
				+ "394.246,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "396.188,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "401.42,38,Input Transition On Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "402.984,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "402.984,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "403.228,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "403.23,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "403.924,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "403.924,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "404.017,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "404.019,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "404.052,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "404.052,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "404.246,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "404.246,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "404.246,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "404.818,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "404.818,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		assertEquals(11.0,result.getEffectiveDelayTime());
	}
	
	@Test
	void analyzeCalculatesTimeInDelayWhenInterrupted() {
		String input = "1196.524,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "1196.524,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "1196.524,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "1196.524,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "1196.524,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "1196.524,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "1197.86,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "1197.86,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "1197.861,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "1197.861,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "1197.861,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "1197.861,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "1199.654,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "1199.654,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "1199.655,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "1199.655,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "1199.655,16,Variable Event,_Trial_Counter,,4,1,Value,65,,,,,,,,\r\n"
				+ "1199.655,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "1200,21,Group Change Event,Group Change,,5,1,New Group,6,,,,,,,,\r\n"
				+ "1200,16,Variable Event,_Schedule_Timer,,6,1,Value,1200,,,,,,,,\r\n"
				+ "1200,16,Variable Event,_Trial_Timer,,6,1,Value,0,,,,,,,,\r\n"
				+ "1200,16,Variable Event,Acclimatisation_timer,,6,1,Value,1199.957,,,,,,,,\r\n"
				+ "1200,16,Variable Event,Delay_Timer,,6,1,Value,0.345,,,,,,,,\r\n"
				+ "1200,38,Input Transition On Event,Tray #1,,6,0,,,,,,,,,,\r\n"
				+ "1200,38,Input Transition On Event,FIRBeam #1,,6,0,,,,,,,,,,\r\n"
				+ "1200,39,Input Transition Off Event,Tray #1,,6,0,,,,,,,,,,\r\n"
				+ "1200,39,Input Transition Off Event,FIRBeam #1,,6,0,,,,,,,,,,\r\n"
				+ "1200,38,Input Transition On Event,Tray #1,,6,0,,,,,,,,,,\r\n"
				+ "1200,38,Input Transition On Event,FIRBeam #1,,6,0,,,,,,,,,,\r\n"
				+ "1200,39,Input Transition Off Event,Tray #1,,6,0,,,,,,,,,,\r\n"
				+ "1200,39,Input Transition Off Event,FIRBeam #1,,6,0,,,,,,,,,,\r\n"
				+ "1200,9999,Schedule Shutdown Event,(SYSTEM),,6,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		assertEquals(0.345,result.getEffectiveDelayTime(),0.001);
	}
	
	@Test
	void analyzeCalculatesTotalTrayBeamsFirstTrial() {
		String input = "0.051,1,Condition Event,Prime Feed,,2,0,,,,,,,,,,\r\n"
				+ "0.051,3,Output On Event,TrayLight #1,,2,0,,,,,,,,,,\r\n"
				+ "0.051,7,Pulse Output Event,Feeder #1,,2,1,Duration,6,,,,,,,,\r\n"
				+ "0.051,1,Condition Event,Pulse Tone if required,,2,0,,,,,,,,,,\r\n"
				+ "0.051,7,Pulse Output Event,Sound_On #1,,2,1,Duration,1,,,,,,,,\r\n"
				+ "0.051,21,Group Change Event,Group Change,,2,1,New Group,3,,,,,,,,\r\n"
				+ "6.356,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "8.32,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "8.32,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "8.322,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "8.322,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "8.322,16,Variable Event,,,3,1,Value,10,,,,,,,,\r\n"
				+ "8.322,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "17.643,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "17.643,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "17.645,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "17.645,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "17.645,16,Variable Event,_Trial_Counter,,4,1,Value,1,,,,,,,,\r\n"
				+ "17.645,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "18.405,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "22.871,38,Input Transition On Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "24.349,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "24.351,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "27.645,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "27.645,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "27.645,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "28.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "28.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "28.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "29.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "29.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "29.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "30.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "30.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "30.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "31.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "31.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "31.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "32.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "32.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "32.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "33.026,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "33.026,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		assertEquals(2,result.getTotalTrayBeams());
	}
	
	@Test
	void analyzeCalculatesTotalFrontBeamsFirstTrial() {
		String input = "0.051,1,Condition Event,Prime Feed,,2,0,,,,,,,,,,\r\n"
				+ "0.051,3,Output On Event,TrayLight #1,,2,0,,,,,,,,,,\r\n"
				+ "0.051,7,Pulse Output Event,Feeder #1,,2,1,Duration,6,,,,,,,,\r\n"
				+ "0.051,1,Condition Event,Pulse Tone if required,,2,0,,,,,,,,,,\r\n"
				+ "0.051,7,Pulse Output Event,Sound_On #1,,2,1,Duration,1,,,,,,,,\r\n"
				+ "0.051,21,Group Change Event,Group Change,,2,1,New Group,3,,,,,,,,\r\n"
				+ "6.356,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "8.32,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "8.32,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "8.322,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "8.322,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "8.322,16,Variable Event,,,3,1,Value,10,,,,,,,,\r\n"
				+ "8.322,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "17.643,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "17.643,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "17.645,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "17.645,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "17.645,16,Variable Event,_Trial_Counter,,4,1,Value,1,,,,,,,,\r\n"
				+ "17.645,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "18.405,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "22.871,38,Input Transition On Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "24.349,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "24.351,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "27.645,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "27.645,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "27.645,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "28.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "28.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "28.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "29.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "29.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "29.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "30.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "30.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "30.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "31.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "31.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "31.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "32.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "32.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "32.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "33.026,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "33.026,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "24.351,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		assertEquals(3,result.getTotalFrontBeams());
	}
	
	
	@Test
	void analyzeCalculatesBeamsDuringDelay() {
		String input = "481.128,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "481.128,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "481.128,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "481.128,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "481.128,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "481.128,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "490.809,30,Touch Down Event,Bussey Mouse Operant Mode 2 x 1 x low,,3,2,Position,1,Image 1,0,,,,,,\r\n"
				+ "490.894,31,Touch Up Event,Bussey Mouse Operant Mode 2 x 1 x low,,3,2,Position,1,Image 1,0,,,,,,\r\n"
				+ "491.143,30,Touch Down Event,Bussey Mouse Operant Mode 2 x 1 x low,,3,2,Position,1,Image 1,0,,,,,,\r\n"
				+ "491.31,31,Touch Up Event,Bussey Mouse Operant Mode 2 x 1 x low,,3,2,Position,1,Image 1,0,,,,,,\r\n"
				+ "492.174,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "492.897,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "492.899,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "492.899,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "492.899,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "492.899,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "492.899,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "495.286,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "495.288,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "495.288,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "495.288,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "495.288,16,Variable Event,_Trial_Counter,,4,1,Value,22,,,,,,,,\r\n"
				+ "495.288,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "497.281,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "497.463,38,Input Transition On Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "498.237,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "498.237,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "498.79,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "498.792,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "498.869,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "498.869,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "499.342,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "498.237,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "497.463,38,Input Transition On Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "498.237,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "499.343,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "498.237,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "501.415,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		assertEquals(2,result.getDelayPeriodBackBeams());
		assertEquals(3,result.getDelayPeriodTrayBeams());
		assertEquals(4,result.getDelayPeriodFrontBeams());
	}
	
	//No feeding latency but feeding time throws exception (this can't happen, they both rely on note tray entry)

	
	//No feeding latency but delay time throws exception
	@Test
	void noFeedingLatencyButDelayTimeThrowsException() {
		String input = "753.886,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "753.886,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "753.886,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "753.886,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "753.886,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "753.886,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "755.975,30,Touch Down Event,Bussey Mouse Operant Mode 2 x 1 x low,,3,2,Position,1,Image 1,0,,,,,,\r\n"
				+ "756.529,31,Touch Up Event,Bussey Mouse Operant Mode 2 x 1 x low,,3,2,Position,1,Image 1,0,,,,,,\r\n"
				+ "757.535,30,Touch Down Event,Bussey Mouse Operant Mode 2 x 1 x low,,3,2,Position,1,Image 1,0,,,,,,\r\n"
				+ "757.624,31,Touch Up Event,Bussey Mouse Operant Mode 2 x 1 x low,,3,2,Position,1,Image 1,0,,,,,,\r\n"
				+ "761.002,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "761.733,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "761.733,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "761.734,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "761.734,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "761.734,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "763.558,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "763.558,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "763.559,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "763.559,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "763.559,16,Variable Event,_Trial_Counter,,4,1,Value,37,,,,,,,,\r\n"
				+ "763.559,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "769.676,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		try {
			PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		} catch (RuntimeException e){
			return;
		}
		fail("No exception");
	}
	
	//Feeding time but no feeding latency throws exception
	@Test
	void feedingTimeButNoFeedingLatencyThrowsException() {
		String input = "773.559,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "773.559,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "773.559,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "773.559,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "773.559,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "775.074,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "776.035,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "776.037,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "776.037,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "776.037,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "776.037,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "776.037,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "777.988,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "777.988,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "777.989,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "777.989,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "777.989,16,Variable Event,_Trial_Counter,,4,1,Value,38,,,,,,,,\r\n"
				+ "777.989,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "779.845,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "786.564,38,Input Transition On Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "787.213,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "787.213,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "787.257,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "787.259,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "787.402,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "787.402,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "787.975,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "787.977,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		try {
			PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		} catch (RuntimeException e){
			return;
		}
		fail("No exception");
	}
	
	//No feeding time but delay time throws exception
	@Test
	void noFeedingTimeButDelayTimeThrowsException() {
		String input = "787.99,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "787.99,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "787.99,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "787.99,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "787.99,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "787.99,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "788.844,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "788.846,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "788.846,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "788.846,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "788.846,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "788.922,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "788.922,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "788.923,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "788.923,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "788.923,16,Variable Event,_Trial_Counter,,4,1,Value,39,,,,,,,,\r\n"
				+ "788.923,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "788.992,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "788.994,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "791.309,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "791.311,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "792.25,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "792.25,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "792.442,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "792.444,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "793.754,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "794.34,38,Input Transition On Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "796.816,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		try {
			PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		} catch (RuntimeException e){
			return;
		}
		fail("No exception");
	}
	
	//Delay time but no feeding time throws exception
	@Test
	void delayTimeButNoFeedintTimeThrowsException() {
		String input = "798.924,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "798.924,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "798.924,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "798.924,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "798.924,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "798.924,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "805.683,30,Touch Down Event,Bussey Mouse Operant Mode 2 x 1 x low,,3,2,Position,2,Image 1,0,,,,,,\r\n"
				+ "806.413,31,Touch Up Event,Bussey Mouse Operant Mode 2 x 1 x low,,3,2,Position,2,Image 1,0,,,,,,\r\n"
				+ "811.987,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "812.884,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "812.884,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "812.885,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "812.885,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "812.885,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "815.313,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "815.313,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "815.314,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "815.314,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "815.314,16,Variable Event,_Trial_Counter,,4,1,Value,40,,,,,,,,\r\n"
				+ "815.314,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "817.804,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "817.804,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "818.887,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "818.887,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "819.047,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "819.047,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "819.146,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "819.146,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "819.262,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "819.262,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "819.391,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "819.393,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "820.888,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "825.314,1,Condition Event,Feed again,,5,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		try {
			PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		} catch (RuntimeException e){
			return;
		}
		fail("No exception");
	}
	
	//delay time but no feeding latency throws exception
	@Test
	void delayTimeButNoFeedingLatencyThrowsException() {
		String input = "825.314,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "825.314,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "825.314,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "825.314,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "825.314,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "825.314,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "830.814,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "831.693,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "831.695,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "831.695,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "831.695,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "831.696,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "834.357,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "834.359,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "834.359,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "834.359,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "834.359,16,Variable Event,_Trial_Counter,,4,1,Value,41,,,,,,,,\r\n"
				+ "834.359,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "835.429,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "835.429,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "835.484,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "835.486,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "835.546,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "835.548,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "835.975,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "835.978,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "837.899,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "838.122,38,Input Transition On Event,BIRBeam #1,,5,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		try {
			PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		} catch (RuntimeException e){
			return;
		}
		fail("No exception");
	}
	
	//No delay time but delay beam breaks throws exception
	@Test
	void nodelayTimeButDelayBeamBreaksThrowsException() {
		String input = "844.36,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "844.36,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "844.36,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "844.36,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "844.36,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "844.36,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "846.104,39,Input Transition Off Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "849.108,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "849.907,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "849.91,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "849.91,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "849.91,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "849.91,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "849.91,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "851.824,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "851.824,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "851.826,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "851.826,16,Variable Event,_Trial_Counter,,4,1,Value,42,,,,,,,,\r\n"
				+ "851.826,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "851.922,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "851.922,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "852.215,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "852.217,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "853.471,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "857.063,30,Touch Down Event,Bussey Mouse Operant Mode 2 x 1 x low,,5,2,Position,1,Image 1,0,,,,,,\r\n"
				+ "857.229,31,Touch Up Event,Bussey Mouse Operant Mode 2 x 1 x low,,5,2,Position,1,Image 1,0,,,,,,\r\n"
				+ "859.263,38,Input Transition On Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "861.042,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "861.044,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "861.283,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "861.283,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,";
		Trial trial = new Trial(Event.readEventsFromString(input));
		try {
			PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		} catch (RuntimeException e){
			return;
		}
		fail("No exception");
	}
	
	//no feeding latency but tray beam breaks throws exception
	void noFeedingLatencyButTrayBeamBreaksThrowsException() {
		String input = "861.826,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "861.826,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "861.826,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "861.826,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "861.826,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "861.826,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "874.259,39,Input Transition Off Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "879.995,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "880.771,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n";
		Trial trial = new Trial(Event.readEventsFromString(input));
		try {
			PairwiseHabiutationV2Result result = analyzer.generateResult(trial, 0, null);
		} catch (RuntimeException e){
			return;
		}
		fail("No exception");
	}

}
