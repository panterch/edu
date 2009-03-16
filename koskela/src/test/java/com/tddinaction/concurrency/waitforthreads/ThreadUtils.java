package com.tddinaction.concurrency.waitforthreads;

import java.util.ArrayList;
import java.util.List;

public class ThreadUtils {

    public static void waitWhileActiveThreadCountIsHigherThan(
            int threadCount, ThreadGroup threadGroup) {
        while (threadGroup.activeCount() > threadCount) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> namesOfActiveThreadsIn(ThreadGroup grp) {
        List<String> threadNames = new ArrayList<String>();
        Thread[] threads = new Thread[grp.activeCount() * 10];
        int count = grp.enumerate(threads);
        for (int i = 0; i < count; i++) {
            threadNames.add(threads[i].getName());
        }
        return threadNames;
    }

}
