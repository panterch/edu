package com.tddinaction.patterns.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tddinaction.mockobjects.Customer;
import com.tddinaction.mockobjects.Order;
import com.tddinaction.mockobjects.OrderProcessor;
import com.tddinaction.mockobjects.PricingService;
import com.tddinaction.mockobjects.Product;

public class SelfShuntExample extends PricingService {

    private final float discount = 10.0f;

    @Test
    public void testOrderProcessorWithMockObject() {
        float initialBalance = 100.0f;
        float listPrice = 30.0f;
        float expectedBalance = initialBalance
                - (listPrice * (1 - discount / 100));
        Customer customer = new Customer(initialBalance);
        Product product = new Product("TDD in Action", listPrice);
        OrderProcessor processor = new OrderProcessor();

        processor.setPricingService(this);

        processor.process(new Order(customer, product));
        assertEquals(expectedBalance, customer.getBalance(), 0.001f);
    }

    // implements the PricingService#getDiscountPercentage API
    @Override
    public float getDiscountPercentage(Customer c, Product p) {
        return discount;
    }
}
