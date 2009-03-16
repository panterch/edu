package com.tddinaction.swing.mvp.common;

import java.awt.Color;

public abstract class Colors {

    public static Color getDefault() {
        return Color.GRAY;
    }

    public static Color testsPassed() {
        return Color.GREEN;
    }

    public static Color testsFailed() {
        return Color.RED;
    }
}
