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
	}

	@Override
	public ITrialPartitioner getTrialPartitioner() {
		return partitioner;
	}

	@Override
	public ITrialAnalyzer getTrialAnalyzer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IParameterReader getParameterReader() {
		return null;
	}

	@Override
	public IPostProcessor getPostProcessor() {
		// TODO Auto-generated method stub
		return null;
	}

}
