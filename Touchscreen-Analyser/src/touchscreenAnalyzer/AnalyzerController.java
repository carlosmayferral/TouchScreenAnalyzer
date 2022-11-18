package touchscreenAnalyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import analysisSets.IAnalysisSet;
import analysisSets.AnalysisSetFactory;
import analysisSets.AnalysisType;
import dataModels.Result;
import dataModels.Session;
import dataModels.SessionInfo;

public class AnalyzerController {

	private static final String STRING_APPENDED_TO_RESULT = "\\results.csv";

	private static final String CALENDAR_FILENAME = "\\experimentCalendar.csv";

	// The experiment reader to be used for reading raw data folders
	private ExperimentReader experiment;

	// The sessions tracked by the controller
	private ArrayList<Session> sessions;

	// The writer used to record results
	private ExperimentWriter resultWriter;

	// The analysis set to be used
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

		// Abort if there is no sessions
		if (sessions.size() == 0) {
			System.out.println("No files were found in directory provided");
			System.exit(0);
		}

		// Sort sessions by identifier, then date using custom comparator
		Collections.sort(sessions);

		// Assign ordinal days to sessions
		this.assignOrdinalDay();

		System.out.println("Number of sessions: " + sessions.size());

		// Open experiment writer
		resultWriter = new ExperimentWriter(resultName);
		resultWriter.openFile();

		int totalSessions = sessions.size();
		int finishedSessions = 0;

		// Print out experimental arrangement: calendar for each animal
		generateCalendarFile(fileName, sessions);

		// Generate analysis set to use
		analysisSet = AnalysisSetFactory.getInstance().createAnalysisSet(analysisType);

		System.out.println("Processing into results:");

		// This value will be used to print progress
		int fivePercentOfTotal = totalSessions / 20;

		// Analysis loop: for each session File
		for (int i = 0; i < totalSessions; i++) {

			Session session = sessions.remove(0);

			// print progress if session number represents 5% increase
			if (totalSessions != 1 && i % fivePercentOfTotal == 0) {
				System.out.println(((float) finishedSessions / (float) totalSessions) * 100 + "%");
			}

			session.generateEventsFromFile();

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

		// Apply any post processing? **WARNING** broken after meta data addition
		if (analysisSet.getPostProcessor() != null) {
			analysisSet.getPostProcessor().process(new File(resultName));
		}
	}

	private void generateCalendarFile(String folderName, ArrayList<Session> sessions) {

		String calendarFileName = folderName + CALENDAR_FILENAME;

		System.out.println("Generating calendar file in: " + calendarFileName);

		try {
			PrintWriter pw = new PrintWriter(new File(calendarFileName));

			pw.println("Legend: R=Run number; T=Total time Recorded (in seconds); C=Chamber; D=Database; ID=Session ID");

			HashMap<Long, Boolean> dates = new HashMap<>();
			HashMap<String, ArrayList<SessionInfo>> animalSessionInfos = new HashMap<>();

			// Order sessions into maps
			for (Session session : sessions) {
				// Add to dates if date hasn't been encountered yet
				if (!dates.containsKey(session.getDate())) {
					dates.put(session.getDate(), true);
				}
				// Insert animal into map if it doesn't exist yet
				if (!animalSessionInfos.containsKey(session.getSessionInfo().getAnimalId())) {
					animalSessionInfos.put(session.getSessionInfo().getAnimalId(), new ArrayList<>());
				}
				// add sessioninfo
				animalSessionInfos.get(session.getSessionInfo().getAnimalId()).add(session.getSessionInfo());
			}

			// Once sessions are placed into maps, sort both collections
			ArrayList<Long> sortedDates = new ArrayList<>(dates.keySet());
			Collections.sort(sortedDates);

			ArrayList<String> sortedAnimalIds = new ArrayList<>(animalSessionInfos.keySet());
			Collections.sort(sortedAnimalIds);

			// Print header
			pw.print("Animal ID");
			for (long date : sortedDates) {
				pw.print("," + date);
			}
			pw.println(",total sessions");

			// Then print each animal
			for (String animalId : sortedAnimalIds) {
				pw.print(animalId);
				// print out for each date on record
				for (Long date : sortedDates) {
					pw.print(",");
					// Get sessionsInfos for this date
					ArrayList<SessionInfo> infosForThisDate = new ArrayList<>();
					for (SessionInfo sessionInfo : animalSessionInfos.get(animalId)) {
						if (sessionInfo.getDate() == date) {
							infosForThisDate.add(sessionInfo);
						}
					}
					// If no session infos, print null
					if (infosForThisDate.size() == 0) {
						pw.print("NULL");
					}
					// Write details of each sessionInfo
					for (SessionInfo sessionInfo : infosForThisDate) {
						pw.print("[R=" + sessionInfo.getOrdinalDay() + ";T=" + Math.floor(sessionInfo.getMaxTime())
								+ ";C=" + sessionInfo.getChamber() + ";D=" + sessionInfo.getDatabase() + ";ID="
								+ sessionInfo.getSessionId() + "]");
					}
				}
				pw.println("," + animalSessionInfos.get(animalId).size());
			}

			pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}

	}

	/**
	 * Assigns ordinal days to an arraylist of sessions. Assumes sessions are sorted
	 * by animal then date.
	 * 
	 * @param sessions
	 */
	public void assignOrdinalDay() {

		int currentDay = 0;

		// for each session
		for (int i = 0; i < (sessions.size() - 1); i++) {
			Session first = sessions.get(i);
			Session second = sessions.get(i + 1);
			first.setDay(currentDay);
			currentDay++;
			if (first.compareAnimalIdTo(second) != 0) {
				currentDay = 0;
			} else {
				// Print warning message for two sessions on the same day
				if (first.compareTo(second) == 0) {
					System.out.println("Warning, animal id " + first.getIdentifier().getAnimalId() + " seems to "
							+ "have been tested twice on " + first.getDate() + " check file " + first.getFileName()
							+ " (" + first.getTime() + ")" + " and " + second.getFileName() + " (" + second.getTime()
							+ ")");

				}
			}
		}

		// set last day
		sessions.get(sessions.size() - 1).setDay(currentDay);
	}

}
