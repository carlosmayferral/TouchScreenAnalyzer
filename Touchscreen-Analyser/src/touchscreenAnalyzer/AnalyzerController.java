package touchscreenAnalyzer;
import java.io.File;

import dataModels.Result;

public class AnalyzerController {
	
	public static void analyze(String string, String resultString) {
		
		System.out.println("Reading experiment folder...");
		
		//Generate an experiment reader, this builds a priority queue of files to analyze
		ExperimentReader experiment = new ExperimentReader(new File(string));
		
		System.out.println("Number of sessions: " + experiment.getNumberOfSessions());
		
		//Open experiment writer
		ExperimentWriter experimentWriter = new ExperimentWriter(resultString);
		experimentWriter.openFile();
		
		File sessionFile;
		int totalSessions = experiment.getNumberOfSessions();
		
		
		
		//Some variables for the analysis loop...
		String currentAnimalId = "";
		int currentDay = -1;
		int finishedSessions = 0;
		
		System.out.println("Processing into results:");
		
		
		//Analysis loop: for each sessionFile
		while ((sessionFile = experiment.getNextSession()) != null) {
			
			System.out.println(((float)finishedSessions / (float)totalSessions) * 100 + "%");
			
			
			//If animal is new start day from 0.
			if (!experiment.getSessionInfo(sessionFile).getAnimalId().equals(currentAnimalId)) {
				currentDay = 0;
				experiment.getSessionInfo(sessionFile).setDay(currentDay);
			}
			//If animal didn't change, assign Day.
			else {
				experiment.getSessionInfo(sessionFile).setDay(currentDay);
			}
			
			
			//each session file gets partitioned into trials and is returned as a session object (containing info and a set of trials)
			Session session = new Session (sessionFile, experiment.getSessionInfo(sessionFile), new TinaTrialPartitioner());
			
			session.generateTrials();
			
			//TODO each session object is sent to a session analyzer, which then sends each trial to a trial analyzer
			//this session analyzer will contain a set of results for each trial
			
			session.generateResults(new tinaTrialAnalyzer(session.getParameters()));
			
			//communicate these results, and the session info, to something that writes results to disk (experiment writer?)
			Result result;
			while((result = session.getResult()) != null) {
				experimentWriter.writeResult(result);
			}
			
			
			//Increment day by 1.
			currentDay++;
			currentAnimalId = experiment.getSessionInfo(sessionFile).getAnimalId();
			finishedSessions++;
		}
		
		System.out.println("Finalizing results file");
		
		//Finally close results file
		experimentWriter.closeFile();
		
	}

}
