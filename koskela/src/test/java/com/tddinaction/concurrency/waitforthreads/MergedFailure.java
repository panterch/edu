package com.tddinaction.concurrency.waitforthreads;

import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;

public class MergedFailure extends Failure {

    public MergedFailure(Description description,
            Throwable[] thrownExceptions) {
        super(description, merge(thrownExceptions));
    }

    private static Throwable merge(final Throwable[] exceptions) {
        if (exceptions.length == 1) {
            return exceptions[0];
        }
        Throwable merged = new Throwable() {
            @Override
            public String getMessage() {
                StringBuffer s = new StringBuffer();
                s.append("MULTIPLE FAILURES:");
                for (Throwable t : exceptions) {
                    s.append("\n");
                    StringWriter sw = new StringWriter();
                    printThreadInformation(new PrintWriter(sw), t);
                    s.append(sw.toString()).append("\n");
                    s.append(t.getMessage());
                }
                return s.toString();
            }

            @Override
            public void printStackTrace() {
                printStackTrace(System.err);
            }

            @Override
            public void printStackTrace(PrintWriter s) {
                s.println("MULTIPLE FAILURES:");
                for (Throwable t : exceptions) {
                    printThreadInformation(s, t);
                    s.println();
                    t.printStackTrace(s);
                }
            }

            private void printThreadInformation(PrintWriter s,
                    Throwable t) {
                String type = "Thread";
                String name = Thread.currentThread().getName();
                if (t instanceof ThrowableFromSpawnedThread) {
                    ThrowableFromSpawnedThread spawned = (ThrowableFromSpawnedThread) t;
                    type = "Spawned thread";
                    name = spawned.getThreadName();
                }
                s.print("[");
                s.print(type);
                s.print(" '");
                s.print(name);
                s.print("']");
            }

            @Override
            public void printStackTrace(PrintStream s) {
                printStackTrace(new PrintWriter(
                        new OutputStreamWriter(s)));
            }
        };
        return merged;
    }
}