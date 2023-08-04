package mPosnerAnalysis;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataModels.Event;
import dataModels.SessionParameters;
import dataModels.Trial;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testUtils.TestUtils;


class MPosnerProbeTrialAnalyzerTest {

	private static final String TEST_FILES_ADDRESS = "src/test/java/mPosnerAnalysis/testFiles/";

	@Test
	void anayzer_parses_timestamp_from_first_event() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_timestamp_testTrial")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(130.591d,result.getTimestamp(),.001d);
	}
	
	@Test
	void analyzer_gets_session_parameters_correctly_exogenous() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_timestamp_testTrial")));
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		
		assertEquals("Exogenous",result.getTrialType());
		assertEquals(0.15d, result.getCueTime(),0.001d);
	}
	
	@Test
	void analyzer_gets_session_parameters_correctly_endogenous() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_endogenous();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_timestamp_testTrial")));
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		
		assertEquals("Endogenous",result.getTrialType());
		assertEquals(0.15d, result.getCueTime(),0.001d);
	}
	
	//Hold time
	@Test
	void analyzer_determines_hold_time_on_first_trial() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_holdTime_firstTrial")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(0.175d,result.getHoldTime(),.001d);
	}
	
	@Test
	void analyzer_determines_hold_time_on_nth_trial() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_timestamp_testTrial")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(0.183d,result.getHoldTime(),.001d);
	}
	
	//Target time
	@Test
	void analyzer_determines_target_time_on_non_probe_trial() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_timestamp_testTrial")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(1d,result.getTargetTime(),.001d);
	}
	
	@Test
	void analyzer_determines_target_time_on_probe_trial_less_than_10_trials() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_targetTime_probe_before10Trials")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(0.5d,result.getTargetTime(),.001d);
	}
	
	@Test
	void analyzer_determines_target_time_on_probe_trial_more_than_10_trials() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_targetTime_probe_after10Trials")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(0.25d,result.getTargetTime(),.001d);
	}
	
	//Cue brightness
	@Test
	void analyzer_determines_cue_brightness_on_non_probe_trial() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_targetTime_probe_after10Trials")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(100d,result.getCueBrightness(),.001d);
	}
	
	@Test
	void analyzer_determines_cue_brightness_on_probe_trial_less_than_10_trials() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_timestamp_testTrial")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(70d,result.getCueBrightness(),.001d);
	}
	
	@Test
	void analyzer_determines_cue_brightness_on_probe_trial_more_than_10_trials() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_cueBrightness_probe_after10Trials")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(50d,result.getCueBrightness(),.001d);
	}
	
	//Cue side
	@Test
	void analyzer_determines_cue_side_exogenous_left() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_targetTime_probe_before10Trials")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals("Left",result.getCueSide());
	}
	
	@Test
	void analyzer_determines_cue_side_exogenous_right() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_CueSide_exogenous_right")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals("Right",result.getCueSide());
	}
	
	@Test
	void analyzer_determines_cue_side_endogenous_left() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_cueSide_endogenous_left")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals("Left",result.getCueSide());
	}
	
	@Test
	void analyzer_determines_cue_side_endogenous_right() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_cueSide_endogenous_right")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals("Right",result.getCueSide());
	}
	
	@Test
	void analyzer_determines_cue_side_endogenous_left_targetTime() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_cueSide_endogenous_left_targetTime")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals("Right",result.getCueSide());
	}
	
	@Test
	void analyzer_determines_cue_side_endogenous_right_targetTime() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_cueSide_endogenous_right_targetTime")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals("Left",result.getCueSide());
	}
	
	//Validity
	@Test
	void analyzer_determines_cue_validity_valid_right() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_cueValidity_valid_right")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals("Valid",result.getCueValidity());
	}
	
	@Test
	void analyzer_determines_cue_validity_valid_left() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_cueValidity_valid_left")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals("Valid",result.getCueValidity());
	}
	
	@Test
	void analyzer_determines_cue_validity_invalid_left() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_cueValidity_invalid_left")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals("Invalid",result.getCueValidity());
	}
	
	@Test
	void analyzer_determines_cue_validity_invalid_right() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_cueValidity_invalid_right")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals("Invalid",result.getCueValidity());
	}
	
	@Test
	void analyzer_determines_no_anticipation_errors() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_anticipationErrors_0")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(0,result.getAnticipationErrors());
	}
	
	@Test
	void analyzer_determines_1_anticipation_error() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_anticipationErrors_1")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(1,result.getAnticipationErrors());
	}
	
	@Test
	void analyzer_determines_3_anticipation_errors() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_anticipationErrors_3")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(3,result.getAnticipationErrors());
	}
	
	@Test
	void analyzer_determines_correct_trial() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_isCorrect_correct")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(1,result.getCorrect());
		assertEquals(0,result.getComissionError());
		assertEquals(0,result.getOmissionError());
	}
	
	@Test
	void analyzer_determines_incorrect_trial() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_isCorrect_incorrect")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(0,result.getCorrect());
		assertEquals(1,result.getComissionError());
		assertEquals(0,result.getOmissionError());
	}
	
	@Test
	void analyzer_determines_omitted_trial() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_isOmission")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(0,result.getCorrect());
		assertEquals(0,result.getComissionError());
		assertEquals(1,result.getOmissionError());
	}
	
	@Test
	void analyzer_determines_stimulus_response_latency_of_non_anticipation_error_trial() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_anticipationErrors_3")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(8.791d,result.getResponseLatency(),.001);
	}
	
	@Test
	void analyzer_determines_reaction_time() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_anticipationErrors_3")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(0.128d,result.getReactionTime(),.001);
	}
	
	//Missing test for when there is no cue
	
	@Test
	void analyzer_determines_movement_time_before_target_vanishes() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_anticipationErrors_1")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(1.019d,result.getMovementTime(),.001);
	}
	
	@Test
	void analyzer_determines_movement_time_after_target_vanishes() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_movementTime_responseAfterTarget")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(0.248d,result.getMovementTime(),.001);
	}
	
	@Test
	void analyzer_determines_movement_time_when_omission() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_isOmission")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assert(Double.isNaN(result.getMovementTime()));
	}
	
	@Test
	void analyzer_determines_reward_latency_before_target_vanishes() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_isCorrect_correct")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(0.575d,result.getRewardCollectionLatency(),.001);
	}
	
	
	@Test
	void analyzer_determines_reward_latency_after_target_vanishes() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_movementTime_responseAfterTarget")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(20.596d,result.getRewardCollectionLatency(),.001);
	}
	
	@Test
	void analyzer_determines_reward_latency_if_incorrect() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_isCorrect_incorrect")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assert(Double.isNaN(result.getRewardCollectionLatency()));
	}
	
	@Test
	void analyzer_doesnt_throw_exception_event_is_in_laggy_group() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_eventFromWrongGroup")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assert(true);
	}
	
	@Test
	void analyzer_determines_ITI_Touches() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_ItiTouches")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(4,result.getTouchesDuringITI());
	}
	
	@Test
	void analyzer_determines_beam_breaks() {
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		Trial trial = new Trial(Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_holdTime_firstTrial")));
		sut.setParameters(parameters);
		
		MPosnerResult result = sut.generateResult(trial, 0, null);
		
		assertEquals(2,result.getTrayBeamBreaks());
		assertEquals(4,result.getFrontBeamBreaks());
		assertEquals(1,result.getBackBeamBreaks());
	}

	@ParameterizedTest
	@MethodSource("provideDistractedTrials")
	void analyzer_determines_distracted_trials(Trial trial){
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		sut.setParameters(parameters);

		MPosnerResult result = sut.generateResult(trial, 0, null);

		assertEquals("1",result.getDistractor());
	}

	static Stream<Arguments> provideDistractedTrials(){
		return Stream.of(
				Arguments.of(new Trial(Event.readEventsFromString(TestTrials.distractedTrial1))),
				Arguments.of(new Trial(Event.readEventsFromString(TestTrials.distractedTrial2))),
				Arguments.of(new Trial(Event.readEventsFromString(TestTrials.distractedTrial3))),
				Arguments.of(new Trial(Event.readEventsFromString(TestTrials.distractedTrial4)))
		);
	}
	@ParameterizedTest
	@MethodSource("provideUndistractedTrials")
	void analyzer_determines_undistracted_trials(Trial trial){
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		sut.setParameters(parameters);

		MPosnerResult result = sut.generateResult(trial, 0, null);

		assertEquals("0",result.getDistractor());
	}

	static Stream<Arguments> provideUndistractedTrials(){
		return Stream.of(
				Arguments.of(new Trial(Event.readEventsFromString(TestTrials.undistractedTrial1))),
				Arguments.of(new Trial(Event.readEventsFromString(TestTrials.undistractedTrial2))),
				Arguments.of(new Trial(Event.readEventsFromString(TestTrials.undistractedTrial3))),
				Arguments.of(new Trial(Event.readEventsFromString(TestTrials.undistractedTrial4)))
		);
	}

	@ParameterizedTest
	@MethodSource("provideAlertingTrials")
	void analyzer_determines_alertingCue_trials(Trial trial){
		MPosnerProbeParameters parameters = (MPosnerProbeParameters) generate_parameters_exogenous();
		MPosnerProbeTrialAnalyzer sut = new MPosnerProbeTrialAnalyzer();
		sut.setParameters(parameters);
		MPosnerResult result = sut.generateResult(trial, 0, null);

		assertEquals("Alerting",result.getCueValidity());
	}

	static Stream<Arguments> provideAlertingTrials(){
		return TestUtils.getFilesFromFolder("C:\\Projects\\TouchScreenAnalyzer\\src\\test\\resources\\AlertingTrials").map((file)->Arguments.of(new Trial(Event.readEventsFromFile(file))));
	}

		//Didnt test touchscreen error?
	
	
	

	private SessionParameters generate_parameters_endogenous() {
		MPosnerProbeParameterReader mPosnerProbeParameterReader = new MPosnerProbeParameterReader();
		Event[] events = Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_endogenous_parameters"));
		return mPosnerProbeParameterReader.readParameters(events);
	}

	private SessionParameters generate_parameters_exogenous() {
		MPosnerProbeParameterReader mPosnerProbeParameterReader = new MPosnerProbeParameterReader();
		Event[] events = Event.readEventsFromFile(new File(TEST_FILES_ADDRESS + "analyzerTest_exogenous_parameters"));
		return mPosnerProbeParameterReader.readParameters(events);
	}
}
