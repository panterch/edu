package com.tddinaction.patterns.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import org.junit.Test;
import org.laughingpanda.beaninject.Inject;

public class PrivilegedAccessExample {

    public static class LegacyCode {
        private static int randomNumber = new Random().nextInt();

        public static void saySomethingTo(PrintStream out) {
            out.println("Random number is " + randomNumber);
        }
    }

    @Test
    public void substitutePrivateFieldWithOurStuff() throws Exception {
        Inject.staticField("randomNumber").of(LegacyCode.class).with(
                5);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        LegacyCode.saySomethingTo(new PrintStream(out));
        assertEquals("Random number is 5", out.toString().trim());
    }
}
