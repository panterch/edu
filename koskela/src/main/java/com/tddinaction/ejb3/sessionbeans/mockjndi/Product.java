package com.tddinaction.ejb3.sessionbeans.mockjndi;

public class Product {

    private String name;

    private int priceInCents;

    public Product(String name, int priceInCents) {
        this.name = name;
        this.priceInCents = priceInCents;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return priceInCents;
    }

}
