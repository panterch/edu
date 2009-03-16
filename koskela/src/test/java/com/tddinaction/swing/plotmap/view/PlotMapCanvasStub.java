package com.tddinaction.swing.plotmap.view;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class PlotMapCanvasStub implements PlotMapCanvas {

    public List<Point> plottedPoints = new ArrayList<Point>();

    public void plot(Point point) {
        plottedPoints.add(point);
    }

    public void clear() {
        plottedPoints.clear();
    }

    public void addRemoveListener(PointEventListener listener) {
    }
}
