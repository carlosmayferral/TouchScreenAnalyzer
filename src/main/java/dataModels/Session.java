package dataModels;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import analysisSets.IParameterReader;
import analysisSets.ITrialAnalyzer;
import analysisSets.ITrialPartitioner;
import touchscreenAnalyzer.FileReaderFactory;
import touchscreenAnalyzer.IFileReader;

public class Session implements Comparable<Session> {

	private File file;

	private SessionInfo sessionInfo;
	
	private IFileReader fileReader;

	private SessionParameters parameters;

	private Event[] events;

	private ArrayList<Trial> trials;

	private ArrayList<Result> results;

	public SessionInfo getSessionInfo() {
		return sessionInfo;
	}

	private MetaData metaData;
	
	//Set default values
	public Session(File sessionFile) {
		this.file = sessionFile;
		this.fileReader = FileReaderFactory.getInstance().generateReader(this.file.getName());
		this.sessionInfo = null;
		this.metaData = null;
		this.parameters = null;
		this.events = null;
		this.trials = null;
		this.results = null;
	}
	
	//ReadInfo uses a fileReader to get basic session information
	public boolean readInfo() {
		if (fileReader == null) {
			return false;
		}
		else {
			sessionInfo = this.fileReader.expressRead(file);
			if (this.sessionInfo != null) return true;
			else return false;
		}
	}
	
	//Returns an identifier object used to map metadata
	public Identifier getIdentifier() {
		return new Identifier(sessionInfo.getAnimalId(), sessionInfo.getGroupId());
	}

	
	public boolean generateEventsFromFile() {
		events = this.fileReader.readEvents(file);
		if (this.events != null) {
			return true;
		} else // null events to be returned when there is no apparent trials
			return false;
	}

	public void generateTrialsfromEvents(ITrialPartitioner partitionProtocol) {

		this.trials = partitionProtocol.partition(events);
		
		if (trials.size()< 5) {
			System.out.println("Warning: session " + this.file.getName() 
			+ " has less than five trials, session might be accidental. Its last event is at timestamp " + this.events[this.events.length-1].getEvent_Time());
		}

		return;
	}

	public void generateParametersFromEvents(IParameterReader parameterReader) {

		if(!(parameterReader == null)) {
			this.parameters = parameterReader.readParameters(events);
		}

	}

	public void generateResults(ITrialAnalyzer trialAnalyzer) {

		int trialcounter = 0;

		results = new ArrayList<Result>();

		for (Trial trial : trials) {
			results.add(trialAnalyzer.analyzeTrial(trial, trialcounter, sessionInfo, metaData));
			trialcounter++;
		}
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

	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}
	
	public long getDate() {
		return this.sessionInfo.getDate();
	}
	
	public String getTime() {
		return this.sessionInfo.getTime();
	}

	public void setDay(int day) {
		this.sessionInfo.setDay(day);
	}
	
	public MetaData getMetaData() {
		return metaData;
	}
	
	public String getFileName() {
		return file.getName();
	}

	@Override
	public int compareTo(Session arg1) {
		//First sort by identifier
		if (this.getIdentifier().hashCode() > arg1.getIdentifier().hashCode()) {
			return 1;
		}
		else if (this.getIdentifier().hashCode() < arg1.getIdentifier().hashCode()) {
			return -1;
		}
		//If animal is the same sort on day
		else {
			if(this.getDate() > arg1.getDate()) {
				return 1;
			}
			else if(this.getDate() < arg1.getDate()) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}
	
	public int compareAnimalIdTo(Session arg1) {
		if (this.getIdentifier().hashCode() > arg1.getIdentifier().hashCode()) {
			return 1;
		}
		else if (this.getIdentifier().hashCode() < arg1.getIdentifier().hashCode()) {
			return -1;
		}
		else return 0;
		
	}

}
