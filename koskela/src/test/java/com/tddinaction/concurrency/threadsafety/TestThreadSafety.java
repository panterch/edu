package com.tddinaction.concurrency.threadsafety;

import java.util.concurrent.CyclicBarrier;

import org.junit.Assert;
import org.junit.Test;

public class TestThreadSafety {

    @Test
    public void testBasicFunctionality() throws Exception {
        Counter counter = new Counter();
        Assert.assertEquals(0, counter.value());
        counter.increment();
        Assert.assertEquals(1, counter.value());
        counter.increment();
        Assert.assertEquals(2, counter.value());
    }

    @Test
    public void testForThreadSafety() throws Exception {
        final Counter codeUnderTest = new Counter();
        final int numberOfThreads = 20;
        final int incrementsPerThread = 100;

        Runnable runnable = new Runnable() {
            public void run() {
                for (int i = 0; i < incrementsPerThread; i++) {
                    codeUnderTest.increment();
                }
            }
        };
        for (int i = 0; i < numberOfThreads; i++) {
            new Thread(runnable).start();
        }
        Thread.sleep(500);
        Assert.assertEquals(numberOfThreads * incrementsPerThread,
                codeUnderTest.value());
    }

    @Test
    public void testForThreadSafetyUsingBarrierToMaximizeConcurrency()
            throws Exception {
        final Counter codeUnderTest = new Counter();
        final int numberOfThreads = 20;
        final int incrementsPerThread = 10000;
        CyclicBarrier entryBarrier = new CyclicBarrier(
                numberOfThreads + 1);
        CyclicBarrier exitBarrier = new CyclicBarrier(
                numberOfThreads + 1);

        Runnable runnable = new Runnable() {
            public void run() {
                for (int i = 0; i < incrementsPerThread; i++) {
                    codeUnderTest.increment();
                }
            }
        };
        for (int i = 0; i < numberOfThreads; i++) {
            new SynchedThread(runnable, entryBarrier, exitBarrier)
                    .start();
        }
        Assert.assertEquals(0, codeUnderTest.value());
        entryBarrier.await(); // let threads proceed when all have been
        // started
        exitBarrier.await(); // wait for all threads to finish their
                                // execution
        Assert.assertEquals(numberOfThreads * incrementsPerThread,
                codeUnderTest.value());
    }
}
