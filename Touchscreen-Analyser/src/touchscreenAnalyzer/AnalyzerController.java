package touchscreenAnalyzer;
import java.io.File;

import analysisSets.AnalysisSet;
import analysisSets.AnalysisSetFactory;
import analysisSets.AnalysisType;
import dataModels.Result;
import dataModels.Session;

public class AnalyzerController {
	
	private AnalysisSet analysisSet;
	private String fileName;
	private String resultName;
	
	public AnalyzerController(String fileName, AnalysisType analysisType) {
		this.fileName = fileName;
		this.resultName = fileName + "\\results.csv";
		this.analysisSet = AnalysisSetFactory.getInstance().createAnalysisSet(analysisType);
	}
	
	
	public void analyze() {
		
		System.out.println("Reading experiment folder...");
		
		//Generate an experiment reader, this builds a priority queue of files to analyze
		ExperimentReader experiment = new ExperimentReader(new File(fileName));
		
		System.out.println("Number of sessions: " + experiment.getNumberOfSessions());
		
		//Open experiment writer
		ExperimentWriter experimentWriter = new ExperimentWriter(resultName);
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
			Session session = new Session (sessionFile, experiment.getSessionInfo(sessionFile));
			
			session.generateParametersFromEvents(analysisSet.getParameterReader());
			
			session.generateTrialsfromEvents(analysisSet.getTrialPartitioner());
			
			//Set default session parameters in trial analyzer before it starts
			analysisSet.getTrialAnalyzer().setParameters(session.getParameters());
			
			session.generateResults(analysisSet.getTrialAnalyzer());
			
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
