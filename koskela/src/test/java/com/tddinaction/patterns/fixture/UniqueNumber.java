package com.tddinaction.patterns.fixture;

public class UniqueNumber {

    private static volatile int counter = 1;

    public static synchronized long next() {
        return counter++;
    }
}
