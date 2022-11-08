package pairwiseHabituationV2AnalysisSet;

import analysisSets.IParameterReader;
import dataModels.Event;
import dataModels.SessionParameters;

/**
 * Responsible for reading important variables for Pairwise Habituation Sessions. These parameters are set at the beggining
 * of the session and are not referenced per trial.
 * @author Carlos May
 *
 */
public class PairwiseHabituationV2ParameterReader implements IParameterReader {
	/**
	 * Item name for the delay time variable event.
	 */
	private static final String DELAY_TIME_ITEM_NAME = "Delay_Time";
	
	/**
	 * Item name for the feeder pulse time variable event.
	 */
	private static final String FEEDER_PULSE_TIME_ITEM_NAME = "Feeder_Pulse_Time";
	
	/**
	 * Item name for the prime feed time variable event.
	 */
	private static final String PRIME_FEED_TIME_ITEM_NAME = "Prime_Feed_Time";

	
	@Override
	public SessionParameters readParameters(Event[] events) {
		//Initiate result object
		PairwiseHabituationV2Parameters parameters = new PairwiseHabituationV2Parameters();
		
		//Iterate through events to find item names
		for (Event event : events) {
			if(event.getItem_Name().equals(DELAY_TIME_ITEM_NAME)) {
				Double delayTime = (double) event.getArgumentValue(1);
				parameters.setDelayTime(delayTime);
			}
			if(event.getItem_Name().equals(FEEDER_PULSE_TIME_ITEM_NAME)) {
				Integer pulseTime = (int) event.getArgumentValue(1);
				parameters.setFeederPulseTimeMilliseconds(pulseTime);
			}
			if(event.getItem_Name().equals(PRIME_FEED_TIME_ITEM_NAME)) {
				Integer pulseTime = (int) event.getArgumentValue(1);
				parameters.setPrimeFeedPulseTimeMilliseconds(pulseTime);
			}
			
			//Return if at any time all three parameters are not null
			if(parameters.getDelayTime() != null && parameters.getFeederPulseTimeMilliseconds() != null && parameters.getPrimeFeedPulseTimeMilliseconds() != null) {
				return parameters;
			}
			
		}
		
		return parameters;
	}

}
