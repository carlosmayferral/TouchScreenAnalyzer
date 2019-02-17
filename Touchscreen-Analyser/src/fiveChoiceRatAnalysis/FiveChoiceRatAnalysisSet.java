package fiveChoiceRatAnalysis;

import analysisSets.IAnalysisSet;
import analysisSets.IParameterReader;
import analysisSets.IPostProcessor;
import analysisSets.ITrialAnalyzer;
import analysisSets.ITrialPartitioner;

public class FiveChoiceRatAnalysisSet implements IAnalysisSet {
	
	private ITrialPartitioner partitioner;
	
	private IParameterReader parameterReader;
	
	private ITrialAnalyzer analyzer;
	
	public FiveChoiceRatAnalysisSet() {
		partitioner = new FiveChoiceRatTrialPartitioner();
		parameterReader = new FiveChoiceRatParameterReader();
		analyzer = new FiveChoiceRatTrialAnalyzer();
	}

	@Override
	public ITrialPartitioner getTrialPartitioner() {
		return this.partitioner;
	}

	@Override
	public ITrialAnalyzer getTrialAnalyzer() {
		return this.analyzer;
	}

	@Override
	public IParameterReader getParameterReader() {
		return this.parameterReader;
	}

	@Override
	public IPostProcessor getPostProcessor() {
		return null;
	}

}
