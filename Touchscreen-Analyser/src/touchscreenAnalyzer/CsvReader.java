package touchscreenAnalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import dataModels.Event;
import dataModels.SessionInfo;

public class CsvReader implements IFileReader {

	@Override
	public SessionInfo expressRead(File file) {

		// Prepare default variables for session info generation
		String schedule = "null";
		String environment = "null";
		String date = "null";
		String database = "null";
		int sessionId = -1;
		String animalId = "null";
		String groupId = null;
		String user = null;
		Float weight = Float.NaN;
		String notes = "";

		// Try opening file
		String line = "";
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(file));

			if (!br.readLine().contains("Name")) {
				return null;
			}

			// Per line loop.... sometimes quotation marks must be removed, for certain csv formats
			while ((line = br.readLine()) != null) {
				// If line is empty
				if ((!line.equals("")) && line.charAt(0) == ',') {
					continue;
				}

				else if (line.split(",")[0].equals("Schedule Name") || line.split(",")[0].equals("Schedule")) {
					schedule = line.split(",")[1];
				} else if (line.split(",")[0].equals("Environment")) {
					environment = line.split(",")[1];
				} else if (line.split(",")[0].equals("Date/Time")) {
					date = line.split(",")[1];
				} else if (line.split(",")[0].equals("Database")) {
					database = line.split(",")[1];
				} else if (line.split(",")[0].equals("Schedule Run ID") || line.split(",")[0].equals("SessionId")) {
					sessionId = Integer.parseInt(line.split(",")[1]);
				} else if (line.split(",")[0].equals("Animal ID")) {
					animalId = line.split(",")[1];
					if (animalId.equals("")) {
						System.out.println("oops");
					}
				} else if (line.split(",")[0].equals("Group ID")) {
					groupId = line.split(",")[1];
				} else if (line.split(",")[0].equals("Notes")) {
					notes = line.split(",")[1];
				}

				else if (line.contains("----")) {
					if (date.equals("null") || animalId.equals("null")) {
						return null;
					}
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
				IFileReader.getNumericalChamber(environment), 
				IFileReader.getNumericalDate(date),
				database, 
				sessionId, 
				animalId, 
				groupId, 
				user,
				weight,
				notes, 
				file);

		return newInfo;
	}

	@Override
	public Event[] readEvents(File file) {
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
		if (events.size() > 0 && (events.get(events.size() -1).getEvent_Time() > IFileReader.MIN_TIME_ELAPSED)) {
			Event[] eventsCopy = new Event[events.size()];
			return (Event[]) events.toArray(eventsCopy);
		} else {
			return null;
		}
	}

}
