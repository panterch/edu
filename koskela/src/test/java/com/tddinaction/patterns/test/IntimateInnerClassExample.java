package com.tddinaction.patterns.test;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.tddinaction.concurrency.threadstartstop.Server;
import com.tddinaction.concurrency.threadstartstop.StartStopSynchronizedThread;

public class IntimateInnerClassExample {

    private StartStopSynchronizedThread thread;

    private class FakeThreadFactory implements ThreadFactory {
        public Thread newThread(Runnable task) {
            thread = new StartStopSynchronizedThread(task);
            return thread;
        }
    }

    @Test
    public void testStartingAndStoppingThreadsThroughAnExecutorService()
            throws Exception {
        Server server = new Server();
        server.setThreadFactory(new FakeThreadFactory());

        server.start();
        thread.shouldBeStartedWithin(1, TimeUnit.SECONDS);

        server.stop();
        thread.shouldBeStoppedWithin(1, TimeUnit.SECONDS);
    }
}
