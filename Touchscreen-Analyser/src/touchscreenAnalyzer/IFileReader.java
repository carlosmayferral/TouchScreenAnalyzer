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
		String[] dateString = date.split(" ")[0].split("/");
		String year = dateString[2];
		String month = dateString[1];
		//padding of month
		if(month.length() == 1) {
			month = "0" + month;
		}
		String day = dateString[0];
		if(day.length() == 1) {
			day = "0" + day;
		}
		return Long.parseLong(year + month + day);
	}
	
	public static String getTimeFromDateString(String dateString) {
		String[] timeStringArray = dateString.split("[\\s\\p{Z}]+")[1].split(":");
		int hour = Integer.parseInt(timeStringArray[0]);
		String minute = timeStringArray[1];
		if (dateString.toLowerCase().contains("p.m.")) {
			if (hour != 12) {
				hour += 12;
			}
		}
		else {
			if (hour == 12) {
				hour = 0;
			}
		}
		return hour + ":" + minute;
	}

	
}
