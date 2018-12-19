package touchscreenAnalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import dataModels.SessionInfo;

public class CSVExpressReader implements IExpressReader {

	public SessionInfo readFile(File file) {

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

			if (!br.readLine().contains("Name")) {
				return null;
			}

			// Per line loop....
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
		SessionInfo newInfo = new SessionInfo(schedule, getNumericalChamber(environment, file), getNumericalDate(date),
				database, sessionId, animalId, groupId, notes, file);

		return newInfo;
	}

	private int getNumericalChamber(String chamber, File file) {
		int numericalChamber = 0;
		try {
			numericalChamber = Integer.parseInt(chamber.replaceAll("\\[", "z").replaceAll("\\]", "z").split("z")[1]);
		} catch (Exception e) {
			System.out.print("Error reading file");
			e.printStackTrace();
			System.exit(-1);
		}
		return numericalChamber;
	}

	private long getNumericalDate(String date) {
		String[] dateString = date.split(" ")[0].split("/");
		String year = dateString[2];
		String month = dateString[0];
		String day = dateString[1];
		return Long.parseLong(year + day + month);
	}

}
