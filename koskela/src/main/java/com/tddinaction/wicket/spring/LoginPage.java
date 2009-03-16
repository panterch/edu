package com.tddinaction.wicket.spring;

public class LoginPage extends BasePage {

    public LoginPage() {
        add(new LoginForm("login_form"));
    }
}
