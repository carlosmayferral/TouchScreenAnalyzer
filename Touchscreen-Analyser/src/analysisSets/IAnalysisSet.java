package analysisSets;

public interface IAnalysisSet {

	public ITrialPartitioner getTrialPartitioner();
	
	public ITrialAnalyzer getTrialAnalyzer();
	
	public IParameterReader getParameterReader();
	
}
