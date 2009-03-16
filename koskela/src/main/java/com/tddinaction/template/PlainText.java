package com.tddinaction.template;

import java.util.Map;

public class PlainText implements Segment {

    private String text;

    public PlainText(String text) {
        this.text = text;
    }

    public boolean equals(Object other) {
        return text.equals(((PlainText) other).text);
    }

    public String evaluate(Map<String, String> variables) {
        return text;
    }
}
