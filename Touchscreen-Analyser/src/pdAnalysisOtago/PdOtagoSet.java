package pdAnalysisOtago;

import analysisSets.AnalysisSet;
import analysisSets.IAnalysisSet;
import analysisSets.IParameterReader;
import analysisSets.IPostProcessor;
import analysisSets.ITrialAnalyzer;
import analysisSets.ITrialPartitioner;

public class PdOtagoSet implements IAnalysisSet {
	
	private PdOtagoPartitioner partitioner;
	private PdOtagoAnalyzer analyzer;
	
	public PdOtagoSet() {
		partitioner = new PdOtagoPartitioner();
		analyzer = new PdOtagoAnalyzer();
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
		return null;
	}

	@Override
	public IPostProcessor getPostProcessor() {
		return null;
	}

}
