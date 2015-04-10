package edu.ncsu.csc216.shipping_simulator.queues;
import edu.ncsu.csc216.shipping_simulator.pkg.*;
import edu.ncsu.csc216.shipping_simulator.simulation.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test for the functionality of ReceivingPlatform.
 * @author benroaman
 *
 */
public class ReceivingPlatformTest {
	/**The instance of RecievingPlatform for testing*/
	private ReceivingPlatform platform;
	/**A LoadingDock[] is necessary for initializing platform*/
	private LoadingDock[] docks;
	/**A Log is necessary for initializing the LoadingDocks in docks*/
	private Log log;

	/**
	 * sets up platform for testing.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		docks = new LoadingDock[5];
		log = new Log();
		for (int i = 0; i < docks.length; i++){
			docks[i] = new LoadingDock(log);
		}
		platform = new ReceivingPlatform(50, docks);
	}

	/**
	 * Tests departTimeNext.
	 */
	@Test
	public void testDepartTimeNext() {
		assertEquals(platform.departTimeNext(), platform.processNext().getOrderTime());
		assertEquals(platform.departTimeNext(), platform.processNext().getOrderTime());
		assertEquals(platform.departTimeNext(), platform.processNext().getOrderTime());
		assertEquals(platform.departTimeNext(), platform.processNext().getOrderTime());	
	}
	
	/**
	 * Tests hasNext.
	 */
	@Test
	public void testHasNext() {
		assertEquals(true, platform.hasNext());
		platform = new ReceivingPlatform(0, docks);
		assertEquals(false, platform.hasNext());
	}
	
	/**
	 * Tests processNext.
	 */
	@Test
	public void testProcessNext() {
		assertEquals(platform.departTimeNext(), platform.processNext().getOrderTime());
		assertEquals(49, platform.size());
	}
	
	/**
	 * Tests size.
	 */
	@Test
	public void testSize() {
		assertEquals(50, platform.size());
		platform.processNext();
		assertEquals(49, platform.size());
	}

}
