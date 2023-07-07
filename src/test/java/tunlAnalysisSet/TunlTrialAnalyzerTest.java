package tunlAnalysisSet;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataModels.Event;
import dataModels.Trial;

class TunlTrialAnalyzerTest {
	
	private TunlTrialAnalyzer analyzer;

	@BeforeEach
	void setUp() throws Exception {
		analyzer = new TunlTrialAnalyzer();
	}

	/*
	 * Requirement 1 - The separation level of a trial is to be determined by looking at the
	 * trial type event. Substracting the location of sample from choice should give the level
	 * of separation.
	 * 
	 * Given a list of events (a trial), the first such event that describes the trial type ("13,
	 * 34, etc) shall be used to determine the separation level.
	 */
	
	@Test // when no trial type is found, no separation level should be returned
	void noSeparationWhenSeparationEventIsNotFound() {
		//generate trial with no "type" event
		Random rng = new Random(123);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),521);
		
		//remove all type events, can insert correction trials!!
		testTrial.removeEvent(new Event("Variable Event","aTrial_Set",null,null));
		
		//correction trials disable separation logic, so remove the event
		testTrial.replaceAllEvents(new Event("Condition Event","Start Correction Trial",null,null),
				new Event("event 1", "start weird trial" , null, null));
		
		
		//assert result does not have a value for trial separation
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertEquals(0,result.getSeparationLevel());
	}
	
	@Test // when 1 trial type event is present, and difference is positive, result is pos
	void positiveDifferenceGivesPositiveSeparation() {
		//generate trial with no "type" event
		Random rng = new Random(426);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),670);
		
		//remove all type events, can insert correction trials!!
		testTrial.removeEvent(new Event("Variable Event","aTrial_Set",null,null));
		
		//correction trials disable separation logic, so remove the event
		testTrial.replaceAllEvents(new Event("Condition Event","Start Correction Trial",null,null),
				new Event("event 1", "start weird trial" , null, null));
		
		String[] argnames = {"Value"};
		float[] argValues = {(float)32};
		
		
		//insert a single type event
		testTrial.replaceEventWith(590, new Event("Variable Event", "aTrial_Set",argnames,argValues));
		
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertEquals(1,result.getSeparationLevel());
	}
	
	
	@Test // when 1 trial type event is present, and difference is negative, result is pos
	void negativeDifferenceGivesPositiveSeparation() {
		//generate trial with no "type" event
		Random rng = new Random(671);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),581);
		
		//remove all type events, can insert correction trials!!
		testTrial.removeEvent(new Event("Variable Event","aTrial_Set",null,null));
		
		//correction trials disable separation logic, so remove the event
		testTrial.replaceAllEvents(new Event("Condition Event","Start Correction Trial",null,null),
				new Event("event 1", "start weird trial" , null, null));
		
		String[] argnames = {"Value"};
		float[] argValues = {(float)13};
		
		
		//insert a single type event
		testTrial.replaceEventWith(480, new Event("Variable Event", "aTrial_Set",argnames,argValues));
		
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertEquals(2,result.getSeparationLevel());
	}
	
	@Test // when 2 trial type events are present, only the first is considered
	void twoTypeEventsResultsInTheSecondBeingIgnored() {
		//generate trial with no "type" event
		Random rng = new Random(6901);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),543);

		
		//remove all type events, can insert correction trials!!
		testTrial.removeEvent(new Event("Variable Event","aTrial_Set",null,null));
		
		//correction trials disable separation logic, so remove the event
		testTrial.replaceAllEvents(new Event("Condition Event","Start Correction Trial",null,null),
				new Event("event 1", "start weird trial" , null, null));
		
		String[] argnames1 = {"Index"};
		float[] argValues1 = {(float)13};
		
		String[] argnames2 = {"Index"};
		float[] argValues2 = {(float)15};
		
		
		//insert a single type event
		testTrial.replaceEventWith(470, new Event("Variable Event", "aTrial_Set",argnames1,argValues1));
		testTrial.replaceEventWith(490, new Event("Variable Event", "aTrial_Set",argnames2,argValues2));
		
		
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertEquals(2,result.getSeparationLevel());
	}
	
	@Test //the choice is to the left
	void choiceIsToTheLeftOfSample() {
		//generate trial with no "type" event
		Random rng = new Random(6911);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),531);
		
		//remove all type events, can insert correction trials!!
		testTrial.removeEvent(new Event("Variable Event","aTrial_Set",null,null));
		
		//correction trials disable separation logic, so remove the event
		testTrial.replaceAllEvents(new Event("Condition Event","Start Correction Trial",null,null),
				new Event("event 1", "start weird trial" , null, null));
		
		String[] argnames = {"Value"};
		float[] argValues = {(float)31};
		
		
		//insert a single type event
		testTrial.replaceEventWith(480, new Event("Variable Event", "aTrial_Set",argnames,argValues));
		
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertEquals("Left",result.getLeftOrRightChoice());
	}
	
	@Test // there is no sample presentation event or delay start event
	void noSampleEventsGivesNoSampleLatency() {
		//generate a random trial
		Random rng = new Random(62111);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),460);
		
		//remove sample presentation events
		testTrial.removeEvent(new Event("Condition Event","Display Sample",null,null));
		//remove delay start events
		testTrial.removeEvent(new Event("Condition Event","Start Delay",null,null));
		
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertTrue(Double.isNaN(result.getSampleLatency()));
	}
	
	@Test // there is a sample and choice event
	void correctSampleChoiceOrderGivesLatencyResult() {
		Random rng = new Random(6541);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),500);
		
		//remove sample presentation events
		testTrial.removeEvent(new Event("Condition Event","Display Sample",null,null));
		//remove delay start events
		testTrial.removeEvent(new Event("Condition Event","Start Delay",null,null));
		
		testTrial.replaceEventWith(411, new Event("5.000,1,Condition Event,Display Sample,,20,0,,,,,,,,,,"));
		testTrial.replaceEventWith(420, new Event("10.000,1,Condition Event,Start Delay,,20,0,,,,,,,,,,"));
		
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertEquals(5.000,result.getSampleLatency());
	}
	
	@Test // there is a sample but no choice
	void sampleButNoChoiceGivesNaNResult() {
		//generate a random trial
		Random rng = new Random(111);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),460);
		
		//remove sample presentation events
		testTrial.removeEvent(new Event("Condition Event","Display Sample",null,null));
		//remove delay start events
		testTrial.removeEvent(new Event("Condition Event","Start Delay",null,null));
		
		testTrial.replaceEventWith(411, new Event("5.000,1,Condition Event,Display Sample,,20,0,,,,,,,,,,"));
		
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertTrue(Double.isNaN(result.getSampleLatency()));
	}
	
	@Test // there is a choice but no sample
	void noSampleButChoiceGivesNaNResult() {
		//generate a random trial
		Random rng = new Random(11122);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),460);
		
		//remove sample presentation events
		testTrial.removeEvent(new Event("Condition Event","Display Sample",null,null));
		//remove delay start events
		testTrial.removeEvent(new Event("Condition Event","Start Delay",null,null));
		
		testTrial.replaceEventWith(411, new Event("5.000,1,Condition Event,Start Delay,,20,0,,,,,,,,,,"));
		
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertTrue(Double.isNaN(result.getSampleLatency()));
	}
	
	@ Test // the sample and choice are encountered in the wrong order
	void sampleAndChoiceHaveWrongOrder() {
		Random rng = new Random(33331);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),500);
		
		//remove sample presentation events
		testTrial.removeEvent(new Event("Condition Event","Display Sample",null,null));
		//remove delay start events
		testTrial.removeEvent(new Event("Condition Event","Start Delay",null,null));
		
		testTrial.replaceEventWith(450, new Event("50.000,1,Condition Event,Display Sample,,20,0,,,,,,,,,,"));
		testTrial.replaceEventWith(401, new Event("3.000,1,Condition Event,Start Delay,,20,0,,,,,,,,,,"));
		
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertTrue(Double.isNaN(result.getSampleLatency()));
	}
	
	@ Test // there is multiple sample presentations
	void manySamplePresentationsOnlyConsiderTheFirst() {
		Random rng = new Random(123792);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),500);
		
		//remove sample presentation events
		testTrial.removeEvent(new Event("Condition Event","Display Sample",null,null));
		//remove delay start events
		testTrial.removeEvent(new Event("Condition Event","Start Delay",null,null));
		
		testTrial.replaceEventWith(411, new Event("5.000,1,Condition Event,Display Sample,,20,0,,,,,,,,,,"));
		testTrial.replaceEventWith(415, new Event("8.000,1,Condition Event,Display Sample,,20,0,,,,,,,,,,"));
		testTrial.replaceEventWith(420, new Event("10.000,1,Condition Event,Start Delay,,20,0,,,,,,,,,,"));
		
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertEquals(5.000,result.getSampleLatency());
	}
	
	@ Test // many delay times , all are ignored except the first
	void manyDelayTimesOnlyConsiderTheFirst() {
		Random rng = new Random(17932);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),500);
		
		//remove sample presentation events
		testTrial.removeEvent(new Event("Condition Event","Display Sample",null,null));
		//remove delay start events
		testTrial.removeEvent(new Event("Condition Event","Start Delay",null,null));
		
		testTrial.replaceEventWith(411, new Event("5.000,1,Condition Event,Display Sample,,20,0,,,,,,,,,,"));
		testTrial.replaceEventWith(415, new Event("8.000,1,Condition Event,Start Delay,,20,0,,,,,,,,,,"));
		testTrial.replaceEventWith(420, new Event("10.000,1,Condition Event,Start Delay,,20,0,,,,,,,,,,"));
		
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertEquals(3.000,result.getSampleLatency());
	}
	
	@Test // trial array size 0 cannot be a correction trial
	void arraysize0CannotBeCorrectionTrial() {
		Random rng = new Random(4828490);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),0);
		
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertEquals(0,result.getCorrectionTrial());
	}
	
	@Test // single event trial cannot be correction if event is not correction
	void arraySizeOneIsNotCorrectionTrialIfEventIsNotCorrectionTrial() {
		Random rng = new Random(435980);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),1);
		
		//remove correction trial events
		testTrial.removeEvent(new Event("Condition Event","Start Correction Trial",null,null));
		
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertEquals(0,result.getCorrectionTrial());
	}
	
	@Test // single event trial is correction trial if event is the correction trial
	void arraySizeOneIsCorrectionTrialIfEventIsCorrectionTrial() {
		Random rng = new Random(23095);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),1);
		
		testTrial.replaceEventWith(0, new Event("5.000,1,Condition Event,Start Correction Trial,,20,0,,,,,,,,,,"));
		
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertEquals(1,result.getCorrectionTrial());
	}
	
	@Test // large trial is not correction trial if key event is not present
	void trialIsNotCorrectionIfEventIsNotPresent() {
		Random rng = new Random(4357);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),800);
		
		//remove correction trial events
		testTrial.removeEvent(new Event("Condition Event","Start Correction Trial",null,null));
		
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertEquals(0,result.getCorrectionTrial());
	}
	
	@Test //large trial is correction trial if key event is present
	void trialIsCorrectionIfEventIsPresent() {
		Random rng = new Random(98546);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),512);
		
		//remove correction trial events
		testTrial.removeEvent(new Event("Condition Event","Start Correction Trial",null,null));
		
		//add single correction trial event
		testTrial.replaceEventWith(511, new Event("5.000,1,Condition Event,Start Correction Trial,,20,0,,,,,,,,,,"));
		
		
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertEquals(1,result.getCorrectionTrial());
	}
	
	@Test //invalid center separation if there is no image display 
	void noImageDisplayGivesInvalidCenterSample() {
		Random rng = new Random(56546);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),80);
		
		//remove trial type events
		testTrial.removeEvent(new Event("Variable Event","aTrial_Set",null,null));
		
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertEquals(-1,result.getCenterSample());
	}
	
//	@Test //not center if trial does not have center display event
//	void nonCenterSampleEventGivesNonCenterTrial() {
//		Random rng = new Random(56597239);
//		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),78);
//
//		//remove trial type events
//		testTrial.removeEvent(new Event("Variable Event","aTrial_Set",null,null));
//
//		//add non center trial
//		testTrial.replaceEventWith(38, new Event("209.606,16,Variable Event,aTrial_Set,,4,1,Value,53,,,,,,,,"));
//
//		TunlResult result = analyzer.analyze(testTrial, 0);
//		assertEquals(0,result.getCenterSample());
//	}
	
	@Test // non center boundary case 1
	void nonCenterSampleEventMakesTrialNonCenter() {
		Random rng = new Random(67589);
		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),400);
		
		//remove trial type events
		testTrial.removeEvent(new Event("Variable Event","aTrial_Set",null,null));
		
		//add non center trial
		testTrial.replaceEventWith(390, new Event("209.606,16,Variable Event,aTrial_Set,,4,1,Value,43,,,,,,,,"));
		
		TunlResult result = analyzer.analyze(testTrial, 0);
		assertEquals(0,result.getCenterSample());
	}
	
//	@Test // non center boundary case 2
//	void nonCenterSampleEventMakesTrialNonCenter2() {
//		Random rng = new Random(8967487);
//		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),385);
//
//		//remove trial type events
//		testTrial.removeEvent(new Event("Variable Event","aTrial_Set",null,null));
//
//		//add non center trial
//		testTrial.replaceEventWith(380, new Event("209.606,16,Variable Event,aTrial_Set,,4,1,Value,23,,,,,,,,"));
//
//		TunlResult result = analyzer.analyze(testTrial, 0);
//		assertEquals(0,result.getCenterSample());
//	}
	
//	@Test //center if the trial has a center display event
//	void centerSampleEventMakesTrialACenterOne() {
//		Random rng = new Random(56597239);
//		TunlTrialStub testTrial = new TunlTrialStub(rng.nextInt(),78);
//
//		//remove trial type events
//		testTrial.removeEvent(new Event("Variable Event","aTrial_Set",null,null));
//
//		//add non center trial
//		testTrial.replaceEventWith(38, new Event("209.606,16,Variable Event,aTrial_Set,,4,1,Value,31,,,,,,,,"));
//
//		TunlResult result = analyzer.analyze(testTrial, 0);
//		assertEquals(1,result.getCenterSample());
//	}

}
