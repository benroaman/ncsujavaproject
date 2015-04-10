package edu.ncsu.csc216.shipping_simulator.simulation;

import edu.ncsu.csc216.shipping_simulator.queues.ConveyerBelt;

/**
 * A calendar that determines the order in which packages leave their queues.
 * Packages leaving constitute the events.
 * 
 * @author Jo Perry
 * @version 3.0
 * @see ReceivingPlatform
 * @see LoadingDock
 */
public class EventCalendar {

	/** LoadingDock collection for the simulation */
	private ConveyerBelt[] loadingDock;
	/** Queue of DVDOrders for the simulation */
	private ConveyerBelt receivingPlatform;

	/**
	 * Initializes the queues in the EventCalendar.
	 * 
	 * @param loadingDock
	 *            all loading docks
	 * @param receivingPlatform
	 *            receiving platform (feeds the loading docks)
	 */
	public EventCalendar(ConveyerBelt[] loadingDock,
			ConveyerBelt receivingPlatform) {
		this.loadingDock = loadingDock;
		this.receivingPlatform = receivingPlatform;
	}

	/**
	 * Determines which loading dock or receiving platform contains the next
	 * item to be processed. If all lines are empty, an IllegalStateException is
	 * thrown.
	 * 
	 * @return The line whose front item has the smallest time.
	 */
	public ConveyerBelt nextToBeProcessed() {
		// Time next item leaves queue
		int nextLoadingDockEntry = receivingPlatform.departTimeNext();
		int soonest = 0;
		for (int k = 1; k < loadingDock.length; k++)
			if (loadingDock[k].departTimeNext() < loadingDock[soonest]
					.departTimeNext())
				soonest = k;
		int departureAtLoadingDock = loadingDock[soonest].departTimeNext();

		// Are all queues empty?
		if (nextLoadingDockEntry == Integer.MAX_VALUE
				&& departureAtLoadingDock == Integer.MAX_VALUE) {
			throw new IllegalStateException();
		}

		// Is the next event a package leaving the receiving platform?
		if (nextLoadingDockEntry <= departureAtLoadingDock) {
			return receivingPlatform;
		}

		return loadingDock[soonest];
	}
}