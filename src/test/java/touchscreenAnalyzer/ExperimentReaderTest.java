package touchscreenAnalyzer;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dataModels.Session;

import org.junit.Assert;

/**
 * The public interface of the experiment reader is simply a method to return a list of sessions. Only attributes of this list
 * can be tested. Experiment reader also assigns metadata, this can be tested.
 * @author Carlos May
 *
 */
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
		ArrayList<Session> sessions = eReader.readExperimentFolder(experimentFolder1);
		Assert.assertEquals((int) 8, sessions.size());
	}

	@Test
	void readExperimentDetectsCorrectAmountOfSessionsInFolder2() {
		ExperimentReader eReader = new ExperimentReader();
		ArrayList<Session> sessions = eReader.readExperimentFolder(experimentFolder2);
		Assert.assertEquals((int) 6, sessions.size());
	}

	@Test
	void getSessionReturnsNullWhenThereIsNoSessions() {
		ExperimentReader eReader = new ExperimentReader();
		ArrayList<Session> sessions = eReader.readExperimentFolder(emptyFolder);
		Assert.assertEquals(0, sessions.size());
	}
	
//	@Test
//	void getSessionInfoGetsCorrectSessionInformation() {
//		ExperimentReader eReader = new ExperimentReader();
//		ArrayList<Session> sessions = eReader.readExperimentFolder(experimentFolder1);
//		Assert.assertEquals(916, eReader.getSessionInfo(eReader.getNextSession()).getSessionId());
//	}
//
//	@Test
//	void getSessionReturnsSessionsInCorrectOrder() {
//		ExperimentReader eReader = new ExperimentReader();
//		ArrayList<Session> sessions = eReader.readExperimentFolder(experimentFolder2);
//		Assert.assertEquals(617, eReader.getSessionInfo(eReader.getNextSession()).getSessionId());
//		Assert.assertEquals(618, eReader.getSessionInfo(eReader.getNextSession()).getSessionId());
//		Assert.assertEquals(623, eReader.getSessionInfo(eReader.getNextSession()).getSessionId());
//		Assert.assertEquals(619, eReader.getSessionInfo(eReader.getNextSession()).getSessionId());
//		Assert.assertEquals(624, eReader.getSessionInfo(eReader.getNextSession()).getSessionId());
//		Assert.assertEquals(620, eReader.getSessionInfo(eReader.getNextSession()).getSessionId());
//	}
//
//	@Test
//	void getSessionAssignsOrdinalDaysCorrectly() {
//		ExperimentReader eReader = new ExperimentReader();
//		ArrayList<Session> sessions = eReader.readExperimentFolder(experimentFolder2);
//		File session = eReader.getNextSession();
//		Assert.assertEquals(617, eReader.getSessionInfo(session).getSessionId());
//		Assert.assertEquals(0, eReader.getSessionInfo(session).getOrdinalDay());
//		session = eReader.getNextSession();
//		Assert.assertEquals(618, eReader.getSessionInfo(session).getSessionId());
//		Assert.assertEquals(1, eReader.getSessionInfo(session).getOrdinalDay());
//		session = eReader.getNextSession();
//		Assert.assertEquals(623, eReader.getSessionInfo(session).getSessionId());
//		Assert.assertEquals(2, eReader.getSessionInfo(session).getOrdinalDay());
//		session = eReader.getNextSession();
//		Assert.assertEquals(619, eReader.getSessionInfo(session).getSessionId());
//		Assert.assertEquals(0, eReader.getSessionInfo(session).getOrdinalDay());
//		session = eReader.getNextSession();
//		Assert.assertEquals(624, eReader.getSessionInfo(session).getSessionId());
//		Assert.assertEquals(1, eReader.getSessionInfo(session).getOrdinalDay());
//		session = eReader.getNextSession();
//		Assert.assertEquals(620, eReader.getSessionInfo(session).getSessionId());
//		Assert.assertEquals(2, eReader.getSessionInfo(session).getOrdinalDay());
//	}
//
//	@Test
//	void getSessionInfoReturnsNullWhenThereIsNoSuchInfo() {
//		ExperimentReader eReader = new ExperimentReader();
//		ArrayList<Session> sessions = eReader.readExperimentFolder(experimentFolder1);
//		Assert.assertEquals(null, eReader.getSessionInfo(new File(
//				"testfiles/ExperimentReader/experiment1/KMBD-03541-W_Julia_Mouse ICPT Stage 4 30% (S+)  HORIZONTAL _624")));
//	}

}
