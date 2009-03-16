package com.tddinaction.concurrency.waitforthreads;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class LoggingRunListener extends RunListener {

    public static void log(String msg) {
        System.out.println("[" + Thread.currentThread().getName()
                + "] " + msg);
    }

    @Override
    public void testStarted(Description desc) throws Exception {
        log("testStarted(\"" + desc.toString() + "\")");
    }

    @Override
    public void testRunStarted(Description desc) throws Exception {
        log("testRunStarted(\"" + desc.toString() + "\")");
    }

    @Override
    public void testFailure(Failure f) throws Exception {
        log("testFailure(" + f.getMessage() + ", "
                + f.getDescription() + ")");
    }

    @Override
    public void testFinished(Description desc) throws Exception {
        log("testFinished(\"" + desc.toString() + "\")");
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        log("testRunFinished(successful=" + result.wasSuccessful()
                + ")");
    }

    @Override
    public void testIgnored(Description desc) throws Exception {
        log("testIgnored(\"" + desc.toString() + "\")");
    }
}
