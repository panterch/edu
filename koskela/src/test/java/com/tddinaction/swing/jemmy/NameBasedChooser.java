package com.tddinaction.swing.jemmy;

import java.awt.Component;

import org.netbeans.jemmy.ComponentChooser;

public class NameBasedChooser implements ComponentChooser {

    private final String name;

    public NameBasedChooser(String name) {
        this.name = name;
    }

    public boolean checkComponent(Component c) {
        return name.equals(c.getName());
    }

    public String getDescription() {
        return "Component named '" + name + "'";
    }
}
