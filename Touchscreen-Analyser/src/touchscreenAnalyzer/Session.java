package touchscreenAnalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import dataModels.Result;
import dataModels.SessionInfo;

public class Session {
	
	private File file;
	
	private SessionInfo sessionInfo;
	
	private SessionParameters parameters;
	
	private ITrialPartitioner partitionProtocol;
	
	private ArrayList<Trial> trials;
	
	private ArrayList<Result> results;

	public Session(File sessionFile, SessionInfo sessionInfo, ITrialPartitioner partitionProtocol) {
		
		this.file = sessionFile;
		this.sessionInfo = sessionInfo;
		this.partitionProtocol = partitionProtocol;
		System.out.println("created session!");
		
	}
	
	
	public void generateTrials() {
		//Get List of events for trial
		ArrayList<Event> events = this.readEvents();
		
		this.parameters = this.partitionProtocol.getParameters(events);
		
		this.trials = this.partitionProtocol.partition(events);
		
		return;
	}
	
	public void generateResults(ITrialAnalyzer trialAnalyzer) {
		
		int trialcounter = 0;
		
		results = new ArrayList<Result>();
		
		System.out.println(sessionInfo.toString());
		
		for (Trial trial : trials) {
			results.add(trialAnalyzer.analyzeTrial(trial, trialcounter, sessionInfo));
			trialcounter++;
		}
	}
	
	
	private ArrayList<Event> readEvents() {
		
		//Initialize events array
		ArrayList<Event> events = new ArrayList<Event>();
		
		//Try opening file
		String line = "";
		BufferedReader br = null;
		
		//Read startup
		boolean eventsHaveStarted = false;
		
			
		try {
			br = new BufferedReader(new FileReader(file));
			
			//Per line loop....
			while ((line = br.readLine()) != null) {
				//Only Start reading if events have started
				if (!eventsHaveStarted) {
					if (line.contains("Item_Name")) {
						eventsHaveStarted = true;
						continue;
					}
					else {
						continue;
					}
				}
				
				//If events have started, convert each line into an event by using the csv format
				else {
					events.add(new Event(line));
				}
				
			}
		}catch (IOException e) {
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
		
		return events;
	}
	
	public SessionParameters getParameters() {
		return this.parameters;
	}
	
	

}
