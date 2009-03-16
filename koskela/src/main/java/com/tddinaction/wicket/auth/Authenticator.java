package com.tddinaction.wicket.auth;

public interface Authenticator {

    boolean authenticate(String username, String password);
}
