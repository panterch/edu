package com.tddinaction.ejb3.sessionbeans.calculator;

import javax.ejb.Local;

@Local
public interface Calculator {

    public int add(int a, int b);
}
