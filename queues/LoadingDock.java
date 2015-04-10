package edu.ncsu.csc216.shipping_simulator.queues;
import java.util.NoSuchElementException;

import edu.ncsu.csc216.shipping_simulator.pkg.*;
import edu.ncsu.csc216.shipping_simulator.simulation.*;

/**
 * Represents queue of DVDPackages and processes them in the order in which they are added.
 * @author benroaman
 *
 */
public class LoadingDock implements ConveyerBelt {
	
	/**The list of DVDPackages waiting to be processed*/
	private DVDQueue line;
	/**Records the information from DVDPackages as they are processed*/
	private Log log;
	/**The time at which the LoadingDock will be empty*/
	private int timeWhenAvailable;

	/**
	 * Constructs a LoadingDock using a specified Log
	 * @param log the Log for the simulation.
	 */
	public LoadingDock(Log log) {
		this.log = log;
		line = new DVDQueue();
		timeWhenAvailable = 0;
	}
	
	/**
	 * Getter for the size of the line.
	 *@return the current size of the line. 
	 */
	public int size() {
		return line.size();
	}
	
	/**
	 * Tells if the line is empty.
	 * @return true if the line is not empty, false if it is empty.
	 */
	public boolean hasNext() {
		return !line.isEmpty();
	}
	
	/**
	 * Logs the information from the first DVDPackage in line, sets
	 * the DVDPackage's onLoadingDock to false, and removes it from the line.
	 * @return the DVDPackage removed from the line.
	 */
	public DVDPackage processNext() {
		if (line.isEmpty()) {
			throw new NoSuchElementException();
		}
		log.logPackage(line.front());
		line.front().removeFromLoadingDock();
		return line.remove();
	}
	
	/**
	 * returns the time at which the next DVDPackage in line will finish processing,
	 * or Integer.MAX_VALUE if the line is empty.
	 * @return the next depart time or Integer.MAX_VALUE if DVDQueue is empty.
	 */
	public int departTimeNext() {
		if (line.isEmpty()) {
			return Integer.MAX_VALUE;
		} else {
			return line.front().getOrderTime() + line.front().getWaitTime() + line.front().getProcessTime();
		}
	}
	/**
	 * Adds a DVDPackage to line, updates timeWhenAvailable and
	 * the DVDPackage's waitTime differently depending on whether line
	 * is empty.
	 * @param pkg the DVDPackage to be added and updated.
	 */
	public void addPackage(DVDPackage pkg) {
		if (line.isEmpty()) {
			pkg.setWaitTime(0);
			timeWhenAvailable = pkg.getOrderTime() + pkg.getProcessTime();
			line.add(pkg);
		} else {
			pkg.setWaitTime(timeWhenAvailable - pkg.getOrderTime());
			timeWhenAvailable += pkg.getProcessTime();
			line.add(pkg);
		}
	}
}
