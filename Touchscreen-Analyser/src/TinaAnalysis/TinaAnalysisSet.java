package TinaAnalysis;

import analysisSets.AnalysisSet;
import analysisSets.IParameterReader;
import analysisSets.ITrialAnalyzer;
import analysisSets.ITrialPartitioner;

public class TinaAnalysisSet implements AnalysisSet {

	private ITrialPartitioner partitioner;
	
	private ITrialAnalyzer analyzer;
	
	private IParameterReader parameterReader;
	
	
	public TinaAnalysisSet() {
		this.partitioner = new TinaTrialPartitioner();
		this.analyzer = new tinaTrialAnalyzer();
		this.parameterReader = new tinaParameterReader();
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

}
