package com.tddinaction.wicket.normal;

import static org.junit.Assert.assertEquals;

import org.apache.wicket.protocol.http.WebApplication;
import org.junit.Test;

public class TestMyWicketApp {

    @Test
    public void testHomePage() throws Exception {
        WebApplication app = new MyWicketApp();
        assertEquals(MyHomePage.class, app.getHomePage());
    }
}
