package edu.ncsu.csc216.shipping_simulator.pkg;
import edu.ncsu.csc216.shipping_simulator.queues.*;
import java.awt.*;

/**
 * Extends DVDPackage to include the algorithm from UC5 S1 and include color functionality.
 * @author benroaman
 *
 */
public class PriorityDVD extends DVDPackage {
	
	/** The packages color in the GUI*/
	private Color color;
	
	/**
	 * Initializes PriorityDVD with specified order and process times, and sets color to magenta.
	 * @param orderTime the time at which the package is ordered.
	 * @param processTime the time it takes to process the package at a loading dock.
	 */
	public PriorityDVD(int orderTime, int processTime) {
		super(orderTime, processTime);
		color = Color.magenta;
	}
	
	/**
	 * getter for color
	 * @return the color of the package in the GUI.
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Chooses a LoadingDock based on the algorithm in UC5 S1.
	 * @param dock the array of loading docks from which to choose.
	 */
	public void getInLine(LoadingDock[] dock) {
		int index = 0;
		int best = dock[0].size();
		for (int i = 0; i < dock.length; i++) {
			if (dock[i].size() < best) {
				best = dock[i].size();
				index = i;
			}
		}
		setDockIndex(index);
		dock[index].addPackage(this);

	}
	
	

}
