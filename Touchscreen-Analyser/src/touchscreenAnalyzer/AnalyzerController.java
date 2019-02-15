package touchscreenAnalyzer;

import java.io.File;

import analysisSets.IAnalysisSet;
import analysisSets.AnalysisSetFactory;
import analysisSets.AnalysisType;
import dataModels.Result;
import dataModels.Session;

public class AnalyzerController {

	public AnalyzerController() {
	}

	public void analyze(String fileName, AnalysisType analysisType) {
		
		//Name to be used for the result
		String resultName = fileName + "\\results.csv";
		
		//Generate analysis set to use
		IAnalysisSet analysisSet = AnalysisSetFactory.getInstance().createAnalysisSet(analysisType);

		System.out.println("Reading experiment folder...");

		// Generate an experiment reader, this builds a priority queue of files to
		// analyze
		ExperimentReader experiment = new ExperimentReader();
		experiment.readExperimentFolder(new File(fileName));

		System.out.println("Number of sessions: " + experiment.getNumberOfSessions());

		// Open experiment writer
		ExperimentWriter experimentWriter = new ExperimentWriter(resultName);
		experimentWriter.openFile();

		File sessionFile;
		int totalSessions = experiment.getNumberOfSessions();
		int finishedSessions = 0;

		System.out.println("Processing into results:");

		// Analysis loop: for each sessionFile
		while ((sessionFile = experiment.getNextSession()) != null) {

			System.out.println(((float) finishedSessions / (float) totalSessions) * 100 + "%");

			// Create session object using file and recorded info
			Session session = new Session(sessionFile, experiment.getSessionInfo(sessionFile));

			// Read events into session
			session.readEventsIntoSession();

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
		
		//Apply any post processing?
		if (analysisSet.getPostProcessor() != null) {
			analysisSet.getPostProcessor().process(new File(resultName));
		}
	}

}
