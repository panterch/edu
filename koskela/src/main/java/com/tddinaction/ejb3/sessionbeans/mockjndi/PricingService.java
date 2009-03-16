package com.tddinaction.ejb3.sessionbeans.mockjndi;

import javax.ejb.Local;

@Local
public interface PricingService {

    Price discountedPrice(Product product, Account account);

}
