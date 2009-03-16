/**
 * 
 */
package com.tddinaction.concurrency.threadstartstop;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;

public class StartStopSynchronizedThread extends Thread {

    private CountDownLatch threadStarted;

    private CountDownLatch threadStopped;

    public StartStopSynchronizedThread(Runnable task) {
        super(task);
        threadStarted = new CountDownLatch(1);
        threadStopped = new CountDownLatch(1);
    }

    @Override
    public void run() {
        threadStarted.countDown();
        super.run();
        threadStopped.countDown();
    }

    public void shouldBeStartedWithin(long timeout, TimeUnit unit)
            throws InterruptedException {
        Assert.assertTrue("Thread not started within timeout.",
                threadStarted.await(timeout, unit));
    }

    public void shouldBeStoppedWithin(int timeout, TimeUnit unit)
            throws InterruptedException {
        Assert.assertTrue("Thread not stopped within timeout.",
                threadStopped.await(timeout, unit));
    }
}