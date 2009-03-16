package com.tddinaction.fit.fixtures;

import com.tddinaction.calculator.Calculator;
import com.tddinaction.calculator.CalculatorPresenter;

public class CalculatorActionFixture extends fit.ActionFixture {

    private CalculatorPresenter presenter;

    public CalculatorActionFixture() {
        // associate ourselves with the 'current actor' of the super class
        actor = this;
        presenter = new CalculatorPresenter(new Calculator());
    }

    public double display() {
        return presenter.display();
    }

    public void equals() {
        presenter.evaluate();
    }

    public void input(double input) {
        presenter.inputValue(input);
    }

    public void plus() {
        presenter.plusClicked();
    }

    public void minus() {
        presenter.minusClicked();
    }

    public void multiply() {
        presenter.multiplyClicked();
    }

    public void divide() {
        presenter.divideClicked();
    }

}
