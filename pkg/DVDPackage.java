package edu.ncsu.csc216.shipping_simulator.pkg;
import edu.ncsu.csc216.shipping_simulator.queues.*;
import java.awt.*;

/**
 * An abstract object that establishes uniform base functionality to
 * all packages in the simulation. Provides fundamental data members,
 * getters and setters.
 * @author benroaman
 *
 */
public abstract class DVDPackage {

	/**The dockIndex for DVDPackages without a LoadingDock*/
	private static final int INITIAL_DOCK_IDX = -1;
	/**The time at which the DVDPackage leaves the RecievingDock*/
	private int orderTime;
	/**The time a DVDPackage must wait at a LoadingDock before processing*/
	private int waitTime;
	/**the time it takes for a LoadingDock to process the DVDPackage*/
	private int processTime;
	/**the index for the DVDPackages assigned */
	private int dockIndex;
	/**True if the DVDPackage is on a LoadingDock, otherwise false*/
	private boolean onLoadingDock;

	/**
	 * Constructor sets order and process time based on parameters.
	 * Sets dock index to -1.
	 * @param orderTime the order time
	 * @param processTime the processing time
	 */
	public DVDPackage(int orderTime, int processTime) {
		if (orderTime < 0 || processTime < 0){
			throw new IllegalArgumentException();
		}
		this.orderTime = orderTime;
		this.processTime = processTime;
		dockIndex = INITIAL_DOCK_IDX;
	}
	
	
	/**
	 * getter for orderTime
	 * @return the time the DVDPackage leaves the RecievingPlatform.
	 */
	public int getOrderTime() {
		return orderTime;
	}
	
	/**
	 * getter for waitTime
	 * @return the time the DVDPackage waits on its LoadingDock before processing.
	 */
	public int getWaitTime() {
		return waitTime;
	}
	
	/**sets the waitTime for the DVDPackage.
	 * @param waitTime the time the DVDPackage must wait on a LoadingDock before processing.
	 */
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	
	/**
	 * getter for processTime
	 * @return The time required for a LoadingDock to process the DVDPackage.
	 */
	public int getProcessTime()  {
		return processTime;
	}
	
	/**
	 * getter for dockIndex
	 * @return The index of the dock containing the DVDPackage.
	 */
	public int getDockIndex() {
		return dockIndex;
	}
	
	/**
	 * tells if this is in a loading dock
	 * @return true if the DVDPackage is in a LoadingDock, false otherwise.
	 */
	public boolean onLoadingDock() {
		return onLoadingDock;
	}
	
	/**
	 * Sets onLoadingDock to false.
	 */
	public void removeFromLoadingDock() {
		onLoadingDock = false;
	}
	
	/**
	 * sets the dockIndex of the DVDPackage.
	 * @param index the index of the dock.
	 */
	protected void setDockIndex(int index) {
		dockIndex = index;
		onLoadingDock = true;
	}
	
	/** chooses a loading dock based on appropriate algorithm.
	 * @param dock the array of docks from which to choose.
	 */
	public abstract void getInLine(LoadingDock[] dock);
	
	/**
	 * getter for color
	 * @return the color of the DVDPackage
	 */
	public abstract Color getColor();
	
}
