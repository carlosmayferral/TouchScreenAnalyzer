package touchscreenAnalyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import analysisSets.IAnalysisSet;
import analysisSets.AnalysisSetFactory;
import analysisSets.AnalysisType;
import dataModels.Result;
import dataModels.Session;

public class AnalyzerController {
	
	private static final String STRING_APPENDED_TO_RESULT = "\\results.csv";
	
	//The experiment reader to be used for reading raw data folders
	private ExperimentReader experiment;
	
	//The sessions tracked by the controller
	private ArrayList<Session> sessions;
	
	//The writer used to record results
	private ExperimentWriter resultWriter;
	
	//The analysis set to be used
	private IAnalysisSet analysisSet;

	public AnalyzerController() {
		experiment = new ExperimentReader();
		sessions = null;
		resultWriter = null;
		analysisSet = null;
	}

	public void analyze(String fileName, AnalysisType analysisType) {
		
		// Name to be used for the result
		String resultName = fileName + STRING_APPENDED_TO_RESULT;
		
		System.out.println("Reading experiment folder...");
		
		// Retrieve list of sessions from reader
		sessions = experiment.readExperimentFolder(new File(fileName));
		
		//Abort if there is no sessions
		if(sessions.size() == 0) {
			System.out.println("No files were found in directory provided");
			System.exit(0);
		}

		//Sort sessions by identifier, then date using custom comparator
		Collections.sort(sessions);
		
		//Assign ordinal days to sessions
		this.assignOrdinalDay();
		
		System.out.println("Number of sessions: " + sessions.size());

		// Open experiment writer
		resultWriter = new ExperimentWriter(resultName);
		resultWriter.openFile();

		int totalSessions = sessions.size();
		int finishedSessions = 0;
		
		//Generate analysis set to use
		analysisSet = AnalysisSetFactory.getInstance().createAnalysisSet(analysisType);

		System.out.println("Processing into results:");

		// Analysis loop: for each session File
		for (Session session : sessions) {

			System.out.println(((float) finishedSessions / (float) totalSessions) * 100 + "%");

			// Generate starting parameters if necessary
			session.generateParametersFromEvents(analysisSet.getParameterReader());

			// Generate trials from event list
			session.generateTrialsfromEvents(analysisSet.getTrialPartitioner());

			// Set default session parameters in trial analyzer before it starts
			analysisSet.getTrialAnalyzer().setParameters(session.getParameters());

			// Analyze trials
			session.generateResults(analysisSet.getTrialAnalyzer());

			// communicate these results to writer
			Result result;
			while ((result = session.getResult()) != null) {
				resultWriter.writeResult(result);
			}

			finishedSessions++;
		}

		System.out.println("Finalizing results file, located at " + fileName);

		// Finally close results file
		resultWriter.closeFile();
		
		//Apply any post processing? **WARNING** broken after meta data addition
		if (analysisSet.getPostProcessor() != null) {
			analysisSet.getPostProcessor().process(new File(resultName));
		}
	}

	/**
	 * Assigns ordinal days to an arraylist of sessions. Assumes sessions are sorted by animal then date.
	 * @param sessions
	 */
	public void assignOrdinalDay() {
		
		int currentDay = 0;
		
		//for each session
		for(int i = 0 ; i < (sessions.size() - 1); i++) {
			Session first = sessions.get(i);
			Session second = sessions.get(i+1);
			first.setDay(currentDay);
			currentDay ++;
			if (first.compareAnimalIdTo(second) != 0) {
				currentDay = 0;
			}
			else {
				//Print warning message for two sessions on the same day
				if (first.compareTo(second) == 0) {
					System.out.println("Warning, animal id " + first.getIdentifier().getAnimalId() + " seems to "
							+ "have been tested twice on " + first.getDate() + " check file " + first.getFileName()
							+ " and " + second.getFileName());
					
				}
			}
		}
		
		//set last day
		sessions.get(sessions.size()-1).setDay(currentDay);
	}

}
