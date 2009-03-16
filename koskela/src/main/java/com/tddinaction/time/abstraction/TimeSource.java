package com.tddinaction.time.abstraction;

public interface TimeSource {

	/**
	 * Returns the current time in milliseconds from epoch.
	 */
	long millis();
}
