package touchscreenAnalyzer;

import java.util.ArrayList;

public interface ITrialPartitioner {

	public ArrayList<Trial> partition (ArrayList <Event> events);
	
	public SessionParameters getParameters(ArrayList <Event> events);

}
