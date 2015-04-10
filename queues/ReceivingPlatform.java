package edu.ncsu.csc216.shipping_simulator.queues;
import java.util.NoSuchElementException;

import edu.ncsu.csc216.shipping_simulator.pkg.*;


/**
 * The initial queue for DVDPackages, uses DVDFactory to initialize the user specified
 * number of DVDPackages.
 * @author benroaman
 *
 */
public class ReceivingPlatform implements ConveyerBelt {
	private DVDQueue receivingPlatform;
	private LoadingDock[] loadingDock;
	
	/**
	 * Constructs a ReceivingPlatform using the number of DVDPackages
	 * and LoadingDocks specified by the user.
	 * @param numPackages user defined number of DVDPackages.
	 * @param loadingDock user defined number of LoadingDocks.
	 */
	public ReceivingPlatform(int numPackages, LoadingDock[] loadingDock) {
		this.loadingDock = loadingDock;
		receivingPlatform = new DVDQueue();
		for (int i = 0; i < numPackages; i++){
			receivingPlatform.add(DVDFactory.generatePackage());			
		}
		
	}
	
	/**
	 * Tells if the ReceivingPlatform still contains DVDPackages.
	 * @return true if receivingPlatform is NOT empty, false otherwise.
	 */
	public boolean hasNext() {
		return !receivingPlatform.isEmpty();
	}
	
	/**
	 * Calls the first DVDPackage in receivingPlatform to getInLine(),
	 * adds it to its designated LoadingDock, and removes it from
	 * receivingPlatform.
	 * @return the DVDPackage that was processed.
	 */
	public DVDPackage processNext() {
		if (receivingPlatform.isEmpty()) {
			throw new NoSuchElementException();
		}
		receivingPlatform.front().getInLine(loadingDock);
		return receivingPlatform.remove();
	}
	
	/**
	 * returns the nextDVDPackages orderTime.
	 * @return the time at which the next DVDPackage will be processed, or Integer.MAX_VALUE is it is empty.
	 */
	public int departTimeNext() {
		if (receivingPlatform.isEmpty()) {
			return Integer.MAX_VALUE;
		} else {
			return receivingPlatform.front().getOrderTime();
		}
		
	}
	
	/**
	 * getter for the size of the DVDQueue.
	 * @return the number of DVDPackages in receivingPlatform.
	 */
	public int size() {
		return receivingPlatform.size();
	}

}
