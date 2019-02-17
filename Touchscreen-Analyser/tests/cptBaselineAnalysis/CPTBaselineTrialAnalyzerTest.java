package cptBaselineAnalysis;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataModels.Event;
import dataModels.Session;
import dataModels.Trial;

class CPTBaselineTrialAnalyzerTest {

	private static CPTBaselineTrialAnalyzer trialAnalyzer = new CPTBaselineTrialAnalyzer();
	
	Session original = new Session(new File("testfiles/CPTTrialAnalyzer/OriginalSession.csv"));
	
	private static Trial hitTrial;
	
	private static Trial missTrial;
	
	private static Trial mistakeTrial;
	
	private static Trial correctRejectionTrial;
	
	private static Trial correctionTrialCorrectRejectionTrial;
	
	private static Trial correctionTrialMistakeTrial;
	
	private static final String TEST_FILES = "testfiles/CPTTrialAnalyzer/";
	
	
	@BeforeEach
	public void setupTrials() {
		hitTrial = new Trial(readEventsFromFile(new File(TEST_FILES + "HitTrial.txt")));
		missTrial = new Trial(readEventsFromFile(new File(TEST_FILES + "MissTrial.txt")));
		mistakeTrial = new Trial(readEventsFromFile(new File (TEST_FILES + "MistakeTrial.txt")));
		correctionTrialCorrectRejectionTrial = new Trial(readEventsFromFile(new File(TEST_FILES + "CorrectionTrialCorrectRejection.txt")));
		correctionTrialMistakeTrial = new Trial(readEventsFromFile(new File (TEST_FILES + "CorrectionTrialMistake.txt")));
		correctRejectionTrial = new Trial(readEventsFromFile(new File (TEST_FILES + "CorrectRejectionTrial.txt")));
	}
	@Test
	public void analyzeReturnsCorrectTimeStampOfHitTrial(){
		CPTBaselineResult result = trialAnalyzer.analyze(hitTrial, 0, null);
		Assert.assertEquals((float) 33.783, result.getTimeStamp(),0.01);
	}
	@Test
	public void analyzeReturnsCorrectTimeStampOfMissTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(missTrial, 0, null);
		Assert.assertEquals(112.961, result.getTimeStamp(), 0.01);
	}
	
	@Test 
	public void analyzeReturnsNonCorrectionTrialInHitTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(hitTrial, 0, null);
		try {
			Assert.assertEquals(false, result.isCorrectionTrial());
		} catch (Exception e) {
			e.printStackTrace();
			fail("value uninitialized");
		}
	}
	
	@Test
	public void analyzeReturnsNonCorrectionTrialInMissTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(missTrial, 0, null);
		try {
			Assert.assertEquals(false, result.isCorrectionTrial());
		} catch (Exception e) {
			e.printStackTrace();
			fail("value uninitialized");
		}
	}
	
	@Test
	public void analyzeReturnsCorrectionTrialOnCorrectionTrialReject() {
		CPTBaselineResult result = trialAnalyzer.analyze(correctionTrialCorrectRejectionTrial, 0, null);
		try {
			Assert.assertEquals(true, result.isCorrectionTrial());
		} catch (Exception e) {
			e.printStackTrace();
			fail("value uninitialized");
		}
	}
	
	@Test
	public void analyzeReturnsCorrectionTrialOnCorrectionTrialMistake() {
		CPTBaselineResult result = trialAnalyzer.analyze(correctionTrialMistakeTrial, 0, null);
		try {
			Assert.assertEquals(true, result.isCorrectionTrial());
		} catch (Exception e) {
			e.printStackTrace();
			fail("value uninitialized");
		}
	}
	
	@Test 
	public void analyzeReturnsIncorrectOnMissTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(missTrial, 0, null);
		try {
			Assert.assertEquals(false, result.isCorrect());
		} catch (Exception e) {
			e.printStackTrace();
			fail("value uninitialized");
		}
	}
	
	@Test
	public void analyzeReturnsIncorrectOnMistakeTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(mistakeTrial, 0, null);
		try {
			Assert.assertEquals(false, result.isCorrect());
		} catch (Exception e) {
			e.printStackTrace();
			fail("value uninitialized");
		}
	}
	
	@Test
	public void analyzeReturnsIncorrectOnCorrectionTrialMistakeTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(correctionTrialMistakeTrial, 0, null);
		try {
			Assert.assertEquals(false, result.isCorrect());
		} catch (Exception e) {
			e.printStackTrace();
			fail("value uninitialized");
		}
	}
	
	@Test
	public void analyzeReturnsTheImageShownOnHitTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(hitTrial, 0, null);
		Assert.assertEquals(2, result.getImageShown());
	}
	
	@Test
	public void analyzeReturnsTheImageShownOnMistakeTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(mistakeTrial, 0, null);
		Assert.assertEquals(3, result.getImageShown());
	}
	
	@Test
	public void analyzeReturnsCorrectOnHit() {
		CPTBaselineResult result = trialAnalyzer.analyze(hitTrial, 0, null);
		try {
			Assert.assertEquals(true, result.isCorrect());
		} catch (Exception e) {
			e.printStackTrace();
			fail("value not initialized");
		}
	}
	
	@Test
	public void analyzeReturnsCorrectOnCorrectRejection() {
		CPTBaselineResult result = trialAnalyzer.analyze(correctRejectionTrial, 0, null);
		try {
			Assert.assertEquals(true, result.isCorrect());
		} catch (Exception e) {
			e.printStackTrace();
			fail("value not initialized");
		}
	}
	
	@Test
	public void analyzeReturnsRightITITouchesInHitTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(hitTrial, 0, null);
		Assert.assertEquals(1, result.getItiCenterTouches());
	}
	
	@Test
	public void analyzeReturnsRightITITouchesInMissTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(missTrial, 0, null);
		Assert.assertEquals(0, result.getItiCenterTouches());
	}
	
	@Test
	public void analyzeReturnsRightTouchesDuringSDInMissTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(missTrial, 0, null);
		Assert.assertEquals(0, result.getTotalTouchesDuringSD());
	}
	
	@Test
	public void analyzeReturnsRightTouchesDuringSDInHitTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(hitTrial, 0, null);
		Assert.assertEquals(2, result.getTotalTouchesDuringSD());
	}
	
	@Test
	public void analyzeReturnsRightTouchesDuringSDInMistakeTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(mistakeTrial, 0, null);
		Assert.assertEquals(2, result.getTotalTouchesDuringSD());
	}
	
	@Test
	public void analyzeReturnsRightTouchesDuringLHInHitTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(hitTrial, 0, null);
		Assert.assertEquals(0, result.getTotalTouchesDuringLH());
	}
	
	@Test
	public void analyzeReturnsRightTouchesDuringLHInMistakeTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(mistakeTrial, 0, null);
		Assert.assertEquals(1, result.getTotalTouchesDuringLH());
	}
	
	@Test
	public void analyzeReturnsRightTouchesDuringLHInCorrectRejectionTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(correctRejectionTrial, 0, null);
		Assert.assertEquals(2, result.getTotalTouchesDuringLH());
	}
	
	@Test
	public void analyzeReturnsRightCenterTouchesDuringPostTrialInMissTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(missTrial, 0, null);
		Assert.assertEquals(0, result.getPostTrialCenterTouches());
	}
	
	@Test
	public void analyzeReturnsRighCenterTouchesDuringPostTrialInMistakeTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(mistakeTrial, 0, null);
		Assert.assertEquals(6, result.getPostTrialCenterTouches());
	}
	
	@Test
	public void analyzeReturnsRightCenterTouchesDuringPostTrialInHitTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(hitTrial, 0, null);
		Assert.assertEquals(1, result.getPostTrialCenterTouches());
	}
	
	@Test
	public void analyzeReturnsRightCenterTouchesDuringPostTrialInCorrectionTrialMistakeTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(correctionTrialMistakeTrial, 0, null);
		Assert.assertEquals(2, result.getPostTrialCenterTouches());
	}
	
	@Test
	public void analyzeReturnsNoResponseLatencyInMissTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(missTrial, 0, null);
		Assert.assertEquals(Float.NaN, result.getResponseLatency(),0.01);
	}
	
	@Test
	public void analyzeReturnsCorrectResponseLatencyInHitTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(hitTrial, 0, null);
		Assert.assertEquals(0.276, result.getResponseLatency(), 0.01);
	}
	
	@Test 
	public void analyzeReturnsCorrectResponseLatencyInMistakeTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(mistakeTrial, 0, null);
		Assert.assertEquals(2.011, result.getResponseLatency(), 0.01);
	}
	
	@Test
	public void analyzeReturnsCorrectResponseLatencyInCorrectionTrialMistakeTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(correctionTrialMistakeTrial, 0, null);
		Assert.assertEquals(0.55, result.getResponseLatency(), 0.01);
	}
	
	@Test
	public void analyzeReturnsNoRewardCollectionLatencyInMissTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(missTrial, 0, null);
		Assert.assertEquals(Float.NaN, result.getRewardCollectionLatency(), 0.01);
	}
	
	@Test
	public void analyzeReturnsNoRewardCollectionLatencyInMistakeTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(mistakeTrial, 0, null);
		Assert.assertEquals(Float.NaN, result.getRewardCollectionLatency(), 0.01);
	}
	
	@Test
	public void analyzeReturnsNoRewardCollectionLatencyInCorrectRejectionTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(correctRejectionTrial, 0, null);
		Assert.assertEquals(Float.NaN, result.getRewardCollectionLatency(), 0.01);
	}
	
	@Test
	public void analyzeReturnsCorrectRewardCollectionLatencyInHitTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(hitTrial, 0, null);
		Assert.assertEquals(0.918, result.getRewardCollectionLatency(), 0.01);
	}
	
	@Test
	public void analyzeReturnsCorrectBeamBreaksInHitTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(hitTrial, 0, null);
		Assert.assertEquals(2, result.getFrontBeamBreaks());
		Assert.assertEquals(2, result.getBackBeamBreaks());
		Assert.assertEquals(1, result.getTrayBeamBreaks());
	}
	
	@Test
	public void analyzeReturnsCorrectBeamBreaksInMissTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(missTrial, 0, null);
		Assert.assertEquals(0, result.getFrontBeamBreaks());
		Assert.assertEquals(0, result.getBackBeamBreaks());
		Assert.assertEquals(0, result.getTrayBeamBreaks());
	}
	
	@Test
	public void analyzeReturnsCorrectBeamBreaksInMistakeTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(mistakeTrial, 0, null);
		Assert.assertEquals(0, result.getFrontBeamBreaks());
		Assert.assertEquals(1, result.getBackBeamBreaks());
		Assert.assertEquals(0, result.getTrayBeamBreaks());
	}
	
	@Test
	public void analyzeReturnsCorrectBeamBreaksInCorrectionTrialMistakeTrial() {
		CPTBaselineResult result = trialAnalyzer.analyze(correctionTrialMistakeTrial, 0, null);
		Assert.assertEquals(1, result.getFrontBeamBreaks());
		Assert.assertEquals(2, result.getBackBeamBreaks());
		Assert.assertEquals(1, result.getTrayBeamBreaks());
	}
	
	private static Event[] readEventsFromFile (File file) {
		try {
			Scanner sc = new Scanner(file);
			
			ArrayList <Event> events = new ArrayList<Event>();
			
			//Read in all events
			while(sc.hasNextLine()) {
				events.add(new Event(sc.nextLine()));
			}
			
			Event[] eventArray = new Event[events.size()];
			
			sc.close();
			
			return events.toArray(eventArray);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("Test file not found");
			return null;
		}
	}
	
}
