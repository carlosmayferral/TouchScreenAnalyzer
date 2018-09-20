package analysisSets;

import dataModels.Result;
import dataModels.SessionInfo;
import dataModels.SessionParameters;
import dataModels.Trial;

public interface ITrialAnalyzer {
	
	public Result analyzeTrial(Trial trial, int counter, SessionInfo sessionInfo);
	
	public void setParameters(SessionParameters parameters);
	
}
