package progressiveRatioAnalysis;

import java.io.File;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dataModels.Event;
import dataModels.Trial;

class ProgressiveRatioTrialAnalyzerTest {
	
	private ProgressiveRatioTrialAnalyzer analyzer;
	
	
	//reference to a PR session that has plenty of trial material
	private File session0;
	private final String SESSION_0_ADDRESS =
			"testfiles/ProgressiveRatioAnalysis/ProgressiveRatioTrialAnalyzerFiles/session0.csv";

	@BeforeEach
	void setUp() throws Exception {
		analyzer = new ProgressiveRatioTrialAnalyzer();
		session0 = new File(this.SESSION_0_ADDRESS);
	}

	@Test
	void firstTimeStampis0() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)0.03);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		
		Assert.assertEquals(0,result.getTimeStamp(),0.01);
	}
	
	@Test
	void firstRequiredRatioIs1() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)0.03);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		
		Assert.assertEquals(1, result.getRequiredRatio());
	}
	
	@Test
	void firstValidTouchesIs1() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)0.03);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		
		Assert.assertEquals(1,result.getValidTouches());
	}
	
	@Test
	void firstITITouchesIs0() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)0.03);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		
		Assert.assertEquals(0, result.getItiTouches());
	}
	
	@Test
	void firstSessionTouchLatencyIsCorrect() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)0.03);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		
		Assert.assertEquals(0.875,result.getFirstTouchLatency(),0.01);
	}
	
	@Test
	void firstRewardCollectionLatencyIsCorrect() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)0.03);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		
		Assert.assertEquals(1.299, result.getRewardCollectionLatency(), 0.01);
	}
	
	@Test
	void firstAverageTouchIsInvalid() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)0.03);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		
		Assert.assertTrue(Float.isNaN(result.getAverageTimeBetweenValidTouches()));
	}
	
	@Test
	void firstTrialHasCorrectFrontBeamBreaks() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)0.03);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		
		Assert.assertEquals(3, result.getFrontBeamBreaks());
	}
	
	@Test
	void firstTimeInTrialIsCorrect() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)0.03);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		
		Assert.assertEquals(8.751, result.getTimeInTrial(),0.01);
	}
	
	@Test
	void secondTrialHasCorrectITITouches() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)13.0);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		
		Assert.assertEquals(1,result.getItiTouches());
	}
	
	@Test
	void secondTrialHasCorrectValidTouches() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)13.0);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		
		Assert.assertEquals(5, result.getValidTouches());
	}
	
	@Test
	void secondTrialHasCorrectAverageBetweenValid() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)13.0);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		
		Assert.assertEquals(0.945, result.getAverageTimeBetweenValidTouches(), 0.01);
	}
	
	@Test
	void secondTrialHasCorrectMedianBetweenValid() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)13.0);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		Assert.assertEquals(0.678, result.getMedianTimeBetweenValidTouches(), 0.01);
	}
	
	@Test
	void secondTrialHasCorrectAverageBetweenCenter() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)13.0);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		Assert.assertEquals(0.756, result.getAverageTimeBetweenCenterTouches(),0.01);
	}
	
	@Test
	void secondTrialHasCorrectMedianBetweenCenter() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)13.0);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		Assert.assertEquals(0.53, result.getMedianTimeBetweenCenterTouches(), 0.01);
	}
	
	@Test
	void thirdTrialHasCorrectValidTouches() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)30.0);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		Assert.assertEquals(9, result.getValidTouches());
	}
	
	@Test
	void thirdTrialHasCorrectITITouches() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)30.0);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		Assert.assertEquals(3, result.getItiTouches());
	}
	
	@Test
	void thirdTrialHasCorrectAverageBetweenCenterTouches() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)30.0);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		Assert.assertEquals(0.584, result.getAverageTimeBetweenCenterTouches(), 0.01);
	}
	
	@Test
	void thirdTrialHasCorrectMedianBetweenCenterTouches() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)30.0);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		Assert.assertEquals(0.587,result.getMedianTimeBetweenCenterTouches(),0.01);
	}
	
	@Test 
	void thirdTrialHasCorrectResponseLatency(){
		Trial testTrial = getTrialByTimeStamp(session0, (float)30.0);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		Assert.assertEquals(3.839, result.getFirstTouchLatency(),0.01);
	}
	
	@Test
	void thirdTrialHasCorrectCollectionLatency() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)30.0);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		Assert.assertEquals(1.121, result.getRewardCollectionLatency(), 0.01);
	}
	
	@Test
	void requiredTouchesProgressionIsCorrect() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)0.5);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		Assert.assertEquals(1, result.getRequiredRatio());
		testTrial = getTrialByTimeStamp(session0, (float)10.0);
		result = analyzer.analyze(testTrial, 1, null, null);
		Assert.assertEquals(5, result.getRequiredRatio());
		testTrial = getTrialByTimeStamp(session0, (float)25.0);
		result = analyzer.analyze(testTrial, 2, null, null);
		Assert.assertEquals(9, result.getRequiredRatio());
		testTrial = getTrialByTimeStamp(session0, (float)44.0);
		result = analyzer.analyze(testTrial, 3, null, null);
		Assert.assertEquals(13, result.getRequiredRatio());
		testTrial = getTrialByTimeStamp(session0, (float)67.0);
		result = analyzer.analyze(testTrial, 4, null, null);
		Assert.assertEquals(17, result.getRequiredRatio());
		testTrial = getTrialByTimeStamp(session0, (float)102.0);
		result = analyzer.analyze(testTrial, 5, null, null);
		Assert.assertEquals(21, result.getRequiredRatio());
	}
	
	@Test
	void validTouchesProgressionIsCorrect() {
		Trial testTrial = getTrialByTimeStamp(session0, (float)0.5);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		Assert.assertEquals(1, result.getValidTouches());
		testTrial = getTrialByTimeStamp(session0, (float)10.0);
		result = analyzer.analyze(testTrial, 1, null, null);
		Assert.assertEquals(5, result.getValidTouches());
		testTrial = getTrialByTimeStamp(session0, (float)25.0);
		result = analyzer.analyze(testTrial, 2, null, null);
		Assert.assertEquals(9, result.getValidTouches());
		testTrial = getTrialByTimeStamp(session0, (float)44.0);
		result = analyzer.analyze(testTrial, 3, null, null);
		Assert.assertEquals(13, result.getValidTouches());
		testTrial = getTrialByTimeStamp(session0, (float)67.0);
		result = analyzer.analyze(testTrial, 4, null, null);
		Assert.assertEquals(17, result.getValidTouches());
		testTrial = getTrialByTimeStamp(session0, (float)102.0);
		result = analyzer.analyze(testTrial, 5, null, null);
		Assert.assertEquals(21, result.getValidTouches());
	}
	
	void trialFourHasCorrectNumberOfBeamBreaks() {
		Trial testTrial = this.getTrialByTimeStamp(session0, (float)56.0);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		Assert.assertEquals(4,result.getFrontBeamBreaks());
		Assert.assertEquals(1, result.getBackBeamBreaks());
		Assert.assertEquals(1, result.getTrayBeamBreaks());
	}
	
	void correctITITouchesInPenultimateTrial() {
		Trial testTrial = this.getTrialByTimeStamp(session0, (float)2654.0);
		ProgressiveRatioResult result = analyzer.analyze(testTrial, 0, null, null);
		Assert.assertEquals(7, result.getItiTouches());
	}
	
	
	
	private Trial getTrialByTimeStamp(File session, float timeStamp) {
		ProgressiveRatioTrialPartitioner partitioner = new ProgressiveRatioTrialPartitioner();
		ArrayList<Trial> testTrials = partitioner.partition(Event.readEventsFromFile(session));
		return Trial.getTrialByTimeStamp(testTrials, timeStamp);
	}

}
