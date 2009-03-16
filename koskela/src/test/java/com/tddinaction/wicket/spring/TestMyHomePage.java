package com.tddinaction.wicket.spring;

import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.tddinaction.wicket.auth.MockAuthenticator;

public class TestMyHomePage {

    private WicketTester tester;

    @Before
    public void setUp() {
        AnnotApplicationContextMock context = new AnnotApplicationContextMock();
        context.putBean("authenticator", new MockAuthenticator());
        tester = new WicketTester();
    }

    @Test
    public void testHomePageHasLinkToLoginPage() throws Exception {
        tester.startPage(MyHomePage.class);

        // verify that no unexpected errors happened
        tester.assertRenderedPage(MyHomePage.class);
        tester.assertNoErrorMessage();

        // check the existence of expected components on the page
        tester.assertLabel("welcomeMessage", "Welcome!");
        tester.assertPageLink("linkToLoginPage", LoginPage.class);
    }

    @Test
    public void testInteractingWithComponents() throws Exception {
        tester.startPage(MyHomePage.class);
        tester.assertRenderedPage(MyHomePage.class);
        tester.clickLink("linkToLoginPage");
        tester.assertRenderedPage(LoginPage.class);
    }
}
