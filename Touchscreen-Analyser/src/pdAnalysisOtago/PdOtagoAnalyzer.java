package pdAnalysisOtago;

import analysisSets.ITrialAnalyzer;
import dataModels.Event;
import dataModels.MetaData;
import dataModels.Result;
import dataModels.SessionInfo;
import dataModels.SessionParameters;
import dataModels.Trial;

public class PdOtagoAnalyzer implements ITrialAnalyzer {

	@Override
	public Result analyzeTrial(Trial trial, int counter, SessionInfo sessionInfo, MetaData metaData) {
		PdOtagoResult pdResult = analyze(trial,counter);
		return new Result(sessionInfo, metaData, pdResult.toString(), pdResult.getHeader());
	}
	
	public PdOtagoResult analyze(Trial trial, int counter) {
		PdOtagoResult result = new PdOtagoResult();
		
		Event[] trialEvents = trial.copyEventsAsArray();
		
		//set trial number and timeStamp
		result.setTrialNumber(counter);
		result.setTimeStamp(trialEvents[0].getEvent_Time());
		
		//set if trial is correction trial
		result.setCorrectionTrial(this.determineIfCorrectionTrial(trialEvents));
		
		//set whether trial is correct or not
		result.setCorrect(determineIfCorrect(trialEvents));
		
		return result;
	}

	public int determineIfCorrect(Event[] trialEvents) {
		
		for (Event event : trialEvents) {
			if (event.getItem_Name().equals("Correct")) {
				return 1;
			}
			else if (event.getItem_Name().equals("Incorrect")) {
				return 0;
			}
		}
		System.out.println("Error determining if trial was correct: no correct or incorrect event");
		return -1;
	}
	
	public int determineIfCorrectionTrial(Event[] trialEvents) {
		
		for (Event event : trialEvents) {
			if (event.getItem_Name().contains("Correction_Trial")) {
				if (event.getArgumentValue(1) == 0) {
					return 0;
				}
				else if (event.getArgumentValue(1) == 1) {
					return 1;
				}
			}
			else if (event.getItem_Name().contains("Next trial non corrected Trial")) {
				return 0;
			}
		}
		
		System.out.println("Could not determine if trial was correction trial or not...");
		return -1;
	}

	@Override
	public void setParameters(SessionParameters parameters) {
		return;
	}

}
