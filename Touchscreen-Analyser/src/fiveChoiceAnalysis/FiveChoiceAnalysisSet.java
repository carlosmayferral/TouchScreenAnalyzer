package fiveChoiceAnalysis;

import analysisSets.IAnalysisSet;
import analysisSets.IParameterReader;
import analysisSets.IPostProcessor;
import analysisSets.ITrialAnalyzer;
import analysisSets.ITrialPartitioner;

public class FiveChoiceAnalysisSet implements IAnalysisSet {
	
	private ITrialPartitioner partitioner;
	
	private ITrialAnalyzer analyzer;
	
	private IParameterReader parameterReader;
	
	private IPostProcessor postProcessor;
	
	public FiveChoiceAnalysisSet() {
		this.partitioner = new FiveChoiceTrialPartitioner();
		this.analyzer = new FiveChoiceTrialAnalyzer();
		this.parameterReader = new FiveChoiceParameterReader();
		this.postProcessor = null;
	}

	@Override
	public ITrialPartitioner getTrialPartitioner() {
		return partitioner;
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
		return this.postProcessor;
	}

}
