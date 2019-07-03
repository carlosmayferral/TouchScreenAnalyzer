package touchscreenAnalyzer;


import java.io.File;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataModels.Event;
import dataModels.SessionInfo;

/**
 * The responsibility of the CSV reader is to parse session information using express read and
 *  read file data into an array of events when file has a CSV format. Parsing can be tested here.
 * @author Carlos May
 *
 */
class CsvReaderTest {
	
	CsvReader reader;
	
	// File test cases
	// completely unrelated file
	static File unrelatedFile;

	// File with missing information (enough to make it non functional)
	static File incompleteFile;

	// Valid File 1
	File validFile1;
	String realSchedule = "PROGRESSIVE RATIO";
	int realChamber = 1;
	long realDate = 20180727;
	String realTime = "10:06";
	String realDatabase = "Julia";
	int realSessionId = 916;
	String realAnimalId = "1";

	// Valid File 2
	File validFile2;
	String realSchedule2 = "Wonky Tonk";
	int realChamber2 = 8;
	long realDate2 = 20180727;
	String realTime2 = "10:06";
	String realDatabase2 = "Carlos3";
	int realSessionId2 = 1027;
	String realAnimalId2 = "a16";

	@BeforeEach
	void setUp() throws Exception {
		reader = new CsvReader();
		unrelatedFile = new File("testfiles/csvExpressReader/loretipsum.csv");
		incompleteFile = new File("testfiles/csvExpressReader/SessionWithMissingInfo.csv");
		validFile1 = new File("testfiles/csvExpressReader/ValidSession.csv");
		validFile2 = new File("testfiles/csvExpressReader/ValidSession2.csv");
	}

	
	@Test
	public void expressReadReturnsNullOnRandomFile() {
		Assert.assertEquals(null, reader.expressRead(unrelatedFile));
	}

	@Test
	public void expressReadReturnsNullIfMissingCriticalInformation() {
		Assert.assertEquals(null, reader.expressRead(incompleteFile));
	}

	@Test
	public void expressReadReadsScheduleAsPRWhenScheduleIsPR() {
		SessionInfo info = reader.expressRead(validFile1);
		Assert.assertEquals(realSchedule, info.getSchedule());
	}

	@Test
	public void expressReadReadsScheduleAsWonkTonkWhenScheduleIsWonkyTonk() {
		SessionInfo info = reader.expressRead(validFile2);
		Assert.assertEquals(this.realSchedule2, info.getSchedule());
	}

	@Test
	public void expressReadReadsChamberAs1WhenChamberIs1() {
		SessionInfo info = reader.expressRead(validFile1);
		Assert.assertEquals(this.realChamber, info.getChamber());
	}

	@Test
	public void expressReadReadsChamberAs8WhenChamberIs8() {
		SessionInfo info = reader.expressRead(validFile2);
		Assert.assertEquals(this.realChamber2, info.getChamber());
	}

	@Test
	public void expressReadReadsCorrectDate() {
		SessionInfo info = reader.expressRead(validFile1);
		Assert.assertEquals(realDate, info.getDate());
	}
	
	@Test
	public void expressReadReadsCorrectTime1() {
		SessionInfo info = reader.expressRead(validFile1);
		Assert.assertEquals(realTime, info.getTime());
	}

	@Test
	public void expressReadReadsCorrectDate2016() {
		SessionInfo info = reader.expressRead(validFile2);
		Assert.assertEquals(realDate2, info.getDate());
	}
	
	@Test
	public void expressReadReadsCorrectTime2() {
		SessionInfo info = reader.expressRead(validFile2);
		Assert.assertEquals(realTime2, info.getTime());
	}

	@Test
	public void expressReadReadsDatabaseAsJuliaWhenDatabaseIsJulia() {
		SessionInfo info = reader.expressRead(validFile1);
		Assert.assertEquals(realDatabase, info.getDatabase());
	}

	@Test
	public void expressReadReadsDatabaseAsCarlos3WhenDatabaseIsCarlos3() {
		SessionInfo info = reader.expressRead(validFile2);
		Assert.assertEquals(realDatabase2, info.getDatabase());
	}

	@Test
	public void expressReadReadsSessionIdAs916() {
		SessionInfo info = reader.expressRead(validFile1);
		Assert.assertEquals(realSessionId, info.getSessionId());
	}

	@Test
	public void expressReadReadsSessionIdAs1027WhenSessionIdIs1027() {
		SessionInfo info = reader.expressRead(validFile2);
		Assert.assertEquals(realSessionId2, info.getSessionId());
	}

	@Test
	public void expressReadReadsAnimalIdAs1WhenAnimalIdIs1() {
		SessionInfo info = reader.expressRead(validFile1);
		Assert.assertEquals(realAnimalId, info.getAnimalId());
	}

	@Test
	public void expressReadReadsAnimalIdAsA16WhenAnimalIdIsA16() {
		SessionInfo info = reader.expressRead(validFile2);
		Assert.assertEquals(realAnimalId2, info.getAnimalId());
	}
	
	//Test methods for event Reading
	
	@Test
	public void readEventsReturnsCorrectNumberOfEventsForValidFile1() {
		Event[] events = reader.readEvents(validFile1);
		Assert.assertEquals(9161, events.length);
	}

}
