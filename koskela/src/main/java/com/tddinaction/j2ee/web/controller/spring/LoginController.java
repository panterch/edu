package com.tddinaction.j2ee.web.controller.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.tddinaction.j2ee.web.controller.authenticator.Authenticator;

public class LoginController implements Controller {

    private Authenticator authenticator;

    public void setAuthenticator(Authenticator authenticator) {
        this.authenticator = authenticator;
    }

    public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String user = request.getParameter("j_username");
        String pass = request.getParameter("j_password");
        if (authenticator.isValidLogin(user, pass)) {
            return new ModelAndView("frontpage");
        }
        return new ModelAndView("wrongpassword");
    }
}
