package pairwiseHabituationV2AnalysisSet;

import analysisSets.ITrialAnalyzer;
import dataModels.Event;
import dataModels.MetaData;
import dataModels.Result;
import dataModels.SessionInfo;
import dataModels.SessionParameters;
import dataModels.Trial;

/**
 * Analyzes trials for the pairwise habituation V2 schedule.
 * @author Carlos May
 *
 */
public class PairwiseHabituationV2TrialAnalyzer implements ITrialAnalyzer {
	
	private static final String PRIME_FEED_ITEM_NAME = "Prime Feed";

	private static final Object TRAY_ENTRY_NOTED_ITEM_NAME = "Note Tray Entry";

	private static final Object PULSE_FEED_ITEM_NAME = "Feed again";

	private static final Object START_DELAY_ITEM_NAME = "Start Delay";

	private static final Object WAIT_FOR_TRAY_EXIT_ITEM_NAME = "Wait for tray exit";

	private static final Object DELAY_TIME_ITEM_NAME = "Delay_Time";

	private static final Object SHUTDOWN_ITEM_NAME = "Schedule Shutdown Event";

	private static final Object TRAY_BEAM_ITEM_NAME = "Tray #1";

	private static final Object INPUT_ON_EVENT_NAME = "Input Transition On Event";

	private static final Object FRONT_BEAM_ITEM_NAME = "FIRBeam #1";

	private static final Object BACK_BEAM_ITEM_NAME = "BIRBeam #1";
	
	//Parameters from parameter reader
	private Double delayTimeParameter;
	private Integer feederPulseTimeMillisecondsParameter;
	private Integer primeFeedPulseTimeMillisecondsParameter;

	@Override
	public Result analyzeTrial(Trial trial, int counter, SessionInfo sessionInfo, MetaData metaData) {
		PairwiseHabiutationV2Result result = this.generateResult(trial,counter,sessionInfo);
		return new Result(sessionInfo, metaData, result.toString(), result.getHeader());
	}

	/**
	 * The main method of this class, takes a trial (essentially a list of events), a trial number counter and session information (for error messages).
	 * @param trial
	 * @param counter
	 * @param sessionInfo
	 * @return
	 */
	public PairwiseHabiutationV2Result generateResult(Trial trial, int counter, SessionInfo sessionInfo) {
		//If trial is empty return null
		if(trial.copyEventsAsArray().length == 0) {
			return null;
		}
		
		//Initiate result object
		PairwiseHabiutationV2Result result = new PairwiseHabiutationV2Result();
		
		//Get events from trial
		Event[] events = trial.copyEventsAsArray();
		
		//Timestamp is simply the timestamp of the first event in the trial
		result.setTimestamp((double) events[0].getEvent_Time());
		
		//trial number is passed as argument to analyze
		result.setTrialNumber(counter);
		
		//Parameters should have been set before running generate result
		result.setPulseFeedTimeMs(feederPulseTimeMillisecondsParameter);
		result.setPrimeFeedTimeMs(primeFeedPulseTimeMillisecondsParameter);
		
		//calculate feeding latency
		result.setFeedingLatency(calculateFeedingLatency(events));
		
		//calculate feeding time
		result.setFeedingTime(calculateFeedingTime(events));
		
		//calculate effective delay
		result.setEffectiveDelayTime(calculateEffectiveDelay(events));
		
		calculateBeamBreaks(result,events);
		
		//sanity check!!!!
		
		//if no feeding latency, cannot have feeding time or delay time
		if(Double.isNaN(result.getFeedingLatency())) {
			if(!Double.isNaN(result.getFeedingTime())) {
				throwExceptionAtThisTrial(counter,sessionInfo);
			}
			if(!Double.isNaN(result.getEffectiveDelayTime())) {
				throwExceptionAtThisTrial(counter,sessionInfo);
			}
		}
		
		//if feeding time, must have feeding latency
		if(!Double.isNaN(result.getFeedingLatency())) {
			if(Double.isNaN(result.getFeedingLatency())) {
				throwExceptionAtThisTrial(counter,sessionInfo);
			}
		}
		
		//if no feeding time, cannot have delay time
		if(Double.isNaN(result.getFeedingTime())) {
			if(!Double.isNaN(result.getEffectiveDelayTime())) {
				throwExceptionAtThisTrial(counter,sessionInfo);
			}
		}
		
		//if delay time must have feeding time and feeding latency
		if(!Double.isNaN(result.getEffectiveDelayTime())) {
			if(Double.isNaN(result.getFeedingTime())) {
				throwExceptionAtThisTrial(counter,sessionInfo);
			}
			if(Double.isNaN(result.getFeedingLatency())) {
				throwExceptionAtThisTrial(counter,sessionInfo);
			}
		}
		
		//if no delay time, cannot have any delay beam breaks
		if(Double.isNaN(result.getEffectiveDelayTime())) {
			if(result.getDelayPeriodBackBeams()> 0 || result.getDelayPeriodFrontBeams() > 0 || result.getDelayPeriodTrayBeams() >0) {
				throwExceptionAtThisTrial(counter,sessionInfo);
			}
		}
		
		//if no feeding latency, cannot have any tray beam breaks
		if(Double.isNaN(result.getFeedingLatency())) {
			if(result.getTotalTrayBeams() > 0) {
				throwExceptionAtThisTrial(counter,sessionInfo);
			}
		}
		
		
		return result;
	}

	private void throwExceptionAtThisTrial(int counter, SessionInfo sessionInfo) {
		if(sessionInfo == null) {
			throw new RuntimeException("Integrity error at trial number " + counter);
		}
		else {
			throw new RuntimeException("Integrity error at trial number " + counter + " in file " + sessionInfo.getFile().getName());
		}
	}

	private void calculateBeamBreaks(PairwiseHabiutationV2Result result, Event[] events) {
		
		for(Event event : events) {
			if (event.getEvent_Name().equals(INPUT_ON_EVENT_NAME)){
				
				if(event.getItem_Name().equals(TRAY_BEAM_ITEM_NAME) & event.getGroup_Id() != 6) {
					result.setTotalTrayBeams(result.getTotalTrayBeams() + 1);
					if(event.getGroup_Id() == 5) {
						result.setDelayPeriodTrayBeams(result.getDelayPeriodTrayBeams()+1);
					}
				}
				if(event.getItem_Name().equals(FRONT_BEAM_ITEM_NAME) & event.getGroup_Id() != 6) {
					result.setTotalFrontBeams(result.getTotalFrontBeams() + 1);
					if(event.getGroup_Id() == 5) {
						result.setDelayPeriodFrontBeams(result.getDelayPeriodFrontBeams() + 1);
					}
				}
				if(event.getItem_Name().equals(BACK_BEAM_ITEM_NAME) & event.getGroup_Id() != 6) {
					result.setTotalBackBeams(result.getTotalBackBeams() + 1);
					if(event.getGroup_Id() == 5) {
						result.setDelayPeriodBackBeams(result.getDelayPeriodBackBeams()+1);
					}
				}
				
			}
		}
		
	}

	private Double calculateEffectiveDelay(Event[] events) {
		Double delayDuration = 0.0;
		Double delayStartTimeStamp = null;
		for (Event event : events) {
			if(event.getItem_Name().equals(DELAY_TIME_ITEM_NAME)) {
				delayDuration += event.getArgumentValue(1);
			}
			if(event.getItem_Name().equals(START_DELAY_ITEM_NAME)) {
				delayStartTimeStamp = (double) event.getEvent_Time();
			}
			if(event.getEvent_Name().equals(SHUTDOWN_ITEM_NAME) & delayStartTimeStamp != null) {
				return (double)event.getEvent_Time() - delayStartTimeStamp;
			}
		}
		if(delayStartTimeStamp == null) {
			return Double.NaN;
		}
		return delayDuration;
	}

	private Double calculateFeedingTime(Event[] events) {
		Double trayEntryTimeStamp = null;
		for(Event event : events) {
			if(event.getItem_Name().equals(TRAY_ENTRY_NOTED_ITEM_NAME)) {
				trayEntryTimeStamp = (double) event.getEvent_Time();
			}
			if(event.getItem_Name().equals(START_DELAY_ITEM_NAME) && trayEntryTimeStamp != null) {
				return (double)event.getEvent_Time() - trayEntryTimeStamp;
			}
		}
		return Double.NaN;
	}

	private Double calculateFeedingLatency(Event[] events) {
		Double feederPulseTimestamp = null;
		for (Event event: events) {
			if (event.getItem_Name().equals(PRIME_FEED_ITEM_NAME) || event.getItem_Name().equals(PULSE_FEED_ITEM_NAME)) {
				feederPulseTimestamp = (double) event.getEvent_Time();
			}
			if (event.getItem_Name().equals(TRAY_ENTRY_NOTED_ITEM_NAME) && feederPulseTimestamp != null) {
				return (double) event.getEvent_Time() - feederPulseTimestamp;
			}
		}
		return Double.NaN;
	}

	@Override
	public void setParameters(SessionParameters parameters) {
		PairwiseHabituationV2Parameters pairwiseParameters = (PairwiseHabituationV2Parameters) parameters;
		this.delayTimeParameter = pairwiseParameters.getDelayTime();
		this.feederPulseTimeMillisecondsParameter = pairwiseParameters.getFeederPulseTimeMilliseconds();
		this.primeFeedPulseTimeMillisecondsParameter = pairwiseParameters.getPrimeFeedPulseTimeMilliseconds();
	}

}
