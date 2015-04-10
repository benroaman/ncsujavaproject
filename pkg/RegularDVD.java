package edu.ncsu.csc216.shipping_simulator.pkg;
import edu.ncsu.csc216.shipping_simulator.queues.*;

import java.awt.*;


/**
 * Extends DVDPackage to include the algorithm from UC5 S2 and include color functionality.
 * @author benroaman
 *
 */
public class RegularDVD extends DVDPackage {
	
	/**The package's color*/
	private Color color;
	
	
	/**
	 * Initializes RegularDVD with specified order and process times, and sets color to red.
	 * @param orderTime the time at which the package is ordered.
	 * @param processTime the time it takes to process the package at a loading dock.
	 */
	public RegularDVD(int orderTime, int processTime) {
		super(orderTime, processTime);
		color = Color.red;
	}

	/**
	 * getter for the GUI color of the package.
	 * @return the color of the package in the GUI.
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Chooses a loading dock based on the algorithm in UC5 S2.
	 * @param dock the array of loading docks from which to choose
	 */
	public void getInLine(LoadingDock[] dock) {
		int index = 1; //skips the priority loading dock
		int best = dock[1].size(); //sets best option to first option
		for (int i = 1; i < dock.length; i++) {
			if (dock[i].size() < best) {
				best = dock[i].size();
				index = i;
			}
		}
		setDockIndex(index);
		dock[index].addPackage(this);
		
	}
}
