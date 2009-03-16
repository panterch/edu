package com.tddinaction.j2ee.web.controller.authenticator;

public interface Authenticator {

    boolean isValidLogin(String username, String password);

}
