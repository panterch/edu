package com.tddinaction.concurrency.waitforthreads;

import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;

public class DelayedFailureRunNotifier extends RunNotifier {
    private final RunNotifier notifier;

    private final List<Failure> failures;

    public DelayedFailureRunNotifier(RunNotifier notifier,
            List<Failure> failures) {
        this.notifier = notifier;
        this.failures = failures;
    }

    @Override
    public void addFirstListener(RunListener listener) {
        notifier.addFirstListener(listener);
    }

    @Override
    public void addListener(RunListener listener) {
        notifier.addListener(listener);
    }

    @Override
    public void removeListener(RunListener listener) {
        notifier.removeListener(listener);
    }

    @Override
    public void pleaseStop() {
        notifier.pleaseStop();
    }

    @Override
    public void fireTestStarted(Description desc)
            throws StoppedByUserException {
        failures.clear();
        notifier.fireTestStarted(desc);
    }

    @Override
    public void fireTestFinished(Description desc) {
        if (!failures.isEmpty()) {
            notifier.fireTestFailure(mergeFailures(failures));
        }
        notifier.fireTestFinished(desc);
    }

    private Failure mergeFailures(List<Failure> failures) {
        Throwable[] exceptions = new Throwable[failures.size()];
        for (int i = 0; i < failures.size(); i++) {
            exceptions[i] = failures.get(i).getException();
        }
        return new MergedFailure(failures.get(0).getDescription(),
                exceptions);
    }

    @Override
    public void fireTestFailure(Failure failure) {
        failures.add(failure);
    }

    @Override
    public void fireTestIgnored(Description desc) {
        notifier.fireTestIgnored(desc);
    }

    @Override
    public void fireTestRunFinished(Result result) {
        notifier.fireTestRunFinished(result);
    }

    @Override
    public void fireTestRunStarted(Description desc) {
        notifier.fireTestRunStarted(desc);
    }

}
