package mPosnerAnalysis;

import analysisSets.IAnalysisSet;
import analysisSets.IParameterReader;
import analysisSets.IPostProcessor;
import analysisSets.ITrialAnalyzer;
import analysisSets.ITrialPartitioner;

public class MPosnerProbeAnalysisSet implements IAnalysisSet {
	
	private MPosnerProbeParameterReader parameterReader;
	private MPosnerProbeTrialPartitioner partitioner;
	private MPosnerProbeTrialAnalyzer analyzer;
	
	public MPosnerProbeAnalysisSet() {
		this.parameterReader = new MPosnerProbeParameterReader();
		this.partitioner = new MPosnerProbeTrialPartitioner();
		this.analyzer = new MPosnerProbeTrialAnalyzer();
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
