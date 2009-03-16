package com.tddinaction.ejb3.sessionbeans.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CalculatorBeanTest extends EJB3SessionBeanTestCase {

    private Calculator calc;

    @Before
    public void createObjectUnderTest() {
        calc = new CalculatorBean();
    }

    @Test
    public void addPositiveIntegers() {
        assertEquals(7, calc.add(2, 5));
    }

    @Test
    public void addNegativeIntegers() {
        assertEquals(-5, calc.add(-2, -3));
    }

    @Override
    protected Class<? extends Object> getBeanClass() {
        return CalculatorBean.class;
    }
}
