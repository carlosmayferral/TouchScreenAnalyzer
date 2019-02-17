package touchscreenAnalyzer;

import java.io.File;

import dataModels.Event;
import dataModels.SessionInfo;

public interface IFileReader {
	
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
		String day = dateString[0];
		return Long.parseLong(year + month + day);
	}

	
}
