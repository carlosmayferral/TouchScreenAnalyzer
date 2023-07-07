package analysisSets;

import dataModels.MetaData;
import dataModels.Result;
import dataModels.SessionInfo;
import dataModels.SessionParameters;
import dataModels.Trial;

public interface ITrialAnalyzer {

	public Result analyzeTrial(Trial trial, int counter, SessionInfo sessionInfo, MetaData metaData);

	public void setParameters(SessionParameters parameters);

}
