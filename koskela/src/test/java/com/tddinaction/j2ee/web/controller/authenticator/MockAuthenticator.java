package com.tddinaction.j2ee.web.controller.authenticator;

import java.util.HashMap;
import java.util.Map;

public class MockAuthenticator implements Authenticator {

    private Map<String, String> users = new HashMap<String, String>();

    public void addUser(String user, String pass) {
        users.put(user, pass);
    }

    public boolean isValidLogin(String user, String pass) {
        return users.containsKey(user)
                && pass.equals(users.get(user));
    }
}
