package com.tddinaction.j2ee.web.controller.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tddinaction.j2ee.web.controller.authenticator.Authenticator;

public class LoginServlet extends HttpServlet {

    // injected property
    protected Authenticator getAuthenticator() {
        return null;
    }

    @Override
    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {
        String user = request.getParameter("j_username");
        String pass = request.getParameter("j_password");
        if (getAuthenticator().isValidLogin(user, pass)) {
            request.getSession().setAttribute("username", user);
            response.sendRedirect("/frontpage");
        } else {
            forwardTo("/invalidlogin", request, response);
        }
    }

    private void forwardTo(String path, HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {
        RequestDispatcher dispatcher = request
                .getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }
}
