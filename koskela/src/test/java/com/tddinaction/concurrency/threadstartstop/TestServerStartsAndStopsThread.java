package com.tddinaction.concurrency.threadstartstop;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestServerStartsAndStopsThread {

    private StartStopSynchronizedThread thread;

    @Test
    public void testStartingAndStoppingThreadsThroughAnExecutorService()
            throws Exception {
        ThreadFactory threadFactory = new ThreadFactory() {
            public Thread newThread(Runnable task) {
                thread = new StartStopSynchronizedThread(task);
                return thread;
            }
        };
        Server server = new Server();
        server.setThreadFactory(threadFactory);

        server.start();
        thread.shouldBeStartedWithin(1, TimeUnit.SECONDS);

        server.stop();
        thread.shouldBeStoppedWithin(1, TimeUnit.SECONDS);
    }
}
