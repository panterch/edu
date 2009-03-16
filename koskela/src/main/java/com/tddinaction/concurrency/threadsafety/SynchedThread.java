/**
 * 
 */
package com.tddinaction.concurrency.threadsafety;

import java.util.concurrent.CyclicBarrier;

public class SynchedThread extends Thread {

    private CyclicBarrier entryBarrier;

    private final CyclicBarrier exitBarrier;

    public SynchedThread(Runnable runnable,
            CyclicBarrier entryBarrier, CyclicBarrier exitBarrier) {
        super(runnable);
        this.entryBarrier = entryBarrier;
        this.exitBarrier = exitBarrier;
    }

    @Override
    public void run() {
        try {
            entryBarrier.await();
            super.run();
            exitBarrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}