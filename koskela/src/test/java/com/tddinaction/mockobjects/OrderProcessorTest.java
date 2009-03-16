package com.tddinaction.mockobjects;

import org.junit.Test;
import static org.junit.Assert.*;

public class OrderProcessorTest {

    @Test
    public void testOrderProcessorWithMockObject() {
        // arrange
        float initialBalance = 100.0f;
        float listPrice = 30.0f;
        float discount = 10.0f;
        float expectedBalance = initialBalance
                - (listPrice * (1 - discount / 100));
        Customer customer = new Customer(initialBalance);
        Product product = new Product("TDD in Action", listPrice);
        OrderProcessor processor = new OrderProcessor();
        processor.setPricingService(new MockPricingService(discount));

        // act
        processor.process(new Order(customer, product));

        // assert
        assertEquals(expectedBalance, customer.getBalance(), 0.001f);
    }
}
