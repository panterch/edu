package com.tddinaction.j2ee.web.controller.spring;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

import com.tddinaction.j2ee.web.controller.authenticator.MockAuthenticator;

public class TestLoginController {

    private static final String CORRECT_PASSWORD = "correctpassword";

    private static final String VALID_USERNAME = "validuser";

    private MockHttpServletRequest request;

    private MockHttpServletResponse response;

    private LoginController controller;

    @Before
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        controller = new LoginController();

        MockAuthenticator auth = new MockAuthenticator();
        auth.addUser(VALID_USERNAME, CORRECT_PASSWORD);
        controller.setAuthenticator(auth);
    }

    @Test
    public void wrongPasswordShouldRedirectToErrorPage()
            throws Exception {
        request.addParameter("j_username", "nosuchusername");
        request.addParameter("j_password", "nosuchpassword");
        ModelAndView v = controller.handleRequest(request, response);

        assertEquals("wrongpassword", v.getViewName());
    }

    @Test
    public void validLoginForwardsToFrontPageAndStoresUsername()
            throws Exception {
        request.addParameter("j_username", VALID_USERNAME);
        request.addParameter("j_password", CORRECT_PASSWORD);
        ModelAndView v = controller.handleRequest(request, response);

        assertEquals("frontpage", v.getViewName());
    }
}
