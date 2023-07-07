package pdAnalysis;

import analysisSets.BeamBreakCounter;
import analysisSets.ITrialAnalyzer;
import dataModels.Event;
import dataModels.MetaData;
import dataModels.Result;
import dataModels.SessionInfo;
import dataModels.SessionParameters;
import dataModels.Trial;
import pdAnalysis.analyzerHelpers.PdScreenInteractionStateMachine;

class PdTrialAnalyzer implements ITrialAnalyzer {
	
	private boolean correctionTrialExpected;
	
	private int expectedSPlusLocation;
	
	private static PdTrialSettingsReader settings;
	
	private float timeoutLength;
	
	private float itiLength;

	private String sPlusId;
	
	
	
	public PdTrialAnalyzer() {
		settings = new PdTrialSettingsReader();
		correctionTrialExpected = false;
		timeoutLength = Float.NaN;
		itiLength = Float.NaN;
	}

	@Override
	public Result analyzeTrial(Trial trial, int counter, SessionInfo sessionInfo, MetaData metaData) {
		PdResult pdResult = analyze(trial,counter);
		return new Result(sessionInfo, metaData, pdResult.toString(), pdResult.getHeader());
	}
	
	public PdResult analyze(Trial trial,int counter) {
		
		PdResult result = new PdResult();
		Event[] events = trial.copyEventsAsArray();
		
		result.setTrialNumber(counter);
		result.setTimeStamp(events[0].getEvent_Time());
		result.setTimeInTrial(events[events.length-1].getEvent_Time() - events[0].getEvent_Time());
		
		//Per Trial settings
		settings.readSettings(events);
		result.setCorrectionTrial(correctionTrialExpected ? 1 : 0);
//		if (correctionTrialExpected) {
//			result.setsPlusLocation(expectedSPlusLocation);
//		}
//		else {
//			result.setsPlusLocation(settings.getSPlusLocation());
//		}
		
		//Per Session Settings derived from parameters
		result.setTimeoutLength(timeoutLength);
		result.setItiLength(itiLength);
		result.setsPlusId(sPlusId);
		
		//Screen interaction measurements
		PdScreenInteractionStateMachine interactions = new PdScreenInteractionStateMachine();
		interactions.scanEvents(events,sPlusId);
		result.setsPlusLocation(interactions.getCorrectLocation());
		result.setInitiationLatency(interactions.getInitiationLatency());
		result.setHeadWithdrawalTime(interactions.getHeadWithdrawalTime());
		result.setResponseLatency(interactions.getResponseLatency());
		if (interactions.getCorrectAccordingToEvent() == 1) {
			result.setCorrect(1);
			this.correctionTrialExpected = false;
		}
		else {
			result.setCorrect(0);
			this.correctionTrialExpected = true;
			this.expectedSPlusLocation = result.getsPlusLocation();
		}
		result.setCorrect(interactions.getCorrect());
		result.setRewardCollectionLatency(interactions.getRewardCollectionLatency());
		result.setFeedingTime(interactions.getFeedingTime());
		result.setInitiationTouches(interactions.getInitiationPeriodTouches());
		result.setPostStimulusTouches(interactions.getPostStimulusTouches());
		result.setItiTouches(interactions.getIniTouches());
		
		//Beam Breaks
		BeamBreakCounter beamBreaks = new BeamBreakCounter();
		beamBreaks.countBeamBreaks(events);
		result.setFrontBeamBreaks(beamBreaks.getFrontBeamBreaks());
		result.setBackBeamBreaks(beamBreaks.getBackBeamBreaks());
		result.setTrayBeamBreaks(beamBreaks.getTrayBeamBreaks());
		
		//Inconsistency / error checking
		
		//supposed to be correction trial
		if (result.getCorrectionTrial() == 1 && (!this.correctionTrialExpected)){
			result.setErrorCheck(1);
		}
		//supposed to be correct
		else if (result.getCorrect() != interactions.getCorrectAccordingToEvent()) {
			result.setErrorCheck(1);
		}
		//cant have reward collection or feeding time if incorrect
		else if (result.getCorrect() == 0) {
			if ((!((Float)result.getRewardCollectionLatency()).isNaN())
					||
					(!((Float)result.getFeedingTime()).isNaN())) {
				result.setErrorCheck(1);
			}
		}
		
		if (interactions.getCorrectAccordingToEvent() != interactions.getCorrect()) {
			System.out.println("DEBUG: trial has correct event but seemingly no correct touch, setting correct");
		}
		
		return result;
	}

	@Override
	public void setParameters(SessionParameters parameters) {
		PdSessionParameters pdParameters = (PdSessionParameters) parameters;
		this.itiLength = pdParameters.getItiLength();
		this.timeoutLength = pdParameters.getTimeoutLength();
		this.sPlusId = pdParameters.getSpPlusId();
		this.expectedSPlusLocation = -1;
		this.correctionTrialExpected = false;
	}
	
	private static class PdTrialSettingsReader{
		
		private int correctionTrial;
		private int sPlusLocation;
		
		public void readSettings(Event[] events) {
			
			correctionTrial = -1;
			sPlusLocation = -1;
			
			for (Event event : events) {
				
				if(event.getItem_Name().equals("Correction_Trial")) {
					correctionTrial = (int)event.getArgumentValue(1);
				}
				
				if(event.getItem_Name().equals("Correct_Grid_Position")) {
					sPlusLocation = (int)event.getArgumentValue(1);
				}
				
				if (event.getEvent_Name().equals("Whisker - Display Image")) {
					break;
				}
				
			}
			
		}
		
		public int getSPlusLocation() {
			return this.sPlusLocation;
		}
		
		public int getIfCorrectionTrial() {
			return this.correctionTrial;
		}
		
	}

}
