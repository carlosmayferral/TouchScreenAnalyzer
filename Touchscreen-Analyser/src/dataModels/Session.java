package dataModels;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import analysisSets.IParameterReader;
import analysisSets.ITrialAnalyzer;
import analysisSets.ITrialPartitioner;

public class Session {

	private File file;

	private SessionInfo sessionInfo;

	private SessionParameters parameters;

	Event[] events;

	private ArrayList<Trial> trials;

	private ArrayList<Result> results;

	public Session(File sessionFile, SessionInfo sessionInfo) {

		this.file = sessionFile;
		this.sessionInfo = sessionInfo;
		this.events = null;

	}

	public boolean readEventsIntoSession() {
		events = this.readEvents();
		if (this.events != null) {
			return true;
		} else
			return false;
	}

	public void generateTrialsfromEvents(ITrialPartitioner partitionProtocol) {

		this.trials = partitionProtocol.partition(events);
		
		if (trials.size()< 5) {
			System.out.println("Warning: session # " + sessionInfo.getSessionId() 
			+ " has less than five trials, session might be accidental.");
		}

		return;
	}

	public void generateParametersFromEvents(IParameterReader parameterReader) {

		this.parameters = parameterReader.readParameters(events);

	}

	public void generateResults(ITrialAnalyzer trialAnalyzer) {

		int trialcounter = 0;

		results = new ArrayList<Result>();

		for (Trial trial : trials) {
			results.add(trialAnalyzer.analyzeTrial(trial, trialcounter, sessionInfo));
			trialcounter++;
		}
	}

	private Event[] readEvents() {

		// Initialize events array
		ArrayList<Event> events = new ArrayList<Event>();

		// Try opening file
		String line = "";
		BufferedReader br = null;

		// Read startup
		boolean eventsHaveStarted = false;

		try {
			br = new BufferedReader(new FileReader(file));

			// Per line loop....
			while ((line = br.readLine()) != null) {
				// Only Start reading if events have started
				if (!eventsHaveStarted) {
					if (line.contains("Item_Name")) {
						eventsHaveStarted = true;
						continue;
					} else {
						continue;
					}
				}

				// If events have started, convert each line into an event by using the csv
				// format
				else {
					events.add(new Event(line));
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (events.size() >= 0) {
			Event[] eventsCopy = new Event[events.size()];
			return (Event[]) events.toArray(eventsCopy);
		} else
			return null;
	}

	public Result getResult() {
		if (this.results != null && (this.results.size() > 0)) {
			return this.results.remove(0);
		} else
			return null;
	}

	public SessionParameters getParameters() {
		return parameters;
	}

	public Event[] getEventArrayCopy() {
		return Arrays.copyOf(events, events.length);
	}

}
