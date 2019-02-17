package touchscreenAnalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import dataModels.Event;
import dataModels.SessionInfo;

public class XmlReader implements IFileReader {

	@Override
	public SessionInfo expressRead(File file) {
		// Prepare default variables for session info generation
		String schedule = "null";
		String environment = "null";
		String date = "null";
		String database = "null";
		int sessionId = -1;
		String animalId = "null";
		String groupId = "null";
		String notes = "";

		// Try opening file
		String line = "";
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(file));
			
			//Reading of xml file commences
			
		}
		catch (IOException e) {
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
				IFileReader.getNumericalChamber(environment), 
				IFileReader.getNumericalDate(date),
				database, 
				sessionId, 
				animalId, 
				groupId, 
				notes, 
				file);

		return newInfo;
	}
	
	

	@Override
	public Event[] readEvents(File file) {
		// TODO Auto-generated method stub
		return null;
	}

	public static IFileReader getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

}
