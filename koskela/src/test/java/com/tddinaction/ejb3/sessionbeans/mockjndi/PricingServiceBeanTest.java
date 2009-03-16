package com.tddinaction.ejb3.sessionbeans.mockjndi;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.naming.InitialContext;

import junit.framework.JUnit4TestAdapter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockejb.jndi.MockContextFactory;

public class PricingServiceBeanTest {

    @Before
    public void setUp() throws Exception {
        MockContextFactory.setAsInitial();
    }

    @After
    public void tearDown() {
        MockContextFactory.revertSetAsInitial();
    }

    @Test
    public void discountBasedOnVolumeOfPastPurchases1()
            throws Exception {
        Account account = new MockAccount();
        Product product = new Product("PROD001", 10000);

        // create dependency
        DiscountService discountService = createMock(DiscountService.class);
        expect(discountService.getDiscountPercentage(account))
                .andReturn(25);
        replay(discountService);

        // bind dependency into fake JNDI tree
        InitialContext context = new InitialContext();
        context.bind("example/DiscountServiceBean/local",
                discountService);

        // create the bean under test
        PricingServiceBean bean = new PricingServiceBean();

        // invoke the business logic
        Price price = bean.discountedPrice(product, account);

        // verify results
        verify(discountService);
        assertEquals(new Price(7500), price);
    }

    @Test
    public void discountBasedOnVolumeOfPastPurchases2()
            throws Exception {
        Account account = new MockAccount();
        Product product = new Product("PROD001", 10000);

        // create dependency
        final DiscountService discountService = createMock(DiscountService.class);
        expect(discountService.getDiscountPercentage(account))
                .andReturn(25);
        replay(discountService);

        // create the bean under test and override the getter
        PricingServiceBean bean = new PricingServiceBean() {
            @Override
            protected DiscountService getDiscountService() {
                return discountService;
            }
        };

        // invoke the business logic
        Price price = bean.discountedPrice(product, account);

        // verify results
        verify(discountService);
        assertEquals(new Price(7500), price);
    }

    @Test
    public void discountBasedOnVolumeOfPastPurchases3()
            throws Exception {
        Account account = new MockAccount();
        Product product = new Product("PROD001", 10000);

        // create dependency
        DiscountService discountService = createMock(DiscountService.class);
        expect(discountService.getDiscountPercentage(account))
                .andReturn(25);
        replay(discountService);

        // create the bean under test and override the getter
        PricingServiceBean bean = new PricingServiceBean();
        injectField(bean, "discountService", discountService);

        // invoke the business logic
        Price price = bean.discountedPrice(product, account);

        // verify results
        verify(discountService);
        assertEquals(new Price(7500), price);
    }

    @Test
    public void discountBasedOnVolumeOfPastPurchases4()
            throws Exception {
        Account account = new MockAccount();
        Product product = new Product("PROD001", 10000);

        // create dependency
        DiscountService discountService = createMock(DiscountService.class);
        expect(discountService.getDiscountPercentage(account))
                .andReturn(25);
        replay(discountService);

        // create the bean under test and override the getter
        PricingServiceBean bean = new PricingServiceBean();
        bean.setDiscountService(discountService);

        // invoke the business logic
        Price price = bean.discountedPrice(product, account);

        // verify results
        verify(discountService);
        assertEquals(new Price(7500), price);
    }

    private void injectField(Object target, String fieldName,
            Object impl) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        Annotation ejb = field.getAnnotation(EJB.class);
        Annotation res = field.getAnnotation(Resource.class);
        assertTrue("Missing @EJB or @Resource annotation",
                (ejb != null || res != null));
        field.setAccessible(true);
        field.set(target, impl);
    }

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(PricingServiceBeanTest.class);
    }

}
