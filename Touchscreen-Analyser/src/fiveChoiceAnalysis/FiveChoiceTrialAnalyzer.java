package fiveChoiceAnalysis;

import analysisSets.BeamBreakCounter;
import analysisSets.ITrialAnalyzer;
import dataModels.Event;
import dataModels.MetaData;
import dataModels.Result;
import dataModels.SessionInfo;
import dataModels.SessionParameters;
import dataModels.Trial;
import fiveChoiceAnalysis.AnalyzerHelpers.FiveChoiceTouchRecorder;
import fiveChoiceAnalysis.AnalyzerHelpers.TrialSettingsReader;

class FiveChoiceTrialAnalyzer implements ITrialAnalyzer {
	
	private float bufferedStimulusDuration;
	
	private float bufferedStimulusDelay;
	
	private float bufferedStimulusBrightness;
	
	private int delayBlock;
	
	public FiveChoiceTrialAnalyzer() {
		this.bufferedStimulusBrightness = Float.NaN;
		this.bufferedStimulusDelay = Float.NaN;
		this.bufferedStimulusDuration = Float.NaN;
		this.delayBlock = -1;
	}
	

	@Override
	public Result analyzeTrial(Trial trial, int counter, SessionInfo sessionInfo , MetaData metaData) {
		FiveChoiceResult result = this.generateResult(trial,counter,sessionInfo);
		return new Result(sessionInfo, metaData, result.toString(), result.getHeader());
	}

	public FiveChoiceResult generateResult(Trial trial, int counter, SessionInfo sessionInfo) {
		FiveChoiceResult result = new FiveChoiceResult();
		Event[] events = trial.copyEventsAsArray();
		
		result.setTimeStamp(events[0].getEvent_Time());
		result.setTrialNumber(counter);
		
		//Initialize settings structure
		TrialSettingsReader settings =new TrialSettingsReader();
		settings.scanEvents(events);
		
		//Stimulus duration
		if (!((Float)settings.getStimulusDuration()).isNaN()) {
			result.setStimulusDuration(settings.getStimulusDuration());
			bufferedStimulusDuration = settings.getStimulusDuration();
		} else result.setStimulusDuration(bufferedStimulusDuration);
		
		//Stimulus delay
		if (!((Float)settings.getStimulusDelay()).isNaN()) {
			result.setStimulusDelay(settings.getStimulusDelay());
			bufferedStimulusDelay = settings.getStimulusDelay();
		} else {
			result.setStimulusDelay(bufferedStimulusDelay);
		}
		
		//Stimulus brightness
		if (!((Float)settings.getStimulusBrightness()).isNaN()) {
			result.setStimulusBrightness(settings.getStimulusBrightness());
			bufferedStimulusBrightness = settings.getStimulusBrightness();
		} else {
			result.setStimulusBrightness(bufferedStimulusBrightness);
		}
		
		result.setTargetPosition(settings.getTargetLocation());
		
		result.setDelayBlock(this.delayBlock);
		
		//Initialize touch and latency scanner
		FiveChoiceTouchRecorder touchRecorder = new FiveChoiceTouchRecorder();
//		touchRecorder.scanTouches(events, settings.getTargetLocation());
//		
//		result.setPremature(touchRecorder.isTrialPremature());
//		
//		result.setResponse(touchRecorder.isResponded());
//		
//		result.setCorrect(touchRecorder.isCorrect());
//		
//		result.setInitiationLatency(touchRecorder.getInitiation_latency());
//		
//		result.setResponseLatency(touchRecorder.getResponse_latency());
//		
//		result.setRewardCollectionLatency(touchRecorder.getResponse_to_tray_latency());
//		
//		result.setInitiationPeriodTouches(touchRecorder.getInitiationPeriodTouches());
//		
//		result.setTotalPostStimulusPeriodTouches(touchRecorder.getPostStimulusTouches());
//		
//		result.setPerseverativeTouches(touchRecorder.getPerseverativeTouches());
		
		result.setTotalTouchesDuringITI(touchRecorder.getItiTouches());
		
		BeamBreakCounter beams = new BeamBreakCounter();
		beams.countBeamBreaks(events);
		
		result.setFrontBeamBreaks(beams.getFrontBeamBreaks());
		
		result.setBackBeamBreaks(beams.getBackBeamBreaks());
		
		result.setTrayBeamBreaks(beams.getTrayBeamBreaks());
		
		return result;
	}


	@Override
	public void setParameters(SessionParameters rawParameters) {
		FiveChoiceSessionParameters parameters;
		parameters = (FiveChoiceSessionParameters) rawParameters;
		this.bufferedStimulusBrightness = parameters.getStimulusBrightness();
		this.bufferedStimulusDelay = parameters.getStimulusDelay();
		this.bufferedStimulusDuration = parameters.getStimulusDuration();
		this.delayBlock = parameters.getDelayBlock();
	}

}
