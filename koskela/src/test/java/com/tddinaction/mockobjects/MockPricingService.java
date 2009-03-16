package com.tddinaction.mockobjects;

public class MockPricingService extends PricingService {

    private float discount;

    public MockPricingService(float discount) {
        this.discount = discount;
    }

    public float getDiscountPercentage(Customer c, Product p) {
        return discount;
    }

}
