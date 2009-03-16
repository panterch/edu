package com.tddinaction.wicket.auth;

import java.util.HashMap;
import java.util.Map;

public class MockAuthenticator implements Authenticator {

    private static class User {

        public String username;

        public String password;

        public boolean passwordExpired;

        public User(String login, String pass, boolean expired) {
            this.username = login;
            this.password = pass;
            this.passwordExpired = expired;
        }
    }

    private Map<String, User> accounts = new HashMap<String, User>();

    public void addUser(String user, String pass, boolean expired) {
        accounts.put(user, new User(user, pass, expired));
    }

    public boolean authenticate(String username, String password) {
        User user = accounts.get(username);
        return password.equals(user.password);
    }
}
