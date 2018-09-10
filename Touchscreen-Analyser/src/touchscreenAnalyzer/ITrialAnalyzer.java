package touchscreenAnalyzer;

import dataModels.Result;
import dataModels.SessionInfo;

public interface ITrialAnalyzer {
	
	public Result analyzeTrial(Trial trial, int counter, SessionInfo sessionInfo);
	
}
