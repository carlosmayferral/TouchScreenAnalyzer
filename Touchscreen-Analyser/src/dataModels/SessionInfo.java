package dataModels;

import java.io.File;

public class SessionInfo {
	
	private String schedule;
	
	private int chamber;
	
	private long date;
	
	private String database;
	
	private int sessionId;
	
	private String animalId;
	
	private String groupId;
	
	private String notes;
	
	private File file;
	
	private final String header = "Animal_Id,Group_Id,Date,Database,Chamber,Session_Id,";

	public SessionInfo(
			String schedule, 
			int chamber, 
			long date, 
			String database,
			int sessionId,
			String animalId, 
			String groupId,
			String notes,
			File file) {
		this.schedule = schedule;
		this.chamber = chamber;
		this.date = date;
		this.database = database;
		this.sessionId = sessionId;
		this.animalId = animalId;
		this.groupId = groupId;
		this.notes = notes;
		this.file = file;
	}

	/**
	 * @return the schedule
	 */
	public String getSchedule() {
		return schedule;
	}

	/**
	 * @return the chamber
	 */
	public int getChamber() {
		return chamber;
	}

	/**
	 * @return the date
	 */
	public long getDate() {
		return date;
	}

	/**
	 * @return the database
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * @return the sessionId
	 */
	public int getSessionId() {
		return sessionId;
	}

	/**
	 * @return the animalId
	 */
	public String getAnimalId() {
		return animalId;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	public String toString() {
		return this.schedule + "," + animalId + "," + groupId + "," + date + "," + database + "," + chamber + "," + sessionId + ',';
	}

	public String getHeader() {
		return header;
	}
}

