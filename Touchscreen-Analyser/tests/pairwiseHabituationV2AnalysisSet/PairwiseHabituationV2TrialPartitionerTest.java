package pairwiseHabituationV2AnalysisSet;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataModels.Event;
import dataModels.Trial;

class PairwiseHabituationV2TrialPartitionerTest {
	
	private PairwiseHabituationV2TrialPartitioner partitioner;

	@BeforeEach
	void setUp() throws Exception {
		partitioner = new PairwiseHabituationV2TrialPartitioner();
	}

	@Test
	void partitionReturnsNullIfNoEvents() {
		assertTrue(partitioner.partition(new Event[0]) == null);
	}
	
	@Test
	void partitionReturnsEmptyArrayListIfNoValidTrialsAreContained() {
		Event[] events = {
				new Event("0,16,Variable Event,_Trial_Timer,,0,1,Value,0,,,,,,,,"),
				new Event("0,16,Variable Event,A2Sound2,,0,1,Value,1,,,,,,,,"),
				new Event("0,16,Variable Event,A3Sound3,,0,1,Value,0,,,,,,,,")
		};
		ArrayList<Trial> trials = partitioner.partition(events);
		assertTrue(trials.size() == 0);
	}
	
	@Test
	void partitionReturnsZeroTrialsIfTrialIsIncomplete() {
		String input = "0.043,1,Condition Event,Adjust Sound Level 2,,1,0,,,,,,,,,,\r\n"
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
				+ "0.051,21,Group Change Event,Group Change,,2,1,New Group,3,,,,,,,,";
		Event[] events = Event.readEventsFromString(input);
		ArrayList<Trial> trials = partitioner.partition(events);
		
		assertEquals(0,trials.size());
	}
	
	@Test
	void partitionReturnszeroTrialsATrialIfTrialIsInterrupted() {
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
		Event[] events = Event.readEventsFromString(input);
		ArrayList<Trial> trials = partitioner.partition(events);
		
		assertEquals(0,trials.size());
	}
	
	@Test
	void partitionReturnsTwoTrials() {
		String input = "452.699,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "452.869,38,Input Transition On Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "453.09,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "453.189,38,Input Transition On Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "455.312,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "460.787,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "460.787,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "460.787,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "460.787,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "460.787,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "460.787,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "461.817,30,Touch Down Event,Bussey Mouse Operant Mode 2 x 1 x low,,3,2,Position,1,Image 1,0,,,,,,\r\n"
				+ "461.897,31,Touch Up Event,Bussey Mouse Operant Mode 2 x 1 x low,,3,2,Position,1,Image 1,0,,,,,,\r\n"
				+ "467.124,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "468.01,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "468.01,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "468.012,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "468.012,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "468.012,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "468.012,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "471.127,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "471.127,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "471.128,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "471.128,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "471.128,16,Variable Event,_Trial_Counter,,4,1,Value,21,,,,,,,,\r\n"
				+ "471.128,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "471.179,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "471.179,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "471.225,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "471.227,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "472.112,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "472.114,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "472.146,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "472.146,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "473.129,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "473.131,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "473.579,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "473.579,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "474.109,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "474.111,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "474.208,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "474.208,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "476.636,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "476.636,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "477.244,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "477.27,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "478.442,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "481.128,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
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
				+ "499.343,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "501.415,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n";
		Event[] events = Event.readEventsFromString(input);
		ArrayList<Trial> trials = partitioner.partition(events);
		assertEquals(1,trials.size());
	}
	
	@Test
	void partitionreads7trialsFromStart() {
		String input = "0.043,1,Condition Event,Adjust Sound Level 2,,1,0,,,,,,,,,,\r\n"
				+ "0.043,3,Output On Event,Sound #2,,1,0,,,,,,,,,,\r\n"
				+ "0.043,32,Whisker - Display Image,Bussey Mouse Operant Mode 2 x 1 x low,Background,1,2,Position,1,Image 1,0,,,,,,\r\n"
				+ "0.043,32,Whisker - Display Image,Bussey Mouse Operant Mode 2 x 1 x low,Background,1,2,Position,2,Image 1,0,,,,,,\r\n"
				+ "0.043,29,Timer Event,Acclimatisation_timer,,1,1,Value,0,,,,,,,,\r\n"
				+ "0.043,21,Group Change Event,Group Change,,1,1,New Group,2,,,,,,,,\r\n"
				+ "0.051,1,Condition Event,Prime Feed,,2,0,,,,,,,,,,\r\n"
				+ "0.051,3,Output On Event,TrayLight #1,,2,0,,,,,,,,,,\r\n"
				+ "0.051,7,Pulse Output Event,Feeder #1,,2,1,Duration,6,,,,,,,,\r\n"
				+ "0.051,1,Condition Event,Pulse Tone if required,,2,0,,,,,,,,,,\r\n"
				+ "0.051,7,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "0.051,21,Group Change Event,Group Change,,2,1,New Group,3,,,,,,,,\r\n"
				+ "6.356,38,Input Transition On Event,BIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "8.32,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "8.32,38,Input Transition On Event,FIRBeam #1,,3,0,,,,,,,,,,\r\n"
				+ "8.322,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "8.322,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "8.322,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "8.322,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "17.643,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "17.643,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "8.322,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "17.645,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "17.645,16,Variable Event,_Trial_Counter,,4,1,Value,1,,,,,,,,\r\n"
				+ "17.645,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "8.322,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "22.871,38,Input Transition On Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "8.322,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "24.351,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "27.645,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "8.322,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "27.645,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n"
				+ "28.646,1,Condition Event,Wait for tray exit,,5,0,,,,,,,,,,\r\n"
				+ "28.646,29,Timer Event,Delay_Timer,,5,1,Value,0,,,,,,,,\r\n"
				+ "28.646,16,Variable Event,Delay_Time,,5,1,Value,1,,,,,,,,\r\n";
		Event[] events = Event.readEventsFromString(input);
		ArrayList<Trial> trials = partitioner.partition(events);
		assertEquals(6,trials.size());
	}
	
	@Test
	void partitionerReturns7TrialsFromEnd() {
		String input = "1176.292,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1176.769,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1176.772,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1177.556,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1177.556,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1182.167,39,Input Transition Off Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1183.726,38,Input Transition On Event,BIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1183.823,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "1183.823,7,Pulse Output Event,Feeder #1,,5,1,Duration,0.28,,,,,,,,\r\n"
				+ "1183.823,3,Output On Event,TrayLight #1,,5,0,,,,,,,,,,\r\n"
				+ "1183.823,1,Condition Event,Pulse Tone if required 2,,5,0,,,,,,,,,,\r\n"
				+ "1183.823,7,Pulse Output Event,Sound_On #1,,5,1,Duration,1,,,,,,,,\r\n"
				+ "1183.823,21,Group Change Event,Group Change,,5,1,New Group,3,,,,,,,,\r\n"
				+ "1184.778,38,Input Transition On Event,Tray #1,,3,0,,,,,,,,,,\r\n"
				+ "1183.823,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "1184.779,1,Condition Event,Note Tray Entry,,3,0,,,,,,,,,,\r\n"
				+ "1184.779,4,Output Off Event,TrayLight #1,,3,0,,,,,,,,,,\r\n"
				+ "1184.779,16,Variable Event,Delay_Time,,3,1,Value,10,,,,,,,,\r\n"
				+ "1184.779,21,Group Change Event,Group Change,,3,1,New Group,4,,,,,,,,\r\n"
				+ "1186.522,39,Input Transition Off Event,Tray #1,,4,0,,,,,,,,,,\r\n"
				+ "1186.524,39,Input Transition Off Event,FIRBeam #1,,4,0,,,,,,,,,,\r\n"
				+ "1186.524,1,Condition Event,Start Delay,,4,0,,,,,,,,,,\r\n"
				+ "1186.524,29,Timer Event,Delay_Timer,,4,1,Value,0,,,,,,,,\r\n"
				+ "1186.524,16,Variable Event,_Trial_Counter,,4,1,Value,64,,,,,,,,\r\n"
				+ "1186.524,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\r\n"
				+ "1186.537,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1186.537,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1186.552,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1186.552,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1186.918,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1186.918,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1186.949,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1183.823,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "1186.994,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1186.994,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1187.087,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1187.087,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1190.428,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1190.43,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1190.804,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1190.804,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1190.995,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1190.995,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1191.296,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1183.823,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "1191.633,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1191.635,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1191.805,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1191.808,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1192.21,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1192.21,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1192.404,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1192.406,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1192.765,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1192.765,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1192.829,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1192.831,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1192.853,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1192.853,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1183.823,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "1192.9,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1194.076,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1194.078,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1194.324,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1194.324,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1194.919,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1194.919,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1194.959,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1194.959,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1195.433,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1195.435,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1195.457,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1195.457,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1196.141,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1196.143,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1196.246,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1196.246,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1196.388,38,Input Transition On Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1196.39,38,Input Transition On Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1196.419,39,Input Transition Off Event,Tray #1,,5,0,,,,,,,,,,\r\n"
				+ "1196.42,39,Input Transition Off Event,FIRBeam #1,,5,0,,,,,,,,,,\r\n"
				+ "1196.524,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
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
				+ "1196.524,1,Condition Event,Feed again,,5,0,,,,,,,,,,\r\n"
				+ "1200,38,Input Transition On Event,Tray #1,,6,0,,,,,,,,,,\r\n"
				+ "1200,38,Input Transition On Event,FIRBeam #1,,6,0,,,,,,,,,,\r\n"
				+ "1200,39,Input Transition Off Event,Tray #1,,6,0,,,,,,,,,,\r\n"
				+ "1200,39,Input Transition Off Event,FIRBeam #1,,6,0,,,,,,,,,,\r\n"
				+ "1200,9999,Schedule Shutdown Event,(SYSTEM),,6,0,,,,,,,,,,";
		Event[] events = Event.readEventsFromString(input);
		ArrayList<Trial> trials = partitioner.partition(events);
		assertEquals(6,trials.size());
	}
	

}
