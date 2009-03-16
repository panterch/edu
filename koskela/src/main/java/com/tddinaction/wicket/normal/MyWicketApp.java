package com.tddinaction.wicket.normal;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

public class MyWicketApp extends WebApplication {

    public MyWicketApp() {
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return MyHomePage.class;
    }
}
