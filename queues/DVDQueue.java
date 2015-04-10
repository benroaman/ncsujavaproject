package edu.ncsu.csc216.shipping_simulator.queues;

import java.util.LinkedList;

import edu.ncsu.csc216.shipping_simulator.pkg.DVDPackage;

/**
 * Implements a simple queue (first-in, first-out) of DVDPackages.
 * @author Jo Perry
 */
public class DVDQueue {
	
	/** The underlying queue data structure. */
	private LinkedList<DVDPackage> queue;  
	
	/**
	 * Creates an empty queue.
	 */
	public DVDQueue() { 
		queue = new LinkedList<DVDPackage>(); 
	}
	
	/**
	 * Returns the number of elements in the queue.
	 * @return the number of elements
	 */
	public int size() {
		return queue.size();
	}
	
	/**
	 * Adds a new item to the back of the queue.
	 * @param dvd the item to add
	 */
	public void add(DVDPackage dvd){
		queue.addLast(dvd);
	}
	
	/**
	 * Removes and returns the front item from the queue. Throws a NoSuchElementException
	 * if the queue is empty.
	 * @return the DVDPackage at the front of the queue
	 */
	public DVDPackage remove() {
		//The call to queue.removeFirst() will throw the NoSuchElementException if
		//the queue is empty.  This exception is allowed to pass through this class.
		return queue.removeFirst();
	}
	
	/**
	 * Gets the front element of the queue without removing it, or null
	 * if the queue is empty.
	 * @return the front element or null if the queue is empty
	 */
	public DVDPackage front() {
		return queue.peek();
	}
	
	/**
	 * Returns true if the queue is empty, false otherwise.
	 * @return true if the queue has no elements
	 */
	public boolean isEmpty() {
		return queue.isEmpty();
	}
}