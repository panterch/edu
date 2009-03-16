package com.tddinaction.time.abstraction;

import java.util.Calendar;
import java.util.Date;

/**
 * Abstraction for system time.
 * 
 * @author Lasse Koskela
 */
public class SystemTime {

	private static TimeSource defaultSource = new TimeSource() {
		public long millis() {
			return System.currentTimeMillis();
		}
	};

	private static TimeSource source = defaultSource;

	public static long asMillis() {
		return getTimeSource().millis();
	}

	public static Calendar asCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(getTimeSource().millis());
		return cal;
	}

	public static Date asDate() {
		return new Date(getTimeSource().millis());
	}

	public static void reset() {
		setTimeSource(null);
	}

	public static void setTimeSource(TimeSource source) {
		SystemTime.source = source;
	}

	private static TimeSource getTimeSource() {
		return (source != null ? source : defaultSource);
	}

}
