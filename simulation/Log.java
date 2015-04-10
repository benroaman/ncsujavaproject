package edu.ncsu.csc216.shipping_simulator.simulation;
import edu.ncsu.csc216.shipping_simulator.pkg.*;

/**
 * Log keeps track of the information that is the purpose of the simulation,
 * total time spent waiting, total time spent processing, and total completed
 * packages. Log has methods to calculate averages for wait and process times.
 * @author benroaman
 *
 */
public class Log {
	/**The number of DVDPackages that have completed processing*/
	private int numCompleted;
	/**Total wait times of all processed packages*/
	private int totalWaitTime;
	/**Total process time of all processed packages*/
	private int totalProcessTime;
	
	/**
	 * Default constructor is adequate as all stats begin at zero.
	 */
	public Log() { //Null constructor is adequate as all values start at zero
	}
	
	/**
	 * Getter for numCompleted.
	 * @return number of packages completed.
	 */
	public int getNumCompleted() {
		return numCompleted;
	}
	
	/**
	 * adds the wait and process times of a given package to the total times,
	 * and increments numCompleted.
	 * @param pkg DVDPackage to be logged.
	 */
	public void logPackage(DVDPackage pkg) {
		totalWaitTime += pkg.getWaitTime();
		totalProcessTime += pkg.getProcessTime();
		numCompleted++;
	}
	
	/**
	 * Calculates average wait time.
	 * @return average wait time for completed packages.
	 */
	public double averageWaitTime() {
		if (numCompleted < 1) {
			return 0.0;
		} else {
		return (double)totalWaitTime / (double)numCompleted;
		}
	}
	
	/**
	 * Calculates average processing time.
	 * @return average processing time for completed packages.
	 */
	public double averageProcessTime() {
		if (numCompleted < 1) {
			return 0.0;
		} else {
		return (double)totalProcessTime / (double)numCompleted;
		}
	}
}
