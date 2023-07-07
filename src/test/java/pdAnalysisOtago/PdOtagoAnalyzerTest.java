package pdAnalysisOtago;

import java.io.File;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataModels.Event;

class PdOtagoAnalyzerTest {

	private PdOtagoAnalyzer analyzer;
	
	private final String incorrectNonCorrectionFirstTrial = "testfiles/PdOtagoAnalyzerTest/session0.csv";
	private final String incorrectNonCorrectionNthTrial = "testfiles/PdOtagoAnalyzerTest/session1.csv";
	private final String incorrectNonCorrectionFinalTrial = "testfiles/PdOtagoAnalyzerTest/session2.csv";
	private final String correctNonCorrectionFirstTrial = "testfiles/PdOtagoAnalyzerTest/session3.csv";
	private final String correctNonCorrectionNthTrial = "testfiles/PdOtagoAnalyzerTest/session4.csv";
	private final String correctNonCorrectionFinalTrial = "testfiles/PdOtagoAnalyzerTest/session5.csv";
	private final String incorrectCTNthTrial = "testfiles/PdOtagoAnalyzerTest/session6.csv";
	private final String incorrectCTFinalTrial = "testfiles/PdOtagoAnalyzerTest/session7.csv";
	private final String correctCTFinalTrial = "testfiles/PdOtagoAnalyzerTest/session8.csv";
	private final String correctCTNthTrial = "testfiles/PdOtagoAnalyzerTest/session9.csv";
		
	@BeforeEach
	void setUp() throws Exception {
		analyzer = new PdOtagoAnalyzer();
	}

	//Test case 1: incorrect non correction trial, first trial
	@Test
	void incorrectNonCorrectionFirstTrial() {
		Event[] events = Event.readEventsFromFile(new File(incorrectNonCorrectionFirstTrial));
		Assertions.assertEquals(0, analyzer.determineIfCorrectionTrial(events));
	}
	
	@Test
	void incorrectNonCorrectionFirstTrial_incorrect() {
		Event[] events = Event.readEventsFromFile(new File(incorrectNonCorrectionFirstTrial));
		Assertions.assertEquals(0, analyzer.determineIfCorrect(events));	
	}
	
	//Test case 2: incorrect non correction trial, nth trial 
	@Test
	void incorrectNonCorrectionNthTrial_ct() {
		Event[] events = Event.readEventsFromFile(new File(incorrectNonCorrectionNthTrial));
		Assertions.assertEquals(0, analyzer.determineIfCorrectionTrial(events));
	}
	
	@Test
	void incorrectNonCorrectionNthTrial_incorrect() {
		Event[] events = Event.readEventsFromFile(new File(incorrectNonCorrectionNthTrial));
		Assertions.assertEquals(0, analyzer.determineIfCorrect(events));	
	}
	
	//Test case 3: incorrect non correction trial, final trial
	@Test
	void incorrectNonCorrectionFinalTrial_ct() {
		Event[] events = Event.readEventsFromFile(new File(incorrectNonCorrectionFinalTrial));
		Assertions.assertEquals(0, analyzer.determineIfCorrectionTrial(events));
	}
	
	@Test
	void incorrectNonCorrectionFinalTrial_incorrect() {
		Event[] events = Event.readEventsFromFile(new File(incorrectNonCorrectionFinalTrial));
		Assertions.assertEquals(0, analyzer.determineIfCorrect(events));	
	}
	
	//Test case 4: correct non correction trial, first trial
	@Test
	void correctNonCorrectionFirstTrial_ct() {
		Event[] events = Event.readEventsFromFile(new File(correctNonCorrectionFirstTrial));
		Assertions.assertEquals(0, analyzer.determineIfCorrectionTrial(events));
	}
	
	@Test
	void correctNonCorrectionFirstTrial_correct() {
		Event[] events = Event.readEventsFromFile(new File(correctNonCorrectionFirstTrial));
		Assertions.assertEquals(1, analyzer.determineIfCorrect(events));	
	}
	
	//Test case 5: correct non correction trial, nth trial
	@Test
	void correctNonCorrectionNthTrial_ct() {
		Event[] events = Event.readEventsFromFile(new File(correctNonCorrectionNthTrial));
		Assertions.assertEquals(0, analyzer.determineIfCorrectionTrial(events));	
	}
	
	@Test
	void correctNonCorrectionNthTrial_correct() {
		Event[] events = Event.readEventsFromFile(new File(correctNonCorrectionNthTrial));
		Assertions.assertEquals(1, analyzer.determineIfCorrect(events));	
	}
	
	//Test case 6: correct non correction trial, final trial
	@Test
	void correctNonCorrectionFinalTrial_ct() {
		Event[] events = Event.readEventsFromFile(new File(correctNonCorrectionFinalTrial));
		Assertions.assertEquals(0, analyzer.determineIfCorrectionTrial(events));	
	}
	
	@Test
	void correctNonCorrectionFinalTrial_correct() {
		Event[] events = Event.readEventsFromFile(new File(correctNonCorrectionFinalTrial));
		Assertions.assertEquals(1, analyzer.determineIfCorrect(events));		
	}
	
	//Test case 7: incorrect correction trial, Nth trial
	@Test
	void incorrectCTNthTrial_ct() {
		Event[] events = Event.readEventsFromFile(new File(incorrectCTNthTrial));
		Assertions.assertEquals(1, analyzer.determineIfCorrectionTrial(events));	
	}
	
	@Test
	void incorrectCTNthTrial_incorrect() {
		Event[] events = Event.readEventsFromFile(new File(incorrectCTNthTrial));
		Assertions.assertEquals(0, analyzer.determineIfCorrect(events));		
	}
	
	//Test case 8: incorrect correction trial, final trial
	@Test
	void incorrectCTFinalTrial_ct() {
		Event[] events = Event.readEventsFromFile(new File(incorrectCTFinalTrial));
		Assertions.assertEquals(1, analyzer.determineIfCorrectionTrial(events));			
	}
	
	@Test
	void incorrectCTFinalTrial_incorrect() {
		Event[] events = Event.readEventsFromFile(new File(incorrectCTFinalTrial));
		Assertions.assertEquals(0, analyzer.determineIfCorrect(events));				
	}
	
	//Test case 9: correct correction trial, final trial
	@Test
	void correctCTFinalTrial_ct() {
		Event[] events = Event.readEventsFromFile(new File(correctCTFinalTrial));
		Assertions.assertEquals(1, analyzer.determineIfCorrectionTrial(events));				
	}
	
	@Test
	void correctCtFinalTrial_correct() {
		Event[] events = Event.readEventsFromFile(new File(correctCTFinalTrial));
		Assertions.assertEquals(1, analyzer.determineIfCorrect(events));					
	}
	
	//Test case 10: correct correction trial, nth trial
	
	@Test
	void correctCTNthTrial_ct() {
		Event[] events = Event.readEventsFromFile(new File(correctCTNthTrial));
		Assertions.assertEquals(1, analyzer.determineIfCorrectionTrial(events));					
	}
	
	@Test
	void correctCTNthTrial_correct() {
		Event[] events = Event.readEventsFromFile(new File(correctCTNthTrial));
		Assertions.assertEquals(1, analyzer.determineIfCorrect(events));						
	}

}
