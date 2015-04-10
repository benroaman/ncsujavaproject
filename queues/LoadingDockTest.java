package edu.ncsu.csc216.shipping_simulator.queues;
import edu.ncsu.csc216.shipping_simulator.pkg.*;
import edu.ncsu.csc216.shipping_simulator.simulation.*;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit tests for the functionality of LoadingDock.
 * @author benroaman
 *
 */
public class LoadingDockTest {
	/**LoadingDock instance used for testing*/
	private LoadingDock dock;
	/**DVDPackage for testing*/
	private DVDPackage reg;
	/**DVDPackage for testing*/
	private DVDPackage pri;
	/**DVDPackage for testing*/
	private DVDPackage bun;
	/**Log to initialize LoadingDock*/
	private Log log;
	

	/**
	 * Sets up the instances needed for testing.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		log = new Log();
		dock = new LoadingDock(log);
		reg = new RegularDVD(5, 10);
		pri = new PriorityDVD(10, 15);
		bun = new BundledDVD(15, 20);
		
	}

	/**
	 * Tests addPackage and ProcessNext. Combined because addPackage sets up ProcessNext
	 * so nicely.
	 */
	@Test
	public void testAddPackageAndProcessNext() {
		dock.addPackage(reg);
		assertEquals("size should return 1", dock.size(), 1);
		assertEquals("next depart time should be 15", dock.departTimeNext(), 15);
		assertEquals("reg waitTime should be 0", reg.getWaitTime(), 0);
		dock.addPackage(pri);
		assertEquals("size should return 2", dock.size(), 2);
		assertEquals("pri waitTime should be 5", pri.getWaitTime(), 5);
		dock.addPackage(bun);
		assertEquals("size should return 3", dock.size(), 3);
		assertEquals("bun waitTime should be 15", bun.getWaitTime(), 15);
		dock.processNext();
		assertEquals("size should return 2", dock.size(), 2);
		assertEquals("next depart time should be 30", dock.departTimeNext(), 30);
		dock.processNext();
		assertEquals("size should return 1", dock.size(), 1);
		assertEquals("next depart time should be 50", dock.departTimeNext(), 50);
	}
	/**
	 * Tests hasNext.
	 */
	@Test
	public void testHasNext(){
		assertEquals("dock should be empty", dock.hasNext(), false);
		dock.addPackage(reg);
		assertEquals("dock should not be empty", dock.hasNext(), true);
	}

}
