package pdAnalysis;

import analysisSets.IAnalysisSet;
import analysisSets.IParameterReader;
import analysisSets.IPostProcessor;
import analysisSets.ITrialAnalyzer;
import analysisSets.ITrialPartitioner;

public class PdAnalysisSet implements IAnalysisSet {
	
	private ITrialPartitioner partitioner;
	private ITrialAnalyzer analyzer;
	private IParameterReader parameterReader;
	
	public PdAnalysisSet() {
		partitioner = new PdTrialPartitioner();
		analyzer = new PdTrialAnalyzer();
		parameterReader = new PdParameterReader();
	}

	@Override
	public ITrialPartitioner getTrialPartitioner() {
		return partitioner;
	}

	@Override
	public ITrialAnalyzer getTrialAnalyzer() {
		return analyzer;
	}

	@Override
	public IParameterReader getParameterReader() {
		return parameterReader;
	}

	
	//Post processor currently broken due to session information revamp
	@Override
	public IPostProcessor getPostProcessor() {
		// TODO Auto-generated method stub
		return null;
	}

}
