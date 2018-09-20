package analysisSets;

import TinaAnalysis.TinaAnalysisSet;

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
	
	public AnalysisSet createAnalysisSet(AnalysisType type) {
		switch (type) {
		case TINA_BY_TRIAL:
		return new TinaAnalysisSet();
		default:
		return null;
		}
	}
	
	
	
}
