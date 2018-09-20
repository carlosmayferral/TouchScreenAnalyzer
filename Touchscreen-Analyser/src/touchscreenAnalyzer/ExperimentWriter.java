package touchscreenAnalyzer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import dataModels.Result;

/**
 * The experimentwriter writes results to a csv file each time the writeResults method is called.
 * @author Carlos May
 *
 */
public class ExperimentWriter {

	private String outputFileName;
	private int row;
	private PrintWriter writer;
	
	public ExperimentWriter(String outputFileName) {
		this.outputFileName = outputFileName;
		row = 0;
	}
	
	public void openFile() {
		
		try {
			writer = new PrintWriter(new File(outputFileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void writeResult (Result result) {
		if (row == 0) {
			writer.println(result.getHeader()); //Print Header
			writer.println(result.toString()); //And first trial
			row++;
		}
		else writer.println(result.toString());
		row++;
	}
	
	public void closeFile() {
		if (writer != null) {
			writer.close();
		}
	}
	
	
}
