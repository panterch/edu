/**
 * 
 */
package com.tddinaction.concurrency.waitforthreads;

import org.junit.runner.notification.Failure;

public class FailureCatchingThreadGroup extends ThreadGroup {

    private ThreadedExecutionContext context;

    public FailureCatchingThreadGroup(ThreadedExecutionContext context) {
        super("FailureCatchingThreadGroup");
        this.context = context;
    }

    public void uncaughtException(Thread t, Throwable e) {
        context.add(new Failure(context.getCurrentTest(),
                new ThrowableFromSpawnedThread(
                        Thread.currentThread(), e)));
    }
}