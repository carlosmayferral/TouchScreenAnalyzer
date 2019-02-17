package touchscreenAnalyzer;

import java.io.File;
import java.util.ArrayList;

import analysisSets.IAnalysisSet;
import analysisSets.AnalysisSetFactory;
import analysisSets.AnalysisType;
import dataModels.Result;
import dataModels.Session;
import dataModels.SessionComparator;

public class AnalyzerController {

	public AnalyzerController() {
	}

	public void analyze(String fileName, AnalysisType analysisType) {
		
		//Name to be used for the result
		String resultName = fileName + "\\results.csv";
		
		//Generate analysis set to use
		IAnalysisSet analysisSet = AnalysisSetFactory.getInstance().createAnalysisSet(analysisType);

		System.out.println("Reading experiment folder...");

		// Generate an experiment reader, this builds a list of sessions in the folder that contain basic information
		ExperimentReader experiment = new ExperimentReader();
		
		ArrayList<Session> sessions = experiment.readExperimentFolder(new File(fileName));
		
		//Abort if there is no sessions
		if(sessions.size() == 0) {
			System.exit(0);
		}

		//Sort sessions by identifier, then date using custom comparator
		sessions.sort(new SessionComparator());
		
		//Assign ordinal days to sessions
		assignOrdinalDay(sessions);
		
		System.out.println("Number of sessions: " + sessions.size());

		// Open experiment writer
		ExperimentWriter experimentWriter = new ExperimentWriter(resultName);
		experimentWriter.openFile();

		int totalSessions = sessions.size();
		int finishedSessions = 0;

		System.out.println("Processing into results:");

		// Analysis loop: for each sessionFile
		for (Session session : sessions) {

			System.out.println(((float) finishedSessions / (float) totalSessions) * 100 + "%");

			// Read events into session
			if(!session.generateEventsFromFile()) {
				System.out.println("Error parsing events in file " + session.getFileName() + "!! System will exit");
			}

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
				experimentWriter.writeResult(result);
			}

			finishedSessions++;
		}

		System.out.println("Finalizing results file, located at " + fileName);

		// Finally close results file
		experimentWriter.closeFile();
		
		//Apply any post processing? **WARNING** broken after meta data addition
		if (analysisSet.getPostProcessor() != null) {
			analysisSet.getPostProcessor().process(new File(resultName));
		}
	}

	/**
	 * Assigns ordinal days to an arraylist of sessions. Assumes sessions are sorted by animal then date.
	 * @param sessions
	 */
	public static void assignOrdinalDay(ArrayList<Session> sessions) {
		
		int currentDay = 0;
		SessionComparator comparator = new SessionComparator();
		
		//for each session
		for(int i = 0 ; i < (sessions.size() - 1); i++) {
			Session first = sessions.get(i);
			Session second = sessions.get(i+1);
			first.setDay(currentDay);
			currentDay ++;
			if (comparator.compareAnimalId(first, second) != 0) {
				currentDay = 0;
			}
			else {
				//Print warning message for two sessions on the same day
				if (comparator.compare(first, second) == 0) {
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
