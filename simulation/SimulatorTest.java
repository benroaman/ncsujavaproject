package edu.ncsu.csc216.shipping_simulator.simulation;
import edu.ncsu.csc216.shipping_simulator.queues.*;
import edu.ncsu.csc216.shipping_simulator.pkg.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Tests for the funcionality of Simulator.
 * @author benroaman
 *
 */
public class SimulatorTest {
	/**Instance of Simulation for testing*/
	private Simulator simulator;

	/**
	 * Initializes a Simulator with 4 LoadingDocks and 30 DVDPackages.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		simulator = new Simulator(4, 30);
	}

	/**
	 * Tests step and other methods by stepping through the simulation and
	 * periodically checking the state of the data.
	 */
	@Test
	public void testStep() {
		simulator.step();
		assertEquals(0, (int)simulator.averageWaitTime());
		assertEquals(0, (int)simulator.averageProcessTime());
		assertEquals(false, simulator.packageLeftSimulation());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(true, simulator.moreSteps());

		for (int i = 0; i < 28; i++) {
			simulator.step();
		}
		assertEquals(287, (int)(simulator.averageWaitTime() * 100));
		assertEquals(1525, (int)(100 * simulator.averageProcessTime()));
		assertEquals(false, simulator.packageLeftSimulation());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(true, simulator.moreSteps());
		
		for (int i = 0; i < 15; i++) {
			simulator.step();
		}
		assertEquals(614, (int)(simulator.averageWaitTime() * 100));
		assertEquals(1457, (int)(100 * simulator.averageProcessTime()));
		assertEquals(true, simulator.packageLeftSimulation());
		assertEquals(0, simulator.getCurrentIndex());
		assertEquals(true, simulator.moreSteps());
		for (int i = 0; i < 16; i++) {
			simulator.step();
		}
		assertEquals(false, simulator.moreSteps());	
	}

}
