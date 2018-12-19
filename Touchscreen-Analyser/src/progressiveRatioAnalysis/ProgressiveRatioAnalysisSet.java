package progressiveRatioAnalysis;

import analysisSets.IAnalysisSet;
import analysisSets.IParameterReader;
import analysisSets.IPostProcessor;
import analysisSets.ITrialAnalyzer;
import analysisSets.ITrialPartitioner;

public class ProgressiveRatioAnalysisSet implements IAnalysisSet {

	private ITrialPartitioner trialpartitioner;

	private ITrialAnalyzer trialAnalyzer;

	private IParameterReader parameterReader;

	public ProgressiveRatioAnalysisSet() {
		this.trialpartitioner = new ProgressiveRatioTrialPartitioner();
		this.parameterReader = new ProgressiveRatioParameterReader();
		this.trialAnalyzer = new ProgressiveRatioTrialAnalyzer();
	}

	@Override
	public ITrialPartitioner getTrialPartitioner() {
		return trialpartitioner;
	}

	@Override
	public ITrialAnalyzer getTrialAnalyzer() {
		return trialAnalyzer;
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
