package touchscreenAnalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

import dataModels.SessionInfo;

class ExperimentReader {
	
	private File experimentFolder;
	
	private HashMap<File, SessionInfo> sessionInfoMap;
	
	private HashMap<String, String> groupInfoMap;
	
	private PriorityQueue<File> sessions;
	
	private Comparator<File> sessionComparator;

	public ExperimentReader(File experimentFolder) {
		this.experimentFolder = experimentFolder;
		
		//Initialize info Map
		sessionInfoMap = new HashMap<File, SessionInfo>();
		
		System.out.println(experimentFolder.getAbsolutePath());
		
		//Initialize comparator
		sessionComparator = new Comparator<File>(){
			public int compare(File arg1, File arg2) {
				String animalId1 = sessionInfoMap.get(arg1).getAnimalId();
				String animalId2 = sessionInfoMap.get(arg2).getAnimalId();
				long date1 = sessionInfoMap.get(arg1).getDate();
				long date2 = sessionInfoMap.get(arg2).getDate();
				if (animalId1.equals(animalId2)){
					if (date1 > date2) {
						return 1;
					}
					else if (date1 < date2) {
						return -1;
					}
					else {
						System.out.println("Two sessions exist for animal ID " + animalId1 + ", see session IDs :"
								+ sessionInfoMap.get(arg1).getSessionId() + 
								" and " + sessionInfoMap.get(arg2).getSessionId());
						return 0;
					}
				}
				else {
					if (Integer.parseInt(animalId1.replaceAll("[^0-9]", "")) > Integer.parseInt(animalId2.replaceAll("[^0-9]", ""))) {
						return 1;
					}
					else {
						return -1;
					}
				}
			}
		};
		
		//initialize comparator
		sessions = new PriorityQueue<File>(sessionComparator);
				
		
		//Read obtain all files in directory
		File[] fileList = this.experimentFolder.listFiles();
		
		//For each File
		for (File file : fileList) {
			//Simple read if its a csv
			if (file.getName().contains(".csv")){
				simpleRead(file);
				sessions.add(file);
			}
			//Record group data if its a meta file
			else if (file.getName().contains(".meta")) {
				metaRead(file);
			}
		}
		
		
	}

	private void metaRead(File file) {
		
		// TODO generate method for reading meta files
		
	}

	private void simpleRead(File file) {
		
		//Prepare default variables for session info generation
		String schedule = "null";
		String environment = "null";
		String date = "null";
		String database = "null";
		int sessionId = -1;
		String animalId = "null";
		String groupId = "null";
		String notes = "";
		
		//Try opening file
		String line = "";
		BufferedReader br = null;
		
		 try {
			br = new BufferedReader(new FileReader(file));
			
			//Per line loop....
			while ((line = br.readLine()) != null) {
				if (line.split(",")[0].equals("Schedule Name")||
						line.split(",")[0].equals("Schedule")){
					schedule = line.split(",")[1];
				}
				else if (line.split(",")[0].equals("Environment")){
					environment = line.split(",")[1];
				}
				else if (line.split(",")[0].equals("Date/Time")){
					date = line.split(",")[1];
				}
				else if (line.split(",")[0].equals("Database")){
					database = line.split(",")[1];
				}
				else if (line.split(",")[0].equals("Schedule Run ID") ||
						line.split(",")[0].equals("SessionId")){
					sessionId = Integer.parseInt(line.split(",")[1]);
				}
				else if (line.split(",")[0].equals("Animal ID")){
					animalId = line.split(",")[1];
				}
				else if (line.split(",")[0].equals("Group ID")){
					groupId = line.split(",")[1];
				}
				else if (line.split(",")[0].equals("Notes")){
					notes = line.split(",")[1];
				}
				
				else if (line.contains("----")){
					break;
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
	
		// Create new session info object
	    SessionInfo newInfo = new SessionInfo(
	    		schedule,
	    		getNumericalChamber(environment),
	    		getNumericalDate(date),
	    		database,
	    		sessionId,
	    		animalId,
	    		groupId,
	    		notes,
	    		file
	    		) ;  
	    
	    
	    //Associate the sessionInfo to a file
	    this.sessionInfoMap.put(file, newInfo);
	}

	
	private int getNumericalChamber(String chamber) {
		return Integer.parseInt(chamber.replaceAll("\\[", "z").replaceAll("\\]","z").split("z")[1]);
	}
	
	private long getNumericalDate(String date) {
		String [] dateString = date.split(" ")[0].split("/");
		String year = dateString[2];
		String month = dateString[1];
		String day =dateString[0];
		return Long.parseLong(year+month+day);
	}

	public SessionInfo getSessionInfo(File file) {
		return sessionInfoMap.get(file);
	}
	
	public String getGroupInfo(String animalId) {
		return this.groupInfoMap.get(animalId);
	}
	
	public File getNextSession(){
		if (sessions.size() > 0) {
			return sessions.poll();
		}
		else {
			return null;
		}
	}
}
