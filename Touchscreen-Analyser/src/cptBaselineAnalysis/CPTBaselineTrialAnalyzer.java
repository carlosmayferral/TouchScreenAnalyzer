package cptBaselineAnalysis;

import analysisSets.BeamBreakCounter;
import analysisSets.ITrialAnalyzer;
import cptBaselineAnalysis.analyzerHelpers.CPTBaselineReferenceEvents;
import cptBaselineAnalysis.analyzerHelpers.CPTBaselineTouchAndLatencyCounter;
import dataModels.Event;
import dataModels.MetaData;
import dataModels.Result;
import dataModels.SessionInfo;
import dataModels.SessionParameters;
import dataModels.Trial;

class CPTBaselineTrialAnalyzer implements ITrialAnalyzer {
	
	private static final float DEFAULT_DURATION = 2;
	private static final float DEFAULT_CONTRAST = 100;
	private static final float DEFAULT_ITI_DURATION = 5;
	private int currentCorrectImage  = -1;

	@Override
	public Result analyzeTrial(Trial trial, int counter, SessionInfo sessionInfo, MetaData metaData) {
		
		CPTBaselineResult result;
		result = analyze(trial, counter, sessionInfo);
		
		
		return new Result (sessionInfo, metaData, result.toString(), result.getHeader());
	}
	
	public CPTBaselineResult analyze(Trial trial, int counter, SessionInfo sessionInfo){
		
		CPTBaselineResult result = new CPTBaselineResult();
		
		Event[] events = trial.copyEventsAsArray();
		
		result.setTrialNumber(counter);
		result.setTimeStamp(events[0].getEvent_Time());
		result.setCorrectImage(this.currentCorrectImage);
		result.setCorrectionTrial((events[0].equals(CPTBaselineReferenceEvents.CORRECTION_ITI_START)) ? true : false);
		result.setImageShown(determineImageShown(events));
		
		//Stimulus duration is relevant to stimulus duration probe.
		result.setStimulusDuration(determineStimulusDuration(events));
		
		//Stimulus contrast is only relevant for the stimulus contrast probe
		result.setStimulusContrast(determineStimulusContrast(events));
		
		//ITI duration is only relevant for the ITI probe
		result.setItiDuration(determineITIduration(events));
		
		//Distracter values are only relevant to the distracter probe
		result.setDistracter(determineIfDistracted(events));
		try {
			result.setCongruentDistracter(determineIfCongruentDistracter(events));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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

	private boolean determineIfCongruentDistracter(Event[] events) throws Exception {
		for (Event event : events) {
			if (event.getEvent_Name().equalsIgnoreCase("Condition Event") && 
					event.getItem_Name().equalsIgnoreCase("Display Incongruent Images")) {
				if (this.determineIfDistracted(events)) return false;
				else throw new Exception("Distracter found in supposedly undistracted trial");
			}
			if (event.getEvent_Name().equalsIgnoreCase("Condition Event") && 
					event.getItem_Name().equalsIgnoreCase("Display Congruent Images")) {
				if (this.determineIfDistracted(events)) return true;
				else throw new Exception("Distracter found in supposedly undistracted trial");
			}
		}
		return false;
	}

	private boolean determineIfDistracted(Event[] events) {
		for (Event event : events) {
			if (event.getEvent_Name().equalsIgnoreCase("Variable Event") && 
					event.getItem_Name().equalsIgnoreCase("Distractor")) {
						if (event.getArgumentValue(1) != 0) {
							return true;
						} else return false;
			}
		}
		return false;
	}

	private float determineITIduration(Event[] events) {
		for (Event event : events) {
			if (event.getEvent_Name().equalsIgnoreCase("Variable Event") && 
					event.getItem_Name().equalsIgnoreCase("Current_ITI")) {
				return event.getArgumentValue(1);
			}
		}
		return DEFAULT_ITI_DURATION;
	}

	private float determineStimulusContrast(Event[] events) {
		for (Event event : events) {
			if(event.getEvent_Name().equalsIgnoreCase("Condition Event") &&
					event.getItem_Name().contains("Select")) {
				switch(event.getItem_Name()) {
				case "Select 12.5 pc image":
					return (float)12.5;
				case "Select 25 pc image":
					return (float) 25;
				case "Select 50 pc image":
					return (float)50;
				case "Select 100 pc image":
					return (float)100;
				default:
					System.out.println("error determining contrast");
					return (Float.NaN);
				}
			}
		}
		return DEFAULT_CONTRAST;
	}

	private float determineStimulusDuration(Event[] events) {
		for (Event event : events) {
			if (event.getEvent_Name().equalsIgnoreCase("Variable Event") && 
					event.getItem_Name().equalsIgnoreCase("stimulus_duration")) {
				return event.getArgumentValue(1);
			}
		}
		return DEFAULT_DURATION;
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
