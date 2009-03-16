package com.tddinaction.mockobjects;

public class OrderProcessor {

    private PricingService pricingService;

    public void setPricingService(PricingService service) {
        this.pricingService = service;
    }

    public void process(Order order) {
        float discountPercentage = pricingService.getDiscountPercentage(order
                .getCustomer(), order.getProduct());
        float discountedPrice = order.getProduct().getPrice()
                * (1 - (discountPercentage / 100));
        order.getCustomer().setBalance(
                order.getCustomer().getBalance() - discountedPrice);
    }

}
