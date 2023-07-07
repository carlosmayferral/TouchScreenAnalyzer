package analysisSets;

import TinaAnalysis.TinaAnalysisSet;
import cptBaselineAnalysis.CPTBaselineAnalysisSet;
import extinctionAnalysisSet.ExtinctionAnalysisSet;
import fiveChoiceAnalysis.FiveChoiceAnalysisSet;
import fiveChoiceRatAnalysis.FiveChoiceRatAnalysisSet;
import mPosnerAnalysis.MPosnerProbeAnalysisSet;
import pairwiseHabituationV2AnalysisSet.PairwiseHabituationV2AnalysisSet;
import pdAnalysis.PdAnalysisSet;
import pdAnalysisOtago.PdOtagoSet;
import progressiveRatioAnalysis.ProgressiveRatioAnalysisSet;
import tunlAnalysisSet.TunlAnalysisSet;

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
		case OTAGO_PAIRWISE_DISCRIMINATION:
			return new PdOtagoSet();
		case TUNL:
			return new TunlAnalysisSet();
		case PAIRWISE_HABITUATION_V2:
			return new PairwiseHabituationV2AnalysisSet();
		case MPOSNER_PROBES:
			return new MPosnerProbeAnalysisSet();
		default:
			return null;
		}
	}

}
