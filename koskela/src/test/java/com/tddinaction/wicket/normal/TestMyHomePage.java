package com.tddinaction.wicket.normal;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Test;

public class TestMyHomePage {

    @Test
    public void testHomePageHasLinkToLoginPage() throws Exception {
        WicketTester tester = new WicketTester();
        tester.startPage(MyHomePage.class);

        // verify that no unexpected errors happened
        tester.assertRenderedPage(MyHomePage.class);
        tester.assertNoErrorMessage();

        // check the existence of expected components on the page
        tester.assertLabel("welcomeMessage",
                "Welcome to the home page!");
        tester.assertPageLink("linkToLoginPage", LoginPage.class);
    }

    @Test
    public void testInteractingWithComponents() throws Exception {
        WicketTester tester = new WicketTester();
        tester.startPage(MyHomePage.class);
        tester.assertRenderedPage(MyHomePage.class);
        tester.clickLink("linkToLoginPage");
        tester.assertRenderedPage(LoginPage.class);
    }
}
