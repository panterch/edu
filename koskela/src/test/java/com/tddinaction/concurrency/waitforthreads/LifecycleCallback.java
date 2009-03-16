package com.tddinaction.concurrency.waitforthreads;

public interface LifecycleCallback {

    void run();

    void before();

    void after();

}
