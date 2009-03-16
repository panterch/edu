/**
 * 
 */
package com.tddinaction.swing.mvp.common;

public class GreenBarModelStub implements GreenBarModel {

    public boolean testsWillPassOnNextTestRun;

    private int numberOfFailures;

    private int numberOfTests;

    public void runTests() {
        numberOfTests = 2;
        numberOfFailures = testsWillPassOnNextTestRun ? 0 : 2;
    }

    public int numberOfFailures() {
        return numberOfFailures;
    }

    public int numberOfTests() {
        return numberOfTests;
    }
}