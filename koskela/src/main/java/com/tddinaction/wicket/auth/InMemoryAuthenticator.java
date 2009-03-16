package com.tddinaction.wicket.auth;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAuthenticator implements Authenticator {

    private Map<String, String> validLogins = new HashMap<String, String>();

    public boolean authenticate(String username, String password) {
        return validLogins.containsKey(username)
                && validLogins.get(username).equals(password);
    }

    public void setValidUsers(Map<String, String> users) {
        validLogins.putAll(users);
    }
}
