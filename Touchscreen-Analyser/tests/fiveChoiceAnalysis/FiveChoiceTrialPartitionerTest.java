package fiveChoiceAnalysis;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataModels.Event;
import dataModels.Session;

class FiveChoiceTrialPartitionerTest {
	
	FiveChoiceTrialPartitioner partitioner;
	
	Event[] emptySession;
	
	Event[] sessionWithInfoOnly;
	
	Event[] sessionWithHalfATrial;
	
	Event[] sessionWithLastTrialComplete;
	
	Event[] sessionWithLastTrialInterrupted;
	
	Event[] sessionWithACorrectTrial;
	
	Event[] sessionWithAnIncorrectTrial;
	
	Event[] sessionWithAPrematureTrial;
	
	Event[] sessionWithAnOmmittedTrial;
	
	Event[] sessionWithOnePointFiveTrials;
	
	Event[] sessionWithATrialAfterAPremature;
	
	Event[] sessionWith10Trials;
	
	Event[] fullSessionOne;
	
	Event[] fullSessionTwo;
	
	

	@BeforeEach
	void setUp() throws Exception {
		partitioner = new FiveChoiceTrialPartitioner();
	}
	
	@Test
	void emptySessionHasZeroTrials() {
		this.emptySession = this.readTestSession("EmptySession.csv");
		if (partitioner.partition(emptySession).isEmpty()) {
			return;
		}
		else fail("Session with no trials is not empty");
	}
	
	@Test 
	void infoSessionHasZeroTrials(){
		this.sessionWithInfoOnly = this.readTestSession("SessionWithInfoOnly.csv");
		if (partitioner.partition(sessionWithInfoOnly).isEmpty()){
			return;	
		}
		else fail("Session with info only is not empty");
	}
	
	@Test
	void halfTrialSessionHasZeroTrials() {
		this.sessionWithHalfATrial = this.readTestSession("SessionWithHalfATrial.csv");
		if (partitioner.partition(sessionWithHalfATrial).isEmpty()) {
			return;
		}
		else fail("Session with half a trial is not empty");
	}
	
	@Test
	void lastTrialCompleteIsOneTrial() {
		this.sessionWithLastTrialComplete = this.readTestSession("SessionWithLastTrialComplete.csv");
		Assert.assertEquals(1, partitioner.partition(sessionWithLastTrialComplete).size());
	}
	
	@Test 
	void lastTrialInterruptedIsZeroTrials(){
		this.sessionWithLastTrialInterrupted = this.readTestSession("SessionWithLastTrialInterrupted.csv");
		Assert.assertEquals(0, partitioner.partition(sessionWithLastTrialInterrupted).size());
	}
	
	@Test
	void sessionWithAPrematureTrialIsOneTrial() {
		this.sessionWithAPrematureTrial = this.readTestSession("SessionWithAPrematureTrial.csv");
		Assert.assertEquals(1, partitioner.partition(sessionWithAPrematureTrial).size());
	}
	
	@Test 
	void sessionWithAnOmmittedTrialIsOneTrial(){
		this.sessionWithAnOmmittedTrial = this.readTestSession("SessionWithAnOmmittedTrial.csv");
		Assert.assertEquals(1, partitioner.partition(sessionWithAnOmmittedTrial).size());
	}
	
	@Test
	void sessionWithAnIncorrectTrialIsOneTrial() {
		this.sessionWithAnIncorrectTrial = this.readTestSession("SessionWithAnIncorrectTrial.csv");
		Assert.assertEquals(1, partitioner.partition(sessionWithAnIncorrectTrial).size());
	}
	
	@Test
	void sessionWithACorrectTrialIsOneTrial() {
		this.sessionWithACorrectTrial = this.readTestSession("SessionWithACorrectTrial.csv");
		Assert.assertEquals(1, partitioner.partition(sessionWithACorrectTrial).size());
	}
	
	@Test
	void sessionWithOnePointFiveTrialsIsOneTrial() {
		this.sessionWithOnePointFiveTrials = this.readTestSession("SessionWithOnePointFiveTrials.csv");
		Assert.assertEquals(1, partitioner.partition(sessionWithOnePointFiveTrials).size());
	}
	
	@Test
	void sessionWithATrialAfterAPrematureIsTwoTrials() {
		this.sessionWithATrialAfterAPremature = this.readTestSession("SessionWithATrialAfterAPremature.csv");
		Assert.assertEquals(2, partitioner.partition(sessionWithATrialAfterAPremature).size());
	}
	
	@Test
	void sessionWith10TrialsHasTenTrials() {
		this.sessionWith10Trials = this.readTestSession("SessionWithTenTrials.csv");
		Assert.assertEquals(10, partitioner.partition(sessionWith10Trials).size());
	}
	
	@Test
	void fullSessionOneDoesIndeedHaveFiftyTrials() {
		this.fullSessionOne = this.readTestSession("FullSessionOne.csv");
		Assert.assertEquals(50, partitioner.partition(fullSessionOne).size());
	}
	
	@Test
	void fullSessionTwoDoesIndeedHaveFiftySixTrials() {
		this.fullSessionTwo = this.readTestSession("fullSessionTwo.csv");
		Assert.assertEquals(56, partitioner.partition(fullSessionTwo).size());
	}
	
	
	
	Event[] readTestSession(String fileName) {
		String fileLocation = "testfiles/FiveChoiceTrialPartitioner/" + fileName;
		Session toBeRead = new Session(new File(fileLocation));
		toBeRead.generateEventsFromFile();
		return toBeRead.getEventArrayCopy();
	}
}
