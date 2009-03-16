package com.tddinaction.patterns.test;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.tddinaction.concurrency.threadstartstop.Server;
import com.tddinaction.concurrency.threadstartstop.StartStopSynchronizedThread;

public class IntimateAnonymousInnerClassExample {

    private StartStopSynchronizedThread thread;

    @Test
    public void testStartingAndStoppingThreadsThroughAnExecutorService()
            throws Exception {
        Server server = new Server();
        server.setThreadFactory(new ThreadFactory() {
            public Thread newThread(Runnable task) {
                thread = new StartStopSynchronizedThread(task);
                return thread;
            }
        });

        server.start();
        thread.shouldBeStartedWithin(1, TimeUnit.SECONDS);

        server.stop();
        thread.shouldBeStoppedWithin(1, TimeUnit.SECONDS);
    }
}
