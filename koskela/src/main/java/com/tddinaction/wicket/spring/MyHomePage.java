package com.tddinaction.wicket.spring;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.PageLink;

public class MyHomePage extends WebPage {

    public MyHomePage() {
        add(new Label("welcomeMessage", "Welcome!"));
        add(new PageLink("linkToLoginPage", LoginPage.class));
    }
}
