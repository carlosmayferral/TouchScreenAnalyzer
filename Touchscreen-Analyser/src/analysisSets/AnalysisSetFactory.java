package analysisSets;

import TinaAnalysis.TinaAnalysisSet;
import cptBaselineAnalysis.CPTBaselineAnalysisSet;
import extinctionAnalysisSet.ExtinctionAnalysisSet;
import fiveChoiceAnalysis.FiveChoiceAnalysisSet;
import fiveChoiceRatAnalysis.FiveChoiceRatAnalysisSet;
import pdAnalysis.PdAnalysisSet;
import progressiveRatioAnalysis.ProgressiveRatioAnalysisSet;

public class AnalysisSetFactory {

	private static AnalysisSetFactory instance = null;

	public static AnalysisSetFactory getInstance() {
		if (instance != null) {
			return instance;
		} else {
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
		case CPT_BASELINE:
			return new CPTBaselineAnalysisSet();
		case FIVE_CHOICE:
			return new FiveChoiceAnalysisSet();
		case FIVE_CHOICE_RAT:
			return new FiveChoiceRatAnalysisSet();
		case PAIRWISE_DISCRIMINATION:
			return new PdAnalysisSet();
		default:
			return null;
		}
	}

}
