package dataModels;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import analysisSets.IParameterReader;
import analysisSets.ITrialAnalyzer;
import analysisSets.ITrialPartitioner;
import touchscreenAnalyzer.FileReaderFactory;
import touchscreenAnalyzer.IFileReader;

public class Session {

	private File file;

	private SessionInfo sessionInfo;
	
	private IFileReader fileReader;

	private SessionParameters parameters;

	private Event[] events;

	private ArrayList<Trial> trials;

	private ArrayList<Result> results;

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

	public void setDay(int day) {
		this.sessionInfo.setDay(day);
	}
	
	public MetaData getMetaData() {
		return metaData;
	}
	
	public String getFileName() {
		return file.getName();
	}

}
