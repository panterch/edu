package com.tddinaction.mockobjects;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OrderProcessorEasymockTest {

    @Test
    public void testOrderProcessorWithEasyMock() {
        // arrange
        float initialBalance = 100.0f;
        float listPrice = 30.0f;
        float discount = 10.0f;
        float expectedBalance = initialBalance
                - (listPrice * (1 - discount / 100));

        Customer customer = new Customer(initialBalance);
        Product product = new Product("TDD in Action", listPrice);

        // record expected collaboration for the PricingService
        PricingService ps = createMock(PricingService.class);
        expect(ps.getDiscountPercentage(customer, product))
                .andReturn(discount);
        replay(ps);

        // act
        OrderProcessor processor = new OrderProcessor();
        processor.setPricingService(ps);
        processor.process(new Order(customer, product));

        // assert
        assertEquals(expectedBalance, customer.getBalance(), 0.001f);
        verify(ps);
    }
}
