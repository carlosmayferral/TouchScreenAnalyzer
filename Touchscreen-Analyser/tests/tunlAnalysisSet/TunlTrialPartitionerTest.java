package tunlAnalysisSet;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import dataModels.Event;
import dataModels.Trial;

public class TunlTrialPartitionerTest {
	
	private TunlTrialPartitioner partitioner;

	@Before
	public void setUp() throws Exception {
		partitioner = new TunlTrialPartitioner();
	}
	
	/* Partitioning:
	 * a list of events might
	 * - contain one event
	 * - contain many events but no trial
	 * - contain trial that starts but doesn't end
	 * - contain trial that ends but doesn't start
	 * 
	 * 
	 * - contain one trial exactly
	 * - contain one trial and a bit more
	 * - contain x trials and a bit more
	 * - contain many + 1 trials and a bit more
	 */

	@Test
	public void oneEventYielsNoTrials() {
		Random rnd = new Random(123);
		Event[] eventArray = {new TunlEventStub(rnd.nextInt())};		
		
		
		assertEquals(0,partitioner.partition(eventArray).size());
	}
	
	@Test
	public void manyRandomEventsYieldNoTrials() {
		Random rnd = new Random(627);
		Event[] eventArray = new Event[12];
		for (int i = 0; i < 12; i++) {
			eventArray[i] = new TunlEventStub(rnd.nextInt());
		}
		assertTrue(eventArray.length == 12);
		assertEquals(0,partitioner.partition(eventArray).size());
	}
	
	@Test
	public void startButNoFinishEventYieldsNoTrials() {
		Random rnd = new Random(421);
		TunlEventStub[] eventArray = new TunlEventStub[16];
		for (int i = 0; i < 16; i++) {
			if (i == 7) {
				eventArray[i] = new TunlEventStub(rnd.nextInt());
				eventArray[i].setTrialStart();
				continue;
			}
			eventArray[i] = new TunlEventStub(rnd.nextInt());
		}
		assertTrue(eventArray.length == 16);
		assertEquals(0,partitioner.partition(eventArray).size());
	}
	
	@Test
	public void endButNoStartYieldsNoTrials() {
		Random rnd = new Random(421);
		int listSize = 15;
		TunlEventStub[] eventArray = new TunlEventStub[listSize];
		for (int i = 0; i < listSize; i++) {
			if (i == 12) {
				eventArray[i] = new TunlEventStub(rnd.nextInt());
				eventArray[i].setTrialTransition();
				continue;
			}
			eventArray[i] = new TunlEventStub(rnd.nextInt());
		}
		assertTrue(eventArray.length == listSize);
		assertEquals(0,partitioner.partition(eventArray).size());
	}
	
	@Test
	public void startAndEndGiveOneTrial() {
		Random rnd = new Random(627);
		int listSize = 11;
		TunlEventStub[] eventArray = new TunlEventStub[listSize];
		for (int i = 0; i < listSize; i++) {
			eventArray[i] = new TunlEventStub(rnd.nextInt());
		}
		eventArray[2].setTrialStart();
		eventArray[10].setTrialTransition();
		assertTrue(eventArray.length == listSize);
		assertEquals(1,partitioner.partition(eventArray).size());
	}
	
	@Test
	public void startEndAndExcessStillGiveOneTrial(){
		Random rnd = new Random(737);
		int listSize = 21;
		TunlEventStub[] eventArray = new TunlEventStub[listSize];
		for (int i = 0; i < listSize; i++) {
			eventArray[i] = new TunlEventStub(rnd.nextInt());
		}
		eventArray[1].setTrialStart();
		eventArray[15].setTrialTransition();
		assertTrue(eventArray.length == listSize);
		assertEquals(1,partitioner.partition(eventArray).size());
	}
	
	@Test
	public void startAnd3TransitionsGiveThreeTrials() {
		Random rnd = new Random(169);
		int listSize = 18;
		TunlEventStub[] eventArray = new TunlEventStub[listSize];
		for (int i = 0; i < listSize; i++) {
			eventArray[i] = new TunlEventStub(rnd.nextInt());
		}
		eventArray[2].setTrialStart();
		eventArray[5].setTrialTransition();
		eventArray[8].setTrialTransition();
		eventArray[10].setTrialTransition();
		assertTrue(eventArray.length == listSize);
		assertEquals(3,partitioner.partition(eventArray).size());
	}
	
	@Test
	public void startAnd4TransitionsGivesFourTrials() {
		Random rnd = new Random(321);
		int listSize = 21;
		TunlEventStub[] eventArray = new TunlEventStub[listSize];
		for (int i = 0; i < listSize; i++) {
			eventArray[i] = new TunlEventStub(rnd.nextInt());
		}
		eventArray[3].setTrialStart();
		eventArray[7].setTrialTransition();
		eventArray[11].setTrialTransition();
		eventArray[16].setTrialTransition();
		eventArray[18].setTrialTransition();
		assertTrue(eventArray.length == listSize);
		assertEquals(4,partitioner.partition(eventArray).size());
	}
	
	@Test
	public void ensureEventIntegrityAfterConversion() {
		Random rnd = new Random(66);
		int listSize = 28;
		TunlEventStub[] eventArray = new TunlEventStub[listSize];
		for (int i = 0; i < listSize; i++) {
			eventArray[i] = new TunlEventStub(rnd.nextInt());
		}
		eventArray[4].setTrialStart();
		eventArray[7].setTrialTransition();
		eventArray[13].setTrialTransition();
		eventArray[19].setTrialTransition();
		eventArray[26].setTrialTransition();
		assertTrue(eventArray.length == listSize);
		ArrayList<Trial> result = partitioner.partition(eventArray);
		assertTrue(result.get(0).copyEventsAsArray()[0].getItem_Name().equals(eventArray[4].getItem_Name()));
	}
	
	

}
