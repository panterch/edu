package com.tddinaction.wicket.normal;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

public class LoginForm extends Form {

    public LoginForm(String name) {
        super(name, new CompoundPropertyModel(new LoginInfo()));
        add(new TextField("j_username"));
        add(new PasswordTextField("j_password"));
    }

    @Override
    protected void onSubmit() {
        LoginInfo login = (LoginInfo) getModelObject();
        // do something with LoginInfo...
        getPage().setResponsePage(MyHomePage.class);
    }
}
