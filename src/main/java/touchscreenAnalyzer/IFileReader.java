package touchscreenAnalyzer;

import java.io.File;

import dataModels.Event;
import dataModels.SessionInfo;

public interface IFileReader {
	
	public static float MIN_TIME_ELAPSED = 180;
	
	public SessionInfo expressRead(File file);
	
	public Event[] readEvents(File file);
	
	public static int getNumericalChamber(String chamber) {
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

	
	public static long getNumericalDate(String date) {
		
		boolean isScheduleStartTime = false;
		
		
		//determine what kind of date was passed
		if (date.contains("T")) isScheduleStartTime = true;
		
		//handling schedule start time
		if (isScheduleStartTime) {
			date = date.split("T")[0];
			String[] dateString = date.split("-");
			String year = dateString[0];
			String month = dateString[1];
			//padding of month
			if(month.length() == 1) {
				month = "0" + month;
			}
			String day = dateString[2];
			if(day.length() == 1) {
				day = "0" + day;
			}
			return Long.parseLong(year + month + day);
		}
		//handling date time
		else {
			date = date.split(" ")[0];
			String[] dateArray = date.split("/");
			String year = dateArray[2];
			String month = dateArray[1];
			//padding of month
			if(month.length() == 1) {
				month = "0" + month;
			}
			String day = dateArray[0];
			if(day.length() == 1) {
				day = "0" + day;
			}
			return Long.parseLong(year + month + day);
		}
	}
	
	public static String getTimeFromDateString(String dateString) {
		
		boolean isScheduleStartTime = false;
		
		//determine what kind of date was passed
		if (dateString.contains("T")) isScheduleStartTime = true;
		
		//handle schedule start time
		if(isScheduleStartTime) {
			dateString = dateString.split("T")[1];
			String[] timeStringArray = dateString.split(":");
			int hour = Integer.parseInt(timeStringArray[0]);
			String minute = timeStringArray[1];
			if (dateString.toLowerCase().contains("p")) {
				if (hour != 12) {
					hour += 12;
				}
			}
			else if (dateString.toLowerCase().contains("a")) {
				if (hour == 12) {
					hour = 0;
				}
			}
			return hour + ":" + minute;
		}
		//handle date / Time
		else {
			dateString = dateString.split(" ")[1];
			String[] timeStringArray = dateString.split(":");
			int hour = Integer.parseInt(timeStringArray[0]);
			String minute = timeStringArray[1];
			return hour + ":" + minute;
		}
	}

	
}
