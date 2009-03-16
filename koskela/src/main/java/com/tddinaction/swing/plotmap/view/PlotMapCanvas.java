package com.tddinaction.swing.plotmap.view;

import java.awt.Point;

public interface PlotMapCanvas {

    void plot(Point point);

    void clear();

    void addRemoveListener(PointEventListener listener);
}
