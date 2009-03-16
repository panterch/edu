package com.tddinaction.concurrency.methodblocking;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Assert;
import org.junit.Test;

public class TestBlackMarket {

    @Test
    public void testBlockingBehavior() throws Exception {
        final AtomicBoolean blocked = new AtomicBoolean(true);
        Thread buyer = new Thread() {
            @Override
            public void run() {
                try {
                    new BlackMarket().buyTicket();
                    blocked.set(false);
                } catch (InterruptedException success) {
                }
            }
        };
        buyer.start();
        Thread.sleep(1000);
        buyer.interrupt();
        buyer.join(1000);
        Assert.assertFalse("Thread didn't interrupt!", buyer
                .isAlive());
        Assert.assertTrue("Method didn't block!", blocked.get());
    }
}
