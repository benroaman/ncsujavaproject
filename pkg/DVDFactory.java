package edu.ncsu.csc216.shipping_simulator.pkg;
import java.util.*;

/**
 * A simple factory class whose only task is to generate shipping packages.<br />
 *   Bundled DVD packages are generated 20% of the time, with process time 25 - 28 seconds. <br />
 *   Priority DVD packages are generated 25% of the time, with process time 10 - 12 seconds.<br />
 *   Regular DVD packages are generated 55% of the time, with process time 15 - 19 seconds.
 * @author Jo Perry
 * @author Sarah Heckman
 * @see BundledDVD
 * @see RegularDVD
 * @see PriorityDVD
 */
public class DVDFactory {
	/** Absolute time for DVD's created for the simulation.  The 
	 * time starts at zero and increases by up to MAX_PKG_GENERATION_DELAY
	 * for each package created by the DVDFactory. */
	private static int time = 0; 
	/** Random object with seed that allows for testing of simulation */
	private static Random randomNumber = new Random(10); 
	/** Maximum delay between creation of DVDs */
	private static final double MAX_PKG_GENERATION_DELAY = 5.4;
	/** Percentage of time a BundledDVD package should be created */
	private static final double BUNDLED_PERCENT = .20;
	/** Percentage of time a PriorityDVD package should be created */
	private static final double PRIORITY_PERCENT = .25;
	/** Minimum amount of time required to process a BundledDVD */
	private static final int B_MIN_PROCESS_TIME = 25;
	/** Process time range for a BundledDVD */
	private static final int B_PROCESS_TIME_RANGE = 3;
	/** Minimum amount of time required to process a PriorityDVD */
	private static final int P_MIN_PROCESS_TIME = 10;
	/** Process time range for a PriorityDVD */
	private static final int P_PROCESS_TIME_RANGE = 2;
	/** Minimum amount of time required to process a RegularDVD */
	private static final int R_MIN_PROCESS_TIME = 15;
	/** Process time range for a RegularDVD */
	private static final int R_PROCESS_TIME_RANGE = 4;
	/**
	 * Generate a new DVDPackage as described above.  
	 * @return the DVDPackage created
	 */
	public static DVDPackage generatePackage() {
		// Update the overall time with up to the floor of MAX_PKG_GENERATION_DELAY seconds.
		// The value is cast to an int, which is the floor of the original double.
		time += (int)(randomNumber.nextDouble() * MAX_PKG_GENERATION_DELAY);

		// Random number that determines which type of DVD that will be created.  The generated number
		// is between 0 and 1.0.  By splitting the priorities across the range of numbers generated, we
		// can simulate creation of different DVDPackages with the appropriate priorities.
		double x = randomNumber.nextDouble();
		if (x < BUNDLED_PERCENT) {
			// If the generated number is less than BUNDLED_PERCENT, create a BundledDVD
			// with a process time range between B_MIN_PROCESS_TIME and B_PROCESS_TIME_RANGE.
			return new BundledDVD(time, B_MIN_PROCESS_TIME + (int) (randomNumber.nextDouble() * B_PROCESS_TIME_RANGE)); 
		}
		else if (x < BUNDLED_PERCENT + PRIORITY_PERCENT) {
			// If the generated number is less than BUNDLED_PERCENT + PRIORITY_PERCENT, create a PriorityDVD
			// with a process time range between P_MIN_PROCESS_TIME and P_PROCESS_TIME_RANGE.
			return new PriorityDVD(time, P_MIN_PROCESS_TIME + (int) (randomNumber.nextDouble() * P_PROCESS_TIME_RANGE));
		}
		else {
			// Otherwise, create a BundledDVD with a process time range between R_MIN_PROCESS_TIME 
			// and R_PROCESS_TIME_RANGE.
			return new RegularDVD(time, R_MIN_PROCESS_TIME + (int) (randomNumber.nextDouble() * R_PROCESS_TIME_RANGE));
		}
	}

	/**
	 * Resets the DVDFactory.  This should ONLY be used for testing!
	 */
	public static void resetFactory() {
		time = 0;
		randomNumber = new Random(10);
	}
}