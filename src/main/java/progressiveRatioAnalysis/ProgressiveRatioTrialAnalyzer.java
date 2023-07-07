package progressiveRatioAnalysis;

import analysisSets.BeamBreakCounter;
import analysisSets.ITrialAnalyzer;
import dataModels.Event;
import dataModels.MetaData;
import dataModels.Result;
import dataModels.SessionInfo;
import dataModels.SessionParameters;
import dataModels.Trial;
import progressiveRatioAnalysis.analyzerHelpers.ProgressiveRatioTouchCounter;

public class ProgressiveRatioTrialAnalyzer implements ITrialAnalyzer {

	public static final int STARTING_RATIO = 1;
	public static final int RAMP_VALUE = 4;

	@Override
	public Result analyzeTrial(Trial trial, int counter, SessionInfo sessionInfo, MetaData metaData) {

		ProgressiveRatioResult result = analyze(trial,counter,sessionInfo,metaData);
		
		return new Result(sessionInfo, metaData, result.toString(), result.getResultHeader());
	}

	public ProgressiveRatioResult analyze(Trial trial, int counter, SessionInfo sessionInfo, MetaData metaData) {
		
		//Initialize base result
		ProgressiveRatioResult result = new ProgressiveRatioResult();
		
		//Set counter
		result.setTrialNumber(counter);
		
		Event[] events = trial.copyEventsAsArray();
		
		// Touch counter data structure
		ProgressiveRatioTouchCounter touchCounter = new ProgressiveRatioTouchCounter(events);
		
		// Beam break data structure
		BeamBreakCounter beamBreaks = new BeamBreakCounter();
		beamBreaks.countBeamBreaks(events);
		
		//All the other stuff
		result.setTimeStamp(events[0].getEvent_Time());
		result.setRequiredRatio(this.getRequiredRatio(events, counter));
		result.setValidTouches(touchCounter.getValidTouches());
		result.setItiTouches(touchCounter.getItiTouches());
		result.setInterRatioTouches(touchCounter.getInterRatioIntervalTouches());
		result.setRewardPeriodTouches(touchCounter.getRewardPeriodTouches());
		result.setFirstTouchLatency(this.calculateFirstTouchLatency(events));
		result.setRewardCollectionLatency(this.calculateRewardCollectionLatency(events));
		result.setAverageTimeBetweenValidTouches(touchCounter.getAverageTimeBetweenValid());
		result.setMedianTimeBetweenValidTouches(touchCounter.getMedianTimeBetweenValid());
		result.setAverageTimeBetweenCenterTouches(touchCounter.getAverageTimeBetweenCenter());
		result.setMedianTimeBetweenCenterTouches(touchCounter.getMedianTimeBetweenCenter());
		result.setFrontBeamBreaks(beamBreaks.getFrontBeamBreaks());
		result.setBackBeamBreaks(beamBreaks.getBackBeamBreaks());
		result.setTrayBeamBreaks(beamBreaks.getTrayBeamBreaks());
		result.setTimeInTrial(events[events.length - 1].getEvent_Time() - events[0].getEvent_Time());
		return result;
	}

	private float calculateRewardCollectionLatency(Event[] events) {

		float correctTime = Float.NaN;
		float collectTime = Float.NaN;
		boolean rewardAvailable = false;

		for (Event event : events) {
			if (event.getItem_Name().equals("PROGRESSIVE RATIO END CALC")) {
				rewardAvailable = true;
				correctTime = event.getEvent_Time();
			}
			if (event.getItem_Name().equals("Tray #1") && rewardAvailable) {
				collectTime = event.getEvent_Time();
				return collectTime - correctTime;
			}
		}

		return Float.NaN;
	}

	private float calculateFirstTouchLatency(Event[] events) {

		float displayTime = Float.NaN;
		float touchTime = Float.NaN;
		boolean imageIsDisplayed = false;

		for (Event event : events) {
			if (event.getItem_Name().equals("ITI TO IMAGE TRANSITION")) {
				imageIsDisplayed = true;
				displayTime = event.getEvent_Time();
			}
			if (event.getItem_Name().equals("Image Touched") && imageIsDisplayed) {
				touchTime = event.getEvent_Time();
				return touchTime - displayTime;
			}
		}
		return Float.NaN;
	}

	private int getRequiredRatio(Event[] events, int counter) {
		if (counter == 0) {
			return STARTING_RATIO;
		} else {
			return (STARTING_RATIO + (counter * RAMP_VALUE));
		}
	}

	@Override
	public void setParameters(SessionParameters parameters) {
		// TODO Auto-generated method stub
	}

}
