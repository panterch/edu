/**
 * 
 */
package com.tddinaction.swing.mvp.pv;

import java.awt.Color;

import com.tddinaction.swing.mvp.common.RunButtonListener;

public class GreenBarViewStub implements GreenBarPassiveView {

    public RunButtonListener registeredRunButtonListener;

    public Color barColor;

    public void setBarColor(Color color) {
        this.barColor = color;
    }

    public void addRunButtonListener(RunButtonListener listener) {
        registeredRunButtonListener = listener;
    }
}