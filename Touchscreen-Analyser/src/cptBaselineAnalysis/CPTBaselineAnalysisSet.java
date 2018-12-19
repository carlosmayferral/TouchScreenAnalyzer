package cptBaselineAnalysis;

import analysisSets.IAnalysisSet;
import analysisSets.IParameterReader;
import analysisSets.IPostProcessor;
import analysisSets.ITrialAnalyzer;
import analysisSets.ITrialPartitioner;

public class CPTBaselineAnalysisSet implements IAnalysisSet {

	private CPTBaselineTrialPartitioner trialPartitioner;
	private CPTBaselineParameterReader parameterReader;
	private CPTBaselineTrialAnalyzer trialAnalyzer;

	public CPTBaselineAnalysisSet() {
		trialPartitioner = new CPTBaselineTrialPartitioner();
		parameterReader = new CPTBaselineParameterReader();
		trialAnalyzer = new CPTBaselineTrialAnalyzer();
	}

	@Override
	public ITrialPartitioner getTrialPartitioner() {
		return trialPartitioner;
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
		// TODO Auto-generated method stub
		return null;
	}

}
