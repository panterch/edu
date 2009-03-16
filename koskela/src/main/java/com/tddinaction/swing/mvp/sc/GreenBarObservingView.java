package com.tddinaction.swing.mvp.sc;

import com.tddinaction.swing.mvp.common.GreenBarModel;
import com.tddinaction.swing.mvp.common.GreenBarView;

public interface GreenBarObservingView extends GreenBarView {

    void update(GreenBarModel model);
}
