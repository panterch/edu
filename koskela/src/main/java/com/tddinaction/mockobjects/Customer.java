package com.tddinaction.mockobjects;

public class Customer {

    private float balance;

    public Customer(float startingBalance) {
        this.balance = startingBalance;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float newBalance) {
        this.balance = newBalance;
    }

}
