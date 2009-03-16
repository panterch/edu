package com.tddinaction.swing.mvp.sc;

import com.tddinaction.swing.mvp.common.GreenBarModel;
import com.tddinaction.swing.mvp.common.GreenBarView;
import com.tddinaction.swing.mvp.common.RunButtonListener;

public class GreenBarController {

    private final GreenBarModel model;

    private final GreenBarObservingView view;

    public GreenBarController(GreenBarObservingView view,
            GreenBarModel model) {
        this.view = view;
        this.model = model;
        registerForUserGestures(view);
        updateView();
    }

    private void registerForUserGestures(GreenBarView view) {
        view.addRunButtonListener(new RunButtonListener() {
            public void onRunButtonClick() {
                model.runTests();
                updateView();
            }
        });
    }

    private void updateView() {
        view.update(model);
    }
}
