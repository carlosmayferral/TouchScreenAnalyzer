package pdAnalysisOtago;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataModels.Event;

class PdOtagoPartitionerTest {
	
	private PdOtagoPartitioner partitioner;
	
	private String sessionWithNoTrayLight = "testfiles/PdOtagoPartitionerTest/SessionWithNoTrayLight.csv";
	
	private String sessionWithTrayLightButNoITI = "testfiles/PdOtagoPartitionerTest/SessionWithTrayLightNoITI.csv";
	
	private String sessionWithTrayLightITIButNoEndTrayLight = "testfiles/PdOtagoPartitionerTest/sessionWithTrayLightITINoEnd.csv";
	
	private String sessionWithCompleteTrial = "testfiles/PdOtagoPartitionerTest/sessionWithFullTrial.csv";
	
	private String sessionWithFiveTrials = "testfiles/PdOtagoPartitionerTest/sessionWith5Trials.csv";

	@BeforeEach
	void setUp() throws Exception {
		partitioner = new PdOtagoPartitioner();
	}

	@Test
	void partitionOnListOfLength0Is0() {
		Assertions.assertEquals(0, partitioner.partition(new Event[0]).size());
	}
	
	@Test
	void partitionOnListWithNoTrayLightGives0Trials() {
		Event[] events = Event.readEventsFromFile(new File(sessionWithNoTrayLight));
		Assertions.assertEquals(0, partitioner.partition(events).size());
	}
	
	@Test
	void partitionOnListWithNoITIGives0Trials() {
		Event[] events = Event.readEventsFromFile(new File(sessionWithTrayLightButNoITI));
		Assertions.assertEquals(0, partitioner.partition(events).size());	
	}
	
	@Test
	void partitionOnListWithITIButNoEndGives0() {
		Event[] events = Event.readEventsFromFile(new File(sessionWithTrayLightITIButNoEndTrayLight));
		Assertions.assertEquals(0, partitioner.partition(events).size());		
	}
	
	@Test
	void partitionOnFullTrialGives1Trial() {
		Event[] events = Event.readEventsFromFile(new File(sessionWithCompleteTrial));
		Assertions.assertEquals(1, partitioner.partition(events).size());			
	}
	
	@Test
	void partitionReturns5When5() {
		Event[] events = Event.readEventsFromFile(new File(sessionWithFiveTrials));
		Assertions.assertEquals(5, partitioner.partition(events).size());				
	}

}
