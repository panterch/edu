package com.tddinaction.fit.fixtures;

import java.util.List;

import com.tddinaction.calculator.Calculator;
import com.tddinaction.calculator.Operation;

public class CalculatorHistoryFixture extends fit.RowFixture {

    public Class getTargetClass() {
        return Operation.class;
    }

    public Object[] query() throws Exception {
        List<Operation> history = Calculator.getInstance()
                .getOperations();
        return (Operation[]) history.toArray(new Operation[history
                .size()]);
    }

}
