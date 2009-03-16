package com.tddinaction.wicket.spring;

import org.apache.wicket.injection.web.InjectorHolder;
import org.apache.wicket.markup.html.WebPage;

public abstract class BasePage extends WebPage {

    public BasePage() {
        InjectorHolder.getInjector().inject(this);
    }
}
