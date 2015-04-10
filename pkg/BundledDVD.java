package edu.ncsu.csc216.shipping_simulator.pkg;
import edu.ncsu.csc216.shipping_simulator.queues.*;

import java.awt.*;

/**
 * Extends DVDPackage to include the algorithm from UC5 S3 and include color functionality.
 * @author benroaman
 *
 */
public class BundledDVD extends DVDPackage {
	
	/**The color of the package in the GUI.*/
	private Color color;
	
	/**
	 * Initializes BundledDVD with specified order and process times, and sets color to black.
	 * @param orderTime the time at which the package is ordered.
	 * @param processTime the time it takes to process the package at a loading dock.
	 */
	public BundledDVD(int orderTime, int processTime) {
		super(orderTime, processTime);
		color = Color.black;
	}
	
	/**
	 * Getter for the BundledDVD's GUI color.
	 * @return the color of the package in the GUI.
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 * Chooses a LoadingDock based on the algorithm in UC5 S3.
	 * @param dock the array of loading docks from which to choose.
	 */
	public void getInLine(LoadingDock[] dock) {
		int index = (int)(dock.length * 0.75); //skip to jumbo loading docks
		int best = dock[index].size(); //set best length to first available length
		for (int i = (int)(dock.length * 0.75); i < dock.length; i++) {
			if (dock[i].size() < best) {
				best = dock[i].size();
				index = i;
			}
		}
		
		setDockIndex(index);
		dock[index].addPackage(this);

	}

}
