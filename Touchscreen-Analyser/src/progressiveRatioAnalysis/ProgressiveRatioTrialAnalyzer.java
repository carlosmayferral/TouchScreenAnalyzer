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

		Event[] events = trial.copyEventsAsArray();

		String resultHeader = "Trial_Number" + "," + "TimeStamp" + "," + "Required_Ratio" + "," + "Valid_Touches" + ","
				+ "ITI_Touches" + "," + "Inter_Ratio_Touches" + "," + "Reward_Period_Touches" + ","
				+ "Total_Center_Touches" + "," + "First_Response_Latency" + "," + "Reward_Collection_Latency" + ","
				+ "Average_Time_Between_Valid_Touches" + "," + "Median_Time_Between_Valid_Touches" + ","
				+ "Front_Beam_Breaks" + "," + "Back_Beam_Breaks" + "," + "Tray_Beam_Breaks" + ","
				+ "Time_Spent_In_Trial";

		// Touch counter data structure
		ProgressiveRatioTouchCounter touchCounter = new ProgressiveRatioTouchCounter(events);

		// Beam break data structure
		BeamBreakCounter beamBreaks = new BeamBreakCounter();
		beamBreaks.countBeamBreaks(events);

		// Trial results
		int trialNumber = counter;
		float timeStamp = events[0].getEvent_Time();
		int requiredRatio = this.getRequiredRatio(events, counter);
		int validTouches = touchCounter.getValidTouches();
		int itiTouches = touchCounter.getItiTouches();
		int interRatioTouches = touchCounter.getInterRatioIntervalTouches();
		int rewardTouches = touchCounter.getRewardPeriodTouches();
		int totalCenterTouches = validTouches + interRatioTouches + rewardTouches + itiTouches;
		float firstTouchLatency = this.calculateFirstTouchLatency(events);
		float rewardCollectionLatency = this.calculateRewardCollectionLatency(events);
		float averageTimeBetweenValidTouches = touchCounter.getAverageTimeBetweenValid();
		float medianTimeBeweenValidTouches = touchCounter.getMedianTimeBetweenValid();
		float timeInTrial = events[events.length - 1].getEvent_Time() - events[0].getEvent_Time();

		String resultContent = trialNumber + "," + timeStamp + "," + requiredRatio + "," + validTouches + ","
				+ itiTouches + "," + interRatioTouches + "," + rewardTouches + "," + totalCenterTouches + ","
				+ firstTouchLatency + "," + rewardCollectionLatency + "," + averageTimeBetweenValidTouches + ","
				+ medianTimeBeweenValidTouches + "," + beamBreaks.getFrontBeamBreaks() + ","
				+ beamBreaks.getBackBeamBreaks() + "," + beamBreaks.getTrayBeamBreaks() + "," + timeInTrial;

		return new Result(sessionInfo, metaData, resultContent, resultHeader);

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
