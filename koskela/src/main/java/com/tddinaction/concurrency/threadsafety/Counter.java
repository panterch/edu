/**
 * 
 */
package com.tddinaction.concurrency.threadsafety;

public class Counter {
	private int counter;

	public synchronized void increment() {
		counter++;
	}

	public int value() {
		return counter;
	}
}