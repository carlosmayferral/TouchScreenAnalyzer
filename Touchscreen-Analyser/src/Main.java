import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Console;
import java.util.Scanner;

import analysisSets.AnalysisType;

import java.awt.*;

import touchscreenAnalyzer.AnalyzerController;

/**
 * The Main class is a controller pattern for the user interface.
 * @author Carlos May
 *
 */
public class Main{

	public static void main(String[] args){
		Console console = System.console();
        start(new String[0]);
        System.out.println("Program has ended, please type 'exit' to close the console");
		
	}

	private static void start(String[] strings) {
			Scanner sc = new Scanner(System.in);
				
			System.out.println("Touchscreen Analyzer.");
			System.out.println("Please enter the full file path where the raw data is contained:");
			
			String fileName = sc.nextLine();
			
			System.out.println("Please select one of the following analysis types by entering a number:");
			
			int i =0;
			for(AnalysisType type : AnalysisType.values()) {
				System.out.println(i + " - " + type.name());
				i++;
			
			}
			AnalysisType chosenType = AnalysisType.values()[sc.nextInt()];

			//Perform analysis, keep as instantiation in case multiple analyses need to be done in the future.
			
			AnalyzerController analysisEngine = new AnalyzerController(fileName, chosenType);
			analysisEngine.analyze();
}
}
