package com.tddinaction.template;

import java.util.Map;

public class Variable implements Segment {

    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public boolean equals(Object other) {
        return name.equals(((Variable) other).name);
    }

    public String evaluate(Map<String, String> variables) {
        if (!variables.containsKey(name)) {
            throw new MissingValueException("No value for ${" + name
                    + "}");
        }
        return variables.get(name);
    }

}
