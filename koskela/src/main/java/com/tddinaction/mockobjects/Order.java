package com.tddinaction.mockobjects;

/**
 * @author Lasse Koskela
 */
public class Order {

    private Customer customer;
    private Product product;

    public Order(Customer customer, Product product) {
        this.customer = customer;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
    
    public Customer getCustomer() {
        return customer;
    }

}
