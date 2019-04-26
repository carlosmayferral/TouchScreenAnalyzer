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
	
	public static String getTimeFromDateString(String dateString) {
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

	
}
