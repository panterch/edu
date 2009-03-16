package com.tddinaction.exceptions;

import static org.junit.Assert.fail;

import org.junit.Test;

public class TestExpectedExceptions {

    @Test
    public void testForExpectedExceptionWithTryCatch()
            throws Exception {
        try {
            Integer.parseInt("This should blow up...");
            fail("Should've thrown an exception!");
        } catch (NumberFormatException expected) {
        }
    }

    @Test(expected = NumberFormatException.class)
    public void testForExpectedExceptionWithAnnotation()
            throws Exception {
        Integer.parseInt("This should blow up...");
    }
}
