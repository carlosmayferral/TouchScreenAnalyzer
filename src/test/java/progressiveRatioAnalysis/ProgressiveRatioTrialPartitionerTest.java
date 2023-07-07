package progressiveRatioAnalysis;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataModels.Event;
import dataModels.Trial;

class ProgressiveRatioTrialPartitionerTest {
	
	static ProgressiveRatioTrialPartitioner partitioner;
	
	//Session 0 Information
	static File session0;
	static final String session0Address = 
			"testfiles/ProgressiveRatioAnalysis/ProgressiveRatioTrialPartitionerFiles/session0.csv";
	static final int SESSION_0_NUMBER_TRIALS = 10;
	
	static File session1;
	static final String session1Address =
			"testfiles/ProgressiveRatioAnalysis/ProgressiveRatioTrialPartitionerFiles/session1.csv";
	static final int SESSION_1_NUMBER_TRIALS = 10;
	
	static File incompleteTrial;
	static final String incompleteTrialAddress = 
			"testfiles/ProgressiveRatioAnalysis/ProgressiveRatioTrialPartitionerFiles/IncompleteTrial.csv";
	static final int INCOMPLETE_TRIAL_NUMBER_TRIALS = 1;
	
	
	@BeforeEach
	void setUp() throws Exception {
		partitioner = new ProgressiveRatioTrialPartitioner();
		session0 = new File(session0Address);
		session1 = new File(session1Address);
		incompleteTrial = new File(incompleteTrialAddress);
	}

	//Correct number of trials detected
	
	@Test
	void partitionReturnsCorrectAmountOfTrialsForSession0() {
		Assert.assertEquals(
				ProgressiveRatioTrialPartitionerTest.SESSION_0_NUMBER_TRIALS, 
				getTrials(session0).size());
	}
	
	@Test
	void partitionReturnsCorrectAmountOfTrialsForSession1() {
		Assert.assertEquals(
				ProgressiveRatioTrialPartitionerTest.SESSION_1_NUMBER_TRIALS,
				this.getTrials(session1).size());
	}
	
	//One trial still detected when an incomplete trial is given (with timeout)
	@Test
	void partitionReturnsOneTrialWhenAnIncompleteTrialIsProvided() {
	Assert.assertEquals(
			ProgressiveRatioTrialPartitionerTest.INCOMPLETE_TRIAL_NUMBER_TRIALS,
			this.getTrials(incompleteTrial).size());
	}

	private ArrayList<Trial> getTrials(File file) {
		Event[] testEvents = Event.readEventsFromFile(file);
		ArrayList<Trial> testTrials = partitioner.partition(testEvents);
		return testTrials;
	}
	

}
