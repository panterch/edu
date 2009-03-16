package com.tddinaction.calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private static final int HISTORY_SIZE = 6;

    private static Calculator instance;

    public static Calculator getInstance() {
        if (instance == null) {
            instance = new Calculator();
        }
        return instance;
    }

    private List<Operation> operationHistory = new ArrayList<Operation>();

    private void addToHistory(double left, char operator, double right) {
        Operation operation = new Operation();
        operation.left = left;
        operation.right = right;
        operation.operator = operator;
        operation.index = operationHistory.size() + 1;
        operationHistory.add(operation);
        if (operationHistory.size() > HISTORY_SIZE) {
            operationHistory.remove(0);
        }
    }

    public double add(double left, double right) {
        addToHistory(left, '+', right);
        return left + right;
    }

    public double subtract(double left, double right) {
        addToHistory(left, '-', right);
        return left - right;
    }

    public double multiply(double left, double right) {
        addToHistory(left, '*', right);
        return left * right;
    }

    public double divide(double left, double right) {
        addToHistory(left, '/', right);
        if (right == 0.0d) {
            throw new IllegalArgumentException(
                    "Can't divide with zero");
        }
        return left / right;
    }

    public List<Operation> getOperations() {
        return operationHistory;
    }

}
