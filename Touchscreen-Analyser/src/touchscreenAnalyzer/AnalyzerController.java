package touchscreenAnalyzer;
import java.io.File;

import dataModels.AnalysisType;

public class AnalyzerController {
	
	public static void analyze(String[] args) {
		
		
		//Generate an experiment reader, this builds a priority queue of files to analyze
		ExperimentReader experiment = new ExperimentReader(new File(args[0]));
		
		
		//Implemented: call next session to get a reference to the file, call get session info to get info on it
		System.out.println(experiment.getSessionInfo(experiment.getNextSession()).toString());
		
		File sessionFile;
		
		//Analyis loop: for each sessionFile
		while ((sessionFile = experiment.getNextSession()) != null) {
			//TODO each session file gets partitioned into trials and is returned as a session object (containing info and a set of trials)
			
			
			//TODO each session object is sent to a session analyzer, which then sends each trial to a trial analyzer
			//this session analyzer will contain a set of results for each trial
			
			//communicate these results, and the session info, to something that writes results to disk (experiment writer?)
			
			
		}
		
	}

}
