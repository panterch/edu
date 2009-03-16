package com.tddinaction.concurrency.waitforthreads;

import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

public class ThrowableFromSpawnedThread extends Throwable {

    private String threadName;

    public ThrowableFromSpawnedThread(Thread thread, Throwable e) {
        super(e);
        this.threadName = thread.getName();
    }

    @Override
    public String getMessage() {
        return "[Occurred in thread '" + threadName + "'] "
                + getCause().getMessage();
    }

    @Override
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override
    public void printStackTrace(PrintWriter s) {
//        s.print("[in thread '" + threadName + "'] ");
        getCause().printStackTrace(s);
    }

    @Override
    public void printStackTrace(PrintStream s) {
        printStackTrace(new PrintWriter(new OutputStreamWriter(s)));
    }

    public String getThreadName() {
        return threadName;
    }
}
