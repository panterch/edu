package com.tddinaction.calculator;

/**
 * This isn't a real implementation of a Presenter (as in Model-View-Presenter)
 * but rather a stub that does enough for demonstrating the necessary Fit stuff.
 * 
 * @author lkoskela
 */
public class CalculatorPresenter {

    private double display;

    private double left;

    private double right;

    private char operator;

    private Calculator calculator;

    public CalculatorPresenter(Calculator calculator) {
        this.calculator = calculator;
    }

    public double display() {
        return display;
    }

    public void inputValue(double input) {
        left = right;
        right = input;
        display = input;
    }

    public void evaluate() {
        switch (operator) {
        case '+':
            display = calculator.add(left, right);
            break;
        case '-':
            display = calculator.subtract(left, right);
            break;
        case '*':
            display = calculator.multiply(left, right);
            break;
        case '/':
            display = calculator.divide(left, right);
            break;
        default:
            System.err.println("Unknown operator: " + operator);
        }
    }

    public void plusClicked() {
        operator = '+';
    }

    public void minusClicked() {
        operator = '-';
    }

    public void multiplyClicked() {
        operator = '*';
    }

    public void divideClicked() {
        operator = '/';
    }

}
