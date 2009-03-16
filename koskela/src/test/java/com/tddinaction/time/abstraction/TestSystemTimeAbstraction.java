package com.tddinaction.time.abstraction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Test;

public class TestSystemTimeAbstraction {

    @After
    public void resetTimeSource() {
        SystemTime.reset();
    }

    @Test
    public void clockReturnsValidTimeInMilliseconds()
            throws Exception {
        long before = System.currentTimeMillis();
        long clock = SystemTime.asMillis();
        long after = System.currentTimeMillis();
        assertBetween(before, clock, after);
    }

    @Test
    public void clockReturnsFakedTimeInMilliseconds()
            throws Exception {
        final long fakeTime = 10000000L;
        SystemTime.setTimeSource(new TimeSource() {
            public long millis() {
                return fakeTime;
            }
        });
        long clock = SystemTime.asMillis();
        assertEquals("Clock should've returned fake time", fakeTime,
                clock);
    }

    private void assertBetween(long before, long actual, long after) {
        assertTrue("Clock should've returned something between "
                + before + " and " + after + " (instead of " + actual
                + ")", before <= actual && actual <= after);
    }

    @Test
    public void clockReturnsValidCalendarObjects() throws Exception {
        long before = Calendar.getInstance().getTimeInMillis();
        long clock = SystemTime.asCalendar().getTimeInMillis();
        long after = Calendar.getInstance().getTimeInMillis();
        assertBetween(before, clock, after);
    }

    @Test
    public void clockReturnsFakedCalendarObjects() throws Exception {
        final long fakeTime = 10000000L;
        SystemTime.setTimeSource(new TimeSource() {
            public long millis() {
                return fakeTime;
            }
        });
        long clock = SystemTime.asCalendar().getTimeInMillis();
        assertEquals("Clock should've returned fake time", fakeTime,
                clock);
    }

    @Test
    public void clockReturnsValidDateObjects() throws Exception {
        long before = new Date().getTime();
        long clock = SystemTime.asDate().getTime();
        long after = new Date().getTime();
        assertBetween(before, clock, after);
    }

    @Test
    public void clockReturnsFakedDateObjects() throws Exception {
        final long fakeTime = 10000000L;
        SystemTime.setTimeSource(new TimeSource() {
            public long millis() {
                return fakeTime;
            }
        });
        long clock = SystemTime.asDate().getTime();
        assertEquals("Clock should've returned fake time", fakeTime,
                clock);
    }
}
