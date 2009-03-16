package com.tddinaction.ejb3.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class SqlEditor extends HttpServlet {

    private DataSource datasource;

    @Override
    public void init(ServletConfig config) throws ServletException {
        lookupDataSource();
    }

    private void lookupDataSource() {
        try {
            if (datasource == null) {
                InitialContext ctx = new InitialContext();
                datasource = (DataSource) ctx
                        .lookup("java:/DefaultDS");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {
        request.setAttribute("results", new ArrayList());
        render(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {
        String sql = request.getParameter("sql");
        request.getSession(true).setAttribute("previousSQL", sql);
        request.setAttribute("results", executeQuery(sql));
        render(request, response);
    }

    private List executeQuery(String sql) {
        List results = new ArrayList();
        Connection c = null;
        try {
            c = datasource.getConnection();
            Statement s = c.createStatement();
            boolean success = s.execute(sql);
            if (success) {
                ResultSet rs = s.getResultSet();
                results = toListOfLists(rs);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            close(c);
        }
        return results;
    }

    private void close(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception ignored) {
        }
    }

    private List toListOfLists(ResultSet rs) throws Exception {
        ResultSetMetaData metaData = rs.getMetaData();
        List<List<String>> results = new ArrayList<List<String>>();

        List<String> headers = new ArrayList<String>();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            headers.add(metaData.getColumnLabel(i));
        }
        results.add(headers);

        while (rs.next()) {
            List<String> row = new ArrayList<String>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                row.add(rs.getString(i));
            }
            results.add(row);
        }
        return results;
    }

    private void render(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {
        if (request.getSession(true).getAttribute("previousSQL") == null) {
            request.getSession(true).setAttribute("previousSQL", "");
        }
        request.getRequestDispatcher("/sql.jsp").forward(request,
                response);
    }

}
