package edu.ncsu.csc216.shipping_simulator.pkg;

import edu.ncsu.csc216.shipping_simulator.simulation.*;
import edu.ncsu.csc216.shipping_simulator.queues.*;
import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests various aspects of PriorityDVD.
 * @author benroaman
 *
 */
public class PriorityDVDTest {
	/**DVDPackage that will be the PriorityDVD instance tested*/
	private DVDPackage pkg;
	/**LoadingDock array for testing getInLine algorithm*/
	private LoadingDock[] docks;
	/**Log for initializing LoadingDocks in the array*/
	private Log log = new Log();
	
	/**
	 * Initializes pkg and docks.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		pkg = new PriorityDVD(10, 25);
		docks = new LoadingDock[8];
		for (int i = 0; i < docks.length; i++){
			docks[i] = new LoadingDock(log);
		}
	}

	/**
	 * Tests the constructor.
	 */
	@Test
	public void testPriorityDVD() {
		assertEquals("orderTime should equal 10", pkg.getOrderTime(), 10);
		assertEquals("processTime should equal 25", pkg.getProcessTime(), 25);
		assertEquals("dockIndex should equal -1", pkg.getDockIndex(), -1);
		
	}
	
	/**
	 * Tests getColor.
	 */
	@Test
	public void testGetColor() {
		assertEquals("Color Should be Magenta", Color.magenta, pkg.getColor());
	}
	
	/**
	 * Tests onLoadingDock.
	 */
	@Test
	public void testOnLoadingDock(){
		assertEquals("onLoadingDock should be false", pkg.onLoadingDock(), false);
		pkg.getInLine(docks);
		assertEquals("onLoadingDock should be true", pkg.onLoadingDock(), true);
	}
	
	/**
	 * Tests removeFromLoadingDock.
	 */
	@Test
	public void testRemoveFromLoadingDock(){
		pkg.getInLine(docks);
		assertEquals("onLoadingDock should be true", pkg.onLoadingDock(), true);
		pkg.removeFromLoadingDock();
		assertEquals("onLoadingDock should be false", pkg.onLoadingDock(), false);
	}
	
	/**
	 * Tests setWaitTime.
	 */
	@Test
	public void testSetWaitTime(){
		pkg.setWaitTime(23);
		assertEquals("waitTime should be 23", pkg.getWaitTime(), 23);
	}

	/**
	 * Tests getInLine by adding two PriorityDVDs to docks.
	 */
	@Test
	public void testGetInLine(){
		pkg.getInLine(docks);
		assertEquals("pkg.dockIndex should equal 0", pkg.getDockIndex(), 0);
		DVDPackage pkg2 = new PriorityDVD(5, 10);
		pkg2.getInLine(docks);
		assertEquals("pkg2.dockIndex should equal 1", pkg2.getDockIndex(), 1);
	}
}
