package com.tddinaction.j2ee.web.controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EchoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {
        PrintWriter writer = response.getWriter();
        for (Enumeration e = request.getParameterNames(); e.hasMoreElements(); ) {
            String parameter = String.valueOf(e.nextElement());
            String[] values = request.getParameterValues(parameter);
            for (int i = 0; i < values.length; i++) {
                writer.write(parameter + "=" + values[i]);
                writer.write("\n");
            }
        }
        writer.close();
    }
}
