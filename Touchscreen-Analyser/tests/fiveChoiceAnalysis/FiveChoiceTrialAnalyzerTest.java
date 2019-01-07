package fiveChoiceAnalysis;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataModels.Event;
import dataModels.Trial;

class FiveChoiceTrialAnalyzerTest {
	
	private static final String firstBrightnessTrialFileName = "FirstBrightnessTrial.csv";
	
	private static final String brightnessTrialFileName = "BrightnessTrial.csv";

	private FiveChoiceTrialAnalyzer analyzer;
	
	private FiveChoiceSessionParameters trainingParameters;
	
	private final String correctTrialFileName = "CorrectTrial.csv";
	
	private final String incorrectTrialFileName = "IncorrectTrial.csv";
	
	private final String ommittedTrialFileName = "OmmittedTrial.csv";
	
	private final String prematureTrialFileName = "PrematureTrial.csv";
	
	private final String firstDelayTrialFileName = "FirstDelayTrial.csv";
	
	private final String delayTrialFileName = "DelayTrial.csv";
	
	

	@BeforeEach
	void setUp() throws Exception {
		analyzer = new FiveChoiceTrialAnalyzer();
		trainingParameters = new FiveChoiceSessionParameters();
	}

	@Test
	void incorrectTrialHasCorrectTimestamp() {
		Trial trial = this.createTrialFromEventFile(incorrectTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(768.562, result.getTimeStamp(), 0.01);
	}
	
	@Test
	void incorrectTrialHasCorrectStimulusDuration() {
		trainingParameters.setStimulusDuration(2);
		analyzer.setParameters(trainingParameters);
		Trial trial = this.createTrialFromEventFile(incorrectTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(2, result.getStimulusDuration(), 0.01);
	}
	
	@Test
	void incorrectTrialHasCorrectDelayValue() {
		trainingParameters.setStimulusDelay(5);
		analyzer.setParameters(trainingParameters);
		Trial trial = this.createTrialFromEventFile(incorrectTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(5, result.getStimulusDelay(), 0.01);
	}
	
	@Test
	void incorrectTrialHasCorrectBrightnessValue() {
		analyzer.setParameters(trainingParameters);
		Trial trial = this.createTrialFromEventFile(incorrectTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(5, result.getStimulusBrightness(), 0.01);
	}
	
	@Test
	void firstDelayTrialHasCorrectDelayValue(){
		Trial trial = this.createTrialFromEventFile(firstDelayTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(3, result.getStimulusDelay(), 0.01);
	}
	
	@Test
	void randomDelayTrialHasCorrectDelayValue() {
		trainingParameters.setStimulusDelay(5);
		analyzer.setParameters(trainingParameters);
		Trial trial = this.createTrialFromEventFile(delayTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(4, result.getStimulusDelay(), 0.01);
	}
	
	@Test
	void firstBrightnessTrialHasCorrectBrightnessValue() {
		trainingParameters.setStimulusBrightness(3);
		analyzer.setParameters(trainingParameters);
		Trial trial = this.createTrialFromEventFile(firstBrightnessTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(3, result.getStimulusBrightness(), 0.01);
	}
	
	@Test
	void randomBrightnessTrialHasCorrectBrightnessValue() {
		trainingParameters.setStimulusBrightness(3);
		analyzer.setParameters(trainingParameters);
		Trial trial = this.createTrialFromEventFile(brightnessTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(1, result.getStimulusBrightness(), 0.01);
	}
	
	@Test
	void correctTrialHasCorrectLocation() {
		Trial trial = this.createTrialFromEventFile(correctTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(1, result.getTargetPosition(), 0.01);
	}
	
	@Test
	void prematureTrialHasCorrectLocation() {
		Trial trial = this.createTrialFromEventFile(prematureTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(5, result.getTargetPosition(), 0.01);
	}
	
	@Test 
	void ommitedTrialIsNotPremature(){
		Trial trial = this.createTrialFromEventFile(ommittedTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertTrue(!result.isPremature());
	}
	
	@Test
	void prematureTrialIsPremature() {
		Trial trial = this.createTrialFromEventFile(prematureTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertTrue(result.isPremature());
	}
	
	@Test 
	void prematureTrialWasRespondedto(){
		Trial trial = this.createTrialFromEventFile(prematureTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertTrue(result.isResponse());
	}
	
	@Test 
	void correctTrialWasRespondedto(){
		Trial trial = this.createTrialFromEventFile(correctTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertTrue(result.isResponse());
	}
	
	@Test 
	void ommittedTrialWasNotRespondedto(){
		Trial trial = this.createTrialFromEventFile(ommittedTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertTrue(!result.isResponse());
	}
	
	@Test
	void prematureTrialIsNotCorrect() {
		Trial trial = this.createTrialFromEventFile(prematureTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertTrue(!result.isCorrect());
	}
	
	@Test 
	void incorrectTrialIsNotCorrect() {
		Trial trial = this.createTrialFromEventFile(incorrectTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertTrue(!result.isCorrect());
	}
	
	@Test
	void correctTrialIsCorrect() {
		Trial trial = this.createTrialFromEventFile(correctTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertTrue(result.isCorrect());
	}
	
	@Test
	void ommittedTrialIsIncorrect() {
		Trial trial = this.createTrialFromEventFile(ommittedTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertTrue(!result.isCorrect());
	}
	
	@Test
	void prematureTrialHasCorrectInitiationLatency() {
		Trial trial = this.createTrialFromEventFile(prematureTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(3.305,result.getInitiationLatency(),0.01);
	}
	
	@Test
	void brightnessTrialHasCorrectInitiationLatency() {
		Trial trial = this.createTrialFromEventFile(brightnessTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(4.741,result.getInitiationLatency(),0.01);
	}
	
	@Test
	void incorrectTrialHasCorrectResponseLatency() {
		Trial trial = this.createTrialFromEventFile(incorrectTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(1.646,result.getResponseLatency(),0.01);
	}
	
	@Test
	void correctTrialHasCorrectResponseLatency() {
		Trial trial = this.createTrialFromEventFile(correctTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(0.773,result.getResponseLatency(),0.01);
	}
	
	@Test
	void delayTrialHasCorrectCollectionLatency() {
		Trial trial = this.createTrialFromEventFile(delayTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(1.056,result.getRewardCollectionLatency(),0.01);
	}
	
	@Test
	void correctTrialHasCorrectCollectionLatency() {
		Trial trial = this.createTrialFromEventFile(correctTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(1.082,result.getRewardCollectionLatency(),0.01);
	}
	
	@Test
	void incorrectTrialHasCorrectCollectionLatency() {
		Trial trial = this.createTrialFromEventFile(incorrectTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertTrue(((Float)result.getRewardCollectionLatency()).isNaN());
	}

	@Test
	void prematureTrialHasCorrectCollectionLatency() {
		Trial trial = this.createTrialFromEventFile(prematureTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(4.709,result.getRewardCollectionLatency(),0.01);
	}
	
	@Test
	void firstDelayTrialHasCorrectInitiationTouches() {
		Trial trial = this.createTrialFromEventFile(firstDelayTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(0, result.getInitiationPeriodTouches());
	}
	
	@Test
	void firstBrightnessTrialHasCorrectInitiationTouches() {
		Trial trial = this.createTrialFromEventFile(firstBrightnessTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(1, result.getInitiationPeriodTouches());
	}
	
	@Test 
	void incorrectTrialHasCorrectTimeoutTouches() {
		Trial trial = this.createTrialFromEventFile(incorrectTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(1, result.getTotalPostStimulusPeriodTouches());
	}
	
	@Test
	void correctTrialHasCorrectPostStimTouches() {
		Trial trial = this.createTrialFromEventFile(correctTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(0, result.getTotalPostStimulusPeriodTouches());
	}
	
	@Test
	void brightnessTrialHasOnePerseverativeTouch() {
		Trial trial = this.createTrialFromEventFile(brightnessTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(1, result.getPerseverativeTouches());
	}
	
	@Test
	void incorrectTrialHasOnePerseverativeTouch() {
		Trial trial = this.createTrialFromEventFile(incorrectTrialFileName);
		FiveChoiceResult result = analyzer.generateResult(trial, 0, null);
		Assert.assertEquals(1, result.getPerseverativeTouches());
	}

	private Trial createTrialFromEventFile(String fileName) {
		String filepath = "testfiles/FiveChoiceTrialAnalyzer/" + fileName;
		Event[] events = Event.readEventsFromFile(new File(filepath));
		return new Trial(events);
	}
	
	

}
