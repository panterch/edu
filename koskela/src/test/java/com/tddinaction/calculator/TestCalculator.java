package com.tddinaction.calculator;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestCalculator {

    @Test
    public void testAddition() throws Exception {
        assertEquals(3.0d, new Calculator().add(1.0d, 2.0d),
                0.0000001d);
    }

    @Test
    public void testSubtraction() throws Exception {
        assertEquals(-2.0d, new Calculator().subtract(1.0d, 3.0d),
                0.0000001d);
    }

    @Test
    public void testMultiplication() throws Exception {
        assertEquals(5.0d, new Calculator().multiply(2.0d, 2.5d),
                0.0000001d);
    }

    @Test
    public void testDivision() throws Exception {
        assertEquals(3.0d, new Calculator().divide(9.0d, 3.0d),
                0.0000001d);
    }
}
