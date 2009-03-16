package com.tddinaction.swing.mvp.pv;

import java.awt.Color;

import com.tddinaction.swing.mvp.common.Colors;
import com.tddinaction.swing.mvp.common.GreenBarModel;
import com.tddinaction.swing.mvp.common.RunButtonListener;

public class GreenBarPresenter {

    private final GreenBarPassiveView view;

    private final GreenBarModel model;

    public GreenBarPresenter(final GreenBarPassiveView view,
            final GreenBarModel model) {
        this.view = view;
        this.model = model;
        view.addRunButtonListener(new RunButtonListener() {
            public void onRunButtonClick() {
                model.runTests();
                updateView();
            }
        });
        view.setBarColor(Colors.getDefault());
    }

    private void updateView() {
        Color newColor = (model.numberOfFailures() == 0 ? Colors
                .testsPassed() : Colors.testsFailed());
        view.setBarColor(newColor);
    }
}
