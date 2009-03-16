package com.tddinaction.template;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.tddinaction.template.Template;

public class TestTemplatePerformance {

    private Template template;

    @Before
    public void setUp() throws Exception {
        buildTemplate();
        populateTemplate();
    }

    private void populateTemplate() {
        for (int var = 1; var < 100; var++) {
            template.set("var" + var, "value of var" + var);
        }
    }

    private void buildTemplate() {
        StringBuffer text = new StringBuffer(50000);
        for (int i = 0, var = 1; i < 1000; i++, var++) {
            text.append(" template ");
            if (i % 1000 / 50 == 0) {
                text.append("${var").append(var).append("}");
            }
        }
        template = new Template(text.toString());
    }

    @Test
    public void templateWith1000WordsAnd20Variables() {
        long expected = 500L;
        long time = System.currentTimeMillis();
        template.evaluate();
        time = System.currentTimeMillis() - time;
        assertTrue("Rendering the template took " + time
                + "ms while the target was " + expected + "ms",
                time <= expected);
    }

    @Test
    public void testRenderingThousandWordTemplate() {
        long time = System.currentTimeMillis();
        template.evaluate();
        time = System.currentTimeMillis() - time;
        assertTrue("Rendering a 1000-word template took " + time
                + "ms while the acceptable limit was 100ms",
                time <= 100);
    }

}
