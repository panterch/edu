package com.tddinaction.ejb3.sessionbeans.mockjndi;

import javax.ejb.Local;

@Local
public interface DiscountService {

    int getDiscountPercentage(Account account);

}
