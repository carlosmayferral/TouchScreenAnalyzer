package cptBaselineAnalysis;

import analysisSets.BeamBreakCounter;
import analysisSets.ITrialAnalyzer;
import cptBaselineAnalysis.analyzerHelpers.CPTBaselineReferenceEvents;
import cptBaselineAnalysis.analyzerHelpers.CPTBaselineTouchAndLatencyCounter;
import dataModels.Event;
import dataModels.Result;
import dataModels.SessionInfo;
import dataModels.SessionParameters;
import dataModels.Trial;

class CPTBaselineTrialAnalyzer implements ITrialAnalyzer {
	
	private int currentCorrectImage  = -1;

	@Override
	public Result analyzeTrial(Trial trial, int counter, SessionInfo sessionInfo) {
		CPTBaselineResult result = analyze(trial, counter, sessionInfo);
		return new Result (sessionInfo, result.toString(), result.getHeader());
	}
	
	public CPTBaselineResult analyze(Trial trial, int counter, SessionInfo sessionInfo) {
		
		CPTBaselineResult result = new CPTBaselineResult();
		
		Event[] events = trial.copyEventsAsArray();
		
		result.setTrialNumber(counter);
		result.setTimeStamp(events[0].getEvent_Time());
		result.setCorrectImage(this.currentCorrectImage);
		result.setCorrectionTrial((events[0].equals(CPTBaselineReferenceEvents.CORRECTION_ITI_START)) ? true : false);
		result.setImageShown(determineImageShown(events));
		result.setCorrect(determineIfCorrect(events));
		
		CPTBaselineTouchAndLatencyCounter touches = new CPTBaselineTouchAndLatencyCounter();
		touches.countTouches(events);
		
		result.setItiCenterTouches(touches.getITITouchArray()[1]);
		result.setTotalTouchesDuringSD(this.sumArray(touches.getSDTouchArray()));
		result.setTotalTouchesDuringLH(this.sumArray(touches.getLHTouchArray()));
		result.setPostTrialCenterTouches(touches.getRewardOrTimeoutTouches()[1]);
		
		//latencies
		result.setResponseLatency(touches.getResponseLatency());
		result.setRewardCollectionLatency(touches.getRewardCollectionLatency());
		
		//beam breaks
		BeamBreakCounter beamBreaks = new BeamBreakCounter();
		beamBreaks.countBeamBreaks(events);
		result.setFrontBeamBreaks(beamBreaks.getFrontBeamBreaks());
		result.setBackBeamBreaks(beamBreaks.getBackBeamBreaks());
		result.setTrayBeamBreaks(beamBreaks.getTrayBeamBreaks());
		
		return result;
		
	}

	private int determineImageShown(Event[] events) {
		for (Event event : events) {
			if (event.getItem_Name().equals("Current_Image")) {
				return (int)event.getArgumentValue(1);
			}
		}
		return -1;
	}

	private boolean determineIfCorrect(Event[] events) {
		for (Event event : events) {
			if (event.getItem_Name().equals("Hit") ||
					event.getItem_Name().equals("Correct Rejection")
					|| event.getItem_Name().equals("Correction Trial Correct Rejection")) {
				return true;
			}
			else if (event.getItem_Name().equals("Missed Hit") ||
					event.getItem_Name().equals("Non Correction Trial Miskake") ||
					event.getItem_Name().equals("Correction Trial Mistake")) {
				return false;
			}
		}
		return false;
	}

	@Override
	public void setParameters(SessionParameters parameters) {
		CPTBaselineParameters cptParameters = (CPTBaselineParameters) parameters;
		this.currentCorrectImage = cptParameters.getCorrectImageIndex();
	}
	
	private int sumArray(int[] array) {
		int sum = 0;
		for (int member : array) {
			sum += member;
		}
		return sum;
	}

}
