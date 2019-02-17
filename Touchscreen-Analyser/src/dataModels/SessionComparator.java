package dataModels;

import java.util.Comparator;

public class SessionComparator implements Comparator<Session> {

	@Override
	public int compare(Session arg0, Session arg1) {
		//First sort by identifier
		if (arg0.getIdentifier().hashCode() > arg1.getIdentifier().hashCode()) {
			return 1;
		}
		else if (arg0.getIdentifier().hashCode() < arg1.getIdentifier().hashCode()) {
			return -1;
		}
		//If animal is the same sort on day
		else {
			if(arg0.getDate() > arg1.getDate()) {
				return 1;
			}
			else if(arg0.getDate() < arg1.getDate()) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}
	
	public int compareAnimalId(Session arg0, Session arg1) {
		if (arg0.getIdentifier().hashCode() > arg1.getIdentifier().hashCode()) {
			return 1;
		}
		else if (arg0.getIdentifier().hashCode() < arg1.getIdentifier().hashCode()) {
			return -1;
		}
		else return 0;
	}

}
