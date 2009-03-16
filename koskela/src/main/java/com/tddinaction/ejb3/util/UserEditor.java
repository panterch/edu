package com.tddinaction.ejb3.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tddinaction.ejb3.entitybeans.ejb3.User;
import com.tddinaction.ejb3.entitybeans.ejb3.UserManager;

public class UserEditor extends HttpServlet {

    @Resource(type = EntityManager.class, mappedName = "java:/EntityManager")
    private EntityManager em;

    private UserManager getUserManager() {
        try {
            InitialContext ctx = new InitialContext();
            return (UserManager) ctx
                    .lookup("calculator/UserManagerBean/local");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {

        System.out.println("UserEditor servlet has entity manager: "
                + em);

        String action = request.getParameter("action");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.setAttribute("message", dispatch(action, username,
                password));

        request.setAttribute("results", new ArrayList());
        request.setAttribute("previousSQL", "");
        render(request, response);
    }

    public String create(String username, String password) {
        getUserManager().create(username, password);
        return "user " + username + " created";
    }

    public String remove(String username, String password) {
        User user = getUserManager().findByUsername(username);
        if (user == null) {
            return "no such user: " + username;
        }
        if (user.matchesPassword(password)) {
            getUserManager().remove(user);
            return "user " + username + " removed";
        } else {
            return "wrong password";
        }
    }

    public String changePassword(String username, String password) {
        User user = getUserManager().findByUsername(username);
        if (user == null) {
            return "no such user: " + username;
        }
        user.setPassword(password);
        getUserManager().update(user);
        return "password changed for " + username;
    }

    private String dispatch(String action, String username,
            String password) {
        if (action != null) {
            try {
                Method actionMethod = getClass().getMethod(action,
                        new Class[] { String.class, String.class });
                return (String) actionMethod.invoke(this,
                        new Object[] { username, password });
            } catch (NoSuchMethodException e) {
                return "No such action: " + action;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return "No action provided";
    }

    private void render(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {
        request.getRequestDispatcher("/sql.jsp").forward(request,
                response);
    }

}
