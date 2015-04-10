package edu.ncsu.csc216.shipping_simulator.simulation;
import edu.ncsu.csc216.shipping_simulator.pkg.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit tests for the functionality of Log.
 * @author benroaman
 *
 */
public class LogTest {
	/**Instance of Log for testing*/
	private Log log;
	/**DVDPackage to test logging*/
	private DVDPackage reg;
	/**DVDPackage to test logging*/
	private DVDPackage pri;
	/**DVDPackage to test logging*/
	private DVDPackage bun;



	/**
	 * Initializes log and the 3 DVDPackages for testing.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		log = new Log();
		reg = new RegularDVD(5, 10);
		reg.setWaitTime(10);
		pri = new PriorityDVD(5, 5);
		pri.setWaitTime(5);
		bun = new BundledDVD(5, 15);
		bun.setWaitTime(15);
	}
	
	/**
	 * Tests logPackage by logging 3 packages and incrementally checking the results.
	 */
	@Test
	public void testLogPackage() {
		log.logPackage(reg);
		assertEquals("numCompleted should equal 1", log.getNumCompleted(), 1);
		log.logPackage(pri);
		assertEquals("numCompleted should equal 2", log.getNumCompleted(), 2);
		log.logPackage(bun);
		assertEquals("Average process should be 1000", 1000, (int)(log.averageProcessTime() * 100));
		assertEquals("Average wait should be 1000", 1000, (int)(log.averageWaitTime() * 100));
	}

}
