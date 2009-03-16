/**
 * 
 */
package com.tddinaction.swing.mvp.sc;

import java.awt.Color;

import com.tddinaction.swing.mvp.common.Colors;
import com.tddinaction.swing.mvp.common.GreenBarModel;
import com.tddinaction.swing.mvp.common.RunButtonListener;

public class GreenBarObservingViewStub implements
        GreenBarObservingView {

    RunButtonListener registeredRunButtonListener;

    Color barColor = Colors.getDefault();

    public void addRunButtonListener(RunButtonListener listener) {
        registeredRunButtonListener = listener;
    }

    public Color getBarColor() {
        return barColor;
    }

    public void update(GreenBarModel model) {
        if (model.numberOfTests() == 0) {
            barColor = Colors.getDefault();
        } else {
            barColor = model.numberOfFailures() == 0 ? Colors
                    .testsPassed() : Colors.testsFailed();
        }
    }
}