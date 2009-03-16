package com.tddinaction.wicket.normal;

import org.apache.wicket.markup.html.WebPage;

public class LoginPage extends WebPage {

    public LoginPage() {
        add(new LoginForm("login_form"));
    }
}
