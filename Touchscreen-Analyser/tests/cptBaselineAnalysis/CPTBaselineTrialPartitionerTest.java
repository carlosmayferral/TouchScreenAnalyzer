package cptBaselineAnalysis;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dataModels.Event;
import dataModels.Session;

class CPTBaselineTrialPartitionerTest {

	static CPTBaselineTrialPartitioner fixture = new CPTBaselineTrialPartitioner();

	static Event[] eventsWithNoTrials;
	
	static Event[] eventsWithSingleTrial;
	
	static Event[] eventsWithHalfTrial;
	
	static Event[] eventsWith368Trials;

	@BeforeAll
	static void setup() {
		Session noTrials = new Session(new File("testfiles/CPTTrialPartitioner/NoTrialsInEvents.csv"), null);
		noTrials.readEventsIntoSession();
		eventsWithNoTrials = noTrials.getEventArrayCopy();
	}

	@Test
	void partitionReturnsEmptyArrayListOfTrialsWhenNoTrialsAreFound() {
		if (fixture.partition(eventsWithNoTrials).isEmpty())
			return;
		else
			fail("did not return empty event list when no events were provided");
	}
	
	@Test
	void partitionReturnsTwoTrialsTrialWhen2TrialsArePresent() {
		Session singleTrial = new Session(new File("testfiles/CPTTrialPartitioner/SingleTrial.csv"),null);
		singleTrial.readEventsIntoSession();
		eventsWithSingleTrial = singleTrial.getEventArrayCopy();
		if (fixture.partition(eventsWithSingleTrial).size() == 2) {
			return;
		}
		else
			fail("did not return single trial when single trial was present");
	}
	
	@Test
	void partitionReturnsEmptyArrayListWhenHalfAtrialIsToBeFound() {
		Session halfTrial = new Session(new File("testfiles/CPTTrialPartitioner/HalfTrial.csv"), null);
		halfTrial.readEventsIntoSession();
		eventsWithHalfTrial = halfTrial.getEventArrayCopy();
		if (fixture.partition(eventsWithHalfTrial).isEmpty()){
			return;
		}
		else {
			fail("did not return empty array for events with half a trial present");
		}
	}
	
	@Test
	void partitionReturns306TrialsWhenThereIsIndeed306Trials() {
		Session threeHundredSixtyEightTrialSession = new Session(new File("testfiles/CPTTrialPartitioner/368Trials.csv"),null);
		threeHundredSixtyEightTrialSession.readEventsIntoSession();
		eventsWith368Trials = threeHundredSixtyEightTrialSession.getEventArrayCopy();
		if (fixture.partition(eventsWith368Trials).size() == 306) {
			return;
		}
		else {
			fail("Returned " + fixture.partition(eventsWith368Trials).size() + " trials when there was supposed to be 283");
		}
	}
	
	
	

}
