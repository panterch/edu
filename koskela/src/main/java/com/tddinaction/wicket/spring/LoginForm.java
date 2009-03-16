package com.tddinaction.wicket.spring;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.tddinaction.wicket.auth.Authenticator;

public class LoginForm extends Form {

    @SpringBean(name = "authenticator")
    private Authenticator auth;

    public void setSetterAuthenticator(Authenticator authenticator) {
        System.out.println("setter injection happened with an "
                + authenticator.getClass().getName());
    }

    public LoginForm(String name) {
        super(name, new CompoundPropertyModel(new LoginInfo()));
        InjectorHolder.getInjector().inject(this);
        add(new TextField("j_username"));
        add(new PasswordTextField("j_password"));
        System.out.println("LoginForm was created with authenticator " + auth);
    }

    @Override
    protected void onSubmit() {
        LoginInfo login = (LoginInfo) getModelObject();
        String username = login.getJ_username();
        String password = login.getJ_password();
        if (auth.authenticate(username, password)) {
            getPage().setResponsePage(MyHomePage.class);
        }
    }
}
