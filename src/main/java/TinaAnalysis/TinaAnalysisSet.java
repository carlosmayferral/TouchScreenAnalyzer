package TinaAnalysis;

import analysisSets.IAnalysisSet;
import analysisSets.IParameterReader;
import analysisSets.IPostProcessor;
import analysisSets.ITrialAnalyzer;
import analysisSets.ITrialPartitioner;

public class TinaAnalysisSet implements IAnalysisSet {

	private ITrialPartitioner partitioner;

	private ITrialAnalyzer analyzer;

	private IParameterReader parameterReader;
	
	private IPostProcessor postProcessor;

	public TinaAnalysisSet() {
		this.partitioner = new TinaTrialPartitioner();
		this.analyzer = new TinaTrialAnalyzer();
		this.parameterReader = new TinaParameterReader();
		this.postProcessor = new TinaPostProcessor();
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
	
	@Override
	public IPostProcessor getPostProcessor() {
		return postProcessor;
	}

}
