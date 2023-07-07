package tunlAnalysisSet;

import analysisSets.ITrialAnalyzer;
import dataModels.Event;
import dataModels.MetaData;
import dataModels.Result;
import dataModels.SessionInfo;
import dataModels.SessionParameters;
import dataModels.Trial;


/**
 * The requirements of the tunlTrialAnalyzer class are to, for each trial in a TUNL session:
 * - determine the separation level of a trial
 * - determine whether the choice is right or left of the sample
 * - determine the "sample response latency"
 * - determine the "latency to back beam"
 * - determine the "choice response latency"
 * - for correct trials, determine the "reward collection latency"
 * @throws Exception
 */
public class TunlTrialAnalyzer implements ITrialAnalyzer {
	
	private int previousSeparation;
	
	private String previousDirection;
	
	private int previousCenter;
	
	public TunlTrialAnalyzer() {
		this.previousSeparation =0;
		this.previousCenter = -1;
		this.previousDirection = "";
	}
	
	@Override
	public Result analyzeTrial(Trial trial, int counter, SessionInfo sessionInfo, MetaData metaData) {
		TunlResult tunlResult = analyze(trial,counter);
		return new Result(sessionInfo, metaData, tunlResult.toString(), tunlResult.getHeader());
	}

	public TunlResult analyze(Trial trial, int counter) {
		
		//reset previous values if counter is 0
		if (counter ==0) {
			previousSeparation = 0;
			previousCenter = -1;
			previousDirection = "";
		}
		
		TunlResult result = new TunlResult();
		Event[] trialEvents = trial.copyEventsAsArray();
		
		result.setTrialCounter(counter);
		
		result.setCorrectionTrial(this.determineIfCorrectionTrial(trialEvents));
		int separation = 0;
		int center = -1;
		if(result.getCorrectionTrial() == 1) {
			separation = previousSeparation;
			center = previousCenter;
		}
		else {
			center = this.determineCenterSample(trialEvents);
			separation = determineSeparationLevel(trialEvents);
		}
		if (separation != 0) result.setSeparationLevel(Math.abs(separation));
		result.setLeftOrRightChoice(separation < 0 ? "Right" : "Left");
		result.setCenterSample(center);
		result.setSampleLatency(determineLatencyBetweenItems(trialEvents,"Display Sample","Start Delay"));
		result.setBackBeamLatency(this.determineLatencyBetweenItems(trialEvents, "Delay End", "Choice Stage"));
		result.setChoiceLatency(this.determineLatencyBetweenItems(trialEvents, "Choice Stage", "Correct"));
		if (Double.isNaN(result.getChoiceLatency())){
			result.setChoiceLatency(this.determineLatencyBetweenItems(trialEvents, "Choice Stage", "Incorrect"));
			result.setCorrect(0);
		}
		else {
			result.setRewardCollectionLatency(this.determineLatencyBetweenItems(trialEvents, "Correct", "Reward Collected Start ITI"));
			result.setCorrect(1);
		}
		this.previousSeparation = separation;
		this.previousDirection = separation<0 ? "Right" : "Left";
		this.previousCenter = result.getCenterSample();
		return result;
	}

	private int determineCenterSample(Event[] trialEvents) {
		
		int firstValue = 0;
		
		//search for trial type event
		for(Event event : trialEvents) {
			if (event.getItem_Name().equals("aTrial_Set")
					&& event.getEvent_Name().equals("Variable Event")){
				
				//extract numbers from string
				if(event.getArgumentValue(1)<10) {
					System.out.println("trial type value invalid");
					return -1;
				}
				
				firstValue = (int)event.getArgumentValue(1)/10;
			}
		}

		if (firstValue == 0) return -1;
		else if (firstValue == 3) return 1;
		else return 0;
	}

	private int determineIfCorrectionTrial(Event[] trialEvents) {
		
		for (Event event : trialEvents) {
			if (event.getItem_Name().equals("Start Correction Trial")) {
				return 1;
			}
		}
		
		
		//if no event is found the default is to return 0 (not a correction trial)
		return 0;
	}

	/**
	 * The sample latency is the time between presentation of the sample and the touching of the sample.
	 * It is calculated by substracting the timestamp of the touch event from the timestamp of the sample
	 * display event (the sample touch event is used to avoid complications). The first sample display event
	 * in the event list is taken, and then the first sample touch event is searched for.
	 * @param trialEvents - The events of the trial under analysis.
	 * @return - the time (in seconds) between the sample presentation and the delay start.
	 */
	private double determineLatencyBetweenItems(Event[] trialEvents, String ItemName1, String ItemName2) {
		
		float sampleTime = Float.NaN;
		float delayTime = Float.NaN;
		
		boolean sampleDisplayed = false;
		
		for (Event event : trialEvents) {
			if (event.getItem_Name().equals(ItemName1) && !sampleDisplayed) {
				sampleTime = event.getEvent_Time();
				sampleDisplayed = true;
			}
			if (event.getItem_Name().equals(ItemName2) && sampleDisplayed) {
				delayTime = event.getEvent_Time();
				break;
			}
		}
		
		if (Float.isNaN(sampleTime) || Float.isNaN(delayTime)) {
			return Double.NaN;
		}
		else {
			return delayTime - sampleTime;
		}
	}

	/**
	 * The separation level of a trial is to be determined by looking at the
	 * trial type event. Substracting the location of sample from choice should give the level
	 * of separation.
	 * 
	 * Given a list of events (a trial), the first such event that describes the trial type ("13,
	 * 34, etc) shall be used to determine the separation level.
	 **/
	private int determineSeparationLevel(Event[] trialEvents) {
		
		
		//search for trial type event
		for(Event event : trialEvents) {
			if (event.getItem_Name().equals("aTrial_Set")
					&& event.getEvent_Name().equals("Variable Event")){
				
				//extract numbers from string
				if(event.getArgumentValue(1)<10) {
					System.out.println("trial type value invalid");
					return 0;
				}
				
				int firstValue = (int)event.getArgumentValue(1)/10;
				int secondValue = (int)event.getArgumentValue(1)%10;
				
				return firstValue-secondValue;
			}
		}
		System.out.println("no trial type event found");
		return 0;
	}
	
	

	@Override
	public void setParameters(SessionParameters parameters) {
		// TODO Auto-generated method stub

	}

}
