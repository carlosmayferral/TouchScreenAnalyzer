package touchscreenAnalyzer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
		String groupId = null;
		String user = null;
		Float weight = Float.NaN;
		String notes = "";

		//For when variables start
		boolean variablesStarted = false;
		
		// Try opening file
		String line = "";
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(file));
			
			//Reading of xml file commences...
			if(!br.readLine().contains("xml version")) {
				return null;
			}
			
			//Skip lines until session data is encountered
			while (!br.readLine().contains("<SessionData>")) {
				continue;
			}
			
			//Read sequentially
			while((line = br.readLine()) != null) {
				
				if (line.contains("<Name>") && !variablesStarted) {
					schedule = detag(line);
				}
				else if (line.contains("<EnvironmentName>")) {
					environment = detag(line);
				}
				else if (line.contains("<Date>")) {
					date = detag(line);
				}
				else if (line.contains("<DataBaseName>")) {
					database = detag(line);
				}
				else if (line.contains("<SessionId>")) {
					sessionId = Integer.parseInt(detag(line));
				}
				else if (line.contains("<SessionVariables>")) {
					variablesStarted = true;
				}
				else if (line.contains("Animal ID")) {
					animalId = detag(br.readLine());
				}
				else if (line.contains("User")) {
					user = detag(br.readLine());
				}
				else if (line.contains("Weight")) {
					weight = Float.parseFloat(detag(br.readLine()));
				}
				else if (line.contains(("</SessionData>"))){
					break;
				}
			}
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
				IFileReader.getNumericalTimeFromDateString(date),
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
	
	

	private String detag(String line) {
		
		int indexOfFirstClosure = line.indexOf('>');
		int indexOfSecondOpening = line.indexOf('<', indexOfFirstClosure);
		
		String substring = line.substring(indexOfFirstClosure + 1, indexOfSecondOpening);
		
		return substring;
	}

	@Override
	public Event[] readEvents(File file) {
		
		//For reading events in xml mode, i'll parse into a csv string and then use the csv contructor
		
		// Initialize events array
		ArrayList<Event> events = new ArrayList<Event>();

		// Try opening file
		String line = "";
		BufferedReader br = null;
		
		//Initialize event structure
		float eventTime = Float.NaN;
		int eventId = -1;
		String eventName = null;
		String itemName = null;
		String aliasName = null;
		int groupId = -1;
		int numberOfArgs = 0;
		String arguments = "";

		// Read startup
		boolean eventsHaveStarted = false;

		try {
			br = new BufferedReader(new FileReader(file));

			// Per line loop....
			while ((line = br.readLine()) != null) {
				// Only Start reading if events have started
				if (!eventsHaveStarted) {
					if (line.contains("<DataEntries>")) {
						eventsHaveStarted = true;
						continue;
					} else {
						continue;
					}
				}

				// Otherwise, if events have started...
				else {
					
					if (line.contains("<Time>")) {
						eventTime = Float.parseFloat(detag(line));
					}
					else if (line.contains("<EventId>")) {
						eventId = Integer.parseInt(detag(line));
					}
					else if (line.contains("<EventText>")) {
						eventName = detag(line);
					}
					else if (line.contains("<EffectText>")) {
						itemName = detag(line);
					}
					else if (line.contains("<AltText>")) {
						aliasName = detag(line);
					}
					else if (line.contains("<Group>")) {
						groupId = Integer.parseInt(detag(line));
					}
					else if (line.contains("<Name>")) {
						numberOfArgs++;
						arguments += "," + detag(line) + ",";
					}
					else if (line.contains("<Value>")) {
						arguments += detag(line);
					}
					//create event, reset variables
					else if (line.contains("</DataEntry>")) {
						String eventLine =
										eventTime + "," +
										eventId + "," +
										eventName + "," +
										itemName + "," +
										aliasName + ","	+
										groupId + "," +
										numberOfArgs + 
										arguments;
						Event newEvent = new Event(eventLine);
						//System.out.println(newEvent.toString());
						events.add(newEvent);
						//reset variables
						eventTime = Float.NaN;
						eventId = -1;
						eventName = null;
						itemName = null;
						aliasName = null;
						groupId = -1;
						numberOfArgs = 0;
						arguments = "";
					}
					
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

}
