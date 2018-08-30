import touchscreenAnalyzer.AnalyzerController;

/**
 * The Main class acts as a placeholder for information sent from a user interface into the analyzer package.
 * @author Carlos May
 *
 */
public class Main {
	
	public static void main(String[] args) {
		//DEBUG: for now args are replaces by assignment
		args = new String[2];
		args[0] = "C:\\Users\\Carlos May\\eclipse-workspace\\Touchscreen-Analyser\\Example_Data";
		args[1] = "CPT";
		
		//send information into the Analyzer Package
		AnalyzerController.analyze(args);
	}

}
