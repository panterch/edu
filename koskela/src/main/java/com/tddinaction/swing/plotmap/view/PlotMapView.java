package com.tddinaction.swing.plotmap.view;

import com.tddinaction.swing.plotmap.model.PlotMapModel;

public interface PlotMapView {

    void registerAdditionListener(PlotAdditionListener listener);

    void registerRemovalListener(PlotRemovalListener listener);

    void drawPlotMap(PlotMapModel model);
}
