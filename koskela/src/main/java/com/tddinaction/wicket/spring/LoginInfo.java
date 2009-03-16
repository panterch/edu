package com.tddinaction.wicket.spring;

import java.io.Serializable;

public class LoginInfo implements Serializable {

    private String username;

    private String password;

    public String getJ_username() {
        return username;
    }

    public void setJ_username(String username) {
        this.username = username;
    }

    public String getJ_password() {
        return password;
    }

    public void setJ_password(String password) {
        this.password = password;
    }
}
