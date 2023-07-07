package pairwiseHabituationV2AnalysisSet;

import analysisSets.IAnalysisSet;
import analysisSets.IParameterReader;
import analysisSets.IPostProcessor;
import analysisSets.ITrialAnalyzer;
import analysisSets.ITrialPartitioner;

public class PairwiseHabituationV2AnalysisSet implements IAnalysisSet {
	
	private PairwiseHabituationV2TrialPartitioner partitioner;
	private PairwiseHabituationV2ParameterReader parameterReader;
	private PairwiseHabituationV2TrialAnalyzer analyzer;
	
	

	public PairwiseHabituationV2AnalysisSet() {
		this.partitioner = new PairwiseHabituationV2TrialPartitioner();
		this.parameterReader = new PairwiseHabituationV2ParameterReader();
		this.analyzer = new PairwiseHabituationV2TrialAnalyzer();
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
		// TODO Auto-generated method stub
		return null;
	}

}
