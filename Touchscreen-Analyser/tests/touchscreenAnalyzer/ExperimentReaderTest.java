package touchscreenAnalyzer;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import org.junit.Assert;

class ExperimentReaderTest {

	private File experimentFolder1 = new File("testfiles/ExperimentReader/experiment1");
	private File experimentFolder2 = new File("testfiles/ExperimentReader/experiment2");
	private File emptyFolder = new File("testfiles/ExperimentReader/experiment3");

	@Test
	void testExperimentReader() {
		ExperimentReader eReader = new ExperimentReader();
		if (eReader != null)
			return;
	}

	@Test
	void readExperimentDetectsCorrectAmountOfSessionsInFolder1() {
		ExperimentReader eReader = new ExperimentReader();
		eReader.readExperimentFolder(experimentFolder1);
		Assert.assertEquals((int) 8, eReader.getNumberOfSessions());
	}

	@Test
	void readExperimentDetectsCorrectAmountOfSessionsInFolder2() {
		ExperimentReader eReader = new ExperimentReader();
		eReader.readExperimentFolder(experimentFolder2);
		Assert.assertEquals((int) 6, eReader.getNumberOfSessions());
	}

	@Test
	void getSessionInfoGetsCorrectSessionInformation() {
		ExperimentReader eReader = new ExperimentReader();
		eReader.readExperimentFolder(experimentFolder1);
		Assert.assertEquals(916, eReader.getSessionInfo(eReader.getNextSession()).getSessionId());
	}

	@Test
	void getSessionReturnsSessionsInCorrectOrder() {
		ExperimentReader eReader = new ExperimentReader();
		eReader.readExperimentFolder(experimentFolder2);
		Assert.assertEquals(617, eReader.getSessionInfo(eReader.getNextSession()).getSessionId());
		Assert.assertEquals(618, eReader.getSessionInfo(eReader.getNextSession()).getSessionId());
		Assert.assertEquals(623, eReader.getSessionInfo(eReader.getNextSession()).getSessionId());
		Assert.assertEquals(619, eReader.getSessionInfo(eReader.getNextSession()).getSessionId());
		Assert.assertEquals(624, eReader.getSessionInfo(eReader.getNextSession()).getSessionId());
		Assert.assertEquals(620, eReader.getSessionInfo(eReader.getNextSession()).getSessionId());
	}

	@Test
	void getSessionAssignsOrdinalDaysCorrectly() {
		ExperimentReader eReader = new ExperimentReader();
		eReader.readExperimentFolder(experimentFolder2);
		File session = eReader.getNextSession();
		Assert.assertEquals(617, eReader.getSessionInfo(session).getSessionId());
		Assert.assertEquals(0, eReader.getSessionInfo(session).getOrdinalDay());
		session = eReader.getNextSession();
		Assert.assertEquals(618, eReader.getSessionInfo(session).getSessionId());
		Assert.assertEquals(1, eReader.getSessionInfo(session).getOrdinalDay());
		session = eReader.getNextSession();
		Assert.assertEquals(623, eReader.getSessionInfo(session).getSessionId());
		Assert.assertEquals(2, eReader.getSessionInfo(session).getOrdinalDay());
		session = eReader.getNextSession();
		Assert.assertEquals(619, eReader.getSessionInfo(session).getSessionId());
		Assert.assertEquals(0, eReader.getSessionInfo(session).getOrdinalDay());
		session = eReader.getNextSession();
		Assert.assertEquals(624, eReader.getSessionInfo(session).getSessionId());
		Assert.assertEquals(1, eReader.getSessionInfo(session).getOrdinalDay());
		session = eReader.getNextSession();
		Assert.assertEquals(620, eReader.getSessionInfo(session).getSessionId());
		Assert.assertEquals(2, eReader.getSessionInfo(session).getOrdinalDay());
	}

	@Test
	void getSessionReturnsNullWhenThereIsNoSessions() {
		ExperimentReader eReader = new ExperimentReader();
		eReader.readExperimentFolder(emptyFolder);
		Assert.assertEquals(null, eReader.getNextSession());
	}

	@Test
	void getSessionInfoReturnsNullWhenThereIsNoSuchInfo() {
		ExperimentReader eReader = new ExperimentReader();
		eReader.readExperimentFolder(experimentFolder1);
		Assert.assertEquals(null, eReader.getSessionInfo(new File(
				"testfiles/ExperimentReader/experiment1/KMBD-03541-W_Julia_Mouse ICPT Stage 4 30% (S+)  HORIZONTAL _624")));
	}

}
