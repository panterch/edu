package com.tddinaction.concurrency.waitforthreads;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.internal.runners.InitializationError;
import org.junit.internal.runners.TestClassMethodsRunner;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

/**
 * A spawned threads-aware test runner implementation for JUnit 4.
 * 
 * @author lkoskela (Based on work by Greg Vaughn <gvaughn@delphis.com>)
 */
public class ThreadedClassMethodsRunner extends
        TestClassMethodsRunner implements ThreadedExecutionContext {

    private static final String MAIN_TEST_THREAD = "ThreadRunner-main";

    public boolean fTestThreadsRunning;

    private Description currentTest;

    public List<Failure> failures;

    private long endTime;

    private Method method;

    public ThreadedClassMethodsRunner(Class<?> test)
            throws InitializationError {
        super(test);
    }

    @Override
    protected void invokeTestMethod(Method method,
            RunNotifier notifier) {
        failures = Collections
                .synchronizedList(new ArrayList<Failure>());
        this.method = method;
        setCurrentTest(methodDescription(method));
        try {
            ThreadGroup grp = new FailureCatchingThreadGroup(this);
            startEndWatcherThread(notifier, grp);
            waitForTimeoutOrThreadCompletion();
            assertThreadGroupHasStopped(notifier, grp);
        } catch (Throwable any) {
            any.printStackTrace();
        }
    }

    private void assertThreadGroupHasStopped(
            final RunNotifier notifier, ThreadGroup group) {
        if (group.activeCount() > 1) { // Only EndWatcher should still run
            Throwable exception = new AssertionError(
                    "Not all spawned threads were stopped: "
                            + getNamesOfActiveThreads(group));
            notifier.fireTestFailure(new Failure(getCurrentTest(),
                    exception));
            notifier.fireTestFinished(getCurrentTest());
        }
    }

    public static List<String> getNamesOfActiveThreads(ThreadGroup grp) {
        List<String> names = ThreadUtils.namesOfActiveThreadsIn(grp);
        names.remove(MAIN_TEST_THREAD);
        return names;
    }

    private void waitForTimeoutOrThreadCompletion() {
        // Now the main JUnit thread waits to either timeout or be
        // notified. (Spawned threads might call notifyAll and wake the
        // JUnit thread.)
        long fTimeout = 10000;
        endTime = fTimeout + System.currentTimeMillis();
        while (fTestThreadsRunning
                && endTime > System.currentTimeMillis()) {
            try {
                synchronized (this) {
                    wait(fTimeout);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private ThreadGroupListener startEndWatcherThread(
            final RunNotifier notifier, ThreadGroup grp) {
        final Semaphore endWatcherStarted = new Semaphore(0);
        ThreadGroupListener endWatcher = new ThreadGroupListener(
                new LifecycleCallback() {
                    public void run() {
                        endWatcherStarted.release();
                        ThreadedClassMethodsRunner.this.runSuper(
                                method, notifier);
                    }

                    public void before() {
                        ThreadedClassMethodsRunner.this.fTestThreadsRunning = true;
                    }

                    public void after() {
                        synchronized (ThreadedClassMethodsRunner.this) {
                            ThreadedClassMethodsRunner.this.fTestThreadsRunning = false;
                            ThreadedClassMethodsRunner.this
                                    .notify(); // main JUnit thread
                        }
                    }
                });
        new Thread(grp, endWatcher, MAIN_TEST_THREAD).start();
        try {
            endWatcherStarted.tryAcquire(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(
                    "Problems starting EndWatcher thread?");
        }
        return endWatcher;
    }

    public static void log(String msg) {
        System.out.println("[" + Thread.currentThread().getName()
                + "] ["
                + new Date(Calendar.getInstance().getTimeInMillis())
                + "] " + msg);
    }

    protected void runSuper(final Method method,
            final RunNotifier notifier) {
        // TODO: encapsulate 'failures' behind an interface
        super.invokeTestMethod(method, new DelayedFailureRunNotifier(
                notifier, failures));
    }

    public static void waitForSpawnedThreads() {
        ThreadGroup grp = Thread.currentThread().getThreadGroup();
        assertThreadGroupIsWatched(grp);
        ThreadUtils.waitWhileActiveThreadCountIsHigherThan(1, grp);
    }

    private static void assertThreadGroupIsWatched(ThreadGroup grp) {
        Thread[] threads = new Thread[grp.activeCount() * 2];
        int threadCount = grp.enumerate(threads);
        boolean endWatcherFound = false;
        for (int i = 0; i < threadCount; i++) {
            if (threads[i].getName().equals(MAIN_TEST_THREAD)) {
                endWatcherFound = true;
                break;
            }
        }
        Assert.assertTrue("No EndWatcher thread in ThreadGroup."
                + " Have you defined ThreadedRunner for @RunWith?",
                endWatcherFound);
    }

    public synchronized Description getCurrentTest() {
        return currentTest;
    }

    public synchronized void setCurrentTest(Description desc) {
        currentTest = desc;
    }

    public synchronized void add(Failure failure) {
        failures.add(failure);
    }
}
