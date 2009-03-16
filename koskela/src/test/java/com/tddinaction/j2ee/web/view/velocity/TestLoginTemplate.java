package com.tddinaction.j2ee.web.view.velocity;

import org.junit.Test;

public class TestLoginTemplate extends VelocityTestCase {

    @Override
    protected String getWebRoot() {
        return "./src/test/resources/websrc/velocity";
    }

    @Test
    public void formFieldsArePresent() throws Exception {
        render("/login.vtl");
        assertFormFieldPresent("j_username");
        assertFormFieldPresent("j_password");
        assertFormSubmitButtonPresent("login");
    }

    @Test
    public void previousUsernameIsRetained() throws Exception {
        String previousUsername = "Bob";
        setAttribute("j_username", previousUsername);
        render("/login.vtl");
        assertFormFieldValue("j_username", previousUsername);
    }

    @Test
    public void errorsAreRenderedForTheUser() throws Exception {
        setAttribute("error", "Invalid password");
        render("/login.vtl");
        assertTextPresent("Invalid password");
    }
}
