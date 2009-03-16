package com.tddinaction.concurrency.waitforthreads;

import org.junit.internal.runners.InitializationError;
import org.junit.internal.runners.TestClassRunner;

/**
 * A spawned threads-aware test runner implementation for JUnit 4.
 * 
 * @author lkoskela
 */
public class ThreadedRunner extends TestClassRunner {

    public ThreadedRunner(Class<?> test) throws InitializationError {
        super(test, new ThreadedClassMethodsRunner(test));
    }

    public static void waitForSpawnedThreads() {
        ThreadedClassMethodsRunner.waitForSpawnedThreads();
    }
}
