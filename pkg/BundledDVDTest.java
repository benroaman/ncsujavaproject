package edu.ncsu.csc216.shipping_simulator.pkg;

import edu.ncsu.csc216.shipping_simulator.simulation.*;
import edu.ncsu.csc216.shipping_simulator.queues.*;
import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Tests for the functionality of BundledDVD.
 * @author benroaman
 *
 */
public class BundledDVDTest {
	/**a DVDPackage to test*/
	private DVDPackage pkg;
	/**a LoadingDock array to test getInLine algorithm*/
	private LoadingDock[] docks;
	/**Only for initializes LoadingDocks*/
	private Log log = new Log();
	
	/**
	 * Sets up pkg and docks to test various methods.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		pkg = new BundledDVD(10, 25);
		docks = new LoadingDock[8];
		for (int i = 0; i < docks.length; i++) {
			docks[i] = new LoadingDock(log);
		}
	}

	/**
	 * Tests the constructor.
	 */
	@Test
	public void testBundledDVD() {
		assertEquals("orderTime should equal 10", pkg.getOrderTime(), 10);
		assertEquals("processTime should equal 25", pkg.getProcessTime(), 25);
		assertEquals("dockIndex should equal -1", pkg.getDockIndex(), -1);
		
	}
	
	/**
	 * Tests getColor.
	 */
	@Test
	public void testGetColor() {
		assertEquals("Color Should be Black", Color.black, pkg.getColor());
	}
	
	/**
	 * Tests onLoadingDock.
	 */
	@Test
	public void testOnLoadingDock() {
		assertEquals("onLoadingDock should be false", pkg.onLoadingDock(), false);
		pkg.getInLine(docks);
		assertEquals("onLoadingDock should be true", pkg.onLoadingDock(), true);
	}
	
	/**
	 * Tests removeFromLoadingDock.
	 */
	@Test
	public void testRemoveFromLoadingDock() {
		pkg.getInLine(docks);
		assertEquals("onLoadingDock should be true", pkg.onLoadingDock(), true);
		pkg.removeFromLoadingDock();
		assertEquals("onLoadingDock should be false", pkg.onLoadingDock(), false);
	}
	
	/**
	 * Tests SetWaitTime.
	 */
	@Test
	public void testSetWaitTime() {
		pkg.setWaitTime(23);
		assertEquals("waitTime should be 23", pkg.getWaitTime(), 23);
	}

	/**
	 * Tests getInLine by adding 2 BundledDVDs to docks.
	 */
	@Test
	public void testGetInLine() {
		pkg.getInLine(docks);
		assertEquals("pkg.dockIndex should equal 6", pkg.getDockIndex(), 6);
		DVDPackage pkg2 = new BundledDVD(5, 10);
		pkg2.getInLine(docks);
		assertEquals("pkg.dockIndex should equal 7", 7, pkg2.getDockIndex());
	}
}
