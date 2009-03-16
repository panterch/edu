package com.tddinaction.concurrency.waitforthreads;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;

public interface ThreadedExecutionContext {

    void setCurrentTest(Description desc);

    Description getCurrentTest();

    void add(Failure failure);

}
