package edu.ncsu.csc216.shipping_simulator.queues;

import edu.ncsu.csc216.shipping_simulator.pkg.DVDPackage;


/**
 * Operations for a line (conveyer belt) of DVDPackages on a 
 * receiving platform or loading dock. 
 * @author Jo Perry
 * @version 2.0
 */
public interface ConveyerBelt {

	/**
	 * Returns true if the ConveyerBelt has more DVDPackages. 
	 * @return true if the ConveyerBelt has more DVDPackages
	 */
	boolean hasNext();

	/**
	 * Processes the package at the front of the line, 
	 * removing it from the line.
	 * @return the DVDPackage at the front of the line
	 * that was just processed
	 */
	DVDPackage processNext();

	/**
	 * Returns the amount of time before the next DVDPackage will leave the ConveyerBelt.
	 * If the ConveyerBelt is empty, Integer.MAX_VALUE is returned.
	 * @return the departure time of the next DVDPackage or Integer.MAX_VALUE if the
	 * ConveyerBelt is empty.
	 */
	int departTimeNext();

	/**
	 * Returns the number of DVDPackages in line.
	 * @return the number DVDPackages in line
	 */
	int size();
}