package touchscreenAnalyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import dataModels.Identifier;
import dataModels.MetaData;
import dataModels.Session;

class ExperimentReader {

	private File experimentFolder;

	private HashMap<Identifier, MetaData> metadataMap;

	public ExperimentReader() {

		this.experimentFolder = null;

		// Initialize metadata Map
		metadataMap = new HashMap<Identifier, MetaData>();
	}
	
	

	public ArrayList<Session> readExperimentFolder(File experimentFolder) {

		//Initialize session list
		ArrayList<Session> sessions = new ArrayList<Session>();
		
		this.experimentFolder = experimentFolder;

		// obtain all files in directory
		File[] fileList = this.experimentFolder.listFiles();

		//Error if no files present...
		if (fileList == null) {
			return sessions;
		}
		
		System.out.println("Checking integrity of files");

		// For each File...
		for (File file : fileList) {

			// Interrupt if file contains the word results
			if (file.getName().equals("results.csv")) {
				continue;
			}
			// If file is a meta file, use it to fill metaData dictionary
			else if (file.getName().contains(".meta")){
				MetaFileReader metaReader = new MetaFileReader();
				metaReader.read(file, this.metadataMap);
			}
			// Else attempt creating a session
			else {
				Session session = new Session(file);
				if (session.readInfo() && session.generateEventsFromFile()) {
					//Furthermore, check if there is enough time recorded
					if (session.getEventArrayCopy()[session.getEventArrayCopy().length-1].getEvent_Time() > IFileReader.MIN_TIME_ELAPSED) {
						//return a version of the session without events, otherwise memory will be overloaded
						Session toBeReturned = new Session(file);
						toBeReturned.readInfo();
						sessions.add(toBeReturned);
					}
					else {
						System.out.println("Not enough time recorded on " + file.getName() + ", ignoring... (less than 3 minutes worth of data)");
					}
				}
				else {
					System.out.println("Error reading events from file " + file.getName());
				}
			}
		}
		
		//Link each session to some metadata
		if (metadataMap.size() == 0) {
			System.out.println("No metadata information was provided in a .meta file,"
					+ " no metadata will be appended");
		}else {
			System.out.println("Assigning metadata...");
			this.assignMetaDataToAnimals(sessions);
		}
		
		return sessions;
	}

	private void assignMetaDataToAnimals(ArrayList<Session> sessions) {
		
		//for each session in sessions, link the session with its meta data
		for (Session session : sessions) {
			if (metadataMap.get(session.getIdentifier()) == null) {
				System.out.println("No metadata was provided for animal " + session.getIdentifier().getAnimalId() + " of group "
						+ session.getIdentifier().getGroupId() + ". See file " + session.getFileName());
				System.out.println("Assigning empty metadata...");
				
				//create new empty metadata and assign to session
				MetaData emptyMetaData = new MetaData();
				session.setMetaData(emptyMetaData);
			}
			else {
				//metadata exists
				session.setMetaData(metadataMap.get(session.getIdentifier()));
			}
		}
		
	}
}
