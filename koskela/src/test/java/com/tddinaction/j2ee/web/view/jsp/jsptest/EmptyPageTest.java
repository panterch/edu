package com.tddinaction.j2ee.web.view.jsp.jsptest;

import java.io.FileReader;

import net.sf.jsptest.JspTestCase;

import com.tddinaction.io.IO;

public class EmptyPageTest extends JspTestCase {

    @Override
    protected String getWebRoot() {
        return "./src/test/resources/websrc/jsp";
    }

    public void testEmptyJspCanBeCompiled() throws Exception {
        get("/empty.jsp");
        String output = IO.readIntoString(new FileReader(
                getRenderedResponse()));
        assertEquals("", output);
    }
}
