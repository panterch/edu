package com.tddinaction.ejb3.sessionbeans.mockjndi;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
public class PricingServiceBean implements PricingService {

    @EJB(beanInterface = DiscountService.class)
    private DiscountService discountService;

    @EJB(beanInterface = DiscountService.class)
    public void setDiscountService(DiscountService impl) {
        this.discountService = impl;
    }

    @PostConstruct
    public void ejbCreate() {
    }

    public Price discountedPrice(Product product, Account account) {
        try {
            DiscountService discounts = getDiscountService();
            int discount = discounts.getDiscountPercentage(account);
            float discountMultiplier = ((100 - discount) / 100.0f);
            return new Price((int) (product.getPrice() * discountMultiplier));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected DiscountService getDiscountService() throws NamingException {
        if (discountService != null) {
            return discountService;
        }
        String jndiName = "example/DiscountServiceBean/local";
        return (DiscountService) new InitialContext().lookup(jndiName);
    }
}
