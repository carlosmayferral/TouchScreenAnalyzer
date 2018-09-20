package analysisSets;

public interface AnalysisSet {

	public ITrialPartitioner getTrialPartitioner();
	
	public ITrialAnalyzer getTrialAnalyzer();
	
	public IParameterReader getParameterReader();
	
}
