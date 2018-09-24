package extinctionAnalysisSet;

import analysisSets.IAnalysisSet;
import analysisSets.IParameterReader;
import analysisSets.ITrialAnalyzer;
import analysisSets.ITrialPartitioner;

public class ExtinctionAnalysisSet implements IAnalysisSet {
	
	private ITrialPartitioner trialPartitioner;
	
	private ITrialAnalyzer trialAnalyzer;
	
	private IParameterReader parameterReader;
	
	public ExtinctionAnalysisSet () {
		this.trialPartitioner = new ExtinctionTrialPartitioner();
		this.parameterReader = new ExtinctionParameterReader();
		this.trialAnalyzer = new ExtinctionTrialAnalyzer();
	}

	@Override
	public ITrialPartitioner getTrialPartitioner() {
		return this.trialPartitioner;
	}

	@Override
	public ITrialAnalyzer getTrialAnalyzer() {
		return this.trialAnalyzer;
	}

	@Override
	public IParameterReader getParameterReader() {
		return this.parameterReader;
	}

}
