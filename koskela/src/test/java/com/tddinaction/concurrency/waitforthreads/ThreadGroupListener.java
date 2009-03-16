/**
 * 
 */
package com.tddinaction.concurrency.waitforthreads;

public class ThreadGroupListener implements Runnable {

    private LifecycleCallback callback;

    public ThreadGroupListener(LifecycleCallback callback) {
        this.callback = callback;
    }

    // This will run within the custom ThreadGroup. First it invokes
    // a callback so if the candidate Test spawns threads, they will
    // by default be part of the same ThreadGroup. Then it watches
    // for all threads in the group to finish before notifying the
    // main JUnit thread.
    public void run() {
        callback.before();
        callback.run();
        waitForThreadsToFinish();
        callback.after();
    }

    private void waitForThreadsToFinish() {
        ThreadGroup grp = Thread.currentThread().getThreadGroup();
        ThreadUtils.waitWhileActiveThreadCountIsHigherThan(1, grp);
    }

}