package touchscreenAnalyzer;

import java.io.File;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.PriorityQueue;

import dataModels.SessionInfo;

class ExperimentReader {

	private File experimentFolder;

	private HashMap<File, SessionInfo> sessionInfoMap;

	private PriorityQueue<File> sessions;

	private Comparator<File> sessionComparator;

	public ExperimentReader() {

		this.experimentFolder = null;

		// Initialize info Map
		sessionInfoMap = new HashMap<File, SessionInfo>();

		// Initialize comparator
		sessionComparator = new Comparator<File>() {
			public int compare(File arg1, File arg2) {
				String animalId1 = sessionInfoMap.get(arg1).getAnimalId();
				String animalId2 = sessionInfoMap.get(arg2).getAnimalId();
				long date1 = sessionInfoMap.get(arg1).getDate();
				long date2 = sessionInfoMap.get(arg2).getDate();
				if (animalId1.equals(animalId2)) {
					if (date1 > date2) {
						return 1;
					} else if (date1 < date2) {
						return -1;
					} else {
						System.out.println("Two sessions on same date exist for animal ID " + animalId1 + " on "
								+ "date " + date1 + ", see session IDs: " + sessionInfoMap.get(arg1).getSessionId()
								+ " and " + sessionInfoMap.get(arg2).getSessionId());
						return 0;
					}
				} else {
					if (Integer.parseInt(animalId1.replaceAll("[^0-9]", "")) > Integer
							.parseInt(animalId2.replaceAll("[^0-9]", ""))) {
						return 1;
					} else {
						return -1;
					}
				}
			}
		};

		// Initialize sessions queue
		sessions = new PriorityQueue<File>(sessionComparator);

	}

	public void readExperimentFolder(File experimentFolder) {

		this.experimentFolder = experimentFolder;
		
		HashMap<String, String> groupMap = null;

		// Initialize express reader
		CSVExpressReader csvExpressReader = new CSVExpressReader();
		
		//Initialize meta reader
		MetaFileReader metaReader = new MetaFileReader();

		// Read obtain all files in directory
		File[] fileList = this.experimentFolder.listFiles();

		if (fileList == null) {
			System.out.println("No files were found in directory provided");
		}

		// For each File
		for (File file : fileList) {
			System.out.println("Checking integrity of file : " + file.getAbsolutePath());

			// Interrupt if file contains the word results
			if (file.getName().equals("results.csv")) {
				continue;
			}
			// CSV expressRead if it is a csv file
			if (file.getName().contains(".csv")) {
				SessionInfo info = csvExpressReader.readFile(file);
				if (info != null) {
					this.sessionInfoMap.put(file, csvExpressReader.readFile(file));
					sessions.add(file);
				} else {
					System.out.println("Format of csv file " + file.getName() + " not recognized, skipping file");
				}
			}
			// XML expressRead if it is an XML file
			// TODO
			
			// Record group data if its a meta file
			else if (file.getName().contains(".meta")) {
				groupMap = new HashMap<String, String>();
				metaReader.read(file, groupMap);
			}
		}
		
		//Assign groups to sessions
		if (groupMap == null) {
			System.out.println("No group information was provided in a .meta file,"
					+ " group column will be null");
		}else this.assignGroupsToAnimals(groupMap);

		// Assign correct ordinal days in session info structure
		this.assignOrdinalDays();

	}

	private void assignGroupsToAnimals(HashMap<String, String> groupInfoMap) {
		
		if(groupInfoMap != null) {
			Iterator<SessionInfo> iterator = this.sessionInfoMap.values().iterator();
			
			while (iterator.hasNext()) {
				SessionInfo info = iterator.next();
				if (groupInfoMap.get(info.getAnimalId()) == null) {
					System.out.println("No group information provided for animal ID: " + info.getAnimalId());
					continue;
				}
				else {
					info.setGroupId(groupInfoMap.get(info.getAnimalId()));
				}
			}
			
		}
		
	}

	private void assignOrdinalDays() {
		if (sessions.size() > 0) {
			File[] tempsessions = new File[sessions.size()];

			String currentAnimal = null;
			int currentDay = 0;

			// Read all of queue into file array to set days
			for (int i = 0; i < tempsessions.length; i++) {
				tempsessions[i] = sessions.poll();
				// If animal Id is new
				if (currentAnimal == null || !currentAnimal.equals(sessionInfoMap.get(tempsessions[i]).getAnimalId())) {
					currentDay = 0;
					currentAnimal = sessionInfoMap.get(tempsessions[i]).getAnimalId();
				}
				// Else increment day
				else {
					currentDay++;
				}
				// Set day
				sessionInfoMap.get(tempsessions[i]).setDay(currentDay);
			}

			// Copy file array back into priority queue
			for (int i = 0; i < tempsessions.length; i++) {
				sessions.add(tempsessions[i]);
			}

		}
	}

	public int getNumberOfSessions() {
		return this.sessions.size();
	}

	public SessionInfo getSessionInfo(File file) {
		if (sessionInfoMap.size() > 0) {
			return sessionInfoMap.get(file);
		} else
			return null;
	}

	public File getNextSession() {
		if (sessions.size() > 0) {
			return sessions.poll();
		} else {
			return null;
		}
	}
}
