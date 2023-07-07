package tunlAnalysisSet;

import analysisSets.IAnalysisSet;
import analysisSets.IParameterReader;
import analysisSets.IPostProcessor;
import analysisSets.ITrialAnalyzer;
import analysisSets.ITrialPartitioner;

public class TunlAnalysisSet implements IAnalysisSet {
	
	private IParameterReader parameterReader;
	
	private ITrialPartitioner partitioner;
	
	private ITrialAnalyzer analyzer;
	
	public TunlAnalysisSet() {
		parameterReader = null;
		partitioner = new TunlTrialPartitioner();
		analyzer = new TunlTrialAnalyzer();
	}

	@Override
	public ITrialPartitioner getTrialPartitioner() {
		return partitioner;
	}

	@Override
	public ITrialAnalyzer getTrialAnalyzer() {
		// TODO Auto-generated method stub
		return analyzer;
	}

	@Override
	public IParameterReader getParameterReader() {
		return parameterReader;
	}

	@Override
	public IPostProcessor getPostProcessor() {
		return null;
	}

}
