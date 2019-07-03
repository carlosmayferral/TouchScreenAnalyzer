package touchscreenAnalyzer;

import java.io.File;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataModels.Event;
import dataModels.SessionInfo;

class XmlReaderTest {
	
	static XmlReader reader;
	
	static File session0;
	static final String session0Address = "testfiles/XmlReader/session0.xml";
	
	static File session1;
	static final String session1Address = "testfiles/XmlReader/session1.xml";

	@BeforeEach
	void setUp() throws Exception {
		reader = new XmlReader();
		session0 = new File(session0Address);
		session1 = new File (session1Address);
	}

	@Test
	void session0HasRightScheduleName() {
		SessionInfo info = reader.expressRead(session0);
		Assert.assertEquals("PD_Baseline_1_v3", info.getSchedule());
	}
	
	@Test
	void session0HasRightChamber() {
		SessionInfo info = reader.expressRead(session0);
		Assert.assertEquals(1, info.getChamber());
	}
	
	@Test
	void session0HasRightDate() {
		SessionInfo info = reader.expressRead(session0);
		Assert.assertEquals(20151001, info.getDate());
	}
	
	@Test
	void session0HasRightDatabase() {
		SessionInfo info = reader.expressRead(session0);
		Assert.assertEquals("GUEPDAPP3MF", info.getDatabase());
	}
	
	@Test
	void session0HasRightSessionId() {
		SessionInfo info = reader.expressRead(session0);
		Assert.assertEquals(239, info.getSessionId());
	}
	
	@Test
	void session0HasRightAnimalId() {
		SessionInfo info = reader.expressRead(session0);
		Assert.assertEquals("gw142a300", info.getAnimalId());
	}
	
	@Test
	void session0HasRightUser() {
		SessionInfo info = reader.expressRead(session0);
		Assert.assertEquals("David", info.getUser());
	}
	
	@Test
	void session0HasRightWeight() {
		SessionInfo info = reader.expressRead(session0);
		Assert.assertEquals(19.6, info.getWeight(), 0.01);
	}
	
	//Test event reading
	@Test
	void session0HasRightNumberOfEvents() {
		Event[] events = reader.readEvents(session0);
		Assert.assertEquals(3364, events.length);
	}
	
	@Test
	void session1HasRightNumberOfEvents() {
		Event[] events = reader.readEvents(session1);
		Assert.assertEquals(3014, events.length);
	}
	
	//Test event strucure
	
	@Test
	void session0Event0HasRightEventName() {
		Event[] events = reader.readEvents(session0);
		Assert.assertEquals("Input Definition", events[0].getEvent_Name());
	}
	
	@Test
	void session0Event0HasRightArgument1Name() {
		Event[] events = reader.readEvents(session0);
		Assert.assertEquals("Tray #1", events[0].getArgumentName(1));
	}

}
