package com.tddinaction.patterns.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterizedTestExample {

    @Parameters
    public static Collection<Object[]> parameters() {
        Object[][] data = new Object[][] { { 0, 0, 0 }, { 1, 1, 0 },
                { 2, 1, 1 }, { 3, 2, 1 }, { 4, 3, 1 }, { 5, 5, 0 },
                { 6, 8, -2 } };
        return Arrays.asList(data);
    }

    public int expected, input1, input2;

    public ParameterizedTestExample(int expected, int input1,
            int input2) {
        this.expected = expected;
        this.input1 = input1;
        this.input2 = input2;
    }

    @Test
    public void executeParameterizedTest() {
        int actual = new Calculator().add(input1, input2);
        assertEquals("Case [" + input1 + ", " + input2 + " = "
                + expected + "] failed.", expected, actual);
    }

    class Calculator {
        public int add(int m, int n) {
            // if (n < 0) {
            // n = n * -1;
            // }
            return m + n;
        }
    }

}