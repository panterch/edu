package com.tddinaction.j2ee.web.controller.servlet;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.tddinaction.j2ee.web.controller.authenticator.Authenticator;
import com.tddinaction.j2ee.web.controller.authenticator.MockAuthenticator;

public class TestLoginServlet {

    private static final String CORRECT_PASSWORD = "correctpassword";

    private static final String VALID_USERNAME = "validuser";

    private LoginServlet servlet;

    private MockAuthenticator authenticator;

    private MockHttpServletRequest request;

    private MockHttpServletResponse response;

    @Before
    public void setUp() {
        authenticator = new MockAuthenticator();
        authenticator.addUser(VALID_USERNAME, CORRECT_PASSWORD);

        servlet = new LoginServlet() {
            @Override
            protected Authenticator getAuthenticator() {
                return authenticator;
            }
        };

        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void wrongPasswordShouldRedirectToErrorPage()
            throws Exception {
        request.addParameter("j_username", VALID_USERNAME);
        request.addParameter("j_password", "wrongpassword");
        servlet.service(request, response);
        assertUserEndedUpOnPage("/invalidlogin");
    }

    @Test
    public void validLoginForwardsToFrontPageAndStoresUsername()
            throws Exception {
        request.addParameter("j_username", VALID_USERNAME);
        request.addParameter("j_password", CORRECT_PASSWORD);
        servlet.service(request, response);
        assertUserEndedUpOnPage("/frontpage");
        assertEquals(VALID_USERNAME, request.getSession()
                .getAttribute("username"));
    }

    private void assertUserEndedUpOnPage(String expected) {
        String actual = response.getRedirectedUrl();
        if (actual == null) {
            actual = response.getForwardedUrl();
        }
        assertEquals(expected, actual);
    }
}
