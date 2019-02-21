package pdAnalysis;


import java.io.File;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataModels.Event;
import dataModels.Trial;
import touchscreenAnalyzer.XmlReader;

class PdTrialPartitionerTest {
	
	static PdTrialPartitioner partitioner;
	
	static File session0;
	static final String session0Address = "testfiles/PdTrialPartitioner/session0.csv";
	
	static File session1;
	static final String session1Address = "testfiles/PdTrialPartitioner/session1.csv";
	
	static File session2;
	static final String session2Address = "testfiles/PdTrialPartitioner/session2.csv";
	
	static File incompleteTrial0;
	static final String incompleteTrial0Address = "testfiles/PdTrialPartitioner/incompleteTrial0.csv";
	
	static File xmlSession;
	static final String xmlSessionAddress = "testfiles/PdTrialPartitioner/xmlSession.xml";

	@BeforeEach
	void setUp() throws Exception {
		partitioner = new PdTrialPartitioner();
		session0 = new File(session0Address);
		session1 = new File(session1Address);
		session2 = new File(session2Address);
		incompleteTrial0 = new File(incompleteTrial0Address);
		xmlSession = new File(xmlSessionAddress);
	}

	//Basic
	
	@Test
	void partitionReturnsSomethingForSession0() {
		Event[] events = Event.readEventsFromFile(session0);
		ArrayList<Trial> trials = partitioner.partition(events);
		Assert.assertTrue(trials.size() > 0);
	}
	
	//Correct number of trials
	
	@Test
	void partitionReturnsCorrectAmountOfTrialsForSession0() {
		Event[] events = Event.readEventsFromFile(session0);
		ArrayList<Trial> trials = partitioner.partition(events);
		Assert.assertEquals(40, trials.size());
	}
	
	@Test
	void partitionReturnsCorrectAmountOfTrialsForSession1() {
		Event[] events = Event.readEventsFromFile(session1);
		ArrayList<Trial> trials = partitioner.partition(events);
		Assert.assertEquals(53, trials.size());
	}
	
	@Test
	void partitioneReturnsCorrectAmountOfTrialsForSession2() {
		Event[] events = Event.readEventsFromFile(session2);
		ArrayList<Trial> trials = partitioner.partition(events);
		Assert.assertEquals(60, trials.size());
	}
	
	//Incomplete trials
	
	@Test
	void partitionReturnsNoTrialsOnIncompleteTrial() {
		Event[] events = Event.readEventsFromFile(incompleteTrial0);
		ArrayList<Trial> trials = partitioner.partition(events);
		Assert.assertEquals(0, trials.size());
	}
	
	//Xml file just to make sure
	
	@Test
	void partitionReturnsCorrectAmountOfTrialsForXmlSession() {
		Event[] events = new XmlReader().readEvents(xmlSession);
		ArrayList<Trial> trials = partitioner.partition(events);
		Assert.assertEquals(37, trials.size());
	}
	
	
	
	

}
