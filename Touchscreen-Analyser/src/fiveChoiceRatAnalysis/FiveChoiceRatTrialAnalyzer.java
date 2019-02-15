package fiveChoiceRatAnalysis;

import analysisSets.BeamBreakCounter;
import analysisSets.ITrialAnalyzer;
import dataModels.Event;
import dataModels.Result;
import dataModels.SessionInfo;
import dataModels.SessionParameters;
import dataModels.Trial;
import fiveChoiceAnalysis.AnalyzerHelpers.FiveChoiceTouchRecorder;
import fiveChoiceAnalysis.AnalyzerHelpers.TrialSettingsReader;

class FiveChoiceRatTrialAnalyzer implements ITrialAnalyzer {
	
	//Some parameters are constant for the whole session, they only change when parameters are set
	
	private float currentStimulusDuration;
	private float currentStimulusDelay;
	private float currentStimulusBrightness;
	private int delayBlock;
	private int distracterPlayed;
	private float timeToDistracter;
	
	
	@Override
	public Result analyzeTrial(Trial trial, int counter, SessionInfo sessionInfo) {
		FiveChoiceRatResult result = this.analyze(trial, counter);
		return new Result(sessionInfo, result.toString(), FiveChoiceRatResult.HEADER);
	}
	
	public FiveChoiceRatResult analyze(Trial trial, int counter) {
		FiveChoiceRatResult result = new FiveChoiceRatResult();
		
		//Trial analysis logic goes here
		
		Event[] events = trial.copyEventsAsArray();
		
		result.setTimeStamp(events[0].getEvent_Time());
		result.setTrialNumber(counter);
		
		//Initialize settings structure
		TrialSettingsReader settings =new TrialSettingsReader();
		settings.scanEvents(events);
		
		//Stimulus duration
		if (!((Float)settings.getStimulusDuration()).isNaN()) {
			result.setStimulusDuration(settings.getStimulusDuration());
			this.currentStimulusDuration = settings.getStimulusDuration();
		} else result.setStimulusDuration(this.currentStimulusDuration);
		
		//Stimulus delay
		if (!((Float)settings.getStimulusDelay()).isNaN()) {
			result.setStimulusDelay(settings.getStimulusDelay());
			this.currentStimulusDelay = settings.getStimulusDelay();
		} else {
			result.setStimulusDelay(this.currentStimulusDelay);
		}
		
		//Stimulus brightness
		if (!((Float)settings.getStimulusBrightness()).isNaN()) {
			result.setStimulusBrightness(settings.getStimulusBrightness());
			this.currentStimulusBrightness = settings.getStimulusBrightness();
		} else {
			result.setStimulusBrightness(this.currentStimulusBrightness);
		}
		
		//If distracter was played
		if (!(settings.getPlayDistracter() == -1)) {
			result.setDistractorPlayed(settings.getPlayDistracter());
			this.distracterPlayed= settings.getPlayDistracter();
		} else {
			result.setDistractorPlayed(this.distracterPlayed);
		}
		
		//The time to the distracter
		if (!((Float)settings.getTimeToDistractor()).isNaN()) {
			result.setTimeToDistractor(settings.getTimeToDistractor());
			this.timeToDistracter = settings.getTimeToDistractor();
		} else {
			result.setTimeToDistractor(this.timeToDistracter);
		}
		
		//Initialize touch and latency scanner
		FiveChoiceTouchRecorder touchRecorder = new FiveChoiceTouchRecorder();
		touchRecorder.scanTouches(events, settings.getTargetLocation());
		
		result.setPremature(touchRecorder.isTrialPremature());
		
		result.setResponse(touchRecorder.isResponded());
		
		result.setCorrect(touchRecorder.isCorrect());
		
		result.setInitiationLatency(touchRecorder.getInitiation_latency());
		
		result.setResponseLatency(touchRecorder.getResponse_latency());
		
		result.setTimeInTrial(events[events.length -1].getEvent_Time() - events[0].getEvent_Time());
		
		result.setRewardCollectionLatency(touchRecorder.getResponse_to_tray_latency());
		
		result.setInitiationPeriodTouches(touchRecorder.getInitiationPeriodTouches());
		
		result.setTotalPostStimulusPeriodTouches(touchRecorder.getPostStimulusTouches());
		
		result.setPerseverativeTouches(touchRecorder.getPerseverativeTouches());
		
		result.setTotalTouchesDuringITI(touchRecorder.getItiTouches());
		
		result.setTargetPosition(settings.getTargetLocation());
		
		result.setDelayBlock(this.delayBlock);
		
		BeamBreakCounter beams = new BeamBreakCounter();
		beams.countBeamBreaks(events);
		
		result.setFrontBeamBreaks(beams.getFrontBeamBreaks());
		
		result.setBackBeamBreaks(beams.getBackBeamBreaks());
		
		result.setTrayBeamBreaks(beams.getTrayBeamBreaks());
		
		return result;
	}

	@Override
	public void setParameters(SessionParameters parameters) {
		
		FiveChoiceRatSessionParameters ratParameters = (FiveChoiceRatSessionParameters) parameters;
		this.currentStimulusDuration = ratParameters.getStimulusDuration();
		this.currentStimulusDelay =ratParameters.getStimulusDelay();
		this.currentStimulusBrightness = ratParameters.getStimulusBrightness();
		this.delayBlock = ratParameters.getDelayBlock();
		this.distracterPlayed = ratParameters.getDistractorPlayed();
		this.timeToDistracter = ratParameters.getTimeToDistractor();

	}

}
