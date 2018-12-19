package touchscreenAnalyzer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import dataModels.SessionInfo;

class CSVExpressReaderTest {

	// File test cases
	// completely unrelated file
	File unrelatedFile = new File("testfiles/csvExpressReader/loretipsum.csv");

	// File with missing information (enough to make it non functional)
	File incompleteFile = new File("testfiles/csvExpressReader/SessionWithMissingInfo.csv");

	// Valid File 1
	File validFile1 = new File("testfiles/csvExpressReader/ValidSession.csv");
	String realSchedule = "PROGRESSIVE RATIO";
	int realChamber = 1;
	long realDate = 20180727;
	String realDatabase = "Julia";
	int realSessionId = 916;
	String realAnimalId = "1";

	// Valid File 2
	File validFile2 = new File("testfiles/csvExpressReader/ValidSession2.csv");
	String realSchedule2 = "Wonky Tonk";
	int realChamber2 = 8;
	long realDate2 = 20160122;
	String realDatabase2 = "Carlos3";
	int realSessionId2 = 1027;
	String realAnimalId2 = "a16";

	// Permanent fixture
	CSVExpressReader reader = new CSVExpressReader();

	@Test
	public void readFileReturnsNullOnRandomFile() {
		Assert.assertEquals(null, reader.readFile(unrelatedFile));
	}

	@Test
	public void readFileReturnsNullIfMissingCriticalInformation() {
		Assert.assertEquals(null, reader.readFile(incompleteFile));
	}

	@Test
	public void readFileReadsScheduleAsPRWhenScheduleIsPR() {
		SessionInfo info = reader.readFile(validFile1);
		Assert.assertEquals(realSchedule, info.getSchedule());
	}

	@Test
	public void readFileReadsScheduleAsWonkTonkWhenScheduleIsWonkyTonk() {
		SessionInfo info = reader.readFile(validFile2);
		Assert.assertEquals(this.realSchedule2, info.getSchedule());
	}

	@Test
	public void readFileReadsChamberAs1WhenChamberIs1() {
		SessionInfo info = reader.readFile(validFile1);
		Assert.assertEquals(this.realChamber, info.getChamber());
	}

	@Test
	public void readFileReadsChamberAs8WhenChamberIs8() {
		SessionInfo info = reader.readFile(validFile2);
		Assert.assertEquals(this.realChamber2, info.getChamber());
	}

	@Test
	public void readFileReadsCorrectDate() {
		SessionInfo info = reader.readFile(validFile1);
		Assert.assertEquals(realDate, info.getDate());
	}

	@Test
	public void readFileReadsCorrectDate2016() {
		SessionInfo info = reader.readFile(validFile2);
		Assert.assertEquals(realDate2, info.getDate());
	}

	@Test
	public void readFileReadsDatabaseAsJuliaWhenDatabaseIsJulia() {
		SessionInfo info = reader.readFile(validFile1);
		Assert.assertEquals(realDatabase, info.getDatabase());
	}

	@Test
	public void readFileReadsDatabaseAsCarlos3WhenDatabaseIsCarlos3() {
		SessionInfo info = reader.readFile(validFile2);
		Assert.assertEquals(realDatabase2, info.getDatabase());
	}

	@Test
	public void readFileReadsSessionIdAs916() {
		SessionInfo info = reader.readFile(validFile1);
		Assert.assertEquals(realSessionId, info.getSessionId());
	}

	@Test
	public void readFileReadsSessionIdAs1027WhenSessionIdIs1027() {
		SessionInfo info = reader.readFile(validFile2);
		Assert.assertEquals(realSessionId2, info.getSessionId());
	}

	@Test
	public void readFileReadsAnimalIdAs1WhenAnimalIdIs1() {
		SessionInfo info = reader.readFile(validFile1);
		Assert.assertEquals(realAnimalId, info.getAnimalId());
	}

	@Test
	public void readFileReadsAnimalIdAsA16WhenAnimalIdIsA16() {
		SessionInfo info = reader.readFile(validFile2);
		Assert.assertEquals(realAnimalId2, info.getAnimalId());
	}

}
