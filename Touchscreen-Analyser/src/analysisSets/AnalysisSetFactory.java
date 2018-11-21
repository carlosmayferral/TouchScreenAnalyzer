package analysisSets;

import TinaAnalysis.TinaAnalysisSet;
import extinctionAnalysisSet.ExtinctionAnalysisSet;
import progressiveRatioAnalysis.ProgressiveRatioAnalysisSet;

public class AnalysisSetFactory {

	private static AnalysisSetFactory instance = null;
	
	public static AnalysisSetFactory getInstance() {
		if (instance != null){
			return instance;
		}
		else {
			instance = new AnalysisSetFactory();
			return instance;
		}
	}
	
	public IAnalysisSet createAnalysisSet(AnalysisType type) {
		switch (type) {
		case TINA_BY_TRIAL:
		return new TinaAnalysisSet();
		case EXTINCTION:
		return new ExtinctionAnalysisSet();
		case PROGRESSIVE_RATIO:
		return new ProgressiveRatioAnalysisSet();
		default:
		return null;
		}
	}
	
	
	
}
