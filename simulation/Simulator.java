package edu.ncsu.csc216.shipping_simulator.simulation;
import edu.ncsu.csc216.shipping_simulator.pkg.*;
import edu.ncsu.csc216.shipping_simulator.queues.*;
import java.awt.*;

/**
 *  * Simulator initializes all the elements of the simulation
 * executes the simulation and communicates with the GUI
 * @author benroaman
 */

public class Simulator {
	/**The ReceivingPlatform for the simulation*/
	private ReceivingPlatform platform;
	/**The array of LoadingDocks for the simulation*/
	private LoadingDock[] docks;
	/**The Log for the simulation*/
	private Log log;
	/**The EventCalendar for the simulation*/
	private EventCalendar calendar;
	/**Represents the DVDPackage most recently processed*/
	private DVDPackage currentPackage;
	/**The minimum allowable number of LoadingDocks*/
	private static final int MIN_NUM_LOADING_DOCKS = 3;
	/**The maximum allowable number of LoadingDocks*/
	private static final int MAX_NUM_LOADING_DOCKS = 9;
	/**Total number of LoadingDocks*/
	private int numLoadingDocks;
	/**Number of DVDPackages in the simulation*/
	private int numPackages;
	/**number of steps taken at a given point in the simulation*/
	private int stepsTaken;
	
	/**
	 * Constructor class uses numPackages to initialize the receiving platform
	 * and numLoadingDocks to initialize and populate the array of loading docks.
	 * Also initializes log and calendar.
	 * @param numLoadingDocks the number of loading docks
	 * @param numPackages the number of packages
	 */
	public Simulator(int numLoadingDocks, int numPackages) {
		if (numLoadingDocks < MIN_NUM_LOADING_DOCKS || numLoadingDocks > MAX_NUM_LOADING_DOCKS || numPackages < 1) {
			throw new IllegalArgumentException();
		}
		this.numLoadingDocks = numLoadingDocks;
		this.numPackages = numPackages;
		log = new Log();
		docks = new LoadingDock[this.numLoadingDocks];
			for (int i = 0; i < docks.length; i++) { //initialize the LoadingDocks in docks
				docks[i] = new LoadingDock(log);
			}
		platform = new ReceivingPlatform(numPackages, docks);
		calendar = new EventCalendar(docks, platform);		
	}
	
	/**
	 * Executes the next step of the simulation. Sets current package to null,
	 * determines next step, executes next step, sets currentPackage to the
	 * DVDPackage being processed and increments the number of steps taken.
	 */
	public void step() {
		currentPackage = null;
		currentPackage = calendar.nextToBeProcessed().processNext();
		stepsTaken++;
	}
		
	/**
	 * getter for stepsTaken
	 * @return number of steps executed so far.
	 */
	public int getStepsTaken() {
		return stepsTaken;
	}
	
	/**
	 * calculates total number of steps in simulation
	 * @return total number of steps required to complete simulation.
	 */
	public int totalNumberOfSteps() {
		return 2 * numPackages;
	}
	
	/**
	 * determines if simulation is complete
	 * @return true if simulation is incomplete, false if complete.
	 */
	public boolean moreSteps() {
		return stepsTaken < totalNumberOfSteps();
	}

	/**
	 * getter for dockIndex of currentPackage
	 * @return Loading dock index of currentPackage, or -1 if currentPackage is null.
	 */
	public int getCurrentIndex() {
		if (currentPackage == null) {
			return -1;
		} else {
		return currentPackage.getDockIndex();
		}
	}
	
	/**
	 * getter for color of currentPackage
	 * @return color of currentPackage or null if currentPackage is null
	 */
	public Color getCurrentPackageColor() {
		if (currentPackage == null) {
			return null;
		} else {
		return currentPackage.getColor();
		}
	}
	
	/**
	 * tells where most recent step occurred
	 * @return true if the most recent step was at a loading docks, false if it was at the receiving dock
	 */
	public boolean packageLeftSimulation() {
		if (currentPackage == null) {
			return false;
		} else {
			return !currentPackage.onLoadingDock();
		}
	}
	
	/**
	 * calculates average wait time
	 * @return current average wait time.
	 */
	public double averageWaitTime() {
		if (log.getNumCompleted() < 1) {
			return 0.0;
		} else {
		return log.averageWaitTime();
		}
	}
	
	/**
	 * calculates average process time
	 * @return current average processing time.
	 */
	public double averageProcessTime() {
		if (log.getNumCompleted() < 1) {
			return 0.0;
		} else {	
		return log.averageProcessTime();
		}
	}
}
