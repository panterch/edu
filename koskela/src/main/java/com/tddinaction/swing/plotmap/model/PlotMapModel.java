package com.tddinaction.swing.plotmap.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlotMapModel {

    private List<Point> plots = new ArrayList<Point>();

    public void add(Point plot) {
        plots.add(plot);
    }

    public void remove(Point plot) {
        plots.remove(plot);
    }

    public List<Point> points() {
        return Collections.unmodifiableList(plots);
    }
}
