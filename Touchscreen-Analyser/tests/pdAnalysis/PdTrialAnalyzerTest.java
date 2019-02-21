package pdAnalysis;


import java.io.File;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataModels.Event;
import dataModels.Trial;

class PdTrialAnalyzerTest {
	
	private static PdTrialAnalyzer analyzer;
	
	static PdSessionParameters session0Parameters;
	static final String session0Address = "testfiles/PdTrialPartitioner/session0.csv";
	
	static PdSessionParameters session1Parameters;
	static final String session1Address = "testfiles/PdTrialPartitioner/session1.csv";
	
	static PdSessionParameters session2Parameters;
	static final String session2Address = "testfiles/PdTrialPartitioner/session2.csv";
	
	
	private static Trial correctTrial0;
	private static final String correctTrial0Address = "testfiles/PdTrialAnalyzer/correctTrial0.csv";

	private static Trial correctTrial1;
	private static final String correctTrial1Address = "testfiles/PdTrialAnalyzer/correctTrial1.csv";
	
	private static Trial correctTrial2;
	private static final String correctTrial2Address = "testfiles/PdTrialAnalyzer/correctTrial2.csv";
	
	private static Trial incorrectTrial0;
	private static final String incorrectTrial0Address = "testfiles/PdTrialAnalyzer/incorrectTrial0.csv";
	
	private static Trial incorrectTrial1;
	private static final String incorrectTrial1Address = "testfiles/PdTrialAnalyzer/incorrectTrial1.csv";
	
	private static Trial incorrectTrial2;
	private static final String incorrectTrial2Address = "testfiles/PdTrialAnalyzer/incorrectTrial2.csv";
	
	private static Trial correctionTrial0;
	private static final String correctionTrial0Address = "testfiles/PdTrialAnalyzer/correctionTrial0.csv";
	
	private static Trial correctionTrial1;
	private static final String correctionTrial1Address = "testfiles/PdTrialAnalyzer/correctionTrial1.csv";
	
	private static Trial correctionTrial2;
	private static final String correctionTrial2Address = "testfiles/PdTrialAnalyzer/correctionTrial2.csv";

	@BeforeEach
	void setUp() throws Exception {
		analyzer = new PdTrialAnalyzer();
		correctTrial0 = new Trial(Event.readEventsFromFile(new File(correctTrial0Address)));
		correctTrial1 = new Trial(Event.readEventsFromFile(new File(correctTrial1Address)));
		correctTrial2 = new Trial(Event.readEventsFromFile(new File(correctTrial2Address)));
		incorrectTrial0 = new Trial(Event.readEventsFromFile(new File(incorrectTrial0Address)));
		incorrectTrial1 =new Trial(Event.readEventsFromFile(new File(incorrectTrial1Address)));
		incorrectTrial2 = new Trial(Event.readEventsFromFile(new File(incorrectTrial2Address)));
		correctionTrial0 = new Trial(Event.readEventsFromFile(new File(correctionTrial0Address)));
		correctionTrial1 = new Trial(Event.readEventsFromFile(new File(correctionTrial1Address)));
		correctionTrial2 = new Trial(Event.readEventsFromFile(new File(correctionTrial2Address)));
		this.session0Parameters = (PdSessionParameters)new PdParameterReader().readParameters(Event.readEventsFromFile(new File(session0Address)));
		this.session1Parameters = (PdSessionParameters)new PdParameterReader().readParameters(Event.readEventsFromFile(new File(session1Address)));
		this.session2Parameters = (PdSessionParameters)new PdParameterReader().readParameters(Event.readEventsFromFile(new File(session2Address)));
	}

	@Test
	void correctTrial0IsNotACorrectionTrial() {
		analyzer.setParameters(session0Parameters);
		PdResult result = analyzer.analyze(correctTrial0,0);
		Assert.assertEquals(result.getCorrectionTrial(), 0);
	}
	
	@Test
	void correctTrial1IsNotAcCorrectionTrial() {
		analyzer.setParameters(session1Parameters);
		PdResult result = analyzer.analyze(correctTrial1,0);
		Assert.assertEquals(result.getCorrectionTrial(), 0);
	}
	
	@Test
	void correctTrial2IsACorrectionTrial() {
		analyzer.setParameters(session2Parameters);
		PdResult result = analyzer.analyze(correctTrial2,0);
		Assert.assertEquals(result.getCorrectionTrial(), 1);
	}
	
	@Test 
	void correctionTrial0IsACorrectionTrial(){
		analyzer.setParameters(session0Parameters);
		PdResult result = analyzer.analyze(correctionTrial0,0);
		Assert.assertEquals(result.getCorrectionTrial(), 1);
	}
	
	@Test
	void correctionTrial1IsACorrectionTrial() {
		analyzer.setParameters(session1Parameters);
		PdResult result = analyzer.analyze(correctionTrial1,0);
		Assert.assertEquals(result.getCorrectionTrial(), 1);
	}
	
	@Test
	void correctionTrial2IsACorrectionTrial() {
		analyzer.setParameters(session2Parameters);
		PdResult result = analyzer.analyze(correctionTrial2,0);
		Assert.assertEquals(result.getCorrectionTrial(), 1);
	}
	
	@Test
	void correctTrial0HasRightStimulusLocation() {
		analyzer.setParameters(session0Parameters);
		PdResult result = analyzer.analyze(correctTrial0,0);
		Assert.assertEquals(result.getsPlusLocation(), 1);
	}
	
	@Test
	void incorrectTrial0HasRightStimulusLocation() {
		analyzer.setParameters(session0Parameters);
		PdResult result = analyzer.analyze(incorrectTrial0,0);
		Assert.assertEquals(result.getsPlusLocation(), 2);
	}
	
	@Test
	void correctionTrial0HasItsPredecesorsStimulusLocation() {
		analyzer.setParameters(session0Parameters);
		PdResult result0 = analyzer.analyze(incorrectTrial0, 0);
		PdResult result = analyzer.analyze(correctionTrial0, 1);
		Assert.assertEquals(result.getsPlusLocation(),2 );
	}
	
	@Test
	void correctionTrial1HasItsPredecesorsStimulusLocation() {
		analyzer.setParameters(session1Parameters);
		PdResult result0 = analyzer.analyze(incorrectTrial1, 0);
		int expectedStimulusLocation = result0.getsPlusLocation();
		PdResult result = analyzer.analyze(correctionTrial1, 1);
		Assert.assertEquals(result.getsPlusLocation(),expectedStimulusLocation );
	}
	
	@Test
	void correctionTrial2HasItsPredecesorsStimulusLocation() {
		analyzer.setParameters(session2Parameters);
		PdResult result0 = analyzer.analyze(incorrectTrial2, 0);
		int expectedStimulusLocation = result0.getsPlusLocation();
		PdResult result = analyzer.analyze(correctionTrial2, 1);
		Assert.assertEquals(result.getsPlusLocation(),expectedStimulusLocation );
	}
	
	@Test
	void correctTrial1HasCorrectInitiationLatency() {
		analyzer.setParameters(session1Parameters);
		PdResult result = analyzer.analyze(correctTrial1, 0);
		Assert.assertEquals(result.getInitiationLatency(), 0, 0.001);
	}
	
	@Test
	void incorrectTrial2HasCorrectInitiationLatency() {
		analyzer.setParameters(session2Parameters);
		PdResult result = analyzer.analyze(incorrectTrial2, 0);
		Assert.assertEquals(result.getInitiationLatency(), 0.988, 0.001);
	}
	
	@Test
	void correctionTrial1HasCorrectInitiationLatency() {
		analyzer.setParameters(session1Parameters);
		PdResult result = analyzer.analyze(correctionTrial1, 0);
		Assert.assertEquals(result.getInitiationLatency(),0.711 , 0.001);
	}
	
	@Test
	void correctTrial1HasCorrectWithdrawalLatency() {
		analyzer.setParameters(session1Parameters);
		PdResult result = analyzer.analyze(correctTrial1, 0);
		Assert.assertEquals(result.getHeadWithdrawalTime(), 2.214 , 0.001);
	}
	
	@Test
	void incorrectTrial1HasCorrectWithdrawalLatency() {
		analyzer.setParameters(session1Parameters);
		PdResult result = analyzer.analyze(incorrectTrial1, 0);
		Assert.assertEquals(result.getHeadWithdrawalTime(), 0.119 , 0.001);
	}
	
	@Test
	void incorrectTrial2HasCorrectWithdrawalLatency() {
		analyzer.setParameters(session2Parameters);
		PdResult result = analyzer.analyze(incorrectTrial2, 0);
		Assert.assertEquals(result.getHeadWithdrawalTime(), 3.018 , 0.001);
	}
	
	@Test
	void correctionTrial1HasCorrectWithdrawalLatency() {
		analyzer.setParameters(session1Parameters);
		PdResult result = analyzer.analyze(correctionTrial1, 0);
		Assert.assertEquals(result.getHeadWithdrawalTime(), 0.031 , 0.001);
	}
	
	@Test
	void correctTrial2HasCorrectResponseLatency() {
		analyzer.setParameters(session2Parameters);
		PdResult result = analyzer.analyze(correctTrial2, 0);
		Assert.assertEquals(4.349, result.getResponseLatency(), 0.001);
	}
	
	@Test
	void incorrectTrial2HasCorrectResponseLatency() {
		analyzer.setParameters(session2Parameters);
		PdResult result = analyzer.analyze(incorrectTrial2, 0);
		Assert.assertEquals(10.883, result.getResponseLatency(), 0.001);
	}
	
	@Test
	void correctionTrial2HasCorrectResponseLatency() {
		analyzer.setParameters(session2Parameters);
		PdResult result = analyzer.analyze(correctionTrial2, 0);
		Assert.assertEquals(5.882, result.getResponseLatency(), 0.001);
	}
	
	@Test
	void correctTrial0IsCorrect() {
		analyzer.setParameters(session0Parameters);
		PdResult result = analyzer.analyze(correctTrial0, 0);
		Assert.assertEquals(1,result.getCorrect());
	}
	
	@Test
	void correctTrial1IsCorrect() {
		analyzer.setParameters(session1Parameters);
		PdResult result = analyzer.analyze(correctTrial1, 0);
		Assert.assertEquals(1,result.getCorrect());
	}
	
	@Test
	void correctTrial2IsCorrect() {
		analyzer.setParameters(session2Parameters);
		PdResult result = analyzer.analyze(correctTrial2, 0);
		Assert.assertEquals(1,result.getCorrect());
	}
	
	@Test
	void incorrectTrial0IsIncorrect() {
		analyzer.setParameters(session0Parameters);
		PdResult result = analyzer.analyze(incorrectTrial0, 0);
		Assert.assertEquals(0, result.getCorrect());
	}
	
	@Test
	void incorrectTrial1IsIncorrect() {
		analyzer.setParameters(session1Parameters);
		PdResult result = analyzer.analyze(incorrectTrial1, 0);
		Assert.assertEquals(0, result.getCorrect());
	}
	
	@Test
	void incorrectTrial2IsIncorrect() {
		analyzer.setParameters(session2Parameters);
		PdResult result = analyzer.analyze(incorrectTrial2, 0);
		Assert.assertEquals(0, result.getCorrect());
	}
	
	@Test
	void correctTrial0HasRightCollectionLatency() {
		analyzer.setParameters(session0Parameters);
		PdResult result = analyzer.analyze(correctTrial0, 0);
		Assert.assertEquals(1.511, result.getRewardCollectionLatency(),0.001);
	}
	
	@Test
	void correctTrial1HasRightCollectionLatency() {
		analyzer.setParameters(session1Parameters);
		PdResult result = analyzer.analyze(correctTrial1, 0);
		Assert.assertEquals(1.061, result.getRewardCollectionLatency(),0.001);
	}
	
	@Test
	void correctTrial2HasRightCollectionLatency() {
		analyzer.setParameters(session2Parameters);
		PdResult result = analyzer.analyze(correctTrial2, 0);
		Assert.assertEquals(1.174, result.getRewardCollectionLatency(),0.001);
	}
	
	@Test
	void correctTrial0HasRightFeedingTime() {
		analyzer.setParameters(session0Parameters);
		PdResult result = analyzer.analyze(correctTrial0, 0);
		Assert.assertEquals(7.677, result.getFeedingTime(), 0.001);
	}
	
	@Test
	void correctTrial1HasRightFeedingTime() {
		analyzer.setParameters(session1Parameters);
		PdResult result = analyzer.analyze(correctTrial1, 0);
		Assert.assertEquals(3.412, result.getFeedingTime(), 0.001);
	}
	
	@Test
	void correctTrial2HasRightFeedingTime() {
		analyzer.setParameters(session2Parameters);
		PdResult result = analyzer.analyze(correctTrial2, 0);
		Assert.assertEquals(4.221, result.getFeedingTime(), 0.001);
	}
	
	@Test
	void correctionTrial0HasRightTimeInTrial() {
		analyzer.setParameters(session0Parameters);
		PdResult result = analyzer.analyze(correctionTrial0, 0);
		Assert.assertEquals(24.402, result.getTimeInTrial(), .001);
	}
	
	@Test
	void correctionTrial1HasRightTimeInTrial() {
		analyzer.setParameters(session1Parameters);
		PdResult result = analyzer.analyze(correctionTrial1, 0);
		Assert.assertEquals(23.321, result.getTimeInTrial(), .001);
	}
	
	@Test
	void correctionTrial2HasRightTimeInTrial() {
		analyzer.setParameters(session2Parameters);
		PdResult result = analyzer.analyze(correctionTrial2, 0);
		Assert.assertEquals(37.762, result.getTimeInTrial(), .001);
	}
	
	

}
